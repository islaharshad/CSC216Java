package edu.ncsu.csc216.pack_scheduler.manager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * This class has a singleton pattern of its own instance. It's the only manager that
 * manages all the registration functionality
 * @author Premsubedi, Isla, Edwerdo
 */
public class RegistrationManager {
	
	private static RegistrationManager instance;
	private CourseCatalog courseCatalog;
	private StudentDirectory studentDirectory;
	private User registrar;
    private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	private static final String PW = "Regi5tr@r";
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
	
	private RegistrationManager() {
		this.courseCatalog = new CourseCatalog();
		this.studentDirectory = new StudentDirectory();
		this.registrar = new Registrar();
	}
	
	/**
	 * Returns an instance object of registration manager.
	 * @return instance an instance of RegistrationManager
	 */
	public static RegistrationManager getInstance() {
		  if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	/**
	 * It returns courseCatalog of all the courses in the University.
	 * @return courseCatalog courseCatalog of all the courses in the system.
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
		
	}
	
	/**
	 * Returns directory of all the students
	 * @return studentDirectory a directory of all the students.
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * This method returns true if the password of the currentUser matches with
	 * the local hash password, returns false otherwise.
	 * @param id user's Unity id.
	 * @param password user's password.
	 * @return false if the password doesn't match.
	 */
	public boolean login(String id, String password) {
		if(currentUser != null) {
			return false;
		}
		
		if (registrar.getId().equals(id)) {
				MessageDigest digest;
			try {
			digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
			if (registrar.getPassword().equals(localHashPW)) {
				currentUser = registrar;
					return true;
			}
			} catch (NoSuchAlgorithmException e) {
	              throw new IllegalArgumentException();
			}
		}
		
		Student s = studentDirectory.getStudentById(id);
		if (s == null) {
			throw new IllegalArgumentException("User doesn't exist.");
		}
		try {
		MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
		digest.update(password.getBytes());
		String localHashPW = new String(digest.digest());
		if (s.getPassword().equals(localHashPW)) {
			currentUser = s;
				return true;
		}
		} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
		}	
		
		
	    return false;
	}

	/**
	 * This method performs logout functionality of the registrationManager.
	 */
	public void logout() {
		currentUser = null; 
	}
	
	/**
	 * Returns currentUser who is logged in at that instant.
	 * @return currentUser the user (either student or Registrar).
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * This method performs clearing data from the courseCatalog and studentDirectory.
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
	}
	
	/**
	 * Registrar is an inner class and also a child class of an user
	 * @author Islahuddin, Edwardo, premsubedi
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
        Schedule schedule = s.getSchedule();
        CourseRoll roll = c.getCourseRoll();
        
        if (s.canAdd(c) && roll.canEnroll(s)) {
            schedule.addCourseToSchedule(c);
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
    try {
        Student s = (Student)currentUser;
        c.getCourseRoll().drop(s);
        return s.getSchedule().removeCourseFromSchedule(c);
    } catch (IllegalArgumentException e) {
        return false; 
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
	
}