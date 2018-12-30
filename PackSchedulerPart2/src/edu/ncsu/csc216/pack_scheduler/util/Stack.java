package edu.ncsu.csc216.pack_scheduler.util;

/**
 * This interface has the basic methods of a stack
 * @author Samuel Ryne Ritter
 * @param <E> The generic object
 */
public interface Stack<E> {

	/**
	 * Adds an element to the top of the stack
	 * @param element The element to be added
	 */
	public void push(E element);
	
	/**
	 * Removes the element on top of the stack
	 * @return The top element
	 */
	public E pop();
	
	/**
	 * If the stack is empty or not
	 * @return True if the stack is empty
	 */
	public boolean isEmpty();
	
	/**
	 * Returns the size of the stack
	 * @return The size of the stack
	 */
	public int size();
	
	/**
	 * Sets the capacity of the stack
	 * @param capacity The capacity to be set
	 */
	public void setCapacity(int capacity);
}
