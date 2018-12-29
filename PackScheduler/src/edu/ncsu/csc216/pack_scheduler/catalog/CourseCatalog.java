/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * 
 * Holds Catalog for Schedules Courses
 * @author Jonathan Aloba
 * @author Gianni Absillis
 * @author Eduardo Bravo-Escudero
 *
 */
public class CourseCatalog {

		private SortedList<Course> catalog;

		/**
		 * Constructs an empty catalog
		 */
		public CourseCatalog() {
			newCourseCatalog();
		}
		
		/**
		 * Returns an array of the courses in string form that occupy the catalog
		 * 
		 * @return the catalog courses in string form
		 */
		public String[][] getCourseCatalog() {
			String[][] catalogArray = new String[catalog.size()][5];
			for(int i = 0; i < catalog.size(); i++){
				if(catalog.get(i) != null){
					Course c = catalog.get(i);
					catalogArray[i] = c.getShortDisplayArray();
				}
			}
			return catalogArray;
		}

		/**
		 * Constructs an empty catalog
		 */
		public void newCourseCatalog() {
			catalog = new SortedList<Course>();
			
		}
		
		/**
		 * Loads course records into the catalog
		 * @param fileName the file to read from
		 * @throws IllegalArgumentException if file is not found
		 */
		public void loadCoursesFromFile(String fileName) throws IllegalArgumentException{
			try {
				catalog = CourseRecordIO.readCourseRecords(fileName);
			} catch (FileNotFoundException e) {
				throw new IllegalArgumentException("Unable to read file " + fileName);
			}
		}
		
		/**
		 * 
		 * Adds course to catalog
		 * 
		 * @param name Course name
		 * @param title Course title
		 * @param section Course section
		 * @param credits Course credits
		 * @param instructorID Instructor id
		 * @param meetingDays Course meeting days
		 * @param startTime Course start time
		 * @param endTime Course end time
		 * @param enrollmentCap maximum students allowed in the course section
		 * @return true if Course is added to catalog
		 */
		public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorID, int enrollmentCap, String meetingDays, int startTime, int endTime) {
			if (name == null || name.equals("")) {
				throw new IllegalArgumentException("Invalid name");
			}
			if (title == null || title.equals("")) {
				throw new IllegalArgumentException("Invalid title");
			}
			if (section == null || section.length() != 3) {
				throw new IllegalArgumentException("Invalid section");
			}
			if (instructorID == null || instructorID.equals("")) {
				throw new IllegalArgumentException("Invalid instructor id");
			}
			if (meetingDays == null || meetingDays.equals("")) {
				throw new IllegalArgumentException("Invalid meeting days");
			}
			if (startTime > endTime) {
				throw new IllegalArgumentException("Invalid meeting times");
			}
			if (enrollmentCap <= 0) {
				throw new IllegalArgumentException("Invalid enrollment cap");
			}
			
			try {
				Course newCourse = new Course(name, title, section, credits, instructorID, enrollmentCap, meetingDays,	startTime, endTime);
			
				for (int i = 0; i < catalog.size(); i++){
					if (newCourse.isDuplicate(catalog.get(i))
							&& newCourse.getSection().equals(catalog.get(i).getSection())) {
						return false;
					}
				}
				catalog.add(newCourse);
				return true;
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Error constructing the course");
			}
		
		}
		

		
		/**
		 * Removes a course from the catalog
		 * @param name the name of the course to remove
		 * @param section the section of the course to remove
		 * @return true if the course is in the catalog, false otherwise.
		 */
		public boolean removeCourseFromCatalog(String name, String section){
			for(int i = 0; i < catalog.size(); i++) {
				if(catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
					catalog.remove(i);
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Saves a file of the course catalog
		 * @param fileName the name to write the file to
		 * @throws IllegalArgumentException if the file cannot be written
		 */
		public void saveCourseCatalog(String fileName) throws IllegalArgumentException{
			try{
				CourseRecordIO.writeCourseRecords(fileName, catalog);
			} catch(IOException e) {
				throw new IllegalArgumentException("File cannot be written.");
			}
		}
		
		/**
		 * finds a course from the catalog by name and section
		 * @param name the name of the course to look for
		 * @param section the section of the course to look for
		 * @return the Course object if found or null
		 */
		public Course getCourseFromCatalog(String name, String section){
			for(int i = 0; i < catalog.size(); i++) {
				if(catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section) ){
					return catalog.get(i);
				}
			}
			return null;
		}
		
	
		
}
