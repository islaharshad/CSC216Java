package edu.ncsu.csc216.wolf_scheduler.course;

//import java.io.FileNotFoundException;

/**
 * The abstract class activity 
 * is the super class that handles 
 * course.java and event.java
 * 
 * @author Islahuddin Arshad
 *
 */
public abstract class Activity {
//	/** Max credits. */
//	private static int MAX_CREDITS = 5;
//	/** Min credits. */
//	private static int MIN_CREDITS = 1;
//	/** Upper time. */
//	private static int UPPER_TIME = 2400;
//	/** Upper hour. */
//	private static int UPPER_HOUR = 60;

	
	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Creates the activity constructor
	 *@param title of the activity
	 *@param meetingDays meeting days of the activity
	 *@param startTime start time of the activity
	 *@param endTime end time of the activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
	}
	
	/**
	 * Abstract method checks whether 
	 * an activity is recurring or not
	 *@param activity the activity that 
	 *can be duplicate or not
	 *@return boolean whether the activity is a duplicate or not
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	/** 
	 *Abstract method that creates a short string array
	 *of the activity
	 *@return String[] the string array of short display
	 */
	public abstract String[] getShortDisplayArray(); 

	/**
	 *Abstract method that creates a long string array
	 *of the activity
	 *@return String[] the string array of long display
	 */
	public abstract String[] getLongDisplayArray(); 
	
	/**
	 * Returns the Title's name.
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Title's name. If the title is null, an
	 * IllegalArgumentException is thrown
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title is null or empty string
	 */
	public void setTitle(String title) {
		if (title == null || title.equals("")) {
	        throw new IllegalArgumentException();
	    }
		this.title = title;
	}

	/**
	 * Returns the meeting days.
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}
	
	

	/**
	 * Sets the meeting days as a name.
	 * If any other character than 'M','T','W',
	 * 'H','F', or A, throws IllegalArgumentException.
	 * But if 'A' is in the meeting list, it must be 
	 * the only character.
	 * @param meetingDays the meeting days to set
	 * @throws IllegalArgumentException if any other 
	 * character than 'M','T','W', 'H','F', or A
	 * present, or if 'A' is present, there are other
	 * letters present with it
	 */
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			char m = meetingDays.charAt(i);
			if (m == 'M' || m == 'T' || m  == 'W' || m == 'H'
					|| m == 'F' || m == 'S' || m == 'U') {
				continue;
			}
			else if (m == 'A') {
				if (i < 0 || i >= 1) {
					throw new IllegalArgumentException();
				}
			}
			else {
				throw new IllegalArgumentException();
			}
		}
	
	
		this.meetingDays = meetingDays;
	}

	/**
	 * Returns the start time.
	 * @return the startTime.
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the end time.
	 * @return the endTime.
	 */
	public int getEndTime() {
		return endTime;
	}

	

	/**
	 * Sets the start time and end time of the class.
	 * If startTime or endTime are less than 0000 or
	 * greater than 2359, IllegalArgumenyException
	 * is thrown. If the meeting time is 'A', the 
	 * start and end time are both set to 0.
	 * @param startTime the start time to set
	 * @param endTime the end time to set
	 * @throws IllegalArgumentException if 
	 * startTime or endTime are less than 0000 or
	 * greater than 2359
	 */
	public void setActivityTime(int startTime, int endTime) {
		
		int hourStart = startTime / 100;
		int hourEnd = endTime / 100;
		
		int minuteStart = startTime % 100;
		int minuteEnd = endTime % 100;
		
		if (getMeetingDays().equals("A")) {
			startTime = 0;
			endTime = 0;
		}
		if (hourStart < 0 || hourStart >= 24) {
			throw new IllegalArgumentException();
		}
		if (hourEnd < 0 || hourEnd >= 24) {
			throw new IllegalArgumentException();
		}
		if (minuteStart < 0 || minuteStart >= 60) {
			throw new IllegalArgumentException();
		}
		if (minuteEnd < 0 || minuteEnd >= 60) {
			throw new IllegalArgumentException();
		}
		if (hourStart > hourEnd) {
			throw new IllegalArgumentException();
		}
		
		
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Based on whether a course follows a normal
	 * time or is just assigned, a string is returned 
	 * containing the course information
	 * @return String the string of meeting info
	 */
	public String getMeetingString() {
	
		int hourStart = startTime / 100;
		int hourEnd = endTime / 100;
	
		int minuteStart = startTime % 100;
		int minuteEnd = endTime % 100;
	
		String zeroS = "";
		String zeroE = "";
	
		if (minuteStart < 10) {
			zeroS = "0";
		}
	
		if (minuteEnd < 10) {
			zeroE = "0";
		}
		String amTimeS = "";
		String pmTimeS = "";
	
		if (hourStart < 12) {
			amTimeS = "AM";
		} else {
			pmTimeS = "PM";
		}
	
		String amTimeE = "";
		String pmTimeE = "";
	
		if (hourEnd < 12) {
			amTimeE = "AM";
		} else {
			pmTimeE = "PM";
		}
	
		if (hourStart > 12) {
			hourStart = hourStart - 12;
	
		}
	
		if (hourEnd > 12) {
			hourEnd = hourEnd - 12;
	
		}
		String time = "";
		if (getMeetingDays().equals("A")) {
			time = "Arranged";
		}
	
		else {
			time = getMeetingDays() + " " + hourStart + ":" + minuteStart + zeroS + pmTimeS + amTimeS + "-" + hourEnd
					+ ":" + minuteEnd + zeroE + pmTimeE + amTimeE;
			
		}
		
		return time;
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


	

}