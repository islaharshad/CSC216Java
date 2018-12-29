/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * 
 * Allows the structure of calling
 * courses from the catalog to the 
 * schedule
 * 
 * @author Islahuddin Arshad
 */
public class WolfScheduler {

	/** The array of courses in the coursecatalog */
	private ArrayList<Course> courseCatalog;
	/** The array of activity of schedule*/
	private ArrayList<Activity> schedule;
	/** The title of the class */
	private String title;
	
	
	/**
	 * Creates the WolfScheduler constructor, sets the title,
	 * reads the given courses from files, and throws IllegalArgumentException
	 * if invalid file entered
	 *@param fileName file name of the course
	 *@exception FileNotFoundException exception if invalid file passed in
	 */
	public WolfScheduler (String fileName) {
		try {
			this.courseCatalog = new ArrayList<Course>();
			ArrayList<Course> courseArray = CourseRecordIO.readCourseRecords(fileName);
			for (int i = 0; i < courseArray.size(); i++) {
				
				courseCatalog.add(courseArray.get(i)); 
			}
			
			this.schedule = new ArrayList<Activity>();
			setTitle("My Schedule");
		}
		catch (FileNotFoundException e) { 
			throw new IllegalArgumentException("Cannot find file");
			
		}
	}

	/**
     * Given a name and a section, gets courses from
     * the catalog
     * @param name name of the course
     * @param section section of the course
     * @return Course the course returned that
     * is not a duplicate
     */
	public Course getCourseFromCatalog(String name, String section) {
		Course courseFound = null;
		boolean flag = false;
		for (int i = 0; i < courseCatalog.size(); i++) {
			if (courseCatalog.get(i).getName().equals(name) && courseCatalog.get(i).getSection().equals(section)) {
				courseFound = courseCatalog.get(i);
				flag = true;
			}
		}
		    if  (!flag) {
		    	return null;
		    }
		    return courseFound;
	 }
	
	/**
     * Returns true if successfully
     * adds a distinct course in the schedule if not
     * already added before
     * @param name name of the course
     * @param section section of the course
     * @return boolean whether a course can be added
     */
	public boolean addCourse(String name, String section) {
		
		boolean courseCantBeAdded = false;
		Course course = getCourseFromCatalog(name, section);
		if (course == null) {
			return false;
		}
			for (int i = 0; i < schedule.size(); i++) {
				if (schedule.get(i).isDuplicate(course)) {
			    throw new IllegalArgumentException("You are already enrolled in " + name);
				}	
			}
			
		if (!courseCantBeAdded) {
			schedule.add(course);
			return true;
		}
		return false;
	}
	/**
	 * Adds an event 
	 *@param title of the event 
	 *@param meetingDays meeting days of the event
	 *@param startTime start time of the event
	 *@param endTime end time of the event
	 *@param eventWeeklyRepeat how many times and event can be repeated
	 *@param eventDetails the details of the event
	 */
	public void addEvent(String title, String meetingDays, int startTime, int endTime, int eventWeeklyRepeat, String eventDetails) {
		
		Event event = new Event(title, meetingDays, startTime, endTime, eventWeeklyRepeat, eventDetails);
		
		
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(event)) {
		    throw new IllegalArgumentException("You have already created an event called " + title);
			}
		}
		
			schedule.add(event);
		
			
			
	}
	    
	   
	
	
	
	/**
     * Returns true if successfully
     * removes a distinct course in the schedule if not
     * already removed before
	 * @param idx TODO
     * @return boolean whether a course can be removed
     */
	public boolean removeActivity(int idx) {
		
		boolean courseCanBeRemoved = true;
		Activity course = null;
		if (schedule.size() <= idx || idx < 0) {
			return false;
		}
			for (int i = 0; i < schedule.size(); i++) {
				if (schedule.get(i).equals(schedule.get(idx))) {
					course = schedule.get(i);
	    			courseCanBeRemoved = true;
				}	
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
		schedule = new ArrayList<Activity>();
	}
			
	/**
     * Returns a 2D array of courses in the catalog
     * @return String[][] a string representation of the 
     * courses in the catalog
     */
	public String[][] getCourseCatalog() {
		String [][] catalogArray = new String[courseCatalog.size()][4];
		for (int i = 0; i < courseCatalog.size(); i++) {
				
				catalogArray[i][0] = courseCatalog.get(i).getName();
				catalogArray[i][1] = courseCatalog.get(i).getSection();
				catalogArray[i][2] = courseCatalog.get(i).getTitle();
				catalogArray[i][3] = courseCatalog.get(i).getMeetingString();
		}
		return catalogArray;
	}

	/**
     * Returns a 2D array of scheduled courses 
     * with name, section, and title
     * @return String[][] a string representation of the 
     * courses in the schedule
     */
	public String[][] getScheduledActivities() {
		String [][] scheduleArray = new String[schedule.size()][3];
		for (int i = 0; i < schedule.size(); i++) {
				schedule.get(i);
				scheduleArray[i] = schedule.get(i).getShortDisplayArray();
				
	
		}
		return scheduleArray;
	}
	
	/**
     * Returns a 2D array of scheduled courses 
     * with name, section, title, credits, 
     * instructorId, meetingdays
     * @return String[][] a string representation of all
     * courses in the schedule
     */
	public String[][] getFullScheduledActivities() {
		String [][] scheduleArray = new String[schedule.size()][7];
		for (int i = 0; i < schedule.size(); i++) {
				schedule.get(i);
				scheduleArray[i] = schedule.get(i).getLongDisplayArray();
		}
		return scheduleArray;
	}

	/**
     * Returns the title of the schedule
     * @return String the title
     */
	public String getTitle() {
	    return title;
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

	/**
     * Writes into a file the given schedule 
     * that is then saved
     * @param fileName the name of the file
     */
	public void exportSchedule(String fileName) {
		try  {
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
		}
		catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
		
	}
}
