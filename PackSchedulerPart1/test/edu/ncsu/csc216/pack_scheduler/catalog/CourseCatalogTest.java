package edu.ncsu.csc216.pack_scheduler.catalog;



import static org.junit.Assert.*;



import org.junit.Test;



import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;

import edu.ncsu.csc216.pack_scheduler.course.Course;



/**A test class to ensure the functionality of the CourseCatalog class and methods

 * @author Wil Elias, Prem Subedi, Isla Huddin, Edwerdo

 *

 */

public class CourseCatalogTest {



	String validFileName = "test-files/expected_course_catalog.txt";

	String invalidFileName = "test-files/invalid_course_records.txt";

	/**

	 * Tests the CourseCatalog constructor by checking if the created catalog is empty after being instantiated

	 */

	@Test

	public void testCourseCatalog() {

		//Tests for the valid creation of an empty catalog

		CourseCatalog c = new CourseCatalog();

		String[][] s = c.getCourseCatalog();

		assertEquals(s.length, 0);

	}



	/**

	 * Tests the newCourseCatalog method by creating a CourseCatalog, adding a course to it and then checking to see if it was empty

	 * after calling the newCourseCatalog method

	 */

	@Test

	public void testNewCourseCatalog() {

		//Create a new courseCatalog and adds a course to it

		CourseCatalog c = new CourseCatalog();

		c.addCourseToCatalog("CSC216", "Intro to Programming - Java", "001", 3, "jesmith2", 10, "MW", 930, 1200);

		//Tests to confirm the new CourseCatalog is empty

		String[][] s = c.getCourseCatalog();

		assertEquals(s.length, 1);

		c.newCourseCatalog();

		s = c.getCourseCatalog();

		assertEquals(s.length, 0);

	}



	/**

	 * Tests the loadCoursesFromFile method by checking the contents of the catalog after loading a valid file and an invalid file

	 */

	@Test

	public void testLoadCoursesFromFile() {

		CourseCatalog c = new CourseCatalog();

		String[][] s;

		//Tests loading a valid file

		c.loadCoursesFromFile(validFileName);

		s = c.getCourseCatalog();

		assertTrue(s.length > 0);

		c.newCourseCatalog();

		s = c.getCourseCatalog();

		assertTrue(s.length == 0);

		//Tests loading an invalid file

		try{

			c.loadCoursesFromFile(invalidFileName);

		} catch(IllegalArgumentException e) {

			assertEquals("Cannot find file", e.getMessage());

		}

		s = c.getCourseCatalog();

		assertTrue(s.length == 0);

	}



	/**

	 * Tests the addCoursesToCatalog method by adding a valid course, an invalid course, and a duplicate course

	 */

	@Test

	public void testAddCoursesToCatalog() {

		CourseCatalog c = new CourseCatalog();

		String[][] s;

		//Tests adding a valid course

		assertTrue(c.addCourseToCatalog("CSC216", "Intro to Programming - Java", "001", 3, "jesmith2", 10, "MW", 930, 1200));

		//Tests adding an invalid course

		try{

		c.addCourseToCatalog("E15", "I Should Have Tried To Test Out", "002", 2, "mgrath4", 10, "TH", 1340, 1430);

		fail("Added an invalid course to the catalog");

		} catch(IllegalArgumentException e){

			//skip line

		}

		s = c.getCourseCatalog();

		assertEquals(s.length, 1);

		//Tests adding a duplicate course

		assertFalse(c.addCourseToCatalog("CSC216", "Intro to Programming - Java", "001", 3, "jesmith2", 10, "MW", 930, 1200));

	}



	/**

	 * Tests the removeCourseFromCatalog method by adding several courses to the catalog and then removing both valid and invalid courses

	 */

	@Test

	public void testRemoveCourseFromCatalog() {

		CourseCatalog c = new CourseCatalog();

		String[][] s;

		//Adds several valid courses

		c.addCourseToCatalog("CSC216", "Intro to Programming - Java", "001", 3, "jesmith2", 10, "MW", 930, 1200);

		c.addCourseToCatalog("CSC216", "Intro to Programming - Java", "002", 3, "jesmith2", 10, "MW", 1230, 1500);

		c.addCourseToCatalog("CSC215", "Intro to Programming - C", "001", 3, "jesmith2", 10, "MW", 930, 1200);

		s = c.getCourseCatalog(); 

		assertTrue(s.length == 3);

		//Tests removing invalid courses

		assertFalse(c.removeCourseFromCatalog("CSC216", "003"));

		assertFalse(c.removeCourseFromCatalog("E115", "001"));

		assertFalse(c.removeCourseFromCatalog("E115", "003"));

		//Tests removing a valid course

		assertTrue(c.removeCourseFromCatalog("CSC216", "001"));

		assertTrue(c.removeCourseFromCatalog("CSC215", "001"));

	}



