package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;


/**
 * The LinkedList implementation of a queue
 * @author Islahuddin Arshad
 * @param <E> generic object
 */
public class LinkedQueue<E> implements Queue<E> {

	/**The capacity of the LinkedQueue*/
	private int capacity;
	 
	/**The standard call to the LinkedAbstractList*/
	private LinkedAbstractList<E> list;
	
	/**
	 * Constructs the Linked queue with the given capacity
	 * @param capacity The given capacity
	 */
	public LinkedQueue(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
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
