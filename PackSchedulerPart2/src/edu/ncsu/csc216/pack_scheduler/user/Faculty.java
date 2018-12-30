package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * This class creates an object that represents a faculty member.
 * Each member has a first and last name, id,  email, password, 
 * and maximum courses that can be taught
 * @author Samuel Ryne Ritter
 */
public class Faculty extends User {

	/** The maximum number of courses a faculty member can teach */
	private int maxCourses;
	/** The minimum number of courses a faculty member can teach */
	public static final int MIN_COURSES = 1;
	/** The maximum number of courses a faculty member can teach */
	public static final int MAX_COURSES = 3;
	/**Faculty Schedule State*/
	private FacultySchedule facschedule;
	
	/**
	 * The constructor that creates a faculty member with a first and last name, id, 
	 * email, password, and maximum courses
	 * @param fname The first name of the faculty member
	 * @param lname The last name of the faculty member
	 * @param id The id of the faculty member
	 * @param email The email of the faculty member
	 * @param pw The password of the faculty member
	 * @param maxCourses The max number of courses the faculty member can teach
	 */
	public Faculty(String fname, String lname, String id, String email, String pw, int maxCourses) {
		super(fname, lname, id, email, pw);
		
		facschedule = new FacultySchedule(id); //added
		
		if(maxCourses < MIN_COURSES || maxCourses > MAX_COURSES){
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = maxCourses;
	}
	
	
	/**
	 * Gets the schedule of faculty
	 * @return FacultySchedule the faculty schedule
	 */
	public FacultySchedule getSchedule() { //added
		return facschedule;
	}
	
	/**
	 * Return true if scheduled courses are greater than max courses
	 * @return boolean true if courses are overloaded
	 */
	public boolean isOverloaded() {  //added
		return facschedule.getNumScheduledCourses() > maxCourses;
		
	}
	
	/**
	 * Sets the maximum number of courses that a faculty member can teach
	 * @param maxCourses the maximum number of courses that a faculty member can teach
	 */
	public void setMaxCourses(int maxCourses) {
		if(maxCourses < MIN_COURSES || maxCourses > MAX_COURSES){
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = maxCourses;
	}
	
	/**
	 * Returns the maximum number of courses that a faculty member can teach
	 * @return The maximum number of courses that a faculty member can teach
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/** 
	 * Generates the hashcode for the Faculty class
	 * @return The hashcode for the Faculty class
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/** 
	 * Returns whether or not the passed object is equal to the current faculty
	 * @return True if they are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}
	
	/**
	 * Returns all the fields comma separated
	 * @return All the fields comma separated
	 */
	@Override
	public String toString() {
		return getFirstName() + "," +
				getLastName() + "," +
				getId() + "," +
				getEmail() + "," +
				getPassword() + "," +
				getMaxCourses();
				
	}
	
	

}
