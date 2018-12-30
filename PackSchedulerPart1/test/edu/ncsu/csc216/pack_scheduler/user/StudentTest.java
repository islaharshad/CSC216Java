package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;


/**
 * This class test the numerous functions of the Student class
 * @author Samuel Ryne Ritter, Wil Elias, Prem Subedi
 */
public class StudentTest {

	/** Test first name */
	private static final String FIRST_NAME = "Fred";
	/** Test last name */
	private static final String LAST_NAME = "Fredrickson";
	/** Test id */
	private static final String ID = "ffredr";
	/** Test email */
	private static final String EMAIL = "ffredr@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "fred";
	/** Test max credits */
	private static final int MAX_CREDITS = 14;
	  
	/**
	 * Tests Student.hashCode()
	 */
	@Test
	public void testHashCode() {
		User s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s2 = new Student("joe", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertTrue(s.hashCode() == s1.hashCode());
		assertFalse(s.hashCode() == s2.hashCode());
	}

	/**
	 * Tests Student Constructor with credits input
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertEquals("Fred" , s.getFirstName());
		assertEquals("Fredrickson" , s.getLastName());
		assertEquals("ffredr" , s.getId());
		assertEquals("ffredr@ncsu.edu" , s.getEmail());
		assertEquals("fred" , s.getPassword());
		assertEquals(14 , s.getMaxCredits());
		
		User s1 = null;
		try{
			s1 = new Student(null, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e){
			assertNull(s1);
		}
		
		User s2 = null;
		try{
			s2 = new Student(FIRST_NAME, null, ID, EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e){
			assertNull(s2);
		}
		
		User s3 = null;
		try{
			s3 = new Student(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e){
			assertNull(s3);
		}
		
		User s4 = null;
		try{
			s4 = new Student(FIRST_NAME, LAST_NAME, ID, null, PASSWORD);
			fail();
		} catch(IllegalArgumentException e){
			assertNull(s4);
		}
		
		User s5 = null;
		try{
			s5 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, null, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e){
			assertNull(s5);
		}
		
		
		User s6 = null;
		try{
			s6 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 0);
			fail();
		} catch(IllegalArgumentException e){
			assertNull(s6);
		}
		
		try{
			s.setId("");
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("fred", s.getPassword());
		}
		
		try{
			s.setId(null);
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("ffredr", s.getId());
		}
	}

	/**
	 * Tests Student Constructor without credits
	 */
	@Test
	public void testStudentStringStringStringStringString() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		assertEquals("Fred" , s.getFirstName());
		assertEquals("Fredrickson" , s.getLastName());
		assertEquals("ffredr" , s.getId());
		assertEquals("ffredr@ncsu.edu" , s.getEmail());
		assertEquals("fred" , s.getPassword());
		assertEquals(18 , s.getMaxCredits());
	}

	/**
	 * Tests Student.setEmail()
	 */
	@Test
	public void testSetEmail() {
		User s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		s.setEmail("email@mail.com");
		assertEquals("email@mail.com", s.getEmail());
		try{
			s.setEmail("emailmail.com");
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("email@mail.com", s.getEmail());
		}
		try{
			s.setEmail(".taha@cm");
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("email@mail.com", s.getEmail());
		}
	}

	/**
	 * Tests Student.setPassword()
	 */
	@Test
	public void testSetPassword() {
		User s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		
		try {
			s.setPassword("0");
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("fred", s.getPassword());
		}
		
		s.setPassword("pass");
		assertEquals("pass", s.getPassword());
		
		
		
		try{
			s.setPassword("");
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("pass", s.getPassword());
		}
		
	}

	/**
	 * Tests Student.setMaxCredits()
	 */
	@Test
	public void testSetMaxCredits() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		s.setMaxCredits(12);
		assertEquals(12, s.getMaxCredits());
		try{
			s.setMaxCredits(19);
			fail();
		} catch(IllegalArgumentException e){
			assertEquals(12, s.getMaxCredits());
		}
	}

	/**
	 * Tests Student.setFirstName()
	 */
	@Test
	public void testSetFirstName() {
		User s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		s.setFirstName("joe");
		assertEquals("joe", s.getFirstName());
		try{
			s.setFirstName("");
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("joe", s.getFirstName());
		}
	}

	/**
	 * Tests Student.setLastName()
	 */
	@Test
	public void testSetLastName() {
		User s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		s.setLastName("johnson");
		assertEquals("johnson", s.getLastName());
		try{
			s.setLastName("");
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("johnson", s.getLastName());
		}
	}

	/**
	 * Tests Student.equals()
	 */
	@Test
	public void testEqualsObject() {
		User s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s2 = new Student("joe", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertTrue(s.equals(s1));
		assertFalse(s.equals(s2));
		User a = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		User a1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
	     User a2 = new Student(FIRST_NAME, "joe", ID, EMAIL, PASSWORD);
		assertTrue(a.equals(a1));
		assertFalse(a.equals(a2));
		 a = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		 a1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
	      a2 = new Student(FIRST_NAME, LAST_NAME, "hi", EMAIL, PASSWORD);
		assertTrue(a.equals(a1));
		assertFalse(a.equals(a2));
	}
	

	/**
	 * Tests Student.toString()
	 */
	@Test
	public void testToString() {
		User s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertEquals("Fred,Fredrickson,ffredr,ffredr@ncsu.edu,fred,14", s.toString());
	}
	/**
	 * Tests Student.compareTo()
	 */
	@Test
	public void testCompareTo() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s2 = new Student("Joe", "Adams", ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s3 = new Student("Ann", "Adams", ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s4 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s5 = new Student(FIRST_NAME, LAST_NAME, "ann", EMAIL, PASSWORD, MAX_CREDITS);
		assertEquals(s1.compareTo(s2), 1);
		assertEquals(s3.compareTo(s2), -1);
		assertEquals(s2.compareTo(s3), 1);
		assertEquals(s2.compareTo(s1), -1);
		assertEquals(s1.compareTo(s4), 0);
		assertEquals(s1.compareTo(s5), 1);
		
		s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		s2 = new Student("Joe", "Adams", ID, EMAIL, PASSWORD);
		s3 = new Student("Ann", "Adams", ID, EMAIL, PASSWORD);
		s4 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		s5 = new Student(FIRST_NAME, LAST_NAME, "ann", EMAIL, PASSWORD);
		assertEquals(s1.compareTo(s2), 1);
		assertEquals(s3.compareTo(s2), -1);
		assertEquals(s2.compareTo(s3), 1);
		assertEquals(s2.compareTo(s1), -1);
		assertEquals(s1.compareTo(s4), 0);
		assertEquals(s1.compareTo(s5), 1);
	}
	
	/**
	 * Tests Student.getSchedule()
	 */
	@Test
	public void testGetSchedule()
	{
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Schedule schedule = s.getSchedule();
		assertEquals("My Schedule", schedule.getTitle());
		assertEquals(0, schedule.getScheduledCourses().length);
		
	}
}
