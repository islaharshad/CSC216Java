package edu.ncsu.csc216.pack_scheduler.course.validator;

/**An subclass of the Exception class that is used for classes that model Finite State Machines
 * @author Wil Elias
 *
 */
public class InvalidTransitionException extends Exception {

	/**
	 * DefaultSerialVersionID
	 */
	private static final long serialVersionUID = 1L;

	/**Default constructor, calls the super with the message "Invalid FSM Transition."
	 */
	public InvalidTransitionException() {
		super("Invalid FSM Transition.");
	}
	
	/**A Constructor that calls the super constructor with the given message
	 * @param message - the message to be given when the exception object is thrown
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
}
