/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Reads the course records from text files and writes a set of them to a file
 * @author Samuel Ryne Ritter, Prem Subedi
 */
public class CourseRecordIO {
	
	/** The number of strings on a line from the file */
	public static final int STRINGS_ON_A_LINE = 5;
	/** The number of integers on a line from the file */
	public static final int INTEGERS_ON_A_LINE = 3;
	
	/**
	 * Reads course records from a file and creates a list of them. Invalid courses are ignored
	 * If the file can't be read a File not found exception is thrown
	 * @param fileName file that the course records are read from
	 * @return a list of valid courses
	 * @throws FileNotFoundException if the file can't be read or found
	 */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new File(fileName));
		SortedList<Course> courses = new SortedList<Course>();
	    while (fileReader.hasNextLine()) {
	        try {
	            Course course = readCourse(fileReader.nextLine());
	            boolean duplicate = false;
	            for (int i = 0; i < courses.size(); i++) {
	                Course c = courses.get(i);
	                if (course.getName().equals(c.getName()) &&
	                        course.getSection().equals(c.getSection())) {
	                    duplicate = true;
	                }
	            }
	            if (!duplicate) {
	                courses.add(course);
	            }
	        } catch (IllegalArgumentException e) {
	        	//skips line
	        }
	    }
	    fileReader.close();
	    return courses;
	}



	/**

	 * Reads a line and separates the line into substring that is then fed into the constructor of the Course class to create a new Course object 

	 * @param nextLine

	 * @return a new Course object

	 */

    private static Course readCourse(String nextLine) {

    	String[] substring = nextLine.split(",", -1);

    	Course c = null;
        if (substring.length != 9 && substring.length !=7) {
        	throw new IllegalArgumentException();
        }
    	try
    	{
    		if(substring.length == 9)
    		{
			 c = new Course(substring[0], substring[1], substring[2], Integer.parseInt(substring[3]), substring[4], 
					        Integer.parseInt(substring[5]),substring[6], Integer.parseInt(substring[7]), Integer.parseInt(substring[8]));
    		}

    		else if (substring.length == 7)
    		{
    			c = new Course(substring[0], substring[1], substring[2], Integer.parseInt(substring[3]), substring[4], 
    					       Integer.parseInt(substring[5]), substring[6]);
    		}
    		else
    		{
    			throw new NoSuchElementException();
    		}
    	}
    	catch(NoSuchElementException e)
    	{
    		throw new IllegalArgumentException();
    	}
		return c;

	}

	/**
	 * Writes the given list of courses to a file
	 * @param fileName Name of the file to be written
	 * @param courses Sorted List of courses
	 * @throws IOException This can be from an error in the process of creating a file
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
		    fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();
	}

}
