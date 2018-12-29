/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * Creates a Course Roll that holds the students that have been enrolledd
 *  in a particular course.
 * 
 * @author Addison Garrigus
 */
public class CourseRoll {
	/** The linked list of students enrolled in the course. */
	private LinkedAbstractList<Student> roll;
	/** The roll's enrollment capacity. */
	private int enrollmentCap;
	/** The number of students on the wait list */
	private LinkedQueue<Student> waitlist;
	/** The course this roll is for */
	private Course course;
	/** The smallest class size. */
	private static final int MIN_ENROLLMENT = 10;
	/** The largest class size. */
	private static final int MAX_ENROLLMENT = 250;
	/** The size of the wait list */
	private static final int WAITLIST_SIZE = 10;
	
	

	/** 
	 * Creates a new Course Roll that will hold the students that are enrolled
	 *  in a course.
	 * 
	 * @param enrollmentCap The number of students that a course can hold
	 * @param c The course this roll is for
	 */
	public CourseRoll(Course c, int enrollmentCap) {
		this.enrollmentCap = enrollmentCap;
		if(c == null){
			throw new IllegalArgumentException();
		}
		this.course = c;
		waitlist = new LinkedQueue<Student>(WAITLIST_SIZE);
		roll = new LinkedAbstractList<Student>(enrollmentCap);
	}
	
	/**
	 * Gets the number of seats that are not currently filled in the course.
	 * 
	 * @return The number of seats that are not currently filled in the course
	 */
	public int getOpenSeats() {
		return (enrollmentCap - roll.size());		
	}
	
	/**
	 * Sets the capacity of students for the course.
	 * 
	 * @param enrollmentCap The number of students that a course can hold
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		roll.setCapacity(enrollmentCap); 
		this.enrollmentCap = enrollmentCap;
	}
	 
	/** Returns the enrollmentCap for this course
	 * @return this.enrollmentCap 
	 */
	public int getEnrollmentCap() {
		return this.enrollmentCap;
	}
	
	/**
	 * Enrolls the given student in the course if there is room.
	 * And if not puts the on the wait list if there is enough room.
	 * 
	 * @param s The student that is being enrolled in the course.
	 */
	public void enroll(Student s) {
		if (s == null || roll.contains(s)) {
			throw new IllegalArgumentException();
		}
		
		if(canEnroll(s)){ 
			roll.add(s);
			s.getSchedule().addCourseToSchedule(course);
		} else { 
			boolean onWaitlist = false;
			if(waitlist.size() > 0){
				int size = waitlist.size();
				for(int i = 0; i < size; i++) {
					Student sbackup = waitlist.dequeue();
					if(s.equals(sbackup)) {
						onWaitlist = true;
					}
					waitlist.enqueue(sbackup);
				}
			}
			if(!onWaitlist){
				waitlist.enqueue(s);
			} 
		}
	}
	
	/**
	 * Removes the given student from the course roll.
	 * 
	 * @param s The student that is being dropped from the course roll
	 */
	public void drop(Student s) {
	
		///if remove student
		/////is waitlist is not empty
		///Student add = waitlist.deq() ---remove the studet
		///enroll(add) ==== add the student
		///add.getsched.addCourseTosche(course) add                 ////////////////This was a teacher solution code that a ta gave me which I jot down and built
		
		
		///else
		///size is waitlist.size
		//for i: that size
		//student temp = waitlis.deq()
		///if temp.equals(s above)
		///waitlist.en(temp)
		
		
		if(s == null) {
			throw new IllegalArgumentException();
		}
		
		int index = -1;
		
		if(roll.contains(s)) {
			for(int i = 0; i < roll.size(); i++) {
				if(s.equals(roll.get(i))) {
					index = i;
				}
			}
		}
		
		if(index == -1 && waitlist.size() > 0){
			int waitlistSize = waitlist.size();
			for(int i = 0; i < waitlistSize; i++) {
				Student sbackup = waitlist.dequeue();
				if(!s.equals(sbackup)) {
					waitlist.enqueue(sbackup);
				}
			}
		}
		
		
		if(index >= 0) {
			roll.remove(index);
			s.getSchedule().removeCourseFromSchedule(course);
			
			
			if(waitlist.size() > 0){
				//roll.add(waitlist.dequeue());
				Student s1 = waitlist.dequeue();
				enroll(s1);
			}
		}
		 
    }
	
	/**
	 * Determines if the given student can be enrolled in the course or not.
	 * 
	 * @param s The student that is trying to enroll in the course
	 * @return true if the student is allowed to be enrolled in the course
	 */
	public boolean canEnroll(Student s) {
		if (getOpenSeats() == 0 || roll.contains(s)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns the number of students on the wait list
	 * @return the number of students on the wait list
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
}
 