	/**

	 * Tests the getCourseFromCatalog method by adding several courses to the catalog and comparing them to duplicate courses by retrieving

	 * them via the getCourseFromCatalog method

	 */

	@Test

	public void testGetCourseFromCatalog() {

		CourseCatalog c = new CourseCatalog();

		String[][] s;

		//Adds several valid courses

		c.addCourseToCatalog("CSC216", "Intro to Programming - Java", "001", 3, "jesmith2", 10, "MW", 930, 1200);

		c.addCourseToCatalog("CSC216", "Intro to Programming - Java", "002", 3, "jesmith2", 10, "MW", 1230, 1500);

		c.addCourseToCatalog("CSC215", "Intro to Programming - C", "001", 3, "jesmith2", 10, "MW", 930, 1200);

		Course a1 = new Course("CSC216", "Intro to Programming - Java", "001", 3, "jesmith2", 10, "MW", 930, 1200);

		Course a2 = new Course("CSC216", "Intro to Programming - Java", "002", 3, "jesmith2", 10, "MW", 1230, 1500);

		Course a3 = new Course("CSC215", "Intro to Programming - C", "001", 3, "jesmith2", 10, "MW", 930, 1200);

		s = c.getCourseCatalog();

		assertTrue(s.length == 3);

		//Tests valid retrievals

		Course g1 = c.getCourseFromCatalog("CSC216", "001");

		Course g2 = c.getCourseFromCatalog("CSC216", "002");

		Course g3 = c.getCourseFromCatalog("CSC215", "001");

		assertEquals(g1, a1);

		assertEquals(g2, a2);

		assertEquals(g3, a3);

		//Tests invalid retrievals

		assertEquals(null, c.getCourseFromCatalog("CSC216", "003"));

		assertEquals(null, c.getCourseFromCatalog("E115", "001"));

		assertEquals(null, c.getCourseFromCatalog("CSC215", "002"));

		

	}



	/**

	 * Tests the getCourseCatalog method by comparing a manually constructed 2d array with a 2d array constructed by the program

	 */

	@Test

	public void testGetCourseCatalog() {

		//Create courses and a string array for comparison

		Course c1 = new Course("CSC215", "Intro to Programming - C", "001", 3, "jesmith2",10, "MW", 930, 1200);

		Course c2 = new Course("CSC216", "Intro to Programming - Java", "001", 3, "jesmith2", 10, "MW", 930, 1200);

		Course c3 = new Course("CSC216", "Intro to Programming - Java", "002", 3, "jesmith2", 10, "MW", 1230, 1500);

		CourseCatalog c = new CourseCatalog();

		String[][] s = new String[3][4];

		s[0][0] = c1.getName(); s[0][1] = c1.getSection(); s[0][2] = c1.getTitle(); s[0][3] = c1.getMeetingString();

		s[1][0] = c2.getName(); s[1][1] = c2.getSection(); s[1][2] = c2.getTitle(); s[1][3] = c2.getMeetingString();

		s[2][0] = c3.getName(); s[2][1] = c3.getSection(); s[2][2] = c3.getTitle(); s[2][3] = c3.getMeetingString();

		//Add courses to catalog for comparison

		c.addCourseToCatalog("CSC216", "Intro to Programming - Java", "001", 3, "jesmith2", 10, "MW", 930, 1200);

		c.addCourseToCatalog("CSC216", "Intro to Programming - Java", "002", 3, "jesmith2", 10, "MW", 1230, 1500);

		c.addCourseToCatalog("CSC215", "Intro to Programming - C", "001", 3, "jesmith2", 10, "MW", 930, 1200);

		//Retrieve the CourseCatalog 2d String array

		String[][] s2 = c.getCourseCatalog();

		assertArrayEquals(s2, s);

	}



	/**

	 * Tests the saveCourseCatalog method for functionality by saving an example to a text file

	 */

	@Test

	public void testSaveCourseCatalog() {

		//Add courses to the catalog for data

		CourseCatalog c = new CourseCatalog();

		c.addCourseToCatalog("CSC216", "Intro to Programming - Java", "001", 3, "jesmith2", 10, "MW", 930, 1200);

		c.addCourseToCatalog("CSC216", "Intro to Programming - Java", "002", 3, "jesmith2", 10, "MW", 1230, 1500);

		c.addCourseToCatalog("CSC215", "Intro to Programming - C", "001", 3, "jesmith2", 10, "MW", 930, 1200);

		//Test if the catalog can be saved

		try{

			c.saveCourseCatalog("test-files/test_save.txt");

		} catch(IllegalArgumentException e){

			fail("Failed to save to a valid file");

		}

		//Validate manually the contents of the file

	}



}