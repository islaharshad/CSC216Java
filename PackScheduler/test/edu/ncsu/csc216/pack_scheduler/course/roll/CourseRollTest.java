/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests the CourseRoll class.
 * 
 * @author Addison Garrigus
 */
public class CourseRollTest {
	
	/** Testing first name **/
	private final static String FIRST_NAME = "Joe";
	/** Testing Last Name **/
	private final static String LAST_NAME = "Bob";
	/** Testing unity id **/
	private final static String ID = "jbob";
	/** Testing email **/
	private final static String EMAIL = "jbob@ncsu.edu";
	/** Hashed password for testing */
	private final static String HASH_PW = "0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ";
	/** Max credits for testing **/
	private final static int MAX_CREDITS = 16;
	/** A sample student used for testing */
	private Student s;
	/** Enrollment capacity used for testing */
	private static final int COURSE_CAP = 10;
	/** Course roll used for testing */
	private CourseRoll roll;
	/** The course this roll is for */
	private Course course;

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
	/** Course meeting days */
	private static final String MEETING_DAYS = "MW";
	/** Course Enrollment cap */
	private static final int ENROLLMENT_CAP = 25;
	
	
	
	
	/**
	 * Tests the CourseRoll constructor.
	 */
	@Test
	public void testCourseEnroll() {
		//Tests the constructor
		course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS);
		roll = new CourseRoll(course, COURSE_CAP);
		assertEquals(COURSE_CAP, roll.getOpenSeats());
		
		//Tests setEnrollmentCap method with too large enrollment cap
		try {
			roll = new CourseRoll(course, 300);
		} catch (IllegalArgumentException e) {
			assertEquals("", e.getMessage());
		}
		
		//Tests setEnrollmentCap method with too small enrollment cap
		try {
			roll = new CourseRoll(course, 5);
		} catch (IllegalArgumentException e) {
			assertEquals("", e.getMessage());
		}
	}
	
	/**
	 * Tests the enroll method in CourseRoll class.
	 */
	@Test
	public void testEnroll() {
		course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS);
		roll = new CourseRoll(course, COURSE_CAP);
		s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s1 = new Student("aaaa", "bbbb", ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s2 = new Student("adcb", "baab", ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s3 = new Student("euyio", "fdysiof", ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s4 = new Student("edisS", LAST_NAME, ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s5 = new Student(FIRST_NAME, "shieofh", ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s6 = new Student("ehtwh", LAST_NAME, ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s7 = new Student(FIRST_NAME, "fheiw", ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s8 = new Student("ghfdiog", LAST_NAME, ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s9 = new Student(FIRST_NAME, "ghiewnw", ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s10 = new Student("fdhi", LAST_NAME, ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s11 = new Student("dgsffi", LAST_NAME, ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s12 = new Student("adsgd", LAST_NAME, ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s13 = new Student("fsfddhi", LAST_NAME, ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s14 = new Student("fdghjhi", LAST_NAME, ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s15 = new Student("fxvdhi", LAST_NAME, ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s16 = new Student("fxvnfdhi", LAST_NAME, ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s17 = new Student("fdaqaqhi", LAST_NAME, ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s18 = new Student("fdmfdhi", LAST_NAME, ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s19 = new Student("fxcyudhi", LAST_NAME, ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s20 = new Student("fxcykudhi", LAST_NAME, ID, EMAIL, HASH_PW, MAX_CREDITS);
		//Tests adding a null student
		try {
			roll.enroll(null);
		} catch (IllegalArgumentException e) {
			//Test Passes
		}
		
		//Test enrolling valid students
		try {
			roll.enroll(s);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		//Tests adding a duplicate student
		try {
			roll.enroll(s);
			fail();
		} catch (IllegalArgumentException e) {
			//Test Passes
		}

		//Test adding a student when capacity has been reached
		try {
			roll.enroll(s1);
			roll.enroll(s2);
			roll.enroll(s3);
			roll.enroll(s4);
			roll.enroll(s5);
			roll.enroll(s6);
			roll.enroll(s7);
			roll.enroll(s8);
			roll.enroll(s9);
		} catch (IllegalArgumentException e) {
			fail();
		}
		try {
			roll.enroll(s10);
			assertEquals(1, roll.getNumberOnWaitlist());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			roll.enroll(s11);
			roll.enroll(s12);
			roll.enroll(s13);
			roll.enroll(s14);
			roll.enroll(s15);
			roll.enroll(s16);
			roll.enroll(s17);
			roll.enroll(s18);
			roll.enroll(s19);
			assertEquals(10, roll.getNumberOnWaitlist());
		}
		catch (IllegalArgumentException e) {
			fail();
		}
		try {
			roll.enroll(s20);
			fail();
		}
		catch(IllegalArgumentException e) {
			assertEquals(10, roll.getNumberOnWaitlist());
		}
	}
	
	/**
	 * Tests the drop method in the CourseRoll class.
	 */
	@Test
	public void testDrop() {
		course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS);
		roll = new CourseRoll(course, COURSE_CAP);
		s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s1 = new Student("aaaa", "bbbb", ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s2 = new Student("adcb", "baab", ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s3 = new Student("euyio", "fdysiof", ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s4 = new Student("edisS", LAST_NAME, ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s5 = new Student(FIRST_NAME, "shieofh", ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s6 = new Student("ehtwh", LAST_NAME, ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s7 = new Student(FIRST_NAME, "fheiw", ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s8 = new Student("ghfdiog", LAST_NAME, ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s9 = new Student(FIRST_NAME, "ghiewnw", ID, EMAIL, HASH_PW, MAX_CREDITS);
		Student s10 = new Student(FIRST_NAME, "ghielkjlwnw", ID, EMAIL, HASH_PW, MAX_CREDITS);
		roll.enroll(s);
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		
		
		
		
		//Test dropping a null student
		try {
			roll.drop(null);
			fail();
		} catch (IllegalArgumentException e) {
			//Test Passes
		}
		
		//Test dropping a valid student
		try {
			roll.drop(s);
			assertEquals(0, roll.getOpenSeats()); 
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		//Test dropping a student not in roll
		try {
			roll.drop(s);
		} catch (IllegalArgumentException e) {
			assertEquals(0, roll.getOpenSeats());
		}
		
		//test setEnrollmentCap
		try {
			roll.setEnrollmentCap(251);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(10, roll.getEnrollmentCap());
		}
		
		try {
			roll.setEnrollmentCap(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(10, roll.getEnrollmentCap());
		}
		roll.setEnrollmentCap(11);
		assertEquals(11, roll.getEnrollmentCap());
	}
	
}
