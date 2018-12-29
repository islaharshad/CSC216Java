package edu.ncsu.csc216.bbtp.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

/**
 * Tests the TestCase class
 * 
 * @author Islah
 *
 */
public class TestCaseTest {

	/**
	 * Tests the TestCase class's constructor
	 */
	@Test
	public void testTestCase() {

		TestingType tp = new TestingType("hi", "isla", "bye");
		Date date = new Date();
		Date date1 = new Date();

		TestCase tc = new TestCase("123", "hi", tp, date, "good", true, date1, "good", true);
		assertEquals("hi", tc.getDescription());

		TestCase tc1 = null;
		try {
			tc1 = new TestCase("", "hi", tp, date, "good", true, date1, "good", true);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(tc1);
		}
	}

	/**
	 * Tests the getDescription method of the TestCase class
	 */
	@Test
	public void testGetDescription() {

		TestingType tp = new TestingType("hui", "isla", "bye");
		Date date = new Date();
		Date date1 = new Date();

		TestCase tc = new TestCase("123", "hi", tp, date, "good", true, date1, "good", true);

		assertEquals("hi", tc.getDescription());

	}

	/**
	 * Tests the setDescription method of the TestCase class
	 */
	@Test
	public void testSetDescription() {
		TestingType tp = new TestingType("hui", "isla", "bye");
		Date date = new Date();
		Date date1 = new Date();

		TestCase tc = new TestCase("123", "hi", tp, date, "good", true, date1, "good", true);

		tc.setDescription("mymy");
		assertEquals("mymy", tc.getDescription());

		try {
			tc.setDescription(null);
		} catch (IllegalArgumentException e) {
			// pass
		}
	}

	/**
	 * Tests the getExpectedResults method of the TestCase class
	 */
	@Test
	public void testGetExpectedResults() {

		TestingType tp = new TestingType("hui", "isla", "bye");
		Date date = new Date();
		Date date1 = new Date();

		TestCase tc = new TestCase("123", "hi", tp, date, "good", true, date1, "good", true);

		assertEquals("good", tc.getExpectedResults());
	}

	/**
	 * Tests the setExpectedResults method of the TestCase class
	 */
	@Test
	public void testSetExpectedResults() {
		TestingType tp = new TestingType("hui", "isla", "bye");
		Date date = new Date();
		Date date1 = new Date();

		TestCase tc = new TestCase("123", "hi", tp, date, "good", true, date1, "good", true);

		tc.setExpectedResults("bad");
		assertEquals("bad", tc.getExpectedResults());

		try {
			tc.setExpectedResults(null);
		} catch (IllegalArgumentException e) {
			// pass
		}
	}

	/**
	 * Tests the getActualResults method of the TestCase class
	 */
	@Test
	public void testGetActualResults() {
		TestingType tp = new TestingType("hui", "isla", "bye");
		Date date = new Date();
		Date date1 = new Date();

		TestCase tc = new TestCase("123", "hi", tp, date, "good", true, date1, "good", true);

		assertEquals("good", tc.getExpectedResults());

	}

