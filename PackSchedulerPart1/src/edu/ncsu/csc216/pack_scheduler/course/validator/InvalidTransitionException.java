/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * This is unchecked Exception sub class that inherits behavior from Exception
 * @author premsubedi, Isla, Edwerdo
 */
public class InvalidTransitionException extends Exception {

	/**
	 * serialVersionUID helps the classes to 
	 * @serialVersionUID  is a class constant.
	 */
	private static final long serialVersionUID = 1L;

	/** Parameterless default constructor for conflictException */
	public InvalidTransitionException() {
		super("Invalid FSM Transition.");
	}
	
	/** 
	 * Parameterized constructor for ConflictException 
	 * @param message exception message, when there is conflict
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
}
