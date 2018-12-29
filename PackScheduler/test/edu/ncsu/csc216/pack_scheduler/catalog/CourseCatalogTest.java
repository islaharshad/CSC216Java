/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the CourseCatalog class
 * 
 * @author Jonathan Aloba
 * @author Gianni Absillis
 *
 */
public class CourseCatalogTest {

	/** Input file for course records */
	private static final String VALID_FILE = "./test-files/course_records.txt";
	/** Course name */
	
//	private static final String NAME = "CSC216";
//	/** Course title */
//	private static final String TITLE = "Programming Concepts - Java";
//	/** Course section */
//	private static final String SECTION = "001";
//	/** Course credits */
//	private static final int CREDITS = 4;
//	/** Course instructor id */
//	private static final String INSTRUCTOR_ID = "sesmith5";
//	/** Course meeting days */
//	private static final String MEETING_DAYS = "MW";
//	/** Course start time */
//	private static final int START_TIME = 1330;
//	/** Course end time */
//	private static final int END_TIME = 1445;
	/**
	 * Tests loading courses from valid and invalid files
	 */
	@Test
	public void testLoadCoursesFromFile() {
		CourseCatalog c = null;
		try {
			c = new CourseCatalog();
			c.loadCoursesFromFile(VALID_FILE);
		} catch(IllegalArgumentException e) {
			fail("Invalid file name");
		}
		
		assertEquals(c.getCourseCatalog().length, 8);
		assertEquals(c.getCourseCatalog()[0][0], "CSC116");
		assertEquals(c.getCourseCatalog()[0][2], "Intro to Programming - Java");
		assertEquals(c.getCourseCatalog()[0][1], "001");
		
		try{
			c.loadCoursesFromFile(" ");
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("Unable to read file  ", e.getMessage());
		}
	}
	
	/**
	 * Tests reseting the course Catalog
	 */
	@Test
	public void testNewCourseCatalog() {
		CourseCatalog c = null;
		try {
			c = new CourseCatalog();
			c.loadCoursesFromFile(VALID_FILE);
		} catch(IllegalArgumentException e) {
			fail("Invalid file name");
		}
		
		assertFalse(c.getCourseCatalog().length == 0);
		
		c.newCourseCatalog();
		
		assertTrue(c.getCourseCatalog().length == 0);
	}
	
	/**
	 * Tests adding courses to the Catalog
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog c = new CourseCatalog();
		c.addCourseToCatalog("CSC101", "Intro to intro", "001", 3, "gabsill", 20, "MW", 1300, 1430);
		//assertArrayEquals(c1.getShortDisplayArray(), c.getCourseCatalog()[0]);
		assertEquals(1, c.getCourseCatalog().length);
		
		assertFalse(c.addCourseToCatalog("CSC101", "Intro to intro", "001", 3, "gabsill", 20, "MW", 1300, 1430));
		assertEquals(1, c.getCourseCatalog().length);
		assertTrue(c.addCourseToCatalog("CSC216", "Java II", "001", 3, "gabsill", 20, "MW", 1300, 1430));
		assertEquals(2, c.getCourseCatalog().length);
		assertTrue(c.addCourseToCatalog("CSC216", "Java II", "002", 3, "gabsill", 20, "MW", 1300, 1430));
		assertEquals(3, c.getCourseCatalog().length);
		
		try{
			c.addCourseToCatalog("name", "title", "abc", 0, "l", 5, "mw", 1, 2);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Error constructing the course", e.getMessage());
		}
	}
	
	/**
	 * Tests removing courses
	 * Precondition: testAddCoursesToCatalog passes
	 */
	@Test
	public void testRemoveCoursesFromCatalog() {
		CourseCatalog c = new CourseCatalog();
		c.addCourseToCatalog("CSC101", "Intro to intro", "001", 3, "gabsill", 20, "MW", 1300, 1430);
		
		assertFalse(c.removeCourseFromCatalog("CSC102", "001"));
		assertEquals(1, c.getCourseCatalog().length);
		assertFalse(c.removeCourseFromCatalog("CSC101", "002"));
		assertEquals(1, c.getCourseCatalog().length);
		
		assertTrue(c.removeCourseFromCatalog("CSC101", "001"));
		assertEquals(0, c.getCourseCatalog().length);
	}
	
	/**
	 * Tests getting courses
	 * Precondition: testAddCoursesToCatalog passes
	 */
	@Test
	public void testGetCoursesFromCatalog() {
		CourseCatalog c = new CourseCatalog();
		c.addCourseToCatalog("CSC101", "Intro to intro", "001", 3, "gabsill", 20, "MW", 1300, 1430);
		Course c1 = new Course("CSC101", "Intro to intro", "001", 3, "gabsill", 20, "MW", 1300, 1430);
		
		assertEquals(c1, c.getCourseFromCatalog("CSC101", "001"));
		assertEquals(null, c.getCourseFromCatalog("CSC201", "001"));
		assertEquals(null, c.getCourseFromCatalog("CSC101", "002"));
	}
	
	
	/**
	 * Tests saving courses to file
	 * Precondition: loadCoursesFromFile works
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog c = new CourseCatalog();
		c.loadCoursesFromFile(VALID_FILE);
		c.saveCourseCatalog("./test-files/SaveCatalogTest.txt");
		Scanner s1;
		try {
			s1 = new Scanner(new File("./test-files/SaveCatalogTest.txt"));
			assertEquals(s1.nextLine(), "CSC116,Intro to Programming - Java,001,3,null,10,MW,910,1100");
		} catch (FileNotFoundException e) {
			fail();
			e.printStackTrace();
		}
		
	}

}
