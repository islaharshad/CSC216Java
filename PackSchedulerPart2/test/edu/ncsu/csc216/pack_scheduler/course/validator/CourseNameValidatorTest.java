/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**A test class CourseNameValidator class, testing both valid and invalid names for Courses
 * @author Jonathan Aloba
 * @author Addison Garrigus
 *
 */
public class CourseNameValidatorTest {

	/**Tests various invalid names for isValid, including misplaced characters and invalid characters 
	 */
	@Test
	public void testInvalidName() {
		CourseNameValidator validator = new CourseNameValidator();
		
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
		CourseNameValidator validator = new CourseNameValidator();
		
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

