package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.File;
import java.util.Scanner;
import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;

/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * 
 * @author Jonathan Aloba
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
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
	                    //it's a duplicate
	                    duplicate = true;
	                }
	            }
	            if (!duplicate) {
	                courses.add(course);
	            }
	        } catch (IllegalArgumentException e) {
	            //skip the line
	        }
	    }
	    fileReader.close();
	    return courses;
	}

	/**
	 * Reads line containing Course information and creates new Course.
	 * @param line line to be read
	 * @return a Course
	 */
    private static Course readCourse(String line) {
    	String name = "";
    	String title = "";
    	String section = "";
    	int credits = 0;
    	String instructorId = null;
    	int enrollmentCap = 0;
    	String meetingDays = "";
    	int startTime = 0;
    	int endTime = 0;
    	
    	
    	
    	
    	
    	
    	Scanner lineScanner = new Scanner(line);
    	lineScanner.useDelimiter(",");
			if(lineScanner.hasNext() ){
				name = lineScanner.next();
			}
			
			if(lineScanner.hasNext() ){
				title = lineScanner.next();
			}
			
			if(lineScanner.hasNext() ){
				section = lineScanner.next();
			}
			
			if(lineScanner.hasNextInt() ){
				credits = lineScanner.nextInt();
			}
			
			if(lineScanner.hasNext() ){
				instructorId = lineScanner.next();
			}
			
			if(lineScanner.hasNextInt()) {
				enrollmentCap = lineScanner.nextInt();
			}
			
			if(lineScanner.hasNext() ){
				meetingDays = lineScanner.next();
			}
			
			if(lineScanner.hasNextInt()){
				startTime = lineScanner.nextInt();
			}
			
			if(lineScanner.hasNextInt() ){
				endTime = lineScanner.nextInt();
			}
			
			

		
		lineScanner.close();
		
		Course c = new Course(name, title, section, credits, null, enrollmentCap, meetingDays, startTime, endTime);
		
		////faculty functionality added
		if (RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId) != null) {
			
			RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructorId).getSchedule().addCourseToSchedule(c);
			
		}
		
		 
		return c;
		
	} 

	/**
     * Writes the given list of Courses to output file.
     * @param fileName name of output file
     * @param courses SortedList of courses
     * @throws IOException if invalid  fileName
     */
    public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
    	PrintStream fileWriter = new PrintStream(new File(fileName));

    	for (int i = 0; i < courses.size(); i++) {
    	    fileWriter.println(courses.get(i).toString());
    	}

    	fileWriter.close();
        
    }

}