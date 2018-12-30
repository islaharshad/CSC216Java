/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Testing class for the Conflict implementation in Activity
 * @author Gianni
 *
 */
public class ActivityTest {


	/**
	 * Test method for {edu.ncsu.csc216.wolf_scheduler.course.Activity#checkConflict(edu.ncsu.csc216.wolf_scheduler.course.Activity)}.
	 */
	@Test
	public void testCheckConflict() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "TH", 1330, 1445);
	    try {
	        a1.checkConflict(a2);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	    
	    try {
	        a2.checkConflict(a1);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	    
	  //Update a1 with the same meeting days and a start time that overlaps the end time of a2
	    a1.setMeetingDays("TH");
	    a1.setActivityTime(1445, 1530);
	    try {
	        a1.checkConflict(a2);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    
	    a1.setMeetingDays("TH");
	    a1.setActivityTime(1445, 1530);
	    try {
	        a2.checkConflict(a1);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    
	    // Update so that there is only a conflict on a single day
	    a1.setMeetingDays("TF");
	    a1.setActivityTime(1445, 1530);
	    try {
	        a1.checkConflict(a2);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TF 2:45PM-3:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    
	    a1.setMeetingDays("TF");
	    a1.setActivityTime(1445, 1530);
	    try {
	        a2.checkConflict(a1);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TF 2:45PM-3:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    
	    // Change so end time is start time of other
	    a1.setMeetingDays("TH");
	    a1.setActivityTime(1200, 1330);
	    try {
	        a1.checkConflict(a2);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TH 12:00PM-1:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    
	    try {
	        a2.checkConflict(a1);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	    	assertEquals("TH 12:00PM-1:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    
	    Activity a3 = new Course("CSC336", "title", "001", 3, "guopin", 10, "A");
	    
	    try{
	    	a1.checkConflict(a3);
	    	assertEquals("TH 12:00PM-1:30PM", a1.getMeetingString());
	        assertEquals("Arranged", a3.getMeetingString());
	    } catch(ConflictException e) {
	    	fail(); // Arranged shouldn't generate conflicts
	    }
	    try{
	    	a3.checkConflict(a1);
	    	assertEquals("TH 12:00PM-1:30PM", a1.getMeetingString());
	        assertEquals("Arranged", a3.getMeetingString());
	    } catch(ConflictException e) {
	    	fail(); // Arranged shouldn't generate conflicts
	    }
	    
	    
	    // set one to completely cover the other
	    a1.setMeetingDays("TH");
	    a1.setActivityTime(1200, 1900);
	    try {
	        a1.checkConflict(a2);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TH 12:00PM-7:00PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    
	    try {
	        a2.checkConflict(a1);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	    	assertEquals("TH 12:00PM-7:00PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	}

}
