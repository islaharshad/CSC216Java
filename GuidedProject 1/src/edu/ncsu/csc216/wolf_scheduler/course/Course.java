/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * 
 *Establishes the course class 
 * 
 * @author Islahuddin Arshad
 */


public class Course {
	
	/** Course's name. */
	private String name;
	/** Course's title. */
	private String title;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	
	/**
	 * Returns the Course's name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the Course's name.  If the name is null, has a length less than 4 or 
	 * greater than 6, an IllegalArgumentException is thrown.
	 * @param name the name to set
	 * @throws IllegalArgumentException if name is null or length is less than 4 or 
	 * greater than 6
	 */
	private void setName(String name) {
	    if (name == null) {
	        throw new IllegalArgumentException();
	    }
	    if (name.length() < 4 || name.length() > 6) {
	        throw new IllegalArgumentException();
	    }
	    this.name = name;
	}
	
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
	 * Returns the Section's name.
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	
	/**
	 * Sets the Section's name. If the section is not 
	 * exactly three digits (three chars), an 
	 * IllegalArgumentException is thrown 
	 * @param section the section to set
	 * @throws IllegalArgumentException if section name is not
	 * three digits long
	 */
	public void setSection(String section) {
		if (section == null || section.equals("")) {
			throw new IllegalArgumentException();
		}
		if (section.length() != 3) {
			throw new IllegalArgumentException();
		}
		this.section = section;
	}
	
	/**
	 * Returns the credits.
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	
     /**
	 * Sets the number of credits. If credits is
	 * not a number or if credit hours are less than
	 * 1 or greater than 5, throws IllegalArgumentException
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credit not a number
	 * or less than 1 or greater than 5
	 */
	public void setCredits(int credits) {
		if (credits < 1 || credits > 5) {
			throw new IllegalArgumentException();
		}
		this.credits = credits;
	}
	
	/**
	 * Returns the instructor's ID.
	 * @return String the instructorID
	 */
	public String getInstructorId() {
		return instructorId;
	}
	
	/**
	 * Sets the instructor's id. If the 
	 * instructor's id is null or an empty string,
	 * throws IllegalArgumentException
	 * @param instructorId the instructorID to set
	 * @throws IllegalArgumentException if instructor id 
	 * is null or an empty string
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.equals("")) {
	        throw new IllegalArgumentException();
	    }
		this.instructorId = instructorId;
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
					|| m == 'F') {
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
	public void setCourseTime(int startTime, int endTime) {
		
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

		if (hourStart >= 12) {
			hourStart = hourStart - 12;

		}

		if (hourEnd >= 12) {
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
	
	/**
	 * Constructs a course object with 
	 * start and end times
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours of course
	 * @param instructorId instructor's unity ID
	 * @param meetingDays meeting days for course as series of chars
	 * @param startTime start time for course
	 * @param endTime end time for course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		setName(name);
	    setTitle(title);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	    setMeetingDays(meetingDays);
	    setCourseTime(startTime, endTime);
	   
	}


	/**
	 * Constructs a course object with no start
	 * or end times
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours of course
	 * @param instructorId instructor's unity ID
	 * @param meetingDays meeting days for course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);

	}
	
	
	

	/**
	 * Generates a hashCode for Course using all fields
	 * @return hasCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + credits;
		result = prime * result + endTime;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality to all fields
	 * @param obj the Object to compare
	 * @return true if the objects are same in all the fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (endTime != other.endTime)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
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

	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (meetingDays.equals("A")) {
			return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays;
		}
		return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays + "," + startTime + "," + endTime;
	}


}
