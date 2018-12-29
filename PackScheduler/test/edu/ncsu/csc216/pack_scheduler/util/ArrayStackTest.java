/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

/**
 * Tests the ArrayStack class
 * @author Jonathan Aloba
 */

public class ArrayStackTest {

	/** Integer Object for testing */
	public static final Integer N1 = new Integer(1);
	/** Integer Object for testing */
	public static final Integer N2 = new Integer(2);
	/** Integer Object for testing */
	public static final Integer N3 = new Integer(3);
	/** Integer Object for testing */
	public static final Integer N4 = new Integer(4);
	/** Integer Object for testing */
	public static final Integer N5 = new Integer(5);
	/** Integer Object for testing */
	public static final Integer N6 = new Integer(6);
	
	/** Tests ArrayStack.add() */
	@Test
	public void testPush() {
		//Test  adding to stack
		ArrayStack<Integer> stack = new ArrayStack<Integer>(10);
		assertTrue(stack.isEmpty());
		stack.push(N1);
		assertEquals(1, stack.size());
		stack.push(N2);
		assertEquals(2, stack.size());
		stack.push(N3);
		assertEquals(3, stack.size());
		stack.push(N4);
		assertEquals(4, stack.size());
		stack.push(N5);
		assertEquals(5, stack.size());
		stack.push(N6);
		assertEquals(6, stack.size());
		
		//Test Removing from Stack
		assertEquals(N6, stack.pop());
		assertEquals(5, stack.size());
		assertEquals(N5, stack.pop());
		assertEquals(N4, stack.pop());
		assertEquals(N3, stack.pop());
		assertEquals(N2, stack.pop());
		assertEquals(N1, stack.pop());
		assertEquals(0, stack.size());
		
		//Interleaved pop and pushes
		stack.push(N1);
		stack.push(N2);
		stack.push(N3);
		stack.pop();
		assertEquals(2, stack.size());
		stack.push(N4);
		stack.push(N5);
		stack.pop();
		assertEquals(3, stack.size());
		stack.pop();
		assertEquals(2, stack.size());
		
		//Setting capacity to size
		//Test setting invalid capacity
		stack.setCapacity(2);
				
	}
	
	/**
	 * Tests the invalid cases for ArrayStack
	 */
	@Test
	public void testInvalid() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(5);
		//Removing from empty list
		
		try {
			stack.pop();
			fail();
		} catch(EmptyStackException e) {
			assertEquals(0, stack.size());
		}
		
		stack.push(N1);
		assertEquals(1, stack.size());
		stack.push(N2);
		assertEquals(2, stack.size());
		stack.push(N3);
		assertEquals(3, stack.size());
		stack.push(N4);
		
		//setting capacity less than size
		try {
			stack.setCapacity(1);
			fail();
		} catch(IllegalArgumentException e) {
			//test passes
		}
		
		//setting negative capacity
		try {
			stack.setCapacity(-2);
			fail();
		} catch(IllegalArgumentException e) {
			//test passes
		}
		
	}

}
