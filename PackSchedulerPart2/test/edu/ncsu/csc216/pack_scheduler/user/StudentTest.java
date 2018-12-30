package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests for Student.java
 * @author gabsill
 *
 */
public class StudentTest {
	
	/** Testing first name **/
	final static String FIRST_NAME = "Joe";
	/** Testing Last Name **/
	final static String LAST_NAME = "Bob";
	/** Testing unity id **/
	final static String ID = "jbob";
	/** Testing email **/
	final static String EMAIL = "jbob@ncsu.edu";
	/** Hashed password for testing */
	final static String HASH_PW = "0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ";
	/** max credits for testing **/
	final static int MAX_CREDITS = 16;
	
	private Student s;
	
	//Course information for testing canAdd()
	
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course Enrollment Cap */
	private static final int ENROLLMENT_CAP = 10;
	/** Course meeting days */
	private static final String MEETING_DAYS = "MW";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;

	/**
	 * Method to set up tests
	 */
	@Before
	public void setUp(){
		s = new Student(
				FIRST_NAME,
				LAST_NAME,
				ID,
				EMAIL,
				HASH_PW,
				MAX_CREDITS
				);
	}
	
	/**
	 * Tests the Hash code method for unique hash codes based on fields
	 */
	@Test
	public void testHashCode(){
		User s1 = 
				new Student(
						FIRST_NAME,
						LAST_NAME,
						ID,
						EMAIL,
						HASH_PW,
						MAX_CREDITS
						);
		assertEquals(s.hashCode(), s1.hashCode());
		
		User s2 = new Student(
				FIRST_NAME + 1,
				LAST_NAME,
				ID,
				EMAIL,
				HASH_PW,
				MAX_CREDITS
				);
		assertNotEquals(s.hashCode(), s2.hashCode());
		
		User s3 = new Student(
				FIRST_NAME,
				LAST_NAME + 1,
				ID,
				EMAIL,
				HASH_PW,
				MAX_CREDITS
				);
		assertNotEquals(s.hashCode(), s3.hashCode());
		
		User s4 = new Student(
				FIRST_NAME,
				LAST_NAME,
				ID + 1,
				EMAIL,
				HASH_PW,
				MAX_CREDITS
				);
		assertNotEquals(s.hashCode(), s4.hashCode());
		
		User s5 = new Student(
				FIRST_NAME,
				LAST_NAME,
				ID,
				EMAIL + 1,
				HASH_PW,
				MAX_CREDITS
				);
		assertNotEquals(s.hashCode(), s5.hashCode());
		
		User s6 = new Student(
				FIRST_NAME,
				LAST_NAME,
				ID,
				EMAIL,
				HASH_PW + 1,
				MAX_CREDITS
				);
		assertNotEquals(s.hashCode(), s6.hashCode());
		
		User s7 = new Student(
				FIRST_NAME,
				LAST_NAME,
				ID,
				EMAIL,
				HASH_PW,
				MAX_CREDITS - 1
				);
		assertNotEquals(s.hashCode(), s7.hashCode());
	}
	
	
	/**
	 * Test the equals() method
	 */
	@Test
	public void testEquals(){
		User s1 = 
				new Student(
						FIRST_NAME,
						LAST_NAME,
						ID,
						EMAIL,
						HASH_PW,
						MAX_CREDITS
						);
		assertTrue(s.equals(s1));
		
		User s2 = new Student(
				FIRST_NAME + 1,
				LAST_NAME,
				ID,
				EMAIL,
				HASH_PW,
				MAX_CREDITS
				);
		assertFalse(s.equals(s2));
		
		User s3 = new Student(
				FIRST_NAME,
				LAST_NAME + 1,
				ID,
				EMAIL,
				HASH_PW,
				MAX_CREDITS
				);
		assertFalse(s.equals(s3));
		
		User s4 = new Student(
				FIRST_NAME,
				LAST_NAME,
				ID + 1,
				EMAIL,
				HASH_PW,
				MAX_CREDITS
				);
		assertFalse(s.equals(s4));
		
		User s5 = new Student(
				FIRST_NAME,
				LAST_NAME,
				ID,
				EMAIL + 1,
				HASH_PW,
				MAX_CREDITS
				);
		assertFalse(s.equals(s5));
		
		User s6 = new Student(
				FIRST_NAME,
				LAST_NAME,
				ID,
				EMAIL,
				HASH_PW + 1,
				MAX_CREDITS
				);
		assertFalse(s.equals(s6));
		
		User s7 = new Student(
				FIRST_NAME,
				LAST_NAME,
				ID,
				EMAIL,
				HASH_PW,
				MAX_CREDITS - 1
				);
		assertFalse(s.equals(s7));
		
		User s8 = null;
		assertFalse(s.equals(s8));
		
		String s9 = "";
		assertFalse(s.equals(s9));	
	}
	
	/**
	 * Tests the setter for the firstName field
	 */
	@Test
	public void testSetFirstName(){
		// Valid
		s.setFirstName("hi");
		assertEquals("hi", s.getFirstName());
		
		// null invalid
		try{
			s.setFirstName(null);
			fail();
		} catch(IllegalArgumentException e){
			// test passes
		}
		
		// empty invalid
		try{
			s.setFirstName("");
			fail();
		} catch(IllegalArgumentException e){
			// test passes
		}
		
	}
	
