package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Class to perform IO operations with faculty records
 * @author Gianni Absillis (gabsill)
 * @author Jonathan Aloba (joaloba)
 * @author Samuel Ryne Ritter
 *
 */
public class FacultyRecordIO {

	/**
	 * Method to read an input file of faculty records
	 * @param fileName the file to use as input, must be formatted properly
	 * @return A linked list of Faculty with the same information as input
	 * @throws FileNotFoundException if file is not found
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		LinkedList<Faculty> facultyRecords = new LinkedList<Faculty>();
		File f = new File(fileName);
		FileInputStream fis = new FileInputStream(f);
		Scanner scan = new Scanner(fis);
		while(scan.hasNextLine()){
			// process line by line
			Scanner lineScan = new Scanner(scan.nextLine());
			lineScan.useDelimiter(",");
			String firstName = lineScan.next();
			String lastName = lineScan.next();
			String id = lineScan.next();
			String email = lineScan.next();
			Faculty s;
			try{
				String hashedPwd = lineScan.next();
				int maxCourses = Integer.parseInt(lineScan.next());
				s = new Faculty(firstName, lastName, id, email, hashedPwd, maxCourses);
				facultyRecords.add(s);
			} catch(IllegalArgumentException e){
				// skip line
			} catch(NoSuchElementException e){
				// skip line
			}

			lineScan.close();
		}
		scan.close();
		return facultyRecords;
	}
	
	/**
	 * Method to write a file of faculty records
	 * @param fileName the file to output to
	 * @param facultyDirectory A sorted list of faculty to generate output from
	 * @throws IOException if file cannot be written
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(fileName);
		for(int i = 0; i < facultyDirectory.size(); i++){
			User s = facultyDirectory.get(i);
			fileWriter.println(s.toString());
		}
		fileWriter.close();
		
	}
}
