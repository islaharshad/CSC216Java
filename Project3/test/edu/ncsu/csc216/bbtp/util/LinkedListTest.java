package edu.ncsu.csc216.bbtp.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the LinkedList class
 * 
 * @author Samuel
 *
 */
public class LinkedListTest {

	/**
	 * Tests the constructor the LinkedList class
	 */
	@Test
	public void testLinkedList() {
		LinkedList l = new LinkedList();
		assertEquals(0, l.size());
	}

	/**
	 * Tests the size method of the ArrayList class
	 */
	@Test
	public void testSize() {
		LinkedList l = new LinkedList();
		assertEquals(0, l.size());
		l.add(1);
		assertEquals(1, l.size());
		l.add(2);
		assertEquals(2, l.size());
		l.add(3);
		assertEquals(3, l.size());
		l.remove(2);
		assertEquals(2, l.size());
	}

	/**
	 * Tests the isEmpty method of the ArrayList class
	 */
	@Test
	public void testIsEmpty() {
		LinkedList l = new LinkedList();
		assertTrue(l.isEmpty());
		l.add(1);
		assertFalse(l.isEmpty());
	}

	/**
	 * Tests the contains method of the ArrayList class
	 */
	@Test
	public void testContains() {
		LinkedList l = new LinkedList();
		assertFalse(l.contains(1));
		l.add(1);
		l.add(2);
		l.add(3);
		assertTrue(l.contains(1));
		assertTrue(l.contains(3));
		assertFalse(l.contains(4));
	}

	/**
	 * Tests the add method of the ArrayList class
	 */
	@Test
	public void testAdd() { /// both add methods
		LinkedList l = new LinkedList();
		assertTrue(l.add(1));
		assertEquals(1, l.get(0));
		assertTrue(l.add(2));
		assertEquals(2, l.get(1));
		assertTrue(l.add(3));
		assertEquals(3, l.get(2));

		l.add(0, 0);
		assertEquals(0, l.get(0));
		assertEquals(1, l.get(1));
		assertEquals(2, l.get(2));
		assertEquals(3, l.get(3));

		l.add(2, 12);
		assertEquals(0, l.get(0));
		assertEquals(1, l.get(1));
		assertEquals(12, l.get(2));
		assertEquals(2, l.get(3));
		assertEquals(3, l.get(4));
	}

	/**
	 * Tests the remove method of the ArrayList class
	 */
	@Test
	public void testRemove() {
		LinkedList l = new LinkedList();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.add(5);

		assertEquals(1, l.remove(0));
		assertEquals(2, l.get(0));
		assertEquals(3, l.get(1));
		assertEquals(4, l.get(2));
		assertEquals(5, l.get(3));

		assertEquals(4, l.remove(2));
		assertEquals(2, l.get(0));
		assertEquals(3, l.get(1));
		assertEquals(5, l.get(2));

		assertEquals(5, l.remove(2));
		assertEquals(2, l.get(0));
		assertEquals(3, l.get(1));

	}

	/**
	 * Tests the indexOf method of the ArrayList class
	 */
	@Test
	public void testIndexOf() {
		LinkedList l = new LinkedList();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.add(5);

		assertEquals(0, l.indexOf(1));
		assertEquals(2, l.indexOf(3));
		assertEquals(4, l.indexOf(5));
	}

}
