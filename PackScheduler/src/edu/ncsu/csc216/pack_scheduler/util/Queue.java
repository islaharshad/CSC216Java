package edu.ncsu.csc216.pack_scheduler.util;

/**
 * This interface has the basic methods of a queue
 * @author Samuel Ryne Ritter
 * @param <E> The generic object
 */
public interface Queue<E> {

	/**
	 * Adds an element to the front of the queue
	 * @param element The element to be added
	 */
	void enqueue(E element);
	
	/**
	 * Removes the element at the back of the queue
	 * @return The top element
	 */
	E dequeue();
	
	/**
	 * If the queue is empty or not
	 * @return True if the queue is empty
	 */
	boolean isEmpty();
	
	/**
	 * Returns the size of the queue
	 * @return The size of the queue
	 */
	int size();
	
	/**
	 * Sets the capacity of the queue
	 * @param capacity The capacity to be set
	 */
	void setCapacity(int capacity);

}
 