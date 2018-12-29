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
public class Course extends Activity {
	
	
	
	/** Course's name. */
	private String name; 
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	
	
	
	/**
	 * Course method that checks whether 
	 * an activity is recurring or not
	 *@param activity the activity that 
	 *can be duplicate or not
	 *@return boolean whether an activity is duplicate or not
	 */
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course c = (Course) activity;
            return c.getName().equals(this.getName());
		}
		return false;
	}
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
	 * Sets the meeting days as a name.
	 * If any character equals 'U' or 'S' throws IllegalArgumentException.
	 * @param meetingDays the meeting days to set
	 * @throws IllegalArgumentException if any other 
	 * character is 'U' or 'S'
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			char m = meetingDays.charAt(i);
			if (m == 'U' || m == 'S') {
				throw new IllegalArgumentException();
			}
		}
		
		super.setMeetingDays(meetingDays);
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
		super(title, meetingDays, startTime, endTime);
		setName(name);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	   
	   
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
	
	@Override
	/**
	 * displays name, section, and title of the course
	 * @return String[] returns the string array of the given fields
	 */
    public String[] getShortDisplayArray() {
		String[] displayShort = new String[4];
		displayShort[0] = getName();
		displayShort[1] = getSection();
		displayShort[2] = getTitle();
		displayShort[3] = getMeetingString();
		return displayShort;
	}

	/**
	 * displays name, section, title, credits, instructor id, and meeting string
	 * @return String[] returns the string array of the given fields
	 */
	public String[] getLongDisplayArray() {
		String[] displayLong = new String[7];
		displayLong[0] = getName();
		displayLong[1] = getSection();
		displayLong[2] = getTitle();
		displayLong[3] = Integer.toString(getCredits());
		displayLong[4] = getInstructorId();
		displayLong[5] = getMeetingString();
		displayLong[6] = "";
		return displayLong;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
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
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (getMeetingDays().equals("A")) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + getSection() + "," + credits + "," + instructorId + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
	}


}
