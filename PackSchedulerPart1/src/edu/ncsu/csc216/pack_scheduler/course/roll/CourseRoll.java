package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
/**
 * The courseroll class enables to see
 * whether students can be enrolled or not
 * @author Islahuddin Arshad, Eduardo Bravo
 *
 */
public class CourseRoll {
	
	/** Link list of students called roll*/
	private LinkedAbstractList<Student> roll;
	/** The enrollment cap of the students*/
	private int enrollmentCap;
	/** Minimum enrollment of any student*/
	private static final int MIN_ENROLLMENT = 10;
	/** Maximum enrollment of any student*/
	private static final int MAX_ENROLLMENT = 250;
	
	
	
	public CourseRoll(int capacity) {
		roll = new LinkedAbstractList<Student>(MAX_ENROLLMENT);
		setEnrollmentCap(capacity);
	}
	
	
	public int getEnrollmentCap() {
		
		return enrollmentCap;
	}
	
	public void setEnrollmentCap(int cap){
		if ((cap < MIN_ENROLLMENT) || cap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		
		if (cap < roll.size()) {
			throw new IllegalArgumentException();
		}
		enrollmentCap = cap;
		
		
	}
	
	public void enroll(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		if (getOpenSeats() == 0) {
			throw new IllegalArgumentException();
		}
		if (canEnroll(s) == false) {
			throw new IllegalArgumentException();
		}
		
		try {
			roll.add(s);
		}
		catch(Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	public void drop(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		try {
			roll.remove(roll.indexAt(s));
		}
		catch(Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	public int getOpenSeats() {
		return getEnrollmentCap() - roll.size();
	}
	
	public boolean canEnroll(Student s) {
		if (getOpenSeats() == 0) {
			return false;
		}
		if (roll.elementIsContained(s)) {
			return false;
		}
		
		return true;
	}
	
	

}









