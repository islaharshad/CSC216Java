package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Tests the ArrayList class.
 * 
 * @author Addison Garrigus
 * @author Jonathan
 * @author Michael
 */
public class ArrayListTest {

	/** Tests the constructor for ArrayList, checks for an empty list
	 */
	@Test
	public void testArrayList() {
		ArrayList<Object> list = new ArrayList<Object>();
		assertEquals(0, list.size());
		
	}
	
	/** Tests the add method in ArrayList, adds to the front, middle, end and tests adding a null and a duplicate
	 */
	@Test
	public void testAdd() {
		//Create an empty ArrayList 
		ArrayList<Object> list = new ArrayList<Object>();
		assertEquals(0, list.size());
		//Test adding to an empty ArrayList
		list.add(0, "aaa");
		assertEquals(1, list.size());
		assertEquals(list.get(0), "aaa");
		//Test adding to the end of the ArrayList
		list.add(1, "bbb");
		assertEquals(list.get(0), "aaa");
		assertEquals(list.get(1), "bbb");
		//Test adding to the middle of the ArrayList
		list.add(1, "ccc");
		assertEquals(list.get(0), "aaa");
		assertEquals(list.get(1), "ccc");
		assertEquals(list.get(2), "bbb");
		//Test adding to the front of the ArrayList
		list.add(0, "ddd");
		assertEquals(list.get(0), "ddd");
		assertEquals(list.get(1), "aaa");
		assertEquals(list.get(2), "ccc");
		assertEquals(list.get(3), "bbb");
		//Test adding a null 
		try{
			list.add(0, null);
			fail("added a null");
		} catch(NullPointerException e) {
			assertEquals(4, list.size());
		}
		//Test adding a duplicate 
		try{
			list.add(0, "aaa");
			fail("added a duplicate");
		} catch(IllegalArgumentException e) {
			assertEquals(4, list.size());
		}
		//Test adding to an negative index
		try{
			list.add(-1, "fff");
			fail("added to a negative index");
		} catch(IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		//Test adding to an index greater than size
		try{
			list.add(5, "fff");
			fail("added to an index greater than size");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		//Test adding to size
		list.add(4, "eee");
		assertEquals(list.get(4), "eee");
		assertEquals(list.size(), 5);
		//Test adding with no specified index
		list.add("fff");
		assertEquals(list.get(5), "fff");
		assertEquals(list.size(), 6);
	}
	
	/** Tests the remove method in ArrayList, tests removing from the middle, beginning, end and out of bounds 
	 */
	@Test
	public void testRemove() {
		//Create an ArrayList
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(0, "aaa");
		list.add(0, "bbb");
		list.add(0, "ccc");
		list.add(0, "ddd");
		assertEquals(4, list.size());
		//Test removing from the end of the ArrayList
		assertEquals(list.remove(3), "aaa");
		assertEquals(list.get(0), "ddd");
		assertEquals(list.get(1), "ccc");
		assertEquals(list.get(2), "bbb");
		assertEquals(list.size(), 3);
		//Test removing from the beginning of the ArrayList
		assertEquals(list.remove(0), "ddd");
		assertEquals(list.get(0), "ccc");
		assertEquals(list.get(1), "bbb");
		assertEquals(list.size(), 2);
		//Test removing from out of bounds
		try{
			list.remove(2);
			fail("removed from a nonexistant index");
		} catch(IndexOutOfBoundsException e) {
			assertEquals(list.size(), 2);
			
		}
		//Test removing from the middle of the ArrayList
		list.add(0, "aaa");
		assertEquals(list.remove(1), "ccc");
		assertEquals(list.get(0), "aaa");
		assertEquals(list.get(1), "bbb");
		assertEquals(list.size(), 2);
		
	}
	
	/**
	 * Tests the set method in the ArrayList class
	 */
	@Test
	public void testSet() {
		//Create an ArrayList
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(0, "aaa");
		list.add(0, "bbb");
		list.add(0, "ccc");
		list.add(0, "ddd");
		assertEquals(4, list.size());
		
		//Test setting a null array element
		try {
			list.set(1, null);
		} catch (NullPointerException e) {
			assertEquals("ccc", list.get(1));
		}
		
		//Test setting a duplicate element
		try {
			list.set(1, "aaa");
		} catch (IllegalArgumentException e) {
			assertEquals("ccc", list.get(1));
		}
		
		//Test setting an element out of the bounds of the array
		try {
			list.set(5, "abc");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		
		//Test setting a valid element
		list.set(1, "abc");
		assertEquals("abc", list.get(1));
		
	}
	
	/** Tests the get method for boundary exceptions
	 */
	@Test
	public void testGet() {
		//Create an ArrayList
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(0, "aaa");
		list.add(0, "bbb");
		list.add(0, "ccc");
		assertEquals("ccc", list.get(0));
		assertEquals("bbb", list.get(1));
		assertEquals("aaa", list.get(2));
		//Test an out of bounds call
		try{
			list.get(-1);
			fail("got an invalid index");
		} catch(IndexOutOfBoundsException e) {
			assertEquals(list.size(), 3);
		}
		try{
			list.get(3);
			fail("got an invalid index");
		} catch(IndexOutOfBoundsException e) {
			assertEquals(list.size(), 3);
		}
	}

}
