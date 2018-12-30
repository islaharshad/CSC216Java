package edu.ncsu.csc216.pack_scheduler.course;

import java.util.regex.Pattern;

/**
 * Class to compose represent an activity will make courses and events
 * @author Gianni
 *
 */
public abstract class Activity implements Conflict {

	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Constructor
	 * @param title title of the activity
	 * @param meetingDays the days the activity meets
	 * @param startTime the start time
	 * @param endTime the end time
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
	}
	
	/**
	 * Checks to see if an activity is a duplicate course or event
	 * @param activity the activity to check
	 * @return if it has the same name as an existing activity of the same type
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Displays information about the activity in an array length of 4
	 * @return 4 items of information about the Activity
	 */
	public abstract String[] getShortDisplayArray();
	/**
	 * Displays information about the activity in an array length of 7
	 * @return 7 items of information about the Activity
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * Gets the title of the course
	 * @return the title of the course
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Generates a hash code
	 * @return the hash code for this instance
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * checks for equality
	 * @param obj the object to compare to
	 * @return if obj is equal to this Activity
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Sets the title of the course
	 * @param title the title to set for the course
	 */
	public void setTitle(String title) {
		if(title == null){
			throw new IllegalArgumentException();
		}
		if(title.equals("")){
			throw new IllegalArgumentException();
		}
		this.title = title;
	}

	/**
	 * Gets a string representation of the days the course meets
	 * @return the days the course meets
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the meeting days
	 * @param meetingDays String representation of the meeting days for the course
	 */
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}

	/**
	 * Gets the start time for the course
	 * @return the start time of the course
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Gets the time the course ends
	 * @return integer representing the end time
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * gets the hours from military time
	 * @param time the military time
	 * @return hours
	 */
	private int militaryHrs(int time) {
		final int divider = 100;
		return time / divider;
	}

	/**
	 * gets the minutes from military time
	 * @param time the military time
	 * @return the minutes in the time
	 */
	private int militaryMins(int time) {
		final int moduloer = 100;
		return time % moduloer;
	}

	/**
	 * Checks to see if the time is a valid military time
	 * @param time the time to check
	 * @return if the integer is valid military time
	 */
	private boolean isValidTime(int time) {
		final int hrsMax = 23;
		final int minsMax = 59;
		int hrs = militaryHrs(time);
		int mins = militaryMins(time);
		return hrs >= 0 && hrs <= hrsMax && mins >= 0 && mins <= minsMax;
	}

	/**
	 * Sets the course start and end times
	 * @param startTime the time to start the course
	 * @param endTime the time to end the course
	 */
	public void setActivityTime(int startTime, int endTime) {
		if(!isValidTime(startTime) || !isValidTime(endTime)){
			throw new IllegalArgumentException();
		}
		if(startTime > endTime){
			throw new IllegalArgumentException();
		}
		if(meetingDays.equals("A") && startTime != 0  && endTime != 0){
			throw new IllegalArgumentException();
		}
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Creates a normal time string out of military time
	 * @param time military time
	 * @return the normal time
	 */
	private String timeNotation(int time) {
		int hrs = militaryHrs(time);
		int mins = militaryMins(time);
		String newTime = hrs % 12 + ":" + String.format("%02d", mins);
		if(hrs > 12){
			newTime += "PM";
		} else if(hrs == 12){
			newTime = hrs + ":" + String.format("%02d", mins) + "PM";
		} else {
			newTime += "AM";
		}
		return newTime;
	}

	/**
	 * Gets a string representation of meeting days and times
	 * @return meetingdays startTime and endTime in form "days start-end"
	 */
	public String getMeetingString() {
		if(meetingDays.equals("A")){
			return "Arranged";
		}
		return meetingDays + " " + timeNotation(startTime) + "-" + timeNotation(endTime);
	}
	
	/**
	 * Checks if there is a conflict between activities
	 * @param possiblyConflictingActivity the activity that may conflict with this one
	 * @throws ConflictException if there is a scheduling conflict
	 */
	@Override
	public void checkConflict(Activity possiblyConflictingActivity) throws ConflictException {
		if(!("A".equals(this.getMeetingDays()) || "A".equals(possiblyConflictingActivity.getMeetingDays()))){
			// not a conflict
			String compare = this.getMeetingDays();
			String pattern = ".*[" + Pattern.quote(compare) + "].*";
			// Check if one string contains any character of another using regex
			if(
				Pattern.compile(pattern).matcher(possiblyConflictingActivity.getMeetingDays()).find()
				&&
				(
				this.getStartTime() >= possiblyConflictingActivity.getStartTime()
				&& this.getStartTime() <= possiblyConflictingActivity.getEndTime()
				||
				this.getEndTime() >= possiblyConflictingActivity.getStartTime()
				&& this.getEndTime() <= possiblyConflictingActivity.getEndTime()
				||
				this.getStartTime() <= possiblyConflictingActivity.getStartTime()
				&& this.getEndTime() >= possiblyConflictingActivity.getEndTime()
				)
			){
				throw new ConflictException();
				// everything else is not a conflict
			}
		}
	}

}