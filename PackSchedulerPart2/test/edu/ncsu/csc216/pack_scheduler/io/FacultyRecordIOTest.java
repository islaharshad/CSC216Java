package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;


import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * JUnit Tests for FacultyRecordIO 
 * @author gabsill
 * @author joaloba
 *
 */
public class FacultyRecordIOTest {
	/** file location for valid records */
	private static final String FACULTY_RECORDS_VALID = "./test-files/faculty_records.txt";
	/** file location for invalid records */
	private static final String FACULTY_RECORDS_INVALID = "./test-files/invalid_faculty_records.txt";
	/** First faculty that should be created from FACULTY_RECORDS_VALID **/
	private Faculty validFaculty;
	
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
	    
	    validFaculty = new Faculty("Norman", "Brady", "nbrady", "pede.nonummy@elitfermentum.co.uk", hashPW, 1);
	}
		
	
	
	/**
	 * Tests Reading a valid input file
	 */
	@Test
	public void testReadValidFile(){
		LinkedList<Faculty> fileInput = new LinkedList<Faculty>();
		try {
			fileInput = FacultyRecordIO.readFacultyRecords(FACULTY_RECORDS_VALID);
			// The valid faculty entry will be the 7th faculty in the list
			User faculty1 = fileInput.get(6);
			assertEquals(faculty1, validFaculty);

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
		LinkedList<Faculty> fileInput = new LinkedList<Faculty>();
		try {
			fileInput = FacultyRecordIO.readFacultyRecords(FACULTY_RECORDS_INVALID);
			assertEquals(0, fileInput.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	/**
	 * Test writing Faculty Records
	 * Requires readFacultyRecords to work correctly
	 */
	@Test
	public void testWriteFacultyRecords1(){
		LinkedList<Faculty> testList = new LinkedList<Faculty>();
		testList.add(validFaculty);
		try {
			FacultyRecordIO.writeFacultyRecords("./test-files/actual_faculty_records.txt", testList);
			Scanner actualFile = new Scanner(new File("./test-files/actual_faculty_records.txt"));
			Scanner expectedFile = new Scanner(new File(FACULTY_RECORDS_VALID));
			User a = FacultyRecordIO.readFacultyRecords("./test-files/actual_faculty_records.txt").get(0);
			// Because this is now sorted this will be the 7th faculty in the list

			User b = FacultyRecordIO.readFacultyRecords(FACULTY_RECORDS_VALID).get(6);
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
	public void testFacultyRecordIOConstructor(){
		FacultyRecordIO testObject = new FacultyRecordIO();
		assertEquals(testObject.getClass(), FacultyRecordIO.class);
	}
}

