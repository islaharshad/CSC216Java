package edu.ncsu.csc216.bbtp.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the ArrayList class
 * 
 * @author Islahuddin Arshad
 *
 */
public class ArrayListTest {

	/**
	 * Tests the constructor of the ArrayList class
	 */
	@Test
	public void testArrayList() { // both constructors
		ArrayList list1 = new ArrayList(100);
		assertEquals(0, list1.size());

		ArrayList list2 = null;
		try {
			list2 = new ArrayList(0);
		} catch (IllegalArgumentException e) {
			assertNull(list2);
		}
		try {
			list2 = new ArrayList(-1);
		} catch (IllegalArgumentException e) {
			assertNull(list2);
		}

		list2 = new ArrayList();
		assertEquals(0, list2.size());
	}

	/**
	 * Tests the size method of the ArrayList class
	 */
	@Test
	public void testSize() {
		ArrayList list1 = new ArrayList();
		list1.add("car");
		list1.add("toy");
		list1.add("mouse");
		list1.add("mouse2");
		assertEquals(4, list1.size());

	}

	/**
	 * Tests the isEmpty method of the ArrayList class
	 */
	@Test
	public void testIsEmpty() {
		ArrayList list1 = new ArrayList();
		assertEquals(true, list1.isEmpty());
		list1.add("car");
		list1.add("toy");
		list1.remove(0);
		assertEquals(false, list1.isEmpty());
	}

	/**
	 * Tests the contains method of the ArrayList class
	 */
	@Test
	public void testContains() {
		ArrayList list1 = new ArrayList();
		list1.add("car");
		assertEquals(true, list1.contains("car"));
		assertEquals(false, list1.contains("something else"));
	}

	/**
	 * Tests the add method of the ArrayList class
	 */
	@Test
	public void testAdd() { /// both add methods
		ArrayList list1 = new ArrayList();
		list1.add("car");
		assertEquals(true, list1.contains("car"));

		list1.add(1, "hi");
		assertEquals(1, list1.indexOf("hi"));

		list1.add(2, "my");
		assertEquals(2, list1.indexOf("my"));

		list1.add(0, "first");
		assertEquals(0, list1.indexOf("first"));
		try {
			list1.add(1, null);
			fail();
		} catch (NullPointerException e) {
			// pass
		}

		try {
			list1.add(-1, "hie");
		} catch (IndexOutOfBoundsException e) {
			// pass
		}

		try {
			list1.add(list1.size() + 1, "hiej");
		} catch (IndexOutOfBoundsException e) {
			// pass
		}

		try {
			list1.add(1, "hi");
		} catch (IllegalArgumentException e) {
			// pass
		}
		try {
			list1.add(list1.size(), "taha");
		}

		catch (IndexOutOfBoundsException e) {
			// pass
		}

		try {
			list1.add(1, null);
		} catch (NullPointerException e) {
			assertEquals(5, list1.size());
		}
	}

	/**
	 * Tests the get method of the ArrayList class
	 */
	@Test
	public void testGet() {

		ArrayList list1 = new ArrayList();
		try {
			list1.get(-1);
		} catch (IndexOutOfBoundsException e) {
			// pass
		}

		try {
			list1.get(list1.size());
		}

		catch (IndexOutOfBoundsException e) {
			// pass
		}
		list1.add("car");
		assertEquals("car", list1.get(0));
	}

	/**
	 * Tests the remove method of the ArrayList class
	 */
	@Test
	public void testRemove() {
		ArrayList list1 = new ArrayList();

		try {
			list1.remove(-1);
		} catch (IndexOutOfBoundsException e) {
			// pass
		}

		try {
			list1.remove(list1.size());
		}

		catch (IndexOutOfBoundsException e) {
			// pass
		}

		list1.add("car");
		list1.add("toy");
		list1.remove(0);
		assertEquals("toy", list1.get(0));
	}

	/**
	 * Tests the indexOf method of the ArrayList class
	 */
	@Test
	public void testIndexOf() {
		ArrayList list1 = new ArrayList();
		list1.add("car");
		list1.add("toy");

		assertEquals(1, list1.indexOf("toy"));
		assertEquals(-1, list1.indexOf("something else"));
	}
}
