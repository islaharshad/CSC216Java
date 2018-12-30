/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Creates a linked abstract list.
 * 
 * @author AddisonGarrigus
 * @param <E> The object being added to the list
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	/** The first node in the Linked List */
	private ListNode front;
	/** The size of the Linked List */
	private int size;
	/** The capacity of the Linked List */
	private int capacity;
	/** The back field points to the end of the LinkedList*/
	private ListNode back;

	/** The constructor for the LinkedAbstractList class, sets front to null and size to zero
	 * @param capacity - the total number of nodes the list can currently hold
	 */
	public LinkedAbstractList(int capacity) {
		this.front = null;
		this.size = 0;
		if (capacity > 0) {
			this.capacity = capacity;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	
	
	/**
	 * Adds an element to the list at given index
	 * 
	 * @param index the index where the element will be added
	 * @param element element to be added
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void add(int index, Object element) {
		if (size == capacity) {
			throw new IllegalArgumentException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		
		for(int i = 0; i < size; i++) {
			if(element.equals(get(i))) {
				throw new IllegalArgumentException();
			}
		}
		
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		

		if(front == null){
			//Add to Empty List
			front = new ListNode((E) element);
		} else if(index == 0) {
			//Add to Front
			ListNode temp = front;
			front = new ListNode((E) element, temp);
		} else if(index == size){
			//Add to end
			ListNode current = front;
			while(current.next != null) {
				current = current.next;
				
			}
			back = current;
			back.next = new ListNode((E) element);
		} else {
			//Add to middle
			ListNode current = front;
			ListNode temp = null;
		
			for(int i = 0; i <= index - 1; i++) {
				if(i < index - 1){
					current = current.next;
				} else {
					temp = current;
					current.next = new ListNode((E) element, temp.next);
				} 
			}
		}
		size++;
	}

	
	/**
	 * Adds an element at end of list
	 */
	@Override
	public boolean add(Object element) {
		add(size, element);
		return true;
		
	}

	/**
	 * Remove
	 */
	@Override
	public E remove(int index) {
		
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		//Remember to handle separately removing from front of list, end of list, and middle of list
		ListNode removed = null;
		
		if(index == 0) {
			removed = front;
			front = front.next;
		} else {
			ListNode current = front;
			for(int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			back = current;
			removed = back.next;
			back.next = removed.next;
		}
		
		size--;
		return (E) removed.data;
	}
	
	/**
	 * Sets value for element at given index
	 * @param index the index where element will be set
	 * @param element the element to be set
	 */
	@SuppressWarnings("unchecked")
	@Override 
	public E set(int index, Object element) {
		if(element == null) {
			throw new NullPointerException();
		}
		
		ListNode current = front;
		for(int i = 0; i < size(); i++) {
			if(current.data.equals(element)) {
				throw new IllegalArgumentException();
			}
			
			current = current.next;
		}
		
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		current = front;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		E ret = current.data;
		current.data = (E)element;
		
		return ret;
	}

	
	/** Returns the object at the given index
	 * @param idx The index of the object that is being pulled
	 */
	@Override
	public E get(int idx) {
		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
	
		ListNode current = front;
		
		if(idx == 0) {
			return current.data;
		} else {
		
			for(int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			
			return (E) current.next.data;
		}
	}

	/** Returns the size of the LinkedAbstractList
	 * @return The size of the LinkedAbstractList
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Sets the capacity of the list
	 * @param capacity The capacity of the list
	 */
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < size) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}
	
	/** The inner class that holds element data and points to the next node
	 * @author Addison
	 */
	private class ListNode {
		/** The data that is held in the ListNode */
		private E data;
		/** The path to the next ListNode */
		private ListNode next;
		
		/** Creates a new ListNode with the desired element data
		 * @param data - the desired element data
		 */
		public ListNode(E data) {
			this.data = data;
		}
		
		/** Creates a new ListNode and also sets the next field to a desired ListNode
		 * @param data - the desired element data
		 * @param next - the following ListNode
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

}
