
package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * This is a course class
 * @author Prem Subedi , Isla Arshad
 */
public class Course extends Activity implements Comparable<Course> {

	/** Course's name */
	private String name;
	/** Course's section */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/**Used to check coursename*/
	private CourseNameValidator validator = new CourseNameValidator();
	/**CourseRoll to check student capacity etc.*/
	private CourseRoll roll;
	
	/**
	 * Course constructor constructs those fields
	 * @param name Course name	       
	 * @param title Course's title       
	 * @param section Course's section          
	 * @param credits Course's credit hour          
	 * @param instructorId Course's instructorId            
	 * @param meetingDays Course's meetingDays       
	 * @param startTime Course's starting time       
	 * @param endTime Course's ending time
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays, int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		roll = new CourseRoll(enrollmentCap);
		this.setName(name);
		this.setSection(section);
		this.setCredits(credits);
		this.setInstructorId(instructorId);
	}
	
	/**
	 * Course's name
	 * @param name course's name
	 * @param title course's title
	 * @param section course's section
	 * @param credits course's credits
	 * @param instructorId course's instructorId
	 * @param meetingDays course's meeting days
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			      String meetingDays) {

		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
		roll = new CourseRoll(enrollmentCap);
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.wolf_scheduler.course.Activity#setMeetingDays(java.lang.String)
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			if (meetingDays.charAt(i) != 'M' && meetingDays.charAt(i) != 'T' && meetingDays.charAt(i) != 'W'
					&& meetingDays.charAt(i) != 'H' && meetingDays.charAt(i) != 'F' && meetingDays.charAt(i) != 'A') {
				throw new IllegalArgumentException();
			}
			if (meetingDays.charAt(i) == 'A' && meetingDays.length() != 1) {
				throw new IllegalArgumentException();
			}
		}
		super.setMeetingDays(meetingDays);
	}

	/**
	 * Returns course name
	 * @return name the course name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is invalid, which
	 * is determined by the CourseNameValidator, an
	 * IllegalArgumentException is thrown. A name is valid
	 * only if it starts with 1-4 letters, followed by exactly 3 numbers,
	 * followed by an optional letter suffix.
	 * @param name the course name
	 * @throws IllegalArgumentException
	 * if name is null or length is less than 4 or greater than 6
	 */
	public void setName(String name) {
		
		if (name == null || name.equals("")) {
			throw new IllegalArgumentException("Invalid name");
		}
		try{
			if(!validator.isValid(name))
			{
				throw new IllegalArgumentException();
			}
		}
		
		catch(InvalidTransitionException e)
		{
			throw new IllegalArgumentException();
		}
		this.name = name;
	}

	/**
	 * Returns course section
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets course section
	 * @param section the Course's section
	 */
	public void setSection(String section) {
		if (section == null || section.equals("") || section.length() != 3) {
			throw new IllegalArgumentException("Invalid section");
		}
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException();
			}
		}
		this.section = section;
	}

	/**
	 * Returns the number of credit hours
	 * @return credits course credits hours
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the number of course's credit hours
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		if (credits < 1 || credits > 5) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.credits = credits;
	}

	/**
	 * Returns instructor unity Id
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets instructor unity Id
	 * @param instructorId the instructorId to set
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.equals("")) {
			throw new IllegalArgumentException("Invalid instructor Id");
		}
		this.instructorId = instructorId;
	}
	/**
	 * Gets the course roll for course
	 * @return roll the roll for course
	 */
	public CourseRoll getCourseRoll() {
		return roll;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (getMeetingDays().equals("A")) {
			return getName() + "," + getTitle() + "," + getSection() + "," + getCredits() + "," + getInstructorId() + "," 
		           + roll.getEnrollmentCap() + "," + getMeetingDays();
		}
		return getName() + "," + getTitle() + "," + getSection() + "," + getCredits() + "," + getInstructorId() + "," 
		       + roll.getEnrollmentCap() +  "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
	}

	@Override
	public String[] getShortDisplayArray() {
		String [] sArray = new String[5];
		sArray[0] = getName();
		sArray[1] = getSection();
		sArray[2] = getTitle();
		sArray[3] = getMeetingString();
		sArray[4] = "" + roll.getOpenSeats(); 
		
		return sArray;
	}

	@Override
	public String[] getLongDisplayArray() {
		String [] lArray  = new String[7];
		lArray[0] = getName();
		lArray[1] = getSection();
		lArray[2] = getTitle();
		lArray[3] = Integer.toString(getCredits());
		lArray[4] = getInstructorId();
		lArray[5] = getMeetingString();
		lArray[6] = "";
		return lArray;
	}

	@Override
	public boolean isDuplicate(Activity activity) {
		if(activity instanceof Course) {
			Course c = (Course) activity;
		if(c.getName().equals(this.name)) {
					return true;
				}
			}
		return false;
	}

	/**
	 * This method compares current course with the another course which passed as parameter.
	 */
	@Override
	public int compareTo(Course o) {
		if(this.getName().compareTo(o.getName()) < 0) {
			return -1;
		} else if(this.getName().compareTo(o.getName()) > 0) {
			return 1;	
		} else {
			if(this.getSection().compareTo(o.getSection()) < 0) {
				return -1;
			} else if (this.getSection().compareTo(o.getSection()) > 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
