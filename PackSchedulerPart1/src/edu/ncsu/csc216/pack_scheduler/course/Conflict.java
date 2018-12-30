/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * An interface 
 * @author premsubedi
 */
public interface Conflict {
	/** 
	 * This is an abstract method that throws conflict exception
	 * @param possibleConflictingActivity activity that has possibility of getting conflict with another
	 * @throws ConflictException it's an exception if there is a conflict.
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
	
	

}
