package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;


/**
* The ArrayList implementation of a queue
* @author Islahuddin Arshad
* @param <E> generic object
*/
public class ArrayQueue<E> implements Queue<E> {

	/**The capacity of the ArrayQueue*/
	private int capacity;
	
	/**The standard call to the LinkedAbstractList*/
	private ArrayList<E> list;
	
	/**
	 * Constructs an instance of ArrayQueue with values for all fields.
	 * @param capacity the capacity of the ArrayQueue
	 */
	public ArrayQueue(int capacity) {
		list = new ArrayList<E>();
		setCapacity(capacity);
	}
	
	/**
	 * Adds an object element at the back of the queue
	 * @param element the element to be added
	 * @throws IllegalArgumentException if the queue has reached capacity
	 */
	@Override
	public void enqueue(Object element) {
		
		if (list.size() == capacity) {
			throw new IllegalArgumentException();
		}
		list.add(list.size(), element);
	}
	
	

	/**
     * Removes the element from the beginning of the queue
     * @throws NoSuchElementException if the queue is empty
     */
	@SuppressWarnings("unchecked")
	@Override
	public E dequeue() {
		if (list.isEmpty()) {
			throw new NoSuchElementException();
		}
		Object front = list.get(0);
		list.remove(0);
		return (E) front;
	}

	/**
	 * Checks whether the queue is empty or not
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
 
	/**
	 * Returns the size of the queue
	 */
	@Override
	public int size() {
		return list.size();
	}

	
	/**
	 * Sets the capacity of the queue 
	 * @param capacity the capacity of the queue
	 * @throws IllegalArgumentException if the capacity is negative
	 * and is less than the size of the queue
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
		
	}

}
