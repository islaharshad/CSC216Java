package edu.ncsu.csc216.pack_scheduler.course;

import java.util.regex.Pattern;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Class to model a course and perform actions to alter it's state
 * @author Gianni Absillis (gabsill)
 *
 */
public class Course extends Activity implements Comparable<Course> {
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Validates Course names */
	private CourseNameValidator validator = new CourseNameValidator();
	/** The Roll of students currently enrolled in this course section */
	private CourseRoll roll;
	/** The maximum number of students allowed in this course section */
	private int enrollmentCap = 0;
	
	/**
	 * Gets the name of the course
	 * @return the name of the course
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the course
	 * @param name the name to set the course to
	 */
	private void setName(String name) {
		if(name == null){ throw new IllegalArgumentException(); }
		if(name.equals("")){ throw new IllegalArgumentException(); }
		try{
			if(validator.isValid(name))
				this.name = name;
			else
				throw new IllegalArgumentException();
		} catch(InvalidTransitionException e){
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * Gets the Section for the course
	 * @return the Section of the course
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Section for the course
	 * @param section the Section to set to
	 */
	public void setSection(String section) {
		if(section == null){
			throw new IllegalArgumentException();
		}
		if(section.length() != 3){
			throw new IllegalArgumentException();
		}
		if(Character.isDigit(section.charAt(0)) 
				&& Character.isDigit(section.charAt(1))
				&& Character.isDigit(section.charAt(2))){
			this.section = section;
		} else {
			throw new IllegalArgumentException();
		}
		
	}

	/**
	 * Gets the credit hours for the course
	 * @return the number of credit hours attributed to the course
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the credit hours for the course
	 * @param credits the amount of credit hours to set the course to
	 */
	public void setCredits(int credits) {
		if(credits < 1 || credits > 5){
			throw new IllegalArgumentException();
		}
		this.credits = credits;
	}

	/**
	 * Gets the ID of the instructor associated with this course
	 * @return the ID of the instructor
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the ID of the instructor associated with this course
	 * @param instructorId the id of the instructor for this course
	 */
	public void setInstructorId(String instructorId) {
//		if (instructorId == null) {
//			///do nothing
//		}
//		
		try {
			if(instructorId.equals("")){
				throw new IllegalArgumentException();
			}	
		} catch(NullPointerException e) {
			//do nothing
		}
		
		this.instructorId = instructorId;
		
		
	}

	/**
	 * Constructs a Course object with filled in fields
	 * @param name Course name
	 * @param title Course title
	 * @param section Course section
	 * @param credits credit hours for course
	 * @param instructorId instructor's unity ID
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime start time for Course
	 * @param endTime end time for Course
	 * @param enrollmentCap max students in the course section
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	    this.enrollmentCap = enrollmentCap;
	    roll = new CourseRoll(this, this.enrollmentCap);
	}

	/**
	 * Generates a hash code for the object
	 * @return hash code for the instance
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

	/**
	 * Checks equality
	 * @param obj the object to compare to
	 * @return if obj is equal to this instance
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

	/**
	 * Creates a course with name, title, section, credits, insstructorID, and meeting days
	 * @param name Course name
	 * @param title Course title
	 * @param section Course section
	 * @param credits Credit hours for course
	 * @param instructorId Unity ID of instructor
	 * @param meetingDays meeting days for course as a series of chars
	 * @param enrollmentCap maximum students enrolled in this course section
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}


	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString(){
		if(getMeetingDays().equals("A")){
			return name + "," + getTitle() + "," + section + "," + credits + ","
					+ instructorId + "," + enrollmentCap + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," 
			+ credits + "," + instructorId + "," + enrollmentCap + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * Returns basic information about the course
	 * @return name, section, title, enrollmentCap, and meetingDays string
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] a = new String[5];
		a[0] = getName();
		a[1] = getSection();
		a[2] = getTitle();
		a[3] = getMeetingString();
		a[4] = getCourseRoll().getOpenSeats() + "";
		return a;
	}

	/**
	 * Compiles more extensive information about the course
	 * @return name, section, title, credits, instructorID, meetingDays string and empty string
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] a = new String[7];
		a[0] = getName();
		a[1] = getSection();
		a[2] = getTitle();
		a[3] = Integer.toString(getCredits());
		a[4] = getInstructorId();
		a[5] = getMeetingString();
		a[6] = "";
		return a;
	}
	
	/**
	 * Course specific time setter for test cases
	 * @param x startTime
	 * @param y endTime
	 */
	public void setCourseTime(int x, int y){
		setActivityTime(x, y);
	}

	/**
	 * Sets the meeting days for the Course must be a day of the week or "A" for Arranged
	 * @param meetingDays the days of the week to meet
	 * @throws IllegalArgumentException if letters are not representative of week days or a lone A
	 */
	@Override
	public void setMeetingDays(String meetingDays) throws IllegalArgumentException{
		if(meetingDays == null){
			throw new IllegalArgumentException();
		}
		if(meetingDays.equals("")){
			throw new IllegalArgumentException();
		}
		
		if(Pattern.compile("[a-zA-Z&&[^MTWHFA]]").matcher(meetingDays).find()){
			throw new IllegalArgumentException();
		}
		if(Pattern.compile("[A]").matcher(meetingDays).find() &&
				Pattern.compile("[a-zB-Z]+").matcher(meetingDays).find()){
			throw new IllegalArgumentException();
		}
		super.setMeetingDays(meetingDays);
	}

	/**
	 * Checks if this course is a the same as the argument
	 * @param activity the activity to compare to
	 * @return if they are duplicates
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if(activity instanceof Course){
			Course c = (Course) activity;
			if(c.getName().equals(this.getName())){
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	/**
	 * compares two courses first by name then by section
	 * @param c the course to compare with
	 * @return less than 0 if it should be ordered before, greater than 0 if after, 0 if they are the same
	 * @throws IllegalArgumentException if arg0 is null
	 */
	@Override
	public int compareTo(Course c) throws IllegalArgumentException{
		if(c == null){
			throw new IllegalArgumentException("Comparing to null");
		}
		if(this.getName().compareTo(c.getName()) < 0){
			return -1;
		} else if(this.getName().compareTo(c.getName()) > 0) {
			return 1;
		} else if(Integer.parseInt(this.getSection()) < Integer.parseInt(c.getSection())){
			return -1;
		} else if(Integer.parseInt(this.getSection()) > Integer.parseInt(c.getSection())){
			return 1;
		} else {
			return 0;
		}
	}
	
	/** Returns the CourseRoll object associated with this course
	 * @return roll - the CourseRoll object
	 */
	public CourseRoll getCourseRoll() {
		return this.roll;
	}
}
