/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;


import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * The schedule class is used to create a schedule for a student user to add
 *  courses to.
 * 
 * @author Addison Garrigus
 */
public class Schedule {
	
	/**	An array of courses that holds the student's schedule */
	public ArrayList<Course> schedule;
	/** The title of the student's schedule */
	public String title;
	
	/**
	 * Constructs a student's schedule with the courses and events that they
	 *  have selected.
	 */
	public Schedule() {
		schedule = new ArrayList<Course>();
		setTitle("My Schedule");
	}
	
	/**
	 * Adds the given course to the schedule.
	 * 
	 * @param course The course that is being added to the schedule
	 * @return True if the course adds correctly
	 * @throws IllegalArgumentException if the course that is being added is invalid
	 */
	public boolean addCourseToSchedule(Course course) {
		for (int i = 0; i < schedule.size(); i++) {
			if (course.isDuplicate(schedule.get(i))) {
				throw new IllegalArgumentException("You are already enrolled in " + course.getName());
			}
			
			try {
				course.checkConflict((Activity) schedule.get(i));
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		schedule.add(course);
		return true;
	}
	
	/**
	 * Removes the selected course from the schedule.
	 * 
	 * @param course The course that is going to be removed from the schedule
	 * @return True if the course is removed correctly
	 */
	public boolean removeCourseFromSchedule(Course course) {
		if (schedule.contains(course)) {
			int idx = 0;
			for(int x = 0; x < schedule.size(); x++) {
				if(schedule.get(x).equals(course)) {
					idx = x;
				}
			}
			schedule.remove(idx);
			return true;
		}
		return false;
	}
	
	/**
	 * Resets the schedule to have no courses.
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
	}

	/**
	 * Gets the courses that are already in the schedule and puts them into
	 *  a 2 dimensional string array.
	 *  
	 * @return The string array of courses
	 */
	public String[][] getScheduledCourses() {
		String[][] courses = null;
		if(schedule.size() == 0) {
			courses = new String[0][0];
		} else {
			courses = new String[schedule.size()][5];
		}
		for (int i = 0; i < schedule.size(); i++) {
			courses[i] = ((Course) schedule.get(i)).getShortDisplayArray();
 		}
		return courses;
	}

	/**
	 * Sets the title of the schedule to the given string.
	 * 
	 * @param title The input title for the schedule
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null");
		}
		this.title = title;
	}
	
	/**
	 * Pulls the title of the schedule.
	 * 
	 * @return title The title of the schedule
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Returns the number of credits taken in the schedule.
	 *  
	 * @return the number of credits that the student is taking
	 */
	public int getScheduleCredits() {
		int credits = 0;
		
		for(int i = 0; i < schedule.size(); i++) {
			credits += schedule.get(i).getCredits();
		}
		
		return credits;
	}
	
	/**
	 * Checks to see if a course can be added to schedule. Returns true if Course can be added, 
	 * false if the course is null, if the course is already in the schedule, or if their is a conflict between courses.
	 * @param c Course to be checked
	 * @return true if course can be added to schedule
	 */
	public boolean canAdd(Course c) {
		if(c == null) {
			return false;
		}
		
		for(int i = 0; i < schedule.size(); i++) {
			if(c.isDuplicate(schedule.get(i))) {
				return false;
			}
			
			try {
				schedule.get(i).checkConflict(c);
			} catch(ConflictException e) {
				return false;
			}
		}
		
		return true;
	}

}
