/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**A test class for the InvalidTransitionException class, tests both constructors
 * @author Addison Garrigus
 * @author Jonathan Aloba
 *
 */
public class InvalidTransitionExceptionTest {

	/** Tests both the default and 1 argument constructors by comparing the messages
	 */
	@Test
	public void test() {
		InvalidTransitionException e = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", e.getMessage());
		
		e = new InvalidTransitionException("TestMessage");
		assertEquals("TestMessage", e.getMessage());
	}

}
