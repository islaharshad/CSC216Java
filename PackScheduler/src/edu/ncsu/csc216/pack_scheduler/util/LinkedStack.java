package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * This class creates a stack using the LinkedAbstractList implementation 
 * @author Samuel Ryne Ritter
 * @param <E> The generic object
 */
public class LinkedStack<E> implements Stack<E> {

	/** The list that contains the stack */
	private LinkedAbstractList<E> list;
	
	/**
	 * This constructs an empty stack with the given capacity
	 * @param capacity The given capacity
	 */
	public LinkedStack(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
	}
	
	/**
	 * Adds an element to the top of the stack
	 * @param element The element to be added
	 */
	@Override
	public void push(E element) {
		list.add(element);
	}

	/**
	 * Removes and returns the element on the top of the stack
	 * @return The element removed
	 */
	@Override
	public E pop() {
		if(size() <= 0) {
			throw new EmptyStackException();
		}
		return list.remove(size() - 1);
	}

	/**
	 * Returns if the stack is empty or not
	 * @return True if the stack is empty
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns the size of the stack
	 * @return The size of the stack
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the capacity of the stack
	 * @param capacity The capacity to be set
	 */
	@Override
	public void setCapacity(int capacity) {
		list.setCapacity(capacity);
	}

}
