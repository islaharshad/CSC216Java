/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests the LinkedQueue class
 * @author Jonathan Aloba
 *
 */
public class LinkedQueueTest {

	/**
	 * Tests the constructor of the LinkedQueue class
	 */
	@Test
	public void testLinkedQueue() {
		LinkedQueue<String> queue = new LinkedQueue<String>(10);
		assertTrue(queue.isEmpty());
		
		//Adding single element
		queue.enqueue("first");
		assertEquals(1, queue.size());
		
		//Adding multiple elements
		queue.enqueue("second");
		queue.enqueue("third");
		queue.enqueue("fourth");
		assertEquals(4, queue.size());
		
		//Removing single element
		assertEquals("first", queue.dequeue());
		
		//Removing multiple elements and last element
		assertEquals("second", queue.dequeue());
		assertEquals("third", queue.dequeue());
		assertEquals("fourth", queue.dequeue());
		
		//Interleaved inserts and removes
		queue.enqueue("first");
		queue.enqueue("second");
		queue.enqueue("third");
		assertEquals("first", queue.dequeue());
		assertEquals(2, queue.size());
		queue.enqueue("fourth");
		queue.enqueue("fifth");
		assertEquals("second", queue.dequeue());
		assertEquals(3, queue.size());
		queue.enqueue("sixth");
		assertEquals("third", queue.dequeue());
		assertEquals(3, queue.size());
		queue.enqueue("seventh");
		queue.enqueue("eighth");
		assertEquals(5, queue.size());
		
		//Setting capacity to size
		try {
			queue.setCapacity(5);
			//Test Passes
		} catch (IllegalArgumentException e) {
			fail();
		}
	}
	
	/**
	 * Tests the invalid cases of LinkedQueue
	 */
	@Test
	public void testInvalid() {
		LinkedQueue<String> queue = new LinkedQueue<String>(10);
		
		//Removing from empty queue
		try {
			queue.dequeue();
			fail();
		} catch(NoSuchElementException e) {
			assertEquals(0, queue.size());
		}
		
		//Setting capacity less than size
		queue.enqueue("second");
		queue.enqueue("third");
		queue.enqueue("fourth");
		try {
			queue.setCapacity(2);
			fail();
		} catch(IllegalArgumentException e) {
			//Test passes
		}
		
		//Test setting a negative capacity
		try {
			queue.setCapacity(-2);
			fail();
		} catch(IllegalArgumentException e) {
			//Test passes
		}
	}
	}
