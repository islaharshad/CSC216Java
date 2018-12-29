/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test the LinkedList class
 * 
 * @author Jonathan Aloba
 *
 */
public class LinkedListTest {
	/**	hi */
	public static final String STRING1 = "hi";
	/** hello */
	public static final String STRING2 = "hello";
	/** howdy */
	public static final String STRING3 = "howdy";
	/** hey there */
	public static final String STRING4 = "hey there";
	
	/**
	 * Tests LinkedList.add()
	 */
	@Test
	public void testLinkedListAdd() {
		LinkedList<String> list = new LinkedList<String>();
		assertEquals(0, list.size());
		list.add(0, STRING1);
		assertEquals(1, list.size());
		//Test adding multiple elements
		list.add(1, STRING2);
		list.add(2, STRING3);
		list.add(3, STRING4);
		assertEquals(4, list.size());
		
		//Test adding identical element
		try {
			
			list.add(4, STRING4);
		} 
		catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
		}
		//test adding a null
		
		try {
			list.add(4, null);
		}
		catch (NullPointerException e) {
			assertEquals(4, list.size());
		}
		
	} 
	
	/**
	 * TestsLinkedList.set
	 */
	@Test
	public void testLinkedListSet() {
		LinkedList<String> list = new LinkedList<String>();
		list.add(0, STRING1);
		list.add(1, STRING2);
		list.add(2, STRING3);
		list.add(3, STRING4);
		list.set(0, "something"); //adding at index 0
		assertEquals("something", list.get(0));
		list.set(1, "somethingelse");
		assertEquals("somethingelse", list.get(1));
		
		try {
			list.set(0, null);
		}
		catch (NullPointerException e) {
			assertEquals(4, list.size());
		}
		
		try {
			list.set(3, null);
			}
		catch (NullPointerException e) {
			assertEquals(4, list.size());
			
		}
	}
	
	/**
	 * Tests LinkedList.remove()
	 */
	@Test
	public void testLinkedListRemove() {
		LinkedList<String> list = new LinkedList<String>();
		assertEquals(0, list.size());
		list.add(0, STRING1);
		assertEquals(1, list.size());
		//Test adding multiple elements
		list.add(1, STRING2);
		list.add(2, STRING3);
		list.add(3, STRING4);
		assertEquals(4, list.size());
		list.remove(1);
		assertEquals(STRING3, list.get(1));
		assertEquals(STRING1, list.get(0));
		
		//Test adding identical element
		try {
			
			list.add(3, STRING4);
		}
		catch (IllegalArgumentException e) {
			assertEquals(3, list.size());
		}
		//test adding a null
		
		try {
			list.add(3, null);
		}
		catch (NullPointerException e) {
			assertEquals(3, list.size());
		}
		
	} 
}