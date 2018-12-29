package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;


/**
 *The class reads the 
 *activities 
 * @author Islahuddin Arshad
 */
public class ActivityRecordIO {
    
	/**
	 *Constructs the 
	 *activityrecordio constructor
	 */
	public ActivityRecordIO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Writes the given list of activities to 
	 * @param fileName the name of file from where 
	 * the course list is located
	 * @param activities the activities in the activity list
	 * @throws IOException thrown if no file to put
	 * print stream on
	 */
	public static void writeActivityRecords(String fileName, ArrayList<Activity> activities)throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		
		for (int i = 0; i < activities.size(); i++) {
		    fileWriter.println(activities.get(i).toString());
		}
	
		fileWriter.close();
	}

}
