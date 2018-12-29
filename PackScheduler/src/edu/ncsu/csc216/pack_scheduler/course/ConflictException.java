package edu.ncsu.csc216.pack_scheduler.course;
/**
 * The exception thrown when a scheduling conflict occurs
 * @author gabsill
 *
 */
public class ConflictException extends Exception {

	/**
	 * ID used for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates an Exception with the default message Schedule conflict.
	 */
	public ConflictException(){
		super("Schedule conflict.");
	}
	
	/**
	 * Creates an exception with a custom message
	 * @param message the message to attach to the exception
	 */
	public ConflictException(String message){
		super(message);
	}
}
