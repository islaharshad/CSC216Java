package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Class to represent a student
 * @author Gianni Absillis (gabsill)
 *
 */
public class Student extends User implements Comparable<Student> {
	/** maximum number of credits a student can take **/
	public static final int MAX_CREDITS = 18;
	/** minimum number of credits a student can take **/
	public static final int MIN_CREDITS = 3;
	/** maximum amount of credits */
	private int maxCredits;
	/** the student's schedule */
	private Schedule schedule;

	/**
	 * Constructor 
	 * 
	 * @param firstName Student First Name
	 * @param lastName Student Last Name
	 * @param id Student ID
	 * @param email Student email
	 * @param hashPW Student password (should be hashed SHA-256)
	 * @param maxCredits Max Credits Student can take
	 * @throws IllegalArgumentException if any of the fields fail to set
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) throws IllegalArgumentException {
		super(firstName, lastName, id, email, hashPW);
		setMaxCredits(maxCredits);
		schedule = new Schedule();
	}
	
	/**
	 * Alternate Constructor without maxCredits (defaults to 18)
	 * @param firstName Student First Name
	 * @param lastName Student Last Name
	 * @param id Student ID
	 * @param email Student email
	 * @param hashPW Student password (should be hashed SHA-256)
	 * @throws IllegalArgumentException if any of the fields fail to set
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) throws IllegalArgumentException{
		this(firstName, lastName, id, email, hashPW, MAX_CREDITS);
	}

	/**
	 * getter for maxCredits
	 * @return the maximum number of credits the student can register for
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * setter for maxCredits
	 * @param maxCredits the max credits a student can register for
	 * @throws IllegalArgumentException if the credits are not between 3 and 18
	 */
	public void setMaxCredits(int maxCredits) throws IllegalArgumentException{
		if(maxCredits < MIN_CREDITS || maxCredits > MAX_CREDITS){
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * returns all the fields comma separated
	 */
	@Override
	public String toString() {
		return getFirstName() + "," +
				getLastName() + "," +
				getId() + "," +
				getEmail() + "," +
				getPassword() + "," +
				getMaxCredits();
				
	}

	/**
	 * Compares Student objects
	 * @return 0 if objects are equal
	 */
	@Override
	public int compareTo(Student s) {
		
		if(s == null){
			throw new IllegalArgumentException();
		}
		
		if(this.getLastName().compareTo(s.getLastName()) < 0){
			return -1;
		} else if(this.getLastName().compareTo(s.getLastName()) > 0) {
			return 1;
		} else if(this.getFirstName().compareTo(s.getFirstName()) < 0){
			return -1;
		} else if(this.getFirstName().compareTo(s.getFirstName()) > 0) {
			return 1;
		} else if(this.getId().compareTo(s.getId()) < 0){
			return -1;
		}  else if(this.getId().compareTo(s.getId()) > 0){
			return 1;
		}
		
		return 0;
	}

	/** 
	 * Generates hashcode for Student class
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/** 
	 * Checks if Student is equal to given object
	 * 
	 * @param obj object to compared to Student
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}
	
	/** Returns the schedule object
	 * @return schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}
	
	/**
	 * Checks if a course can be added to a student's schedule. Returns true if the course can be added,
	 * false if the course is null, the course is already in the student's schedule, there are course conflicts,
	 * or if the course will exceed the student's max credits.
	 * @param c course to be checked.
	 * @return true if course can be added.
	 */
	public boolean canAdd(Course c) {
		
		if(!schedule.canAdd(c)) {
			return false;
		}
		
		if(schedule.getScheduleCredits() + c.getCredits() > maxCredits) {
			return false;
		}
		
		return true;
	}
	

	
}
