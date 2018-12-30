package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Sorted list class using various methods 
 * @author Samuel Ryne Ritter, Wil Elias, Prem Subedi
 *
 */
public class SortedListTest {

	/**Test if it is possible to create 11 element list
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		// Test that the list grows by adding at least 11 elements
		//Remember the list's initial capacity is 10
		list.add("cow");
		list.add("dingo");
		list.add("joe");
		list.add("fred");
		list.add("shoe");
		list.add("hat");
		list.add("shirt");
		list.add("cat");
		list.add("cactus");
		list.add("wombat");
		try{
			list.add("dog");
			assertEquals(11, list.size());
		} catch(IndexOutOfBoundsException e) {
			fail();
		}
	}

	/**Tests the add method in SortedList by adding several elements to different places in the list,
	 * as well as adding a null element, and a duplicate element
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		//Test adding to the front, middle and back of the list
		list.add("apple");
		assertEquals("apple", list.get(0));
		list.add("az");
		assertEquals("az", list.get(1));
		list.add("cow");
		assertEquals("cow", list.get(3));
		// Test adding a null element
		try{
			list.add(null);
			fail();
		} catch(NullPointerException e)
		{ 
			//Skip Line		
		}
		//Test adding a duplicate element
		try{
			list.add("banana");
			fail();
		} catch(IllegalArgumentException e)
		{
			//Skip Line	
			
		}
	}
	
	/** Tests the get method in SortedList by testing boundary values as well as getting values from an empty
	 * list
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//Test getting an element from an empty list
		try{
			list.get(0);
			fail();
		} catch(IndexOutOfBoundsException e){
			//Skip Line
		}
		
		// Add some elements to the list
		list.add("banana");
		list.add("apple");
		// Test getting an element at an index < 0
		try{
			list.get(-1);
			fail();
		} catch(IndexOutOfBoundsException e){
			//Skip Line
		}
		// Test getting an element at size
		try{
			list.get(2);
			fail();
		} catch(IndexOutOfBoundsException e){
			//Skip Line
		}
	}
	
	/** Tests the remove method in SortedList by trying to remove from an empty list, removing various valid indices, 
	 * removing boundary values
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//Test removing from an empty list
		try{
			list.remove(0);
			fail();
		} catch(IndexOutOfBoundsException e){
			//Skip Line
		}
		// Add some elements to the list - at least 4
		list.add("cow");
		list.add("dingo");
		list.add("joe");
		list.add("fred");
		//Test removing an element at an index < 0
		try{
			list.remove(-1);
			fail();
		} catch(IndexOutOfBoundsException e){
			//Skip Line
		}
		//Test removing an element at size
		try{
			list.remove(4);
			fail();
		} catch(IndexOutOfBoundsException e){
			//Skip Line
		}
		//Test removing a middle element
		list.remove(1);
		assertFalse(list.contains("dingo"));
		// Test removing the last element
		list.remove(2);
		assertFalse(list.contains("joe"));
		//Test removing the first element
		list.remove(0);
		assertFalse(list.contains("cow"));
		// Test removing the last element
		list.remove(0);
		assertFalse(list.contains("fred"));
	}
	
	/** Tests the indexOf method of SortedList by testing boundary values, the indexOf an element in an empty list,
	 * the indexOf null, and the indexOf valid elements
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//Test indexOf on an empty list
		assertEquals(-1, list.indexOf(""));
		//Add some elements
		list.add("ManchesterUnited");
		list.add("NFL");
		assertEquals(0, list.indexOf("ManchesterUnited"));
		assertEquals(1, list.indexOf("NFL"));
		//Test various calls to indexOf for elements in the list
		//and not in the list
		assertEquals(-1, list.indexOf("apple"));
		
		//Test checking the index of null
		try {
		   list.indexOf(null);
		   fail();
		} catch(NullPointerException e) 
		{
			//Skip Line
		}	
	}
	
	/**Tests the clear method of SortedList by adding elements to a SortedList then clearing it and checking its size
	 *
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();
		

		// Add some elements
		list.add("shoe");
		list.add("hat");
		list.add("shirt");
		
		// Clear the list
		list.clear();
		//Test that the list is empty
		assertEquals(0, list.size());
	
	}

	/**Tests the isEmpty method of SortedList by evaluating an empty list and a filled list
	 * 
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		// Test that the list starts empty
		assertTrue(list.isEmpty());
		// Add at least one element
		list.add("banana");
		// Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**Test the contains method of SortedList by checking an empty list as well as valid and invalid elements
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//Test the empty list case
		assertFalse(list.contains(" "));
		//Add some elements
		list.add("banana");
		list.add("dog");
		list.add("spaghetti");
		//Test some true and false cases
		assertTrue(list.contains("banana"));
		assertTrue(list.contains("dog"));
		assertTrue(list.contains("spaghetti"));
		assertFalse(list.contains("cat"));
		assertFalse(list.contains("apple"));
		assertFalse(list.contains("ziti"));
	}
	
	/**Tests the equals method of SortedList by comparing equal and unequal lists
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("banana");
		list2.add("banana");
		list1.add("cat");
		list2.add("cat");
		list3.add("unique");
		//Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertFalse(list2.equals(list3));
		assertFalse(list1.equals(list3));
		
	}
	
	/**Tests the hashCode method of SortedList, with identical and different lists
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("banana");
		list2.add("banana");
		list1.add("cat");
		list2.add("cat");
		list3.add("unique");
		//Test for the same and different hashCodes
		assertTrue(list1.hashCode() == list2.hashCode());
		assertFalse(list1.hashCode() == list3.hashCode());
		assertFalse(list2.hashCode() == list3.hashCode());
	}

}
 