	/**
	 * Tests the setActualResults method of the TestCase class
	 */
	@Test
	public void testSetActualResults() {

		TestingType tp = new TestingType("hui", "isla", "bye");
		Date date = new Date();
		Date date1 = new Date();

		TestCase tc = new TestCase("123", "hi", tp, date, "good", true, date1, "good", true);

		tc.setActualResults("not bad");
		assertEquals("not bad", tc.getActualResults());
		try {
			tc.setActualResults(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("not bad", tc.getActualResults());
		}
	}

	/**
	 * Tests the getCreationDateTime method of the TestCase class
	 */
	@Test
	public void testGetCreationDateTime() {
		TestingType tp = new TestingType("hui", "isla", "bye");
		Date date = new Date();
		Date date1 = new Date();

		TestCase tc = new TestCase("123", "hi", tp, date, "good", true, date1, "good", true);

		assertEquals(date, tc.getCreationDateTime());

		try {
			tc.setCreationDateTime(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(date, tc.getCreationDateTime());
		}
	}

	/**
	 * Tests the getLastTestedDateTime method of the TestCase class
	 */
	@Test
	public void testGetLastTestedDateTime() {
		TestingType tp = new TestingType("hui", "isla", "bye");
		Date date = new Date();
		Date date1 = new Date();

		TestCase tc = new TestCase("123", "hi", tp, date, "good", true, date1, "good", true);

		assertEquals(date1, tc.getLastTestedDateTime());
	}

	/**
	 * Tests the setLastTestedDateTime method of the TestCase class
	 */
	@Test
	public void testSetLastTestedDateTime() {
		TestingType tp = new TestingType("hui", "isla", "bye");
		Date date = new Date();
		Date date1 = new Date();
		Date date2 = new Date();

		TestCase tc = new TestCase("123", "hi", tp, date, "good", true, date1, "good", true);

		tc.setLastTestedDateTime(date2);
		assertEquals(date2, tc.getLastTestedDateTime());
	}

	/**
	 * Tests the setTestedStatus method of the TestCase class
	 */
	@Test
	public void testSetTestedStatus() {
		TestingType tp = new TestingType("hui", "isla", "bye");
		Date date = new Date();
		Date date1 = new Date();

		TestCase tc = new TestCase("123", "hi", tp, date, "good", true, date1, "good", true);

		tc.setTestedStatus(false);
		assertFalse(tc.tested());
	}

	/**
	 * Tests the setPass method of the TestCase class
	 */
	@Test
	public void testSetPass() {
		TestingType tp = new TestingType("hui", "isla", "bye");
		Date date = new Date();
		Date date1 = new Date();

		TestCase tc = new TestCase("123", "hi", tp, date, "good", true, date1, "good", true);

		tc.setPass(false);
		assertFalse(tc.pass());
	}

	/**
	 * Tests the setTestingtype method of the TestCase class
	 */
	@Test
	public void testSetTestingtype() {
		TestingType tp = new TestingType("hui", "isla", "bye");
		TestingType tp1 = new TestingType("i", "isla", "bye");
		Date date = new Date();
		Date date1 = new Date();

		TestCase tc = new TestCase("123", "hi", tp, date, "good", true, date1, "good", true);

		tc.setTestingType(tp1);
		assertEquals(tp1, tc.getTestingType());
	}

	/**
	 * Tests the getTestingtype method of the TestCase class
	 */
	@Test
	public void testGetTestingType() {
		TestingType tp = new TestingType("hui", "isla", "bye");
		Date date = new Date();
		Date date1 = new Date();

		TestCase tc = new TestCase("123", "hi", tp, date, "good", true, date1, "good", true);

		assertEquals(tp, tc.getTestingType());
	}

	/**
	 * Tests the getTestCaseID method of the TestCase class
	 */
	@Test
	public void testGetTestCaseID() {
		TestingType tp = new TestingType("hui", "isla", "bye");
		Date date = new Date();
		Date date1 = new Date();

		TestCase tc = new TestCase("123", "hi", tp, date, "good", true, date1, "good", true);

		assertEquals("123", tc.getTestCaseID());
	}

	/**
	 * Tests the equals method of the TestCase class
	 */
	@Test
	public void testEquals() {
		TestingType tp = new TestingType("hui", "isla", "bye");
		Date date = new Date();
		Date date1 = new Date();
		TestCase tc0 = new TestCase("123", "hi", tp, date, "good", true, date1, "good", true);
		TestCase tc1 = new TestCase("123", "hi", tp, date, "good", true, date1, "good", true);
		TestCase tc2 = new TestCase("1234", "hi", tp, date, "good", true, date1, "good", true);
		TestCase tc3 = null;
		assertTrue(tc0.equals(tc1));
		assertFalse(tc0.equals(tc2));
		assertFalse(tc2.equals(tc3));

	}

	/**
	 * Tests the compareTo method of the TestCase class
	 */
	@Test
	public void testCompareTo() {

		TestingType tp = new TestingType("hui", "isla", "bye");
		Date date = new Date();
		Date date1 = new Date();
		TestCase tc0 = new TestCase("1", "hi", tp, date, "good", true, date1, "good", true);
		// TestCase tc1 = new TestCase("123", "hi", tp, date, "good", true,
		// null, "good", true);
		TestCase tc2 = new TestCase("2", "hi", tp, date, "good", true, date1, "good", true);
		// TestCase tc3 = new TestCase("123", "hi", tp, date, "good", true,
		// null, "good", true);
		// assertEquals(0, tc3.compareTo(tc1));
		assertEquals(0, tc2.compareTo(tc0));
	}
}