/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Course;
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

	private ArrayList<Course> courseCatalog;
	private ArrayList<Course> schedule;
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
			
			this.schedule = new ArrayList<Course>();
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
		
		boolean courseCanBeAdded = false;
		Course course = null;
		
			for (int i = 0; i < courseCatalog.size(); i++) {
				if (courseCatalog.get(i).getName().equals(name) && courseCatalog.get(i).getSection().equals(section)) {
					courseCanBeAdded = true;
					course = courseCatalog.get(i);
					
				}
			}
		
			for (int i = 0; i < schedule.size(); i++) {
				if (schedule.get(i).getName().equals(name)) {
	    				throw new IllegalArgumentException("You are already enrolled in " + name);
				}	
			}
				
			
		if (courseCanBeAdded) {
			schedule.add(course);
			return true;
		}
		return false;
	}
	
	/**
     * Returns true if successfully
     * removes a distinct course in the schedule if not
     * already removed before
     * @param name name of the course
     * @param section section of the course
     * @return boolean whether a course can be removed
     */
	public boolean removeCourse(String name, String section) {
		
		boolean courseCanBeRemoved = false;
		Course course = null;
			for (int i = 0; i < schedule.size(); i++) {
				if (schedule.get(i).getName().equals(name) && schedule.get(i).getSection().equals(section)) {
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
		schedule = new ArrayList<Course>();
	}
			
	/**
     * Returns a 2D array of courses in the catalog
     * @return String[][] a string representation of the 
     * courses in the catalog
     */
	public String[][] getCourseCatalog() {
		String [][] catalogArray = new String[courseCatalog.size()][3];
		for (int i = 0; i < courseCatalog.size(); i++) {
				
				catalogArray[i][0] = courseCatalog.get(i).getName();
				catalogArray[i][1] = courseCatalog.get(i).getSection();
				catalogArray[i][2] = courseCatalog.get(i).getTitle();
		}
		return catalogArray;
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
				scheduleArray[i][0] = schedule.get(i).getName();
				scheduleArray[i][1] = schedule.get(i).getSection();
				scheduleArray[i][2] = schedule.get(i).getTitle();
	
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
	public String[][] getFullScheduledCourses() {
		String [][] scheduleArray = new String[schedule.size()][6];
		for (int i = 0; i < schedule.size(); i++) {
				schedule.get(i);
				scheduleArray[i][0] = schedule.get(i).getName();
				scheduleArray[i][1] = schedule.get(i).getSection();
				scheduleArray[i][2] = schedule.get(i).getTitle();
				scheduleArray[i][3] = Integer.toString(schedule.get(i).getCredits());
				scheduleArray[i][4] = schedule.get(i).getInstructorId();
				scheduleArray[i][5] = schedule.get(i).getMeetingString();
				//System.out.println(schedule.get(i).getMeetingDays());
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
			CourseRecordIO.writeCourseRecords(fileName, schedule);
		}
		catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
		
	}
}
