/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The conflict interface hosts the abstract method
 * checkMethod
 * @author Islahuddin Arshad
 *
 */
public interface Conflict {


    /** 
     * Checks whether an activity object is conflicted with another
     * activity object
     * @param possibleConflictingActivity a possible activity
     * object that might interfere in terms of days and timings
     * @throws ConflictException if conflict with day and time found
     */
    void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}

