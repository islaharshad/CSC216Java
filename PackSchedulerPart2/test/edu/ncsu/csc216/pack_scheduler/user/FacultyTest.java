package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Faculty class
 * @author Samuel Ryne Ritter
 *
 */
public class FacultyTest {

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
	final static int MAX_COURSES = 2;
	
	private Faculty f;
	
	/**
	 * Method to set up tests
	 */
	@Before
	public void setUp(){
		f = new Faculty(
				FIRST_NAME,
				LAST_NAME,
				ID,
				EMAIL,
				HASH_PW,
				MAX_COURSES
				);
		
	}
	
	/**
	 * Tests the hashCode method of the Faculty class
	 */
	@Test
	public void testHashCode() {
		
		User f1 = 
				new Faculty(
						FIRST_NAME,
						LAST_NAME,
						ID,
						EMAIL,
						HASH_PW,
						MAX_COURSES
						);
		assertEquals(f.hashCode(), f1.hashCode());
		
		User f2 = new Faculty(
				FIRST_NAME + 1,
				LAST_NAME,
				ID,
				EMAIL,
				HASH_PW,
				MAX_COURSES
				);
		assertNotEquals(f.hashCode(), f2.hashCode());
		
		User f3 = new Faculty(
				FIRST_NAME,
				LAST_NAME + 1,
				ID,
				EMAIL,
				HASH_PW,
				MAX_COURSES
				);
		assertNotEquals(f.hashCode(), f3.hashCode());
		
		User f4 = new Faculty(
				FIRST_NAME,
				LAST_NAME,
				ID + 1,
				EMAIL,
				HASH_PW,
				MAX_COURSES
				);
		assertNotEquals(f.hashCode(), f4.hashCode());
		
		User f5 = new Faculty(
				FIRST_NAME,
				LAST_NAME,
				ID,
				EMAIL + 1,
				HASH_PW,
				MAX_COURSES
				);
		assertNotEquals(f.hashCode(), f5.hashCode());
		
		User f6 = new Faculty(
				FIRST_NAME,
				LAST_NAME,
				ID,
				EMAIL,
				HASH_PW + 1,
				MAX_COURSES
				);
		assertNotEquals(f.hashCode(), f6.hashCode());
		
		User f7 = new Faculty(
				FIRST_NAME,
				LAST_NAME,
				ID,
				EMAIL,
				HASH_PW,
				MAX_COURSES - 1
				);
		assertNotEquals(f.hashCode(), f7.hashCode());
	}

	/**
	 * Tests the equals method of the Faculty class
	 */
	@Test
	public void testEqualsObject() {
		User f1 = 
				new Faculty(
						FIRST_NAME,
						LAST_NAME,
						ID,
						EMAIL,
						HASH_PW,
						MAX_COURSES
						);
		assertTrue(f.equals(f1));
		
		User f2 = new Faculty(
				FIRST_NAME + 1,
				LAST_NAME,
				ID,
				EMAIL,
				HASH_PW,
				MAX_COURSES
				);
		assertFalse(f.equals(f2));
		
		User f3 = new Faculty(
				FIRST_NAME,
				LAST_NAME + 1,
				ID,
				EMAIL,
				HASH_PW,
				MAX_COURSES
				);
		assertFalse(f.equals(f3));
		
		User f4 = new Faculty(
				FIRST_NAME,
				LAST_NAME,
				ID + 1,
				EMAIL,
				HASH_PW,
				MAX_COURSES
				);
		assertFalse(f.equals(f4));
		
		User f5 = new Faculty(
				FIRST_NAME,
				LAST_NAME,
				ID,
				EMAIL + 1,
				HASH_PW,
				MAX_COURSES
				);
		assertFalse(f.equals(f5));
		
		User f6 = new Faculty(
				FIRST_NAME,
				LAST_NAME,
				ID,
				EMAIL,
				HASH_PW + 1,
				MAX_COURSES
				);
		assertFalse(f.equals(f6));
		
		User f7 = new Faculty(
				FIRST_NAME,
				LAST_NAME,
				ID,
				EMAIL,
				HASH_PW,
				MAX_COURSES - 1
				);
		assertFalse(f.equals(f7));
		
		User f8 = null;
		assertFalse(f.equals(f8));
		
		String f9 = "";
		assertFalse(f.equals(f9));	
	}

	/**
	 * Tests the Faculty class's constructor
	 */
	@Test
	public void testFaculty() {
		assertEquals("Joe", f.getFirstName());
		assertEquals("Bob", f.getLastName());
		assertEquals("jbob", f.getId());
		assertEquals("jbob@ncsu.edu", f.getEmail());
		assertEquals("0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ", f.getPassword());
		assertEquals(2, f.getMaxCourses());
		
		User f6 = null;
		try{
			
			f6 = new Faculty(
					FIRST_NAME,
					LAST_NAME,
					ID,
					EMAIL,
					HASH_PW,
					4
					);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, f6);
		}
		
		try{
			
			f6 = new Faculty(
					FIRST_NAME,
					LAST_NAME,
					ID,
					EMAIL,
					HASH_PW,
					0
					);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, f6);
		}
	}

	/**
	 * Tests the setMaxCourses method of the Faculty class
	 */
	@Test
	public void testSetMaxCourses() {
		f.setMaxCourses(3);
		assertEquals(3, f.getMaxCourses());
		
		try{
			f.setMaxCourses(4);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(3, f.getMaxCourses());
		}
		
		try{
			f.setMaxCourses(0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(3, f.getMaxCourses());
		}
	}

	/**
	 * Tests the toString method of the Faculty class
	 */
	@Test
	public void testToString() {
		
		assertEquals("Joe,Bob,jbob,jbob@ncsu.edu,0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ,2", 
				f.toString());
	}

}
