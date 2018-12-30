package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/** Tests the StudentRecordIO class for functionality
 * @author Wil Elias
 *
 */
public class StudentRecordIOTest {
	
	private String hashPW;
	
	private static final String HASH_ALGORITHM = "SHA-256";
	
	private String validFileName = "test-files/student_records.txt";
	
	private String invalidFileName = "test-files/invalid_student_records.txt";
	
	private final String validStudent7 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	private final String validStudent9 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	private final String validStudent5 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	private final String validStudent1 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	private final String validStudent3 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	private final String validStudent4 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	private final String validStudent2 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	private final String validStudent0 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	private final String validStudent6 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	private final String validStudent8 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	private final String inValidStudent =  "Shannon,Hansen,shansen,shansen@ncsu.edu,pw";
	
	
	private String[] validStudents = {validStudent1, validStudent2, validStudent3, validStudent4, validStudent5, 
										validStudent6, validStudent7, validStudent8, validStudent9, validStudent0};
	
	/**Turns the validStudent# strings into a comparable form for the student class
	 * @throws Exception - in the case of the Algorithm not working
	 */
	@Before
	public void setUp() throws Exception {
		try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = new String(digest.digest());
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}

	/**Tests the readStudentRecords method for functionality, compares expected and actual outputs
	 */
	@Test
	public void testReadStudentRecords() {
		//test to see if it read in the valid file correctly
		try
		{
			SortedList<Student> students = StudentRecordIO.readStudentRecords(validFileName);
			assertEquals(10, students.size());
			
			for(int i = 0; i < validStudents.length; i++)
			{
				assertEquals(validStudents[i], students.get(i).toString());
			}
		}
		catch(FileNotFoundException e)
		{
			fail("Unexpected Error reading" + validFileName);
		}
		//test to see if it correctly did not read in the invalid file
		try
		{
			SortedList<Student> students = StudentRecordIO.readStudentRecords(invalidFileName);
			assertEquals(0, students.size());
		}
		catch(FileNotFoundException e)
		{
			fail("Unexpected Error reading" + invalidFileName);
		}
		//test to make sure that the method throws a FileNotFoundException given an invalid file name
		try
		{
			SortedList<Student> students = StudentRecordIO.readStudentRecords("it dont exist");
			fail("FileNotFoundException was not thrown");
			assertEquals(0, students.size());
		}
		catch(FileNotFoundException e)
		{
			//File was indeed not found
		}
		
		// test inputs of each student
		//Student s = null;
		//assertEquals(StudentRecordIO.readStudentRecords(validStudent9);
		
	}

	/** Tests the writeStudentRecords method for functionality, compares expected and actual output
	 */
	@Test
	public void testWriteStudentRecords() {
		//test to check if the given information was written properly
		SortedList<Student> students = new SortedList<Student>();
		try
		{
			students = StudentRecordIO.readStudentRecords(validFileName);
		}
		catch(FileNotFoundException e){
			fail("Unexpected Error reading" + validFileName);
		}
		try
		{
			StudentRecordIO.writeStudentRecords("test-files/actual_student_records, studentDirectory", students);
		}
		catch(IOException e)
		{
			fail("Cannot write student records to file");
		}
		checkFiles("test-files/actual_student_records.txt", "test-files/expected_student_records.txt");	
	}
	
	/**Tests to see if the method writeStudentRecords throws an IOException 
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
	    SortedList<Student> students = new SortedList<Student>();
	    students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
	    //Assumption that you are using a hash of "pw" stored in hashPW
	    //test to see if the IOException is thrown if the file cannot be written
	    try {
	        StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students);
	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
	    } catch (IOException e) {
	        assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", e.getMessage());
	        //The actual error message on Jenkins!
	    }
	    
	}
	
	/**
	 * Tests readStudent method 
	 * @throws FileNotFoundException 
	 */
	@Test
    public void testReadStudent() throws FileNotFoundException {
    try {
		StudentRecordIO.readStudent(inValidStudent);
    } catch (FileNotFoundException e) {
    	fail("Unable to read Invalid Student");
    	throw new IllegalArgumentException();
    }
    // Tests readStudent method for valid student records
    Student s = new  Student("Cassandra" , "Schwartz" , "cschwartz" , 
    		                 "semper@imperdietornare.co.uk" , "pw" , 4);
    try {
		StudentRecordIO.readStudent(validStudent9);
    } catch (FileNotFoundException e) {
    	assertEquals(s, StudentRecordIO.readStudent(validStudent9));
    }
		
	}
	
	/**Compares two files to see if they are the same
	 * @param expFile - the first file to compare
	 * @param actFile - the second file to compare
	 */
	private void checkFiles(String expFile, String actFile) {
	    try {
	        Scanner expScanner = new Scanner(new FileInputStream(expFile));
	        Scanner actScanner = new Scanner(new FileInputStream(actFile));
	        
	        while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
	            String exp = expScanner.nextLine();
	            String act = actScanner.nextLine();
	            assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
	        }
	        if (expScanner.hasNextLine()) {
	            fail("The expected results expect another line " + expScanner.nextLine());
	        }
	        if (actScanner.hasNextLine()) {
	            fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
	        }
	        
	        expScanner.close();
	        actScanner.close();
	    } catch (IOException e) {
	        fail("Error reading files.");
	    }
	}
	
	
}
	    
	
	
	

