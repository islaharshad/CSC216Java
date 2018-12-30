package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * This class represents a student with their respective states
 * @author Samuel Ryne Ritter, Wil Elias
 *
 */
public class Student extends User implements Comparable<Student> {

	/** The student's maximum credits */
	private int maxCredits;
	/** The default credits for students */
	public static final int MAX_CREDITS = 18;
	private Schedule schedule;
	
	/**
	 * Creates a student with a first and last name, id, email, hash code password, and max credits
	 * @param firstName Student's first name
	 * @param lastName Student's last name
	 * @param id Student's id
	 * @param email Student's email
	 * @param hashPW Student's hash code password
	 * @param maxCredits Student's max credits
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		this.setMaxCredits(maxCredits);
		schedule = new Schedule();
	}
	/**
	 * Creates a student with a first and last name, id, email, and hash code password
	 * @param firstName Student's first name
	 * @param lastName Student's last name
	 * @param id Student's id
	 * @param email Student's email
	 * @param hashPW Student's hash code password
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, 18);
		
	}
	
	/**Returns the student's maximum credits
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the student's maximum credits
	 * @param maxCredits the maxCredits to set
	 */
	public void setMaxCredits(int maxCredits) {
		if(maxCredits < 3 || maxCredits > 18){
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * Checks whether a course can be 
	 * added to a student's schedule
	 * @return boolean whether a course can be added or not
	 */
    public boolean canAdd(Course course) {
 		
        if (schedule.canAdd(course)) {
        	return true;
        }
        
        return false;
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
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
	/**
	 * Returns the student represented as a string
	 * @return student represented as a string
	 */
	@Override
	public String toString() {
		String s = (getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + 
				getPassword() + "," + getMaxCredits());
		return s;
	}
	/**
	 * This compares students so the can be sorted alphabetically
	 * @return 0 if equal, 1 if greater than, -1 if less than
	 */
	@Override
	public int compareTo(Student s) {
		if(this.getLastName().compareTo(s.getLastName()) < 0){
			return -1;
		} else if(this.getLastName().compareTo(s.getLastName()) > 0){
			return 1;
		} else {
			if(this.getFirstName().compareTo(s.getFirstName()) < 0){
				return -1;
			} else if(this.getFirstName().compareTo(s.getFirstName()) > 0){
				return 1;
			} else {
				if(this.getId().compareTo(s.getId()) < 0){
					return -1;
				} else if(this.getId().compareTo(s.getId()) > 0){
					return 1;
				} else {
					return 0;
				}
			}
		}
	}
	
	/** Returns schedule of a student 
	 * @return schedule course schedule 
	 */
	public Schedule getSchedule()
	{
		
		return schedule;
	}

}
