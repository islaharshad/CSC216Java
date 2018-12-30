/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the methods in the Schedule class.
 * 
 * @author Addison Garrigus
 */
public class ScheduleTest {
	
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
	 * Tests the constructor in the Schedule class.
	 */
	@Test
	public void testSchedule() {
		Schedule schedule  = new Schedule();
		
		//Tests if schedule was constructed correctly
		assertEquals(0, schedule.getScheduledCourses().length);
		assertEquals("My Schedule", schedule.getTitle());
	}
	
	/**
	 * Tests the addCourseToScheule method in the Schedule class.
	 */
	@Test
	public void testAddCourseToSchedule() {
		Schedule schedule  = new Schedule();
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c3 = new Course("CSC101", TITLE, "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 1300, 1400);
		
		//Tests if a valid course was added correctly
		try {
			schedule.addCourseToSchedule(c1);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(1, schedule.getScheduledCourses().length);
		
		//Tries to add a duplicate course
		try {
			schedule.addCourseToSchedule(c2);
		} catch (IllegalArgumentException e) {
			assertEquals("You are already enrolled in CSC216", e.getMessage());
		}
		assertEquals(1, schedule.getScheduledCourses().length);
		
		//Tries to add a conflicting course
		try {
			schedule.addCourseToSchedule(c3);
		} catch (IllegalArgumentException e) {
			assertEquals("The course cannot be added due to a conflict.", e.getMessage());
		}
		assertEquals(1, schedule.getScheduledCourses().length);
	}
	
	/**
	 * Tests the removeCourseFromSchedule method in the Schedule class.
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		Schedule schedule  = new Schedule();
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		//Tests when course being removed is not in schedule
		assertFalse(schedule.removeCourseFromSchedule(c1));
		
		//Tests removing a course from the schedule
		try {
			schedule.addCourseToSchedule(c1);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertTrue(schedule.removeCourseFromSchedule(c1));
	}
	
	/**
	 * Tests the resetSchedule method in the Schedule class.
	 */
	@Test
	public void testResetSchedule() {
		Schedule schedule  = new Schedule();
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		try {
			schedule.addCourseToSchedule(c1);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		//Tests reseting the schedule
		schedule.resetSchedule();
		assertEquals(0, schedule.getScheduledCourses().length);
	}
	
	/**
	 * Tests the getScheduledCourses method in the Schedule class.
	 */
	@Test
	public void testGetScheduledCourses() {
		Schedule schedule  = new Schedule();
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c2 = new Course("CSC116", TITLE, "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "TF", START_TIME, END_TIME);
		try {
			schedule.addCourseToSchedule(c1);
			schedule.addCourseToSchedule(c2);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		//Tests if it is pulling the correct course information
		assertArrayEquals(c1.getShortDisplayArray(), schedule.getScheduledCourses()[0]);
		assertArrayEquals(c2.getShortDisplayArray(), schedule.getScheduledCourses()[1]);
	}
	
	/**
	 * Tests the setTitle method in the Schedule class.
	 */
	@Test
	public void  testSetTitle() {
		Schedule schedule = new Schedule();
		
		//Test Invalid Title
		try {
			schedule.setTitle(null);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Title cannot be null", e.getMessage());
		}
		
		//Test Valid Title
		schedule.setTitle("Title");
		assertEquals("Title", schedule.getTitle());
	}
	
	/**
	 * Tests Schedule.getScheduleCredits()
	 */
	@Test
	public void testGetScheduleCredits() {
		Schedule schedule = new Schedule();
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c2 = new Course("CSC213", TITLE, SECTION, 3, INSTRUCTOR_ID, ENROLLMENT_CAP, "A");
		Course c3 = new Course("CSC101", TITLE, "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 1200, 1300);
		
		schedule.addCourseToSchedule(c1);
		assertEquals(4, schedule.getScheduleCredits());
		schedule.addCourseToSchedule(c2);
		assertEquals(7, schedule.getScheduleCredits());
		schedule.addCourseToSchedule(c3);
		assertEquals(11, schedule.getScheduleCredits());
		
		
	}
	
	
	/**
	 * Tests Schedule.canAdd()
	 */
	@Test
	public void testCanAdd() {
		Schedule schedule  = new Schedule();
		Course nullCourse = null;
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c3 = new Course("CSC101", TITLE, "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 1300, 1400);
		Course c4 = new Course("CSC101", TITLE, "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 1200, 1300);
		
		assertFalse(schedule.canAdd(nullCourse));
		assertTrue(schedule.canAdd(c1));
		schedule.addCourseToSchedule(c1);
		assertFalse(schedule.canAdd(c2));
		assertFalse(schedule.canAdd(c3));
		assertTrue(schedule.canAdd(c4));
	}
}
