package edu.ncsu.csc216.bbtp.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the TestingTypeList class
 * 
 * @author Samuel
 *
 */
public class TestingTypeListTest {

	/**
	 * Tests the constructor of the TestingTypeList class
	 */
	@Test
	public void testTestingTypeList() {
		TestingTypeList t = new TestingTypeList();
		assertEquals("Testing Types", t.getName());
		try {
			t.getTestingTypeAt(0);
		} catch (IndexOutOfBoundsException e) {
			// pass
		}
	}

	/**
	 * Tests the addTestingType method of the TestingTypeList class
	 */
	@Test
	public void testAddTestingType() {
		TestingTypeList t = new TestingTypeList();
		TestingType ty = new TestingType("TT1", "name", "desc");
		TestingType ty1 = new TestingType("TT2", "name2", "desc");

		assertTrue(t.addTestingType("name", "desc"));
		assertTrue(t.addTestingType("name2", "desc"));
		assertEquals(ty, t.getTestingTypeAt(0));
		assertEquals(ty1, t.getTestingTypeAt(1));
	}

	/**
	 * Tests the indexOf method of the TestingTypeList class
	 */
	@Test
	public void testIndexOf() {
		TestingTypeList t = new TestingTypeList();

		t.addTestingType("name", "desc");
		t.addTestingType("name2", "desc");
		assertEquals(0, t.indexOf("TT1"));
		assertEquals(1, t.indexOf("TT2"));
		assertEquals(-1, t.indexOf("TC1"));
	}

	/**
	 * Tests the size method of the TestingTypeList class
	 */
	@Test
	public void testSize() {
		TestingTypeList t = new TestingTypeList();

		assertEquals(0, t.size());

		t.addTestingType("name", "desc");
		assertEquals(1, t.size());
		t.addTestingType("name2", "desc");
		assertEquals(2, t.size());

	}

	/**
	 * Tests the isEmpty method of the TestingTypeList class
	 */
	@Test
	public void testIsEmpty() {
		TestingTypeList t = new TestingTypeList();

		assertEquals(true, t.isEmpty());
		t.addTestingType("name", "desc");
		t.addTestingType("name2", "desc");

		assertEquals(false, t.isEmpty());
	}

	/**
	 * Tests the removeTestCaseAt method of the TestingTypeList class
	 */
	@Test
	public void testRemoveTestCaseAt() {
		TestingTypeList t = new TestingTypeList();
		TestingType ty = new TestingType("TT1", "name", "desc");
		TestingType ty1 = new TestingType("TT2", "name2", "desc");

		t.addTestingType("name", "desc");
		t.addTestingType("name2", "desc");

		try {
			t.removeTestingTypeAt(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, t.size());
		}

		try {
			t.removeTestingTypeAt(2);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, t.size());
		}

		assertEquals(2, t.size());

		assertEquals(ty1, t.removeTestingTypeAt(1));
		assertEquals(1, t.size());

		assertEquals(ty, t.removeTestingTypeAt(0));
		assertEquals(0, t.size());
	}

	/**
	 * Tests the removeTestCase method of the TestingTypeList class
	 */
	@Test
	public void testRemoveTestCase() {
		TestingTypeList t = new TestingTypeList();

		t.addTestingType("name", "desc");
		t.addTestingType("name2", "desc");

		assertEquals(false, t.removeTestingType("TC"));

		assertEquals(2, t.size());

		assertEquals(true, t.removeTestingType("TT1"));
		assertEquals(1, t.size());

		assertEquals(true, t.removeTestingType("TT2"));
		assertEquals(0, t.size());

	}

	/**
	 * Tests the get2DArray method of the TestingTypeList class
	 */
	@Test
	public void testGet2DArray() {
		TestingTypeList t = new TestingTypeList();
		TestingType ty = new TestingType("TT1", "name", "desc");
		TestingType ty1 = new TestingType("TT2", "name2", "desc");

		t.addTestingType("name", "desc");
		t.addTestingType("name2", "desc");
		String s[][] = new String[2][3];
		s[0][0] = ty.getTestingTypeID();
		s[0][1] = ty.getName();
		s[0][2] = ty.getDescription();

		s[1][0] = ty1.getTestingTypeID();
		s[1][1] = ty1.getName();
		s[1][2] = ty1.getDescription();

		Object r[][] = t.get2DArray();

		assertEquals(s[0][0], r[0][0]);
		assertEquals(s[0][1], r[0][1]);
		assertEquals(s[0][2], r[0][2]);
		assertEquals(s[1][0], r[1][0]);
		assertEquals(s[1][1], r[1][1]);
		assertEquals(s[1][2], r[1][2]);
	}

	/**
	 * Tests the update method of the TestingTypeList class
	 */
	@Test
	public void testUpdate() {
		TestingTypeList t = new TestingTypeList();
		TestingType ty = new TestingType("TT1", "name", "desc");

		t.addTestingType("name", "desc");
		t.addTestingType("name2", "desc");

		t.update(ty, "string");
		assertEquals(2, t.size());

	}

}
