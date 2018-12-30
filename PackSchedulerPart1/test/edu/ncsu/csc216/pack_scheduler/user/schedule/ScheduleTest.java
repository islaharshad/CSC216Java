package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests Schedule class
 * @author Premsubedi, Isla, Edwerdo
 */
public class ScheduleTest {
	

	/**
	 * Test constructor of the Schedule class
	 */

	@Test
	public void testConstructor() {
		
		Schedule sch = new Schedule();
		assertEquals("My Schedule", sch.getTitle());
		
	}
	
	/**
	 * Tests addCourseToSchedule method.
	 */
	@Test
	public void testAddCoursesToSchedule() {
		Schedule sch = new Schedule();
		Course course = new Course("CSC116", "Intro to Programming - Java", "003", 3, "spbalik",10, "MW", 1250, 1440);
		Course course1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5",10, "MW", 1330, 1445);
		assertTrue(sch.addCourseToSchedule(course));
        
		try {
			sch.addCourseToSchedule(course);
			fail();
		}
		catch(IllegalArgumentException e) {
			assertEquals("You are already enrolled in " + course.getName(), e.getMessage());
		}
		
		try {
			sch.addCourseToSchedule(course1);
			fail();
		}
		catch(IllegalArgumentException e) {
			assertEquals("The course cannot be added due to a conflict.", e.getMessage());
			
		}
		try{
			sch.addCourseToSchedule(null);
			fail();
		}
		catch(NullPointerException e)
		{
			assertEquals(sch.getScheduledCourses().length, 1);
		}
	}
	
	/** Tests removeCourseFromSchedule method */
	@Test
	public void testRemoveCourseFromSchedule() {
		Schedule sch = new Schedule();
		Course course = new Course("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", 10, "MW", 1250, 1440);
		Course course1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 1110, 1200);
		sch.addCourseToSchedule(course);
		sch.addCourseToSchedule(course1);
		assertTrue(sch.removeCourseFromSchedule(course));
//		String[][] swag = sch.getScheduledCourses();
		System.out.println(sch.getScheduledCourses()[0][0]);
		assertTrue(sch.removeCourseFromSchedule(course1));
		assertFalse(sch.removeCourseFromSchedule(null));
		assertEquals(0, sch.getScheduledCourses().length);
	}
	
	/** Tests resetSchedule method */
	@Test
	public void testResetSchedule() {
		Schedule sch = new Schedule();
		Course course = new Course("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", 10, "MW", 1250, 1440);
		Course course1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 1110, 1200);
		
		sch.addCourseToSchedule(course);
		sch.addCourseToSchedule(course1);
		sch.resetSchedule();
		assertEquals(0, sch.getScheduledCourses().length);
	}
	
	/** Tests setTitle method */
	@Test
	public void testSetTitle() {
		
		Schedule sch = new Schedule();
			
    	//Set Title and check that changed
		sch.setTitle("New Title");
		assertEquals("New Title", sch.getTitle());
			
			//Check that exception is thrown if null title and no
			//change to title already there.
	    try {
			sch.setTitle(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("New Title", sch.getTitle());
		}
	}
	/**Tests the getScheduleCredits method */
	@Test
	public void testGetScheduleCredits() {
		Schedule sch = new Schedule();
		Course course = new Course("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", 10, "MW", 1250, 1440);
		Course course1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 1110, 1200);
		
		sch.addCourseToSchedule(course);
		sch.addCourseToSchedule(course1);
		assertEquals(7, sch.getScheduleCredits());
	}
	/**Tests the canAdd method */
	@Test
	public void testCanAdd() {
		Schedule sch = new Schedule();
		Course course = new Course("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", 10, "MW", 1250, 1440);
	    assertTrue(sch.canAdd(course));
	}
}


