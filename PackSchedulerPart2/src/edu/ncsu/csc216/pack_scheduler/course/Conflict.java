/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Interface for things that could have conflicts has a method to check if there
 * are conflicts
 * @author gabsill
 *
 */
public interface Conflict {

	/**
	 * Checks if there is a conflict in time with another Activity
	 * @param possiblyConflictingActivity the activity that may conflict
	 * @throws ConflictException if there is a conflict
	 */
	void checkConflict(Activity possiblyConflictingActivity) throws ConflictException;
}
