package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;


/**
 * Tests FacultyDirectory.
 * @author Sarah Heckman
 */
public class FacultyDirectoryTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Fa";
	/** Test last name */
	private static final String LAST_NAME = "Culty";
	/** Test id */
	private static final String ID = "fulty";
	/** Test email */
	private static final String EMAIL = "fulty@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_COURSES = 3;
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if something fails during setup. 
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset faculty_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests FacultyDirectory().
	 */
	@Test
	public void testFacultyDirectory() {
		//Test that the FacultyDirectory is initialized to an empty list
		FacultyDirectory sd = new FacultyDirectory();
		assertFalse(sd.removeFaculty("sesmith5"));
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.testNewFacultyDirectory().
	 */
	@Test
	public void testNewFacultyDirectory() {
		//Test that if there are faculty in the directory, they 
		//are removed after calling newFacultyDirectory().
		FacultyDirectory sd = new FacultyDirectory();
		
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		
		sd.newFacultyDirectory();
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.loadFacultysFromFile().
	 */
	@Test
	public void testLoadFacultysFromFile() {
		FacultyDirectory sd = new FacultyDirectory();
				
		//Test valid file
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		
		//Test invalid file
		try{
			sd.loadFacultyFromFile("notRealFile.txt");
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Unable to read file notRealFile.txt");
		}
	}

	/**
	 * Tests FacultyDirectory.addFaculty().
	 */
	@Test
	public void testAddFaculty() {
		FacultyDirectory sd = new FacultyDirectory();
		
		//Test valid Faculty
		sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		String [][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);
		
		//Test invalid password)
		try {
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, null, MAX_COURSES);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid password");
		}
		
		try {
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "", "", MAX_COURSES);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid password");
		}
		
		//Test passwords that do not match
		try {
			sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "wrongPassword", MAX_COURSES);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Passwords do not match");
		}
		
		//Test adding identical faculty
		assertEquals(false, sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES));
	}

	/**
	 * Tests FacultyDirectory.removeFaculty().
	 */
	@Test
	public void testRemoveFaculty() {
		FacultyDirectory sd = new FacultyDirectory();
				
		//Add facultys and remove
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		assertTrue(sd.removeFaculty("fmeadow"));
		String [][] facultyDirectory = sd.getFacultyDirectory();
		
		assertEquals(7, facultyDirectory.length);
		assertEquals("Brent", facultyDirectory[1][0]);
		assertEquals("Brewer", facultyDirectory[1][1]);
		assertEquals("bbrewer", facultyDirectory[1][2]);
	}

	/**
	 * Tests FacultyDirectory.saveFacultyDirectory().
	 */
	@Test
	public void testSaveFacultyDirectory() {
		FacultyDirectory sd = new FacultyDirectory();
		
		//Add a faculty
		sd.addFaculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", "pw", 2);
		sd.addFaculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", "pw", "pw", 3);
		sd.addFaculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", "pw", "pw", 1);
		assertEquals(3, sd.getFacultyDirectory().length);
		sd.saveFacultyDirectory("test-files/actual_faculty_records.txt");
		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
	}
	
	/**
	 * Tests FacultyDirectory.getFacultyDirectory
	 */
	@Test
	public void testGetFacultyDirectory() {
		FacultyDirectory sd = new FacultyDirectory();
		assertEquals(0, sd.getFacultyDirectory().length);
		sd.loadFacultyFromFile(validTestFile);
		String[][] compare = sd.getFacultyDirectory();
		assertEquals("Ashely", compare[0][0]);
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
	/**
	 * Tests the getFacultyById method for existing and nonexistant students
	 */
	@Test
	public void testGetFacultyById() {
		FacultyDirectory sd = new FacultyDirectory();
		sd.loadFacultyFromFile(validTestFile);
		//Tests a valid return
		String hashPW;
		try {
			MessageDigest digest1 = MessageDigest.getInstance("SHA-256");
			digest1.update("pw".getBytes());
			hashPW = new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
		Faculty z = new Faculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 1);
		sd.addFaculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 1);
		Faculty a1 = sd.getFacultyById("zking");
		assertEquals(a1.getFirstName(), z.getFirstName());
		assertEquals(a1.getLastName(), z.getLastName());
		assertEquals(a1.getEmail(), z.getEmail());
		assertEquals(a1.getId(), z.getId());
		assertEquals(a1.getPassword(), z.getPassword());
		//Tests a null return
		Faculty a2 = sd.getFacultyById("");
		assertNull(a2);
		
		
	}


}

