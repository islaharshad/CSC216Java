package edu.ncsu.csc216.pack_scheduler.manager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * RegistrationManager manages the registration of Students in Courses
 * @author Addison Garrigus
 */
public class RegistrationManager {
	
	/** Instance of RegistrationManager. There can only be one instance of RegistrationManager */
	private static RegistrationManager instance;
	/** Course Catalog */
	private CourseCatalog courseCatalog;
	/** Student Directory */
	private StudentDirectory studentDirectory;
	
	/** Registrar User */
	private User registrar;
	/** Current User */
	private User currentUser;
	/**The directory of faculty*/
	private FacultyDirectory facultyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Registrar password */
	private static final String PW = "Regi5tr@r";
	/** String for hashed passwords */
	private static String hashPW;
	
	//Static code block for hashing the registrar user's password
	{
		try {
			  MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			  digest1.update(PW.getBytes());
			 hashPW = new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	/**
	 * Constructs an instance of RegistrationManager
	 */
	private RegistrationManager() {
		courseCatalog = new CourseCatalog();
		//courseCatalog.loadCoursesFromFile("test-files/course_records.txt");
		studentDirectory = new StudentDirectory();
		//studentDirectory.loadStudentsFromFile("test-files/student_records.txt");
		facultyDirectory = new FacultyDirectory();
		registrar = new Registrar();
		
	}
	
	
	/**
	 * Returns the faculty directory
	 * @return the faculty directory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}
	
	/**
	 * Returns an instance of RegistrationManager, ensuring there is only ever one instance of RegistrationManager.
	 * @return an instance of RegistrationManager
	 */
	public static RegistrationManager getInstance() {
		  if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	/**
	 * Returns the Course Catalog.
	 * @return Course Catalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	/**
	 * Returns the Student Directory
	 * @return Student Directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Logs in a User to the PackScheduler program.
	 * @param id User id
	 * @param password User password
	 * @return true if User is logged in, false is User could not be logged in
	 */
	public boolean login(String id, String password) {
		
		if (registrar.getId().equals(id)) {
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (registrar.getPassword().equals(localHashPW) && (currentUser == null || currentUser == registrar)) {
				currentUser = registrar;
					return true;
				}
				else
					return false;
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
			
		} else if (!registrar.getId().equals(id)){
			Student s = studentDirectory.getStudentById(id);
			Faculty f = facultyDirectory.getFacultyById(id);
			
			if (s == null && f == null) {
				throw new IllegalArgumentException("User doesn't exist.");
			}
			if (s != null) {
			try {
				MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				
				if (s.getPassword().equals(localHashPW) && (currentUser == registrar || currentUser == null)) {
					currentUser = s;
					return true;
			}
		} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
		  }	
			}
			if (f != null) {
 				try { 
					MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
					digest.update(password.getBytes());
					String localHashPW = new String(digest.digest());
					if (f.getPassword().equals(localHashPW) && (currentUser == registrar || currentUser == null)) {
						currentUser = f;
						return true;
				}
			} catch (NoSuchAlgorithmException e) {
					throw new IllegalArgumentException();
			  }	
			
		}
		}
			
		return false;
	}

	/**
	 * Logs out a User from the PackScheduler program.
	 */
	public void logout() {
		currentUser = null; 
	}
	
	/**
	 * Returns the current user
	 * @return the current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * Clears the Course Catalog and Student Directory
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
	}
	
	
	 /**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    
	    try {
	        Student s = (Student)currentUser;
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	    	return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    
	    Student s = (Student)currentUser;
	    int seats = c.getCourseRoll().getOpenSeats();
	    int wait = c.getCourseRoll().getNumberOnWaitlist();
	    c.getCourseRoll().drop(s);
	    return (seats < c.getCourseRoll().getOpenSeats() || wait > c.getCourseRoll().getNumberOnWaitlist());
	}
	
	/**
	 * Adds the given course to the given Faculty's Faculty Schedule. Returns true if the course is successfully added,
	 * false otherwise.
	 * @param course The course to be added
	 * @param faculty The Faculty whose Faculty Schedule will be added to
	 * @return true if course is added to Faculty's FacultyShecdule, false if the course is not added
	 */
	public boolean addFacultyToCourse(Course course, Faculty faculty) {
		if (currentUser != registrar)  {
			throw new IllegalArgumentException();
		}
		if(currentUser != null && currentUser == registrar ) {
			faculty.getSchedule().addCourseToSchedule(course);
			return true;
		}
		
		 
		
		return false;
	}  
	
	/**
	 * Removes the given course from the given Faculty's Faculty Schedule. Returns true if the course is successfully removed,
	 * false otherwise.
	 * @param course The course to be removed
	 * @param faculty The Faculty whose Faculty Schedule will be removed from
	 * @return true if course is removed from Faculty's FacultyShecdule, false if the course is not removed
	 */
	public boolean removeFacultyFromCourse(Course course, Faculty faculty) {
		if(currentUser == null || currentUser != registrar ) {
			throw new IllegalArgumentException();
		} else {
			faculty.getSchedule().removeCourseFromSchedule(course);
			return true;
		}
	}
	
	/**
	 * Resets the given Faculty's faculty schedule.
	 * @param faculty The Faculty whose schedule will be reset
	 */
	public void resetFacultySchedule(Faculty faculty) {
		
		if (currentUser != registrar)  {
			throw new IllegalArgumentException();
		}
		
		if(currentUser != null && currentUser == registrar ) {
			faculty.getSchedule().resetSchedule();
		}
	}
	
	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}
	
	/**
	 * Creates a Registrar that can interact with the RegistrationManager class
	 * 
	 * @author Jonathan Aloba
	 *
	 */
	private static class Registrar extends User {
		
		  private static final String FIRST_NAME = "Wolf";
		  private static final String LAST_NAME = "Scheduler";
		 private static final String ID = "registrar";
		 	private static final String EMAIL = "registrar@ncsu.edu";
		
		/**
		 * Create a registrar user with the user id of registrar and
		 * password of Regi5tr@r.  Note that hard coding passwords in a 
		 * project is HORRIBLY INSECURE, but it simplifies testing here.
		 * This should NEVER be done in practice!
		 */
		public Registrar() {
			super(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPW);
		}
	}
}
