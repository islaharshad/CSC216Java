/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import java.util.Scanner;

/**
 * 
 * Allows course info to read
 * from files
 * 
 * @author Islahuddin Arshad
 */
public class CourseRecordIO {

	
	/**
     * Reads the parsed lines of code from the 
     * text files and returns them into Course 
     * object
     * @param courseLine line in the text file
	 * @throws FileNotFoundException 
     */
	private static Course readCourse(String courseLine) throws FileNotFoundException, InputMismatchException {
		String courseName = "";
    	String title = "";
    	String section = "";
    	int credits = 0;
    	String instructorId = "";
    	String meetingDays = "";
    	int startTime = 0;
		int endTime = 0;
		
		Scanner lineReader = new Scanner(courseLine);
		lineReader.useDelimiter(",");
		Course c = null;
	    try {
	    	courseName = lineReader.next();
	    	if (courseName.equals("")) {
	    		throw new IllegalArgumentException();
	    	}
	    	title = lineReader.next();
	    	if (title.equals("")) {
	    		throw new IllegalArgumentException();
	    	}
	    	section = lineReader.next();
	    	if (section.equals("")) {
	    		throw new IllegalArgumentException();
	    	}
	    	credits = lineReader.nextInt();
	    	if (credits == 0) {
	    		throw new IllegalArgumentException();
	    	}
	    	instructorId = lineReader.next();
	    	if (instructorId.equals("")) {
	    		throw new IllegalArgumentException();
	    	}
	    	meetingDays = lineReader.next();
	    	if (meetingDays.equals("")) {
	    		
	    		throw new IllegalArgumentException();
	    	}
	        
	    	if (meetingDays.equals("A")) { 
	    		if (lineReader.hasNext()) {
	    			
	    			throw new IllegalArgumentException();
	    		}
	    		c = new Course(courseName, title, section, credits, instructorId, meetingDays);
	    		return c;
	    		
	    	}
	    	
	    	
	    	startTime = lineReader.nextInt();
	    	if (startTime == 0) {
	    		throw new IllegalArgumentException();
	    	}
	    	endTime = lineReader.nextInt();
	    	if (endTime == 0) {
	    		throw new IllegalArgumentException();
	    	}
	 
	   		c = new Course(courseName, title, section, credits, instructorId, meetingDays, startTime, endTime);
	    	
	    	
	    	
	    }
	    
	    catch (NoSuchElementException e)  {
	    	throw new IllegalArgumentException();
	    	 
	    }
	    finally {
	    	lineReader.close();
	    }
	   
	    return c;
	}
	
	
	/**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */	
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner fileReader = new Scanner(new File(fileName));
	    ArrayList<Course> courses = new ArrayList<Course>();
	    while (fileReader.hasNextLine()) {
	        try {
	            Course course = readCourse(fileReader.nextLine());
	            boolean duplicate = false;
	            for (int i = 0; i < courses.size(); i++) {
	                Course c = courses.get(i);
	                if (course.getName().equals(c.getName()) &&
	                        course.getSection().equals(c.getSection())) {
	                    //it's a duplicate
	                    duplicate = true;
	                }
	            }
	            if (!duplicate) {
	            	
	                courses.add(course);
	            }
	        } catch (IllegalArgumentException e) {
	            //skip the line
	        	System.out.println("A");
	        	 
	        }
	    } 
	    fileReader.close();
	    return courses;
	}
	
	
	
	 /**
     * Writes the given list of Courses to 
     * @param fileName the name of file from where 
     * the course list is located
     * @param courses the courses in the course list
     * @throws IOException thrown if no file to put
     * print stream on
     * 
     */
	public static void writeCourseRecords(String fileName, ArrayList<Course> courses)throws IOException {
		// TODO Auto-generated method stub
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
		    fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();
	}

}
