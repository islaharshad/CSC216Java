package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

public class CourseRollTest {

	
	@Test
	public void  testCourseRoll(){
		CourseRoll roll = new CourseRoll(35);
		
		assertEquals(35, roll.getEnrollmentCap());
		roll.setEnrollmentCap(50);
		for (int i = 0; i < 15; i++) {
			roll.enroll(new Student("" + i, "" + i, "" + i, i +"@.com", "password" + i));
		}
		assertEquals(35, roll.getOpenSeats());
	}
	
	@Test
	public void testSetEnrollmentCap(){
		CourseRoll roll = new CourseRoll(35);
		
		try {
			roll.setEnrollmentCap(9);
			fail();
		}
		catch(IllegalArgumentException e) {
		    assertEquals(35, roll.getEnrollmentCap());
		}
		
		for (int i = 0; i < 15; i++) {
			roll.enroll(new Student("" + i, "" + i, "" + i, i +"@.com", "password" + i));
		}
		
		try {
			roll.setEnrollmentCap(12);
			fail();
		}
		catch(IllegalArgumentException e) {
			assertEquals(35, roll.getEnrollmentCap());
		}
		
		try {
			roll.setEnrollmentCap(270);
			fail();
		}
		catch(IllegalArgumentException e) {
			assertEquals(35, roll.getEnrollmentCap());
		}
		
		
	}
	
	@Test
	public void testEnroll(){
		CourseRoll roll = new CourseRoll(35);
		Student s = new Student("abc", "def", "ghi", "jkl@sdf.com", "whatever");
		roll.enroll(s);
		assertEquals(34, roll.getOpenSeats());
		
		
		try{
			roll.enroll(null);
			fail();
		}
		catch(IllegalArgumentException e) {
		    assertEquals(34, roll.getOpenSeats());
		}
		
		try {
			roll.enroll(s);
			fail();
		}
		catch(IllegalArgumentException e) {
			assertEquals(34, roll.getOpenSeats());
		}
	
		try {
			for (int i = 0; i < 35; i++) {
				roll.enroll(new Student("" + i, "" + i, "" + i, i +"@.com", "password" + i));
			}
		    fail();
		}
		catch(IllegalArgumentException e) {
			assertEquals(0, roll.getOpenSeats());
		
		}
		
		
	}
	@Test
	public void testDrop(){
		CourseRoll roll = new CourseRoll(35);
		Student s = new Student("" + 3, "" + 3, "" + 3, 3 +"@.com", "password" + 3);
		for (int i = 0; i < 20; i++) {
			roll.enroll(new Student("" + i, "" + i, "" + i, i +"@.com", "password" + i));
		}
		roll.drop(s);
		assertEquals(16, roll.getOpenSeats());
		
		try {
			roll.drop(null);
			fail();
		}
		catch(IllegalArgumentException e) {
			assertEquals(16, roll.getOpenSeats());
		
		}
		
	}
	@Test
	public void testCanEnroll(){
		CourseRoll roll = new CourseRoll(35);
		Student s = new Student("" + 36, "" + 36, "" + 36, 36 +"@.com", "password" + 3);
		for (int i = 0; i < 35; i++) {
			roll.enroll(new Student("" + i, "" + i, "" + i, i +"@.com", "password" + i));
		}
		assertFalse(roll.canEnroll(s));
	}
	
}
