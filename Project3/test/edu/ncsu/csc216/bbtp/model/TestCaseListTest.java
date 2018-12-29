package edu.ncsu.csc216.bbtp.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

/**
 * Tests the TestCaseList class
 * 
 * @author Samuel
 *
 */
public class TestCaseListTest {

	/**
	 * Tests the constructor of the TestCaseList class
	 */
	@Test
	public void testTestCaseList() {
		TestCaseList t = new TestCaseList("boolean", "ID");
		assertEquals("boolean", t.getName());
		assertEquals("ID", t.getTestCaseListID());
		try {
			t.getTestCaseAt(0);
		} catch (IndexOutOfBoundsException e) {
			// pass
		}
	}

	/**
	 * Tests the setName method of the TestCaseList class
	 */
	@Test
	public void testSetName() {
		TestCaseList t = new TestCaseList("boolean", "ID");
		assertEquals("boolean", t.getName());
		t.setName("not boolean");
		assertEquals("not boolean", t.getName());
	}

	/**
	 * Tests the addTestCase method of the TestCaseList class
	 */
	@Test
	public void testAddTestCase() {
		TestCaseList t = new TestCaseList("boolean", "ID");
		TestingType ty = new TestingType("ID", "name", "desc");
		Date d = new Date();
		TestCase tc = new TestCase("ID-TC1", "test", ty, d, "pass", true, d, "pass", true);
		TestCase tc1 = new TestCase("ID-TC2", "test2", ty, d, "pass", true, d, "pass", true);

		assertTrue(t.addTestCase("test", ty, d, "pass", true, d, "pass", true));
		assertTrue(t.addTestCase("test2", ty, d, "pass", true, d, "pass", true));
		assertEquals(tc, t.getTestCaseAt(0));
		assertEquals(tc1, t.getTestCaseAt(1));
	}

	/**
	 * Tests the indexOf method of the TestCaseList class
	 */
	@Test
	public void testIndexOf() {
		TestCaseList t = new TestCaseList("boolean", "ID");
		TestingType ty = new TestingType("ID", "name", "desc");
		Date d = new Date();

		t.addTestCase("test", ty, d, "pass", true, d, "pass", true);
		t.addTestCase("test2", ty, d, "pass", true, d, "pass", true);
		assertEquals(0, t.indexOf("ID-TC1"));
		assertEquals(1, t.indexOf("ID-TC2"));
		assertEquals(-1, t.indexOf("I-TC1"));
	}

	/**
	 * Tests the size method of the TestCaseList class
	 */
	@Test
	public void testSize() {
		TestCaseList t = new TestCaseList("boolean", "ID");
		TestingType ty = new TestingType("ID", "name", "desc");
		Date d = new Date();

		assertEquals(0, t.size());

		t.addTestCase("test", ty, d, "pass", true, d, "pass", true);
		assertEquals(1, t.size());
		t.addTestCase("test2", ty, d, "pass", true, d, "pass", true);
		assertEquals(2, t.size());

	}

	/**
	 * Tests the isEmpty method of the TestCaseList class
	 */
	@Test
	public void testIsEmpty() {
		TestCaseList t = new TestCaseList("boolean", "ID");
		TestingType ty = new TestingType("ID", "name", "desc");
		Date d = new Date();

		assertEquals(true, t.isEmpty());
		t.addTestCase("test", ty, d, "pass", true, d, "pass", true);
		t.addTestCase("test2", ty, d, "pass", true, d, "pass", true);

		assertEquals(false, t.isEmpty());
	}

	/**
	 * Tests the removeTestCaseAt method of the TestCaseList class
	 */
	@Test
	public void testRemoveTestCaseAt() {
		TestCaseList t = new TestCaseList("boolean", "ID");
		TestingType ty = new TestingType("ID", "name", "desc");
		Date d = new Date();
		TestCase tc = new TestCase("ID-TC1", "test", ty, d, "pass", true, d, "pass", true);
		TestCase tc1 = new TestCase("ID-TC2", "test2", ty, d, "pass", true, d, "pass", true);

		t.addTestCase("test", ty, d, "pass", true, d, "pass", true);
		t.addTestCase("test2", ty, d, "pass", true, d, "pass", true);

		try {
			t.removeTestCaseAt(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, t.size());
		}

		try {
			t.removeTestCaseAt(2);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, t.size());
		}

