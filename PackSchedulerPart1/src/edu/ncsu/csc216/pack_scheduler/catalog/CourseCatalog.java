package edu.ncsu.csc216.pack_scheduler.catalog;



import java.io.FileNotFoundException;

import java.io.IOException;



import edu.ncsu.csc216.collections.list.SortedList;

import edu.ncsu.csc216.pack_scheduler.course.Course;

import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;



/**A class for handling the manipulation of courses in a SortedList array called the CourseCatalog

 * @author Wil Elias, Prem Subedi, Islahuddin Arshad

 *   

 */

public class CourseCatalog {



	SortedList<Course> catalog;

	

	/** Basic constructor of the CourseCatalog, instantiates a new empty catalog

	 */

	public CourseCatalog()

	{

		catalog = new SortedList<Course>();

	}

	

	/** Wipes the current catalog and replaces it with an empty catalog

	 */

	public void newCourseCatalog() {

		this.catalog = new SortedList<Course>();

	}

	

	/**Loads courses that are read from the inputed file into the catalog 

	 * @param filename - the file to pull course information from

	 */

	public void loadCoursesFromFile(String filename)

	{

		try{

			catalog = CourseRecordIO.readCourseRecords(filename);

		} catch (FileNotFoundException e)

		{

			throw new IllegalArgumentException("Cannot find file");

		}

	}

	

	/**Adds a course of the inputed values

	 * @param name - the desired name of the course

	 * @param title - the desired title

	 * @param section - the desired section

	 * @param credits - the desired credits

	 * @param instructorId - the desired instructorId

	 * @param meetingDays - the desired meetingDays

	 * @param startTime - the desired startTime

	 * @param endTime - the desired endTime

	 * @return - returns false if the course already existed, and returns true if it was added

	 */

	public boolean addCourseToCatalog(String name, String title, String section, int credits, 
			                          String instructorId, int enrollmentCap, String meetingDays, int startTime, int endTime) {

		Course c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);

		

		for (int i = 0; i < catalog.size(); i++)

		{	

			if(catalog.get(i).getName().equals(c.getName()) && catalog.get(i).getSection().equals(c.getSection()))

			{

				return false;

			}	

		}

		catalog.add(c);

		return true;

	}

	

	/**Removes the selected course from the catalog

	 * @param name - the name of the course to be removed

	 * @param section - the section of the course to be removed

	 * @return - returns true if the course was removed, and false if it was not

	 */

	public boolean removeCourseFromCatalog(String name, String section)

	{

		for(int x = 0; x < catalog.size(); x++)

		{

			if(catalog.get(x).getName().equals(name) && catalog.get(x).getSection().equals(section))

			{

				catalog.remove(x);

				return true;

			}

		}

		return false;

	}

	

	/** returns the Course object that has the inputed name and section

	 * @param name - the name of the desired course

	 * @param section - the section of the desired course

	 * @return - returns null if the object does not exist in the catalog

	 */

	public Course getCourseFromCatalog(String name, String section)

	{

		for(int x = 0; x < catalog.size(); x++)

		{

			if(catalog.get(x).getName().equals(name) && catalog.get(x).getSection().equals(section))

			{

				return catalog.get(x);

			}

		}

		return null;

	}

	

	/** Returns a 2d String array of information on the courses in the catalog

	 * @return - returns the name, section, title and meetingString

	 */

	public String[][] getCourseCatalog()

	{

		String[][] c = new String[catalog.size()][4];

		for(int x = 0; x < catalog.size(); x++)

		{

			c[x][0] = catalog.get(x).getName();

			c[x][1] = catalog.get(x).getSection();

			c[x][2] = catalog.get(x).getTitle();

			c[x][3] = catalog.get(x).getMeetingString();

		}

		return c;

	}

	

	/** Saves the current catalog to the specified file, throwing an exception if an IOException is thrown

	 * @param filename - the name of the file where the catalog will be saved

	 * @throws IllegalArgumentException - the error thrown to the caller of this method

	 */

	public void saveCourseCatalog(String filename) throws IllegalArgumentException

	{

		try{

		CourseRecordIO.writeCourseRecords(filename, catalog);

		} catch (IOException e){

			throw new IllegalArgumentException("File cannot be saved.");

		}

	}

}