package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Test;


/**
 * Tests CourseNameValidatorFSM class
 * @author Premsubedi Edwerdo, Isla
 */
public class CourseNameValidatorFSMTest {

/** Test IsValid method */
	@Test
	public void testIsValid() throws InvalidTransitionException {
		CourseNameValidatorFSM c = null;
	
		c = new CourseNameValidatorFSM();
		
		try {
			c.isValid("0");	
		}
		
		 catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
			
		}
		
		try {
			c.isValid("@");	
		}
		
		 catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
			
		}
		
		
		
			
		assertFalse(c.isValid("L"));
		assertFalse(c.isValid("LL"));
		assertFalse(c.isValid("LLL"));
		assertFalse(c.isValid("LLLL"));
		
		try {
			c.isValid("LLLLL");	
		}
		
		 catch (InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
			
		}
				
	
		assertFalse(c.isValid("LLLL1"));
		assertFalse(c.isValid("LLLL12"));
		assertTrue(c.isValid("LLLL123"));
		try {
			c.isValid("LLLL123");	
		}
		
		 catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
			
		}
		assertTrue(c.isValid("CSC123"));
		assertTrue(c.isValid("CD123"));
		assertTrue(c.isValid("E115"));
		
		
		assertTrue(c.isValid("MAE115E"));
		
		try {
			c.isValid("CSC1123");
			
		}
		catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		
		try {
			c.isValid("MAE115EA");
			
		}
		catch (InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		
		try {
			c.isValid("MAE115A4");
			
		}
		catch (InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
		
		try {
			c.isValid("CSC2A6");
			
		}
		catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		try {
			c.isValid("CSC26A");
			
		}
		catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
	}
}