		assertEquals(2, t.size());

		assertEquals(tc1, t.removeTestCaseAt(1));
		assertEquals(1, t.size());

		assertEquals(tc, t.removeTestCaseAt(0));
		assertEquals(0, t.size());
	}

	/**
	 * Tests the removeTestCase method of the TestCaseList class
	 */
	@Test
	public void testRemoveTestCase() {
		TestCaseList t = new TestCaseList("boolean", "ID");
		TestingType ty = new TestingType("ID", "name", "desc");
		Date d = new Date();

		t.addTestCase("test", ty, d, "pass", true, d, "pass", true);
		t.addTestCase("test2", ty, d, "pass", true, d, "pass", true);

		assertEquals(false, t.removeTestCase("ID_TC"));

		assertEquals(2, t.size());

		assertEquals(true, t.removeTestCase("ID-TC1"));
		assertEquals(1, t.size());

		assertEquals(true, t.removeTestCase("ID-TC2"));
		assertEquals(0, t.size());

	}

	/**
	 * Tests the get2DArray method of the TestCaseList class
	 */
	@Test
	public void testGet2DArray() {
		TestCaseList t = new TestCaseList("boolean", "ID");
		TestingType ty = new TestingType("ID", "name", "desc");
		Date d = new Date();
		TestCase tc = new TestCase("ID-TC1", "test", ty, d, "pass", true, d, "pass", true);
		TestCase tc1 = new TestCase("ID-TC2", "test2", ty, d, "pass", true, d, "pass", true);
		t.addTestCase("test", ty, d, "pass", true, d, "pass", true);
		t.addTestCase("test2", ty, d, "pass", true, d, "pass", true);
		Object s[][] = new Object[2][9];
		s[0][0] = tc.getTestCaseID();
		s[0][1] = tc.getDescription();
		s[0][2] = tc.getTestingType();
		s[0][3] = tc.getCreationDateTime();
		s[0][4] = tc.getLastTestedDateTime();
		s[0][5] = tc.tested();
		s[0][6] = tc.getExpectedResults();
		s[0][7] = tc.getActualResults();
		s[0][8] = tc.pass();

		s[1][0] = tc1.getTestCaseID();
		s[1][1] = tc1.getDescription();
		s[1][2] = tc1.getTestingType();
		s[1][3] = tc1.getCreationDateTime();
		s[1][4] = tc1.getLastTestedDateTime();
		s[1][5] = tc1.tested();
		s[1][6] = tc1.getExpectedResults();
		s[1][7] = tc1.getActualResults();
		s[1][8] = tc1.pass();
		Object r[][] = t.get2DArray();

		assertEquals(s[0][0], r[0][0]);
		assertEquals(s[0][1], r[0][1]);
		assertEquals(s[0][2], r[0][2]);
		assertEquals(s[0][3], r[0][3]);
		assertEquals(s[0][4], r[0][4]);
		assertEquals(s[0][5], r[0][5]);
		assertEquals(s[0][6], r[0][6]);
		assertEquals(s[0][7], r[0][7]);
		assertEquals(s[0][8], r[0][8]);
		assertEquals(s[1][0], r[1][0]);
		assertEquals(s[1][1], r[1][1]);
		assertEquals(s[1][2], r[1][2]);
		assertEquals(s[1][3], r[1][3]);
		assertEquals(s[1][4], r[1][4]);
		assertEquals(s[1][5], r[1][5]);
		assertEquals(s[1][6], r[1][6]);
		assertEquals(s[1][7], r[1][7]);
		assertEquals(s[1][8], r[1][8]);

	}

	/**
	 * Tests the update method of the TestCaseList class
	 */
	@Test
	public void testUpdate() {
		TestCaseList t = new TestCaseList("boolean", "ID");
		TestingType ty = new TestingType("ID", "name", "desc");
		Date d = new Date();
		TestCase tc = new TestCase("ID-TC1", "test", ty, d, "pass", true, d, "pass", true);
		t.addTestCase("test", ty, d, "pass", true, d, "pass", true);
		t.addTestCase("test2", ty, d, "pass", true, d, "pass", true);

		t.update(tc, "string");
		assertEquals(2, t.size());

	}

}
