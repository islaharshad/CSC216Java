/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * 
 * The class event is a
 * subclass of activity
 * 
 * @author Islahuddin Arshad
 *
 */
public class Event extends Activity {

	/** How many times and event is repeated */
	private int weeklyRepeat;
	/** The event's details*/
	private String eventDetails;
	
	
	/**
	 * Constructs an event class which is then
	 * over-ruled by the its parent (Activity.java)
	 * class
	 *@param title of the event
	 *@param meetingDays meeting days of the event
	 *@param startTime start time of the event
	 *@param endTime end time of the event
	 *@param weeklyRepeat how many times and event can be repeated
	 *@param eventDetails the details of the event
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setWeeklyRepeat(weeklyRepeat);
		setEventDetails(eventDetails);
		setMeetingDays(meetingDays);
	}
	
	/**
	 * Event method that checks whether 
	 * an activity is recurring or not
	 *@param activity the activity that 
	 *can be duplicate or not
	 *@return boolean if activity is duplicate or not
	 */
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Event) {
			return activity.getTitle().equals(this.getTitle());
		}
		return false;
	}

	/**
	 * Returns how many times an event is repeated
	 * @return the weeklyRepeat
	 */
	public int getWeeklyRepeat() {
		return weeklyRepeat;
	}


	/**
	 * Checks whether the weekly repeat is less than
	 * 1 or greater than a 4
	 * if yes, throws an IllegalArgumentException
	 * @param weeklyRepeat the weeklyRepeat to set
	 * @throws IllegalArgumentException if weeklyRepeat is less
	 * than 1 or greater than 4
	 */
	public void setWeeklyRepeat(int weeklyRepeat) {
		
		if (weeklyRepeat < 1 || weeklyRepeat > 4) {
			throw new IllegalArgumentException("Invalid Weekly Repeat");
		}
		
		this.weeklyRepeat = weeklyRepeat;
	}



	/**
	 * Sets the meeting days as a name.
	 * If any character equals 'A' throws IllegalArgumentException.
	 * @param meetingDays the meeting days to set
	 * @throws IllegalArgumentException if any other 
	 * character is 'A'
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")){
			throw new IllegalArgumentException();
		}
			char m = meetingDays.charAt(0);
			if (m == 'A') {
				throw new IllegalArgumentException();
			}
			super.setMeetingDays(meetingDays);
		}
		
	
 
	

	/**
	 * Returns the event details
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}



	/**
	 * Adds "every X week" to the meetingString
	 * X represents the number of time the event 
	 * will take place in the week
	 * @return String the print of meeting string
	 */
	@Override
	public String getMeetingString() {
		// TODO Auto-generated method stub
		return super.getMeetingString() + " (every " + getWeeklyRepeat() + " weeks)";
	}

   

	/**
	 *Prints to-String separated by commas
	 *@return String the toString representation
	 */
	@Override
	public String toString() {
		
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + getWeeklyRepeat() + "," + getEventDetails();
	}



	/**
	 * Tests whether an event is null or an empty string
	 * if yes, throw illegal argument exception
	 * @param eventDetails the eventDetails to set
	 * @throws IllegalArgumentException if eventDetails are 
	 * an empty string or a null
	 */
	public void setEventDetails(String eventDetails) {
		
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details");
		}
		this.eventDetails = eventDetails;
	}


	/**
	 * Gives a string array representation of title and 
	 * the meeting string
	 * @return String[] the string array of short description
	 */
	@Override
	public String[] getShortDisplayArray() {
		
		String[] shortString = new String[4];
		shortString[0] = "";
		shortString[1] = "";
		shortString[2] = getTitle();
		shortString[3] = getMeetingString();
		return shortString;
	}

	/**
	 * Gives a string array representation of title, 
	 * the meeting string, and the event details
	 * @return String[] the string array of long description
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longString = new String[7];
		longString[0] = "";
		longString[1] = "";
		longString[2] = getTitle();
		longString[3] = "";
		longString[4] = "";
		longString[5] = getMeetingString();
		longString[6] = getEventDetails();
		return longString;
	}

}
