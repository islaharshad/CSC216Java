package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * This class creates a stack using the ArrayList implementation 
 * @author Samuel Ryne Ritter
 * @param <E> The generic object
 */
public class ArrayStack<E> implements Stack<E> {

	/** The list that contains the stack */
	private ArrayList<E> list;
	/** The capacity of the list */
	private int capacity;
	
	/**
	 * This constructs an empty stack with the given capacity
	 * @param capacity The given capacity
	 */
	public ArrayStack(int capacity) {
		list = new ArrayList<E>();
		this.capacity = capacity;
	}
	
	/**
	 * Adds an element to the top of the stack
	 * @param element The element to be added
	 */
	@Override
	public void push(E element) {
		if(size() >= capacity){
			throw new IllegalArgumentException();
		}
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
		if(capacity < 0 || capacity < size()){
			throw new IllegalArgumentException();
		} else {
			this.capacity = capacity;
		}
		
	}

}
