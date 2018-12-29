/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/** 
 * Tests activity class
 * @author Islahuddin Arshad
 *
 */
public class ActivityTest {


	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.Activity#checkConflict(edu.ncsu.csc216.wolf_scheduler.course.Activity)}.
	 */
	@Test
	public void testCheckConflict() {
	    Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "TH", 1330, 1445);
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
	}
	
	
	/**
	 * Tests if exception is thrown if the days are different 
	 * but the time is the same for the two activities
	 * @throws ConflictException checked exception thrown if 
	 * start or end time of any activity are the same if  
	 * any of the meeting day is the same
	 */
	@Test
	public void testCheckSameTimeDiffDays() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "TH", 1330, 1445);
		
		try {
			a1.checkConflict(a2);
		}
		catch(ConflictException e) {
			assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
			System.out.println(a1.getMeetingString());
		    assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
		
	}

	
	/**
	 * Tests if exception is thrown if at least of the day is the same
	 * but the time is different
	 * @throws ConflictException checked exception thrown if 
	 * start or end time of any activity are the same if 
	 * any of the meeting day is the same
	 */
	@Test
	public void testCheckSameDayDiffTime() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "TH", 1330, 1445);
		
		a1.setMeetingDays("TW");
		a2.setActivityTime(1500, 1650);
		
		try {
			a1.checkConflict(a2);
		}
		catch(ConflictException e) {
			assertEquals("TW 1:30PM-2:45PM", a1.getMeetingString());
		    assertEquals("TH 3:00PM-4:50PM", a2.getMeetingString());
		}
	}

	/**
	 * Tests if exception is thrown if the days are exactly the same
	 * and the end time of one activity is the start time of another
	 * @throws ConflictException checked exception thrown if 
	 * start or end time of any activity are the same if the 
	 * any of the meeting day is the same
	 */
	@Test
	public void testSameEndTimeAndSameStartTime1() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "TH", 1330, 1445);
		
		a1.setMeetingDays("TH");
		a2.setActivityTime(1445, 1500);
	
	    try {
	    	a1.checkConflict(a2);
	    }
	    catch(ConflictException e) {
	    	assertEquals("TH 1:30PM-2:45PM", a1.getMeetingString());
		    assertEquals("TH 2:45PM-3:00PM", a2.getMeetingString());
	    }
	}

	/**
	 * Tests if exception is thrown if at least one the
	 * days is the same and end time of one activity is the same
	 * as the start time of another 
	 * @throws ConflictException checked exception thrown if 
	 * start or end time of any activity are the same if  
	 * any of the meeting day is the same
	 */
	@Test
	public void testSameEndTimeAndSameStartTime2() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "TH", 1330, 1445);
		
		a1.setMeetingDays("MWT");
		a2.setMeetingDays("WTF");
		a1.setActivityTime(1300, 1430);
		a2.setActivityTime(1430, 1500);
	
	    try {
	    	a1.checkConflict(a2);
	    }
	    catch(ConflictException e) {
	    	assertEquals("MWT 1:00PM-2:30PM", a1.getMeetingString());
		    assertEquals("WTF 2:30PM-3:00PM", a2.getMeetingString());
	    }
	}

	/**
	 * Tests if exception is thrown if the dates are the same 
	 * and on the same day
	 * @throws ConflictException checked exception thrown if 
	 * start or end time of any activity are the same if 
	 * any of the meeting day is the same
	 */
	@Test
	public void testSameStartTimeAndSameEndTime() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "TH", 1330, 1445);
		
		a1.setMeetingDays("M");
		a2.setMeetingDays("M");
		a1.setActivityTime(1300, 1430);
		a2.setActivityTime(1300, 1430);
	
	    try {
	    	a1.checkConflict(a2);
	    }
	    catch(ConflictException e) {
	    	assertEquals("M 1:00PM-2:30PM", a1.getMeetingString());
		    assertEquals("M 1:00PM-2:30PM", a2.getMeetingString());
	    }
	}
	/**
	 * Tests if exception is thrown if at least one day is
	 * the same and start times of the activity are the same 
	 * and on the same day
	 * @throws ConflictException checked exception thrown if 
	 * start or end time of any activity are the same if  
	 * any of the meeting day is the same
	 */
	
	@Test
	public void testSameStartTime() {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "TH", 1330, 1445);
		
		a1.setMeetingDays("WF");
		a2.setMeetingDays("MF");
		a1.setActivityTime(1300, 1530);
		a2.setActivityTime(1300, 1430);
	
	    try {
	    	a1.checkConflict(a2);
	    }
	    catch(ConflictException e) {
	    	assertEquals("WF 1:00PM-3:30PM", a1.getMeetingString());
		    assertEquals("MF 1:00PM-2:30PM", a2.getMeetingString());
	    }
	}
}

	
		
		
		
		
		
		
		
		
		
		
		
		

	