package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**Class used in the directory to manage the SortedLists of students, contains methods to read and write
 * SortedLists of Students from/to a file
 * @author Samuel Ryne Ritter, Wil Elias, Prem Subedi
 *
 */
public class StudentRecordIO {
	
	/** Reads an SortedList of Students from a file, throwing a FileNotFoundException if the file does not exist
	 * @param fileName - the file to be read
	 * @return - the SortedList of the information from the file
	 * @throws FileNotFoundException - in the case that an invalid file is inputed
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Student> students = new SortedList<Student>();
		while(fileReader.hasNextLine())
		{
			try
			{	
				Student stud = readStudent(fileReader.nextLine());
				boolean dupl = false; 
				for(int x = 0; x < students.size(); x++)
				{
					if(students.get(x).getId().equals(stud.getId()))
						dupl = true;
					
				}
				if(!dupl)
				{
					students.add(stud);
				}
			}
			catch(IllegalArgumentException e)
			{ //skips line
				}
		}
		fileReader.close();
		return students;
	}
	/**
	 * Writes the ArrrayList of students to a specified file, giving one line per student object
	 * @param fileName - the name of the file 
	 * @param studentDirectory - the SortedList of students
	 * @throws IOException - the error thrown by method
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for(int x = 0; x < studentDirectory.size(); x++)
		{
			fileWriter.println(studentDirectory.get(x).toString());
		}
		fileWriter.close();
		
	}

	/** Creates a Student object from a string
	 * 
	 * @param nextLine - string containing information to build new Student Object
	 * @return - the Student object specified by the string
	 * @throws FileNotFoundException - in the case that the File is not valid
	 */
	public static Student readStudent(String nextLine) throws FileNotFoundException {
		Student s;
		String[] inputs = nextLine.split(",");
		if(inputs.length == 6){
			s = new Student(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], Integer.parseInt(inputs[5]));
			return s;
		}
		if(inputs.length == 5){
			s = new Student(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4]);
			return s;
		}
		throw new IllegalArgumentException();
	}
}
