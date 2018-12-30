package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests InvalidTransitionException
 * @author premsubedi
 *
 */
public class InvalidTransitionExceptionTest {

	/**
	 * Tests InvalidTransitionException method
	 */
	@Test
	public void testInvalidTransitionException() {
	InvalidTransitionException ite = new InvalidTransitionException();
	assertEquals("Invalid FSM Transition.", ite.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.ConflictException#ConflictException(java.lang.String)}.
	 */
	@Test
	public void testInvalidTransitionExceptionString() {
		InvalidTransitionException ite = new InvalidTransitionException("Custom message");
		assertEquals("Custom message", ite.getMessage());
	}

}
