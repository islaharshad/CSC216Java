package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * Tests SortedList class
 * 
 * @author Gianni Absillis
 * @author Jonathan Aloba
 * @author Eduardo Bravo-Escudero
 *
 */
public class SortedListTest {
	
	/** Test String 1 */
	public static final String STRING1 = "hi";
	/** Test String 2 */
	public static final String STRING2 = "dog";
	/** Test String 3 */
	public static final String STRING3 = "jonathan";
	/** Test String 4 */
	public static final String STRING4 = "gianni";
	/** Test String 5 */
	public static final String STRING5 = "eduardo";
	/** Test String 6 */
	public static final String STRING6 = "computer";
	/** Test String 7 */
	public static final String STRING7 = "science";
	/** Test String 8 */
	public static final String STRING8 = "is";
	/** Test String 9 */
	public static final String STRING9 = "great";
	/** Test String 10 */
	public static final String STRING10 = "pudding";
	/** Test String 11 */
	public static final String STRING11 = "bacon";
	
	/**
	 * Tests SortedList()
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		list.add(STRING1);
		assertEquals(1, list.size());
		assertTrue(list.contains(STRING1));
		
		list.add(STRING2);
		assertEquals(2, list.size());
		assertTrue(list.contains(STRING2));
		
		list.add(STRING3);
		assertEquals(3, list.size());
		assertTrue(list.contains(STRING3));
		
		list.add(STRING4);
		assertEquals(4, list.size());
		assertTrue(list.contains(STRING4));

		list.add(STRING5);
		assertEquals(5, list.size());
		assertTrue(list.contains(STRING5));
		

		list.add(STRING6);
		assertEquals(6, list.size());
		assertTrue(list.contains(STRING6));
		

		list.add(STRING7);
		assertEquals(7, list.size());
		assertTrue(list.contains(STRING7));
		

		list.add(STRING8);
		assertEquals(8, list.size());
		assertTrue(list.contains(STRING8));
		

		list.add(STRING9);
		assertEquals(9, list.size());
		assertTrue(list.contains(STRING9));
		

		list.add(STRING10);
		assertEquals(10, list.size());
		assertTrue(list.contains(STRING10));

		list.add(STRING11);
		assertEquals(11, list.size());
		assertTrue(list.contains(STRING11));
				
	}

	/**
	 * Tests SortedList.add()
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		list.add("aardvark");
		assertEquals(2, list.size());
		assertEquals("aardvark", list.get(0));
		
		list.add("apple");
		assertEquals(3, list.size());
		assertEquals("apple", list.get(1));
		
		list.add("donut");
		assertEquals(4, list.size());
		assertEquals("donut", list.get(3));
		
		try{
			list.add(null);
			fail();
		} catch(NullPointerException e) {
			assertEquals(4, list.size());
		}
		
		try {
			list.add("donut");
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(4, list.size());
		}
	}
	
	/**
	 * Tests SortedList.get()
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		try{
			list.get(0);
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			// The test passes.
		}
		list.add(STRING1);
		list.add(STRING2);
		list.add(STRING3);
		list.add(STRING4);
		try{
			list.get(-1);
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			// The test passes.
		}
		try{
			list.get(list.size());
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			// The test passes.
		}
	}
	
	/**
	 * Tests SortedList.remove()
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		try{
			list.remove(0);
		}
		catch(IndexOutOfBoundsException e)
		{
			// The test passes.
		}
		
		list.add(STRING1);
		list.add(STRING2);
		list.add(STRING3);
		list.add(STRING4);
		try{
			list.remove(-1);
		}
		catch(IndexOutOfBoundsException e)
		{
			// The test passes.
		}
		try{
			list.remove(list.size());
		}
		catch(IndexOutOfBoundsException e)
		{
			// The test passes.
		}
		assertEquals(list.remove(1), STRING4);
		assertEquals(list.remove(2), STRING3);
		assertEquals(list.remove(0), STRING2);
		assertEquals(list.remove(0), STRING1);
	}
	
	/**
	 * Tests SortedList.indexOf()
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		assertEquals(list.indexOf(STRING1), -1);
		
		//list = [ dog(String 2), gianni(String 4), hi(String 1),  jonathan(String 3) ]
		list.add(STRING1);
		list.add(STRING2);
		list.add(STRING3);
		list.add(STRING4);
		//and not in the list
		
		assertEquals(list.indexOf(STRING1), 2);
		assertEquals(list.indexOf(STRING2), 0);
		assertEquals(list.indexOf(STRING11), -1);
		try{
			list.indexOf(null);
			fail();
		}
		catch(NullPointerException e)
		{
			//The test passes.
		}
	}
	
	/**
	 * Tests SortedList.clear()
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		list.add(STRING1);
		list.add(STRING2);
		list.add(STRING3);
		list.add(STRING4);
		list.clear();
		assertEquals(list.size(), 0);
	}

	/**
	 * Tests SortedList.isEmpty()
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		
		list.add(STRING1);
		list.add(STRING2);
		list.add(STRING3);
		list.add(STRING4);
		
		assertEquals(list.isEmpty(), false);
	}

	/**
	 * Tests SortedList.contains();
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		assertEquals(list.contains(STRING11), false);
		list.add(STRING1);
		list.add(STRING2);
		list.add(STRING3);
		list.add(STRING4);
		assertEquals(list.contains(STRING1), true);
		assertEquals(list.contains(STRING2), true);
		assertEquals(list.contains(STRING8), false);
	}
	
	/**
	 * Tests SortedList.equals();
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		list1.add(STRING1);
		list1.add(STRING2);
		list1.add(STRING3);
		list1.add(STRING4);
		
		list2.add(STRING1);
		list2.add(STRING2);
		list2.add(STRING3);
		list2.add(STRING4);
		
		list3.add(STRING5);
		list3.add(STRING6);
		list3.add(STRING7);
		list3.add(STRING8);
		assertEquals(list1.equals(list2), true);
		assertEquals(list2.equals(list1), true);
		assertEquals(list1.equals(list3), false);
	}
	
	/**
	 * Tests SortedList.haschCode();
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		list1.add(STRING1);
		list1.add(STRING2);
		list1.add(STRING3);
		list1.add(STRING4);
		
		list2.add(STRING1);
		list2.add(STRING2);
		list2.add(STRING3);
		list2.add(STRING4);
		
		list3.add(STRING5);
		list3.add(STRING6);
		list3.add(STRING7);
		list3.add(STRING8);
		
		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
	}

}
 