	/**
	 * Tests the setter for the lastName field
	 */
	@Test
	public void testSetLastName(){
		// Valid
		String lastName = "hi";
		s.setLastName(lastName);
		assertEquals(lastName, s.getLastName());
		
		// null invalid
		try{
			s.setLastName(null);
			fail();
		} catch(IllegalArgumentException e){
			// test passes
		}
		
		// empty invalid
		try{
			s.setLastName("");
			fail();
		} catch(IllegalArgumentException e){
			// test passes
		}
		
	}
	
	/**
	 * Tests the setter for the ID field
	 */
	@Test
	public void testSetId(){
		// Valid
		s.setId("hi");
		assertEquals("hi", s.getId());
		
		// null invalid
		try{
			s.setId(null);
			fail();
		} catch(IllegalArgumentException e){
			// test passes
		}
		
		// empty invalid
		try{
			s.setId("");
			fail();
		} catch(IllegalArgumentException e){
			// test passes
		}
		
	}
	
	/**
	 * Tests the setter for the Email field
	 */
	@Test
	public void testSetEmail(){
		// Valid
		s.setEmail("g.absill@ncsu.edu");
		assertEquals("g.absill@ncsu.edu", s.getEmail());
		
		// null invalid
		try{
			s.setEmail(null);
			fail();
		} catch(IllegalArgumentException e){
			// test passes
		}
		
		// empty invalid
		try{
			s.setEmail("");
			fail();
		} catch(IllegalArgumentException e){
			// test passes
		}
		
		// no @ sign
		try{
			s.setEmail("ggg.ggg.g");
			fail();
		} catch(IllegalArgumentException e){
			//test passes
		}
		
		// no .
		try{
			s.setEmail("hi@hi");
			fail();
		} catch(IllegalArgumentException e){
			// test passes
		}
		
		// no . after @
		try{
			s.setEmail("hi.h@ello");
			fail();
		} catch(IllegalArgumentException e){
			// test passes
		}
		
	}
	
	/**
	 * Tests the setter for the password field
	 */
	@Test
	public void testSetPassword(){
		// Valid
		s.setPassword("hi");
		assertEquals("hi", s.getPassword());
		
		// null invalid
		try{
			s.setPassword(null);
			fail();
		} catch(IllegalArgumentException e){
			// test passes
		}
		
		// empty invalid
		try{
			s.setPassword("");
			fail();
		} catch(IllegalArgumentException e){
			// test passes
		}
		
	}
	
	/**
	 * Tests the setter for the maxCredits field
	 */
	@Test
	public void testSetMaxCredits(){
		// Valid Boundary Max
		s.setMaxCredits(18);
		assertEquals(18, s.getMaxCredits());
		
		// Valid Boundary Min
		s.setMaxCredits(3);
		assertEquals(3, s.getMaxCredits());
		
		// Equivalence Valid
		s.setMaxCredits(14);
		assertEquals(14, s.getMaxCredits());
		
		//Invalid over
		try{
			s.setMaxCredits(19);
			fail();
		} catch(IllegalArgumentException e){
			// test passes
		}
		
		//Invalid under
		try{
			s.setMaxCredits(2);
			fail();
		} catch(IllegalArgumentException e){
				// test passes

		}
	}
	
	/**
	 * Tests for comparing two students with compareTo
	 * Should be by last name, first name, unityID
	 */
	@Test
	public void testCompareTo(){
		Student s1 = new Student(
				FIRST_NAME, //Joe
				LAST_NAME, //Bob
				ID, //jbob
				EMAIL,
				HASH_PW, 
				MAX_CREDITS
				);
		
		Student s2 = new Student(
				"Sally",
				LAST_NAME,
				ID,
				EMAIL,
				HASH_PW,
				MAX_CREDITS
				);
		
		Student s3 = new Student(
				FIRST_NAME,
				"Tim",
				ID,
				EMAIL,
				HASH_PW,
				MAX_CREDITS
				);
		Student s4 = new Student(
				FIRST_NAME,
				LAST_NAME,
				"jbob1",
				EMAIL,
				HASH_PW,
				MAX_CREDITS
				);
		Student s5 = new Student(
				FIRST_NAME,
				LAST_NAME,
				"jbill",
				EMAIL,
				HASH_PW,
				MAX_CREDITS
				);
		
		assertEquals(0, s.compareTo(s1));
		assertTrue(s.compareTo(s2) < 0);
		assertTrue(s.compareTo(s3) < 0);
		assertTrue(s.compareTo(s4) < 0);
		assertTrue(s.compareTo(s5) > 0);
		
		try{
			s.compareTo(null);
			fail();
		} catch(IllegalArgumentException e){
			// exception caught
		}
	}
	
	/**
	 * Tests the canAdd method in the student class
	 */
	@Test
	public void testCanAdd() {
		Course nullCourse = null;
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c3 = new Course("CSC101", TITLE, "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 1300, 1400);
		Course c4 = new Course("CSC101", TITLE, "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 1200, 1300);
		Course c5 = new Course("CSC213", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "A");
		Course c6 = new Course("ANG253", "Angular Studies", SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "A");
		Course c7 = new Course("ANG254", "More Angular Studies", SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "A");
		
		assertFalse(s.canAdd(nullCourse));
		assertTrue(s.canAdd(c1));
		s.getSchedule().addCourseToSchedule(c1);
		assertFalse(s.canAdd(c2));
		assertFalse(s.canAdd(c3));
		assertTrue(s.canAdd(c4));
		s.getSchedule().addCourseToSchedule(c4);
		assertTrue(s.canAdd(c5));
		s.getSchedule().addCourseToSchedule(c5);
		assertTrue(s.canAdd(c6));
		s.getSchedule().addCourseToSchedule(c6);
		assertFalse(s.canAdd(c7));
	}
}
