/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Buils the conflictexception constructor with 
 * a default name or a given message
 * @author Islahuddin Arshad
 *
 */
public class ConflictException extends Exception {

	/**
     * Constructs a conflictException exception class that
     * can carry given message
     * @param message the message given
     */
	public ConflictException(String message) {
		super(message);
		
		
	}
	
	/**
     * Constructs a conflictException exception class that
     * carries a default message
     */
	public ConflictException() {
		super("Schedule conflict.");
	
		
	}
	
	/**
	 * ID used for serialization
	 */
	private static final long serialVersionUID = 1L;

}
