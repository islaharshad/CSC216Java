package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
/**
 * Class to perform IO operations with student records
 * @author Gianni Absillis (gabsill)
 * @author Jonathan Aloba (joaloba)
 *
 */
public class StudentRecordIO {

	/**
	 * Method to read an input file of student records
	 * @param fileName the file to use as input, must be formatted properly
	 * @return A sorted list of Students with the same information as input
	 * @throws FileNotFoundException if file is not found
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		SortedList<Student> studentRecords = new SortedList<Student>();
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
			String hashedPwd = lineScan.next();
			Student s;
			try{
				int maxCredits = Integer.parseInt(lineScan.next());
				s = new Student(firstName, lastName, id, email, hashedPwd, maxCredits);
				studentRecords.add(s);
			} catch(IllegalArgumentException e){
				// skip line
			} catch(NoSuchElementException e){
				// skip line
			}

			lineScan.close();
		}
		scan.close();
		return studentRecords;
	}

	/**
	 * Method to write a file of student records
	 * @param fileName the file to output to
	 * @param studentDirectory A sorted list of students to generate output from
	 * @throws IOException if file cannot be written
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(fileName);
		for(int i = 0; i < studentDirectory.size(); i++){
			User s = studentDirectory.get(i);
			fileWriter.println(s.toString());
		}
		fileWriter.close();
		
	}

}
