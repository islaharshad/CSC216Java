package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * JUnit Tests for StudentRecordIO 
 * @author gabsill
 * @author joaloba
 *
 */
public class StudentRecordIOTest {
	/** file location for valid records */
	private static final String STUDENT_RECORDS_VALID = "./test-files/student_records.txt";
	/** file location for invalid records */
	private static final String STUDENT_RECORDS_INVALID = "./test-files/invalid_student_records.txt";
	/** First student that should be created from STUDENT_RECORDS_VALID **/
	private Student validStudent;
	
	private static final String HASH_ALGORITHM = "SHA-256";

	private String hashPW;
	/**
	 * Set Up for tests
	 * 1. hash algorithm
	 */
	@Before
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = new String(digest.digest());
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	    
	    validStudent = new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15);
	    new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "0ÉRú±\"ÃùuŸ¦Ù\\7X²F´þâ9•{-OîFâapÄ");
	}
		
	
	
	/**
	 * Tests Reading a valid input file
	 */
	@Test
	public void testReadValidFile(){
		SortedList<Student> fileInput = new SortedList<Student>();
		try {
			fileInput = StudentRecordIO.readStudentRecords(STUDENT_RECORDS_VALID);
			// The valid student entry will be the 7th student in the list
			User student1 = fileInput.get(6);
			assertEquals(student1, validStudent);

		} catch (FileNotFoundException e) {
			fail();
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests reading an invalid input file
	 */
	@Test
	public void testReadInvalidFile(){
		SortedList<Student> fileInput = new SortedList<Student>();
		try {
			fileInput = StudentRecordIO.readStudentRecords(STUDENT_RECORDS_INVALID);
			assertEquals(0, fileInput.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	/**
	 * Test writing Student Records
	 * Requires readStudentRecords to work correctly
	 */
	@Test
	public void testWriteStudentRecords1(){
		SortedList<Student> testList = new SortedList<Student>();
		testList.add(validStudent);
		try {
			StudentRecordIO.writeStudentRecords("./test-files/actual_student_records.txt", testList);
			Scanner actualFile = new Scanner(new File("./test-files/actual_student_records.txt"));
			Scanner expectedFile = new Scanner(new File(STUDENT_RECORDS_VALID));
			User a = StudentRecordIO.readStudentRecords("./test-files/actual_student_records.txt").get(0);
			// Because this is now sorted this will be the 7th student in the list

			User b = StudentRecordIO.readStudentRecords(STUDENT_RECORDS_VALID).get(6);
			assertEquals(a, b);
			actualFile.close();
			expectedFile.close();
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		}
	}
	
	/**
	 * Test Constructor
	 */
	@Test
	public void testStudentRecordIOConstructor(){
		StudentRecordIO testObject = new StudentRecordIO();
		assertEquals(testObject.getClass(), StudentRecordIO.class);
	}
}
