package edu.ncsu.csc216.pack_scheduler.course;

/**
 * This is an super class (common/generic class) which let's its sub-classes to use 
 * and implement its method based on their type
 * @author premsubedi
 */
public abstract class Activity implements Conflict {
	
	private static final int UPPER_TIME = 2359;
	private static final int UPPER_HOUR = 60;

	/** Activity's title */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;

	/**
	 * Activity constructor that constructs title, meeting days and activity time.
	 * @param title title of an activity
	 * @param meetingDays meeting days for an event
	 * @param startTime start time of an event
	 * @param endTime end time of an event
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		this.setTitle(title);
		this.setMeetingDays(meetingDays);
		this.setActivityTime(startTime, endTime);
	}


	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.wolf_scheduler.course.Conflict#checkConflict(edu.ncsu.csc216.wolf_scheduler.course.Activity)
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		if(this.getMeetingDays().equals("A") || possibleConflictingActivity.getMeetingDays().equals("A")) {
			return;
		}
		for(int i = 0; i < this.meetingDays.length(); i++) {
			if(possibleConflictingActivity.getMeetingDays().contains("" + this.meetingDays.charAt(i))) {
				if(possibleConflictingActivity.getStartTime() >= this.getStartTime()
				   && possibleConflictingActivity.getEndTime() <= this.getEndTime()) {
					throw new ConflictException("Schedule conflict.");
				}
				if (possibleConflictingActivity.getStartTime() <= this.getStartTime() 
					&& possibleConflictingActivity.getEndTime() >= this.getStartTime()) {
					throw new ConflictException("Schedule conflict.");
				}
				
				if (possibleConflictingActivity.getStartTime() <= this.getEndTime() 
						&& possibleConflictingActivity.getEndTime() >= this.getEndTime()) {
						throw new ConflictException("Schedule conflict.");
					}

			}
		}
	}

		/**
	 * Returns course title
	 * @return the titl
	 */
	public String getTitle() {
		return title;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Activity other = (Activity) obj;
		if (endTime != other.endTime) {
			return false;
		}
		if (meetingDays == null) {
			if (other.meetingDays != null) {
				return false;
			}
		} else if (!meetingDays.equals(other.meetingDays)) {
			return false;
		}
		if (startTime != other.startTime) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

	/**
	 * Sets Activity title
	 * @param title the course title
	 */
	public void setTitle(String title) {
		if (title == null || title.equals("")) {
			throw new IllegalArgumentException("Invalid title");
		}
		this.title = title;

	}

	/**
	 * Returns Activity's meeting days
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets Activity's meeting days
	 * 
	 * @param meetingDays the meeting days
	 */
	public void setMeetingDays(String meetingDays) {
		if(meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException();
		}
		this.meetingDays = meetingDays;
	}

	/** 
	 * This is a generic method that is used by all of its sub-classes and return consise string array
	 * of information based on their class type
	 * @return array array to be returned
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * This is a generic method that is used by all of its sub-classes and return detailed string array
	 * of information based on their class type
	 * @return array array to be returned
	 */
	public abstract String[] getLongDisplayArray();

	/** 
	 * This generic abstract method returns true or false based on the decision 
	 * if the sub-class object is duplicate or not.
	 * @param activity activity listed in the schedule.
	 * @return true returns true an object of sub-class is duplicate.
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * This methods returns the String representing day and time
	 * @return ret ret is a string representing day and time
	 */
	public String getMeetingString() {
		String ret = "";
		if (getMeetingDays().charAt(0) == 'A') {
			return "Arranged";
		}
		ret += getMeetingDays() + " ";
		int sTime = getStartTime();
		int eTime = getEndTime();
		boolean sTimeIsPM = false;
		if (getStartTime() >= 1200) {
			sTimeIsPM = true;
		}
		if (getStartTime() >= 1300) {
			sTime = getStartTime() - 1200;
		}
		if (sTime < 100) {
			sTime += 1200;
		}
		boolean eTimeIsPM = false;
		if (getEndTime() >= 1200) {
			eTimeIsPM = true;
		}
		if (getEndTime() >= 1300) {
			eTime = getEndTime() - 1200;
		}
		if (eTime < 100) {
			eTime += 1200;
		}
		ret += (sTime / 100) + ":";
		if ((sTime % 100) < 10) {
			ret += "0" + sTime % 100;
		} else {
			ret += (sTime % 100);
		}
		if (sTimeIsPM) {
			ret += "PM";
		} else {
			ret += "AM";
		}
		ret += "-";
		ret += (eTime / 100) + ":";
		if ((eTime % 100) < 10) {
			ret += "0" + eTime % 100;
		} else {
			ret += (eTime % 100);
		}
		if (eTimeIsPM) {
			ret += "PM";
		} else {
			ret += "AM";
		}

		return ret;
	}

	/**
	 * Returns Activity's starting time
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns activity's ending time
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the Activity's ending time
	 * @param startTime starting time of the Activity
	 * @param endTime time that the activity ends
	 */
	public void setActivityTime(int startTime, int endTime) {
		if (startTime < 0 || startTime > UPPER_TIME) {
			throw new IllegalArgumentException();
		}
		if (endTime < 0 || endTime > UPPER_TIME) {
			throw new IllegalArgumentException();
		}
		if (startTime > endTime) {
			throw new IllegalArgumentException("Invalid meeting times");
		}
		if (startTime % 100 >= UPPER_HOUR || endTime % 100 >= UPPER_HOUR) {
			throw new IllegalArgumentException();
		}
		if((meetingDays.charAt(0) == 'A' && startTime != 0) || (meetingDays.charAt(0) == 'A' && endTime != 0)){
			throw new IllegalArgumentException();
		}
		this.startTime = startTime;
		this.endTime = endTime;
	}

}