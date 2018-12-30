/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * This is a sub/child class of super class Exception that throws conflict exception
 * @author premsubedi, isla arshad
 */
public class ConflictException extends Exception {
	

	/**
	 * serialVersionUID helps the classes to 
	 * @serialVersionUID  is a class constant.
	 */
	private static final long serialVersionUID = 1L;

	/** Parameterless default constructor for conflictException */
	public ConflictException() {
		super("Schedule conflict.");
	}
	
	/** 
	 * Parameterized constructor for ConflictException 
	 * @param message exception message, when there is conflict
	 */
	public ConflictException(String message) {
		super(message);
	}
}
