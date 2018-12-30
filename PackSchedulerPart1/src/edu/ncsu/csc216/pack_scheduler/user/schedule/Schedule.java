package edu.ncsu.csc216.pack_scheduler.user.schedule;



import edu.ncsu.csc216.pack_scheduler.course.ConflictException;

import edu.ncsu.csc216.pack_scheduler.course.Course;

import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Class which represents a schedule in PackScheduler program.

 * @author Eduardo, Isla, Prem

 */

public class Schedule {

	

	/** The Schedule's list of courses*/

	private ArrayList<Course> schedule;

	

	/** The Schedule's title, useful for the GUI */

	private String title;

	

	/**



	 * Constructor method. 'schedule' field is initially empty

	 * and 'title' field is initially 'My Schedule'

	 */

	public Schedule() {

		schedule = new ArrayList<Course>();

		title = "My Schedule";

	}



	/**

	 * Returns String representation of title.

	 * @return title schedule's title

	 */

	public String getTitle() {

		return title;

	}

	

	/**

     * Returns a 2D array of scheduled courses 

     * with name, section, and title

     * @return String[][] a string representation of the 

     * courses in the schedule

     */



	public String[][] getScheduledCourses() {

		String [][] scheduleArray = new String[schedule.size()][3];

		for (int i = 0; i < schedule.size(); i++) {

		    	schedule.get(i);

				scheduleArray[i] = schedule.get(i).getShortDisplayArray();



		}

		return scheduleArray;



	}

	

	/**

	 * This method returns true if course can be added

	 * @param course course about to be added.

	 * @return false false if the course can't be added.

	 * @throws IllegalArgumentException if the course is the duplicate of the 

	 * course that is already in the schedule.

	 */

     public boolean addCourseToSchedule(Course course) throws IllegalArgumentException {



		boolean courseCantBeAdded = false;
		
		if(course == null)
		{
			throw new NullPointerException();
		}

			for (int i = 0; i < schedule.size(); i++) {

				if (schedule.get(i).isDuplicate(course)) {

			    throw new IllegalArgumentException("You are already enrolled in " + course.getName());



				}

			    try {

					schedule.get(i).checkConflict(course);

				}

				catch (ConflictException e) {

					throw new IllegalArgumentException("The course cannot be added due to a conflict.");



				}

			}



		if (!courseCantBeAdded) {

			schedule.add(course);

			return true;

		}

		return false;



	}

     

     /**



      * Returns true if the course can be removed from the schedule, false if there 

      * is no any course in the schedule to remove.

      * @param course course in the schedule

      * @return false false if there is no any courses

      */

     public boolean removeCourseFromSchedule(Course course) {



 		boolean courseCanBeRemoved = true;

 		

 			for (int i = 0; i < schedule.size(); i++) {

 				if (schedule.get(i).equals(course)) {

 					

 	    			courseCanBeRemoved = true;

 				}	

 			}

 		if(course == null)

 		{

 			return false;

 		}



 		if (courseCanBeRemoved) {

 			schedule.remove(course);

 			return true;

 		}



 		return false;



 	}

    	

     /**

      * Resets the schedule

      */

 	public void resetSchedule() {



 		schedule = new ArrayList<Course>();

 	}

 	

 	/**

     * Sets the title of the schedule

     * @param title the title of the schedule to set

     */

	public void setTitle(String title) {

		if (title == null) {

			throw new IllegalArgumentException("Title cannot be null");

		}

		this.title = title;

	}
	
	public int getScheduleCredits() {
		int credits = 0;
		for (int i = 0; i < schedule.size(); i++) {
			credits += schedule.get(i).getCredits();
		}
		return credits;
	}

 	public boolean canAdd(Course course) {
 		
 		
        boolean courseCantBeAdded = false;
		    if(course == null) {
	    	    return false;
		    }
			for (int i = 0; i < schedule.size(); i++) {
				if (schedule.get(i).isDuplicate(course)) {
			    return false;
				}
			    try {
					schedule.get(i).checkConflict(course);
				}
				catch (ConflictException e) {
					return false;
				}
			}
		    if (!courseCantBeAdded) {
			    schedule.add(course);
		    	return true;
		    }
		    return false;
	}
	

}