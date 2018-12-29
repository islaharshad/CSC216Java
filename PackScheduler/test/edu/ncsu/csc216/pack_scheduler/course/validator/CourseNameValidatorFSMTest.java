/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/** A test class for the CourseNameValidatorFSM class. Tests both invalid and valid names
 * @author Jonathan Aloba
 *
 */
public class CourseNameValidatorFSMTest {

	/**Tests various invalid names for isValid, including misplaced characters and invalid characters 
	 */
	@Test
	public void testInvalidName() {
		CourseNameValidatorFSM validator = new CourseNameValidatorFSM();
		
		try {
			validator.isValid("$$$$$$");
			fail();
		} catch(InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		try {
			validator.isValid("9");
			fail();
		} catch(InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
		
		try {
			validator.isValid("$$$$$$");
			fail();
		} catch(InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		try {
			validator.isValid("abcde12");
			fail();
		} catch(InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
		
		try {
			validator.isValid("abcd1e");
			fail();
		} catch(InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		try {
			validator.isValid("abcd12e");
			fail();
		} catch(InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
//		try {
//			validator.isValid("abcd12a");
//			fail();
//		} catch(InvalidTransitionException e) {
//			assertEquals("Course name must have 3 digits.", e.getMessage());
//		}
		
		try {
			validator.isValid("abcd1234");
			fail();
		} catch(InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		
		try {
			validator.isValid("abcd123a1");
			fail();
		} catch(InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
		
		try {
			validator.isValid("abcd123ab");
			fail();
		} catch(InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		
		try {
			assertFalse(validator.isValid("abcd1"));
		} catch(InvalidTransitionException e) {
			fail();
		}
	}
	
	/** Test various valid names for isValid
	 */
	@Test
	public void testValidName() {
		CourseNameValidatorFSM validator = new CourseNameValidatorFSM();
		
		try {
			assertTrue(validator.isValid("a123"));
		} catch(InvalidTransitionException e) {
			fail();
		}
		
		try {
			assertTrue(validator.isValid("ab123"));
		} catch(InvalidTransitionException e) {
			fail();
		}
		
		try {
			assertTrue(validator.isValid("abc123"));
		} catch(InvalidTransitionException e) {
			fail();
		}
		
		try {
			assertTrue(validator.isValid("abcd123"));
		} catch(InvalidTransitionException e) {
			fail();
		}
		
		try {
			assertTrue(validator.isValid("abcd123e"));
		} catch(InvalidTransitionException e) {
			fail();
		}
		
	}

}
