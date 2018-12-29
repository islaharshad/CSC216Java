package edu.ncsu.csc216.bbtp.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the TestingType class
 * 
 * @author Islah
 *
 */
public class TestingTypeTest {

	/**
	 * Tests the TestingType class's constructor
	 */
	@Test
	public void testTestingType() {
		TestingType tp = null;
		TestingType tp1 = null;
		TestingType tp2 = null;
		try {
			tp = new TestingType(null, "Isla", "Bye");
		} catch (IllegalArgumentException e) {
			assertNull(tp);
		}

		try {
			tp1 = new TestingType("12", "", "Bye");
		} catch (IllegalArgumentException e) {
			assertNull(tp1);
		}

		try {
			tp2 = new TestingType("1234", null, "Bye");
		} catch (IllegalArgumentException e) {
			assertNull(tp2);
		}

	}

	/**
	 * Tests the getName method of the TestingType class
	 */
	@Test
	public void testGetName() {
		TestingType tp = new TestingType("123", "Isla", "Bye");

		assertEquals("Isla", tp.getName());
	}

	/**
	 * Tests the setName method of the TestingType class
	 */
	@Test
	public void testSetName() {
		TestingType tp1 = null;
		try {
			tp1 = new TestingType(null, "Isla", "Bye");
		} catch (IllegalArgumentException e) {
			assertNull(tp1);
		}

		try {
			tp1 = new TestingType("", "Isla", "Bye");
		} catch (IllegalArgumentException e) {
			// pass
		}

		TestingType tp = new TestingType("123", "Isla", "Bye");

		tp.setName("Sam");

		assertEquals("Sam", tp.getName());

		try {
			tp.setName(null);
		} catch (IllegalArgumentException e) {
			// pass
		}

		try {
			tp.setName("");
		} catch (IllegalArgumentException e) {
			// pass
		}

	}

	/**
	 * Tests the getDescription method of the TestingType class
	 */
	@Test
	public void testGetDescription() {
		TestingType tp = new TestingType("123", "Isla", "Bye");

		assertEquals("Bye", tp.getDescription());
	}

	/**
	 * Tests the setDescription method of the TestingType class
	 */
	@Test
	public void testSetDescription() {
		TestingType tp2 = null;
		try {
			tp2 = new TestingType("123", "Isla", null);
		} catch (IllegalArgumentException e) {
			assertNull(tp2);
		}

		try {
			tp2 = new TestingType("123", "Isla", "");
		} catch (IllegalArgumentException e) {
			assertNull(tp2);
		}

		TestingType tp = new TestingType("123", "Isla", "Bye");

		tp.setDescription("hi");

		assertEquals("hi", tp.getDescription());

		try {
			tp.setDescription(null);
		} catch (IllegalArgumentException e) {
			// pass
		}

		try {
			tp.setDescription("");
		} catch (IllegalArgumentException e) {
			// pass
		}
	}

	/**
	 * Tests the getTestingTypeID method of the TestingType class
	 */
	@Test
	public void testGetTestingTypeID() {
		TestingType tp = new TestingType("123", "Isla", "Bye");

		assertEquals("123", tp.getTestingTypeID());
	}

	/**
	 * Tests the equals method of the TestingType class
	 */
	@Test
	public void testEquals() {
		TestingType tp = new TestingType("123", "Islah", "Bye1");
		TestingType tp1 = new TestingType("123", "Isla", "Bye");
		TestingType tp2 = new TestingType("124", "Isla", "Bye");
		TestingType tp3 = null;
		TestingType tp4 = new TestingType("123", "Islah", "Bye1");
		assertTrue(tp.equals(tp1));
		assertFalse(tp.equals(tp2));
		assertFalse(tp.equals(tp3));
		assertTrue(tp.equals(tp4));

		TestingType tp5 = new TestingType("123", "Islah", "Bye2");
		TestingType tp6 = tp5;
		assertTrue(tp5.equals(tp6));

	}

	/**
	 * Tests the compareTo method of the TestingType class
	 */
	@Test
	public void testCompareTo() {
		TestingType tp = new TestingType("24", "Islah", "Bye1");
		TestingType tp1 = new TestingType("23", "Isla", "Bye");

		assertEquals(1, tp.compareTo(tp1));

	}

	/**
	 * Tests the hashCode method of the TestingType class
	 */
	@Test
	public void testHashCode() {
		TestingType tp = new TestingType("24", "Islah", "Bye1");
		TestingType tpc = new TestingType("24", "Islah", "Bye1");
		TestingType tp1 = new TestingType("23", "Isla", "Bye");

		assertNotEquals(tp.hashCode(), tp1.hashCode());
		assertEquals(tp.hashCode(), tpc.hashCode());
	}

	/**
	 * Tests the toString method of the TestingType class
	 */
	@Test
	public void testToString() {
		TestingType tp = new TestingType("24", "Islah", "Bye1");
		assertEquals("Islah", tp.toString());
	}

}
