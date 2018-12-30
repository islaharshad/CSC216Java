package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

public class LinkedAbstractList<E> extends AbstractList<E> {

	private ListNode front; //First element
	private int size; //size of list
	private int capacity; //maximum size of the list
	
	/**
	 * Constructor of Linked AbstractList.
	 * @param capacity the maximum size of the list
	 * @throws IllegalArgumentException if capacity is non-positive
	 */
	public LinkedAbstractList(int capacity) throws IllegalArgumentException
	{
		front = null;
		size = 0;
		if(capacity > 0)
		{
			this.capacity = capacity;
		}
		else{
			throw new IllegalArgumentException();
		}
		
	}
	
	
	/**
	 *  Adds an element to the list at specified index. All
	 *  elements at or above this index are shifter to the right.
	 * @param index the index of the element
	 * 
	 * @param element the element to be added
	 * 
	 * @throws IndexOutOfBoundsException if 
	 * the index is less than 0 or greater than the size of the list
	 * @throws NullPointerException - If the element is null
	 * 
	 * @throws IllegalArgumentException if the list's size equals
	 * its capacity or the element is already in the list
	 */
	public void add(int index, E element) throws IndexOutOfBoundsException, 
	NullPointerException, IllegalArgumentException
	{
		ListNode currentNode = front;
		ListNode previousNode = null;
		ListNode newNode = new ListNode(element);
		
		if (index < 0 || index > size()) {
			System.out.println(size());
			throw new IndexOutOfBoundsException();
		}
		
		
		if (element == null) {
			throw new NullPointerException();
		}
		
		if (elementIsContained(element))
		{
			throw new IllegalArgumentException();
		}
		
		if(size() == capacity)
		{
			throw new IllegalArgumentException();
		}
		if(front == null)
		{
			front = newNode;
			size++;
			return;
		}
		if(index == 0)
		{
			newNode.next = currentNode;
			front = newNode;
			size++;
			return;
		}
		
		for(int i = 0; i < index; i++)
			{
				previousNode = currentNode;
				currentNode = currentNode.next;
			}
		previousNode.next = newNode;
		newNode.next = currentNode;
		size++; 
	

	}
	/**
	 * Sets value of the specified index to the specified element and
	 * returns the value of the element it's replacing.
	 * 
	 * @param index the specified index
	 * @param element the specified element
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException - if element is already in
	 * the list
	 * @throws IndexOutOfBoundsException if index is negative
	 * or greater than or equal to size
	 * @return the value of the element being replaced
	 */
	public E set(int index, E element) throws NullPointerException,
	IllegalArgumentException, IndexOutOfBoundsException{
		
		
		ListNode currentNode = front;
		ListNode previousNode = null;
		if (element == null) {
			throw new NullPointerException();
		}
		
		if(elementIsContained(element))
		{
			throw new IllegalArgumentException();
		}
		
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if(index == 0)
		{
			front = new ListNode(element);
			front.next = currentNode.next;
			return currentNode.data;
		}
		for(int i = 0; i < index; i++)
		{
			previousNode = currentNode;
			currentNode = currentNode.next;
		}
		previousNode.next = new ListNode(element);
		previousNode.next.next = currentNode.next;
		return currentNode.data;
		
		
		
	}
	
	/**
	 * Removes the element at the specified index.
	 * @param index the index at which element is removed
	 * @throws IndexOutOfBoundsException if the index
	 * is negative or greater than or equal to size
	 * @return the element being removed
	 */
	public E remove(int index) throws IndexOutOfBoundsException{
		
		ListNode current = null;
		ListNode removedNode = null;
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		if (index == 0) {
			removedNode = front;
			front = front.next;
			size--;
			return removedNode.data;
		}
		current = front;
		
		for (int i = 0; i < index - 1; i++) {
			current = current.next;
			System.out.println(current.data);
			}
		ListNode removedElement = current.next;
		current.next = current.next.next;
		size--;
		return removedElement.data;
		
	}
	

	
	/**
	 * Returns the element at the specified index.
	 * @param index the specified index
	 * @throws IndexOutOfBoundsException if index is negative
	 * or greater than or equal to size
	 * @return the element at index
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		ListNode current = front;
		int currentIndex = size;
		
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		if(current.next== null)
		{
			return current.data;
		}
		for(int i = 0; i < index; i++)
		{ 	
			current = current.next;
		}
		return  current.data;
	}


	/**
	 * Returns size of the list.
	 * @return the size of the list
	 */
	@Override
	
	public int size() {
		return size;
	}
	
	/**
	 * Helper method which determines whether an element
	 * is already in the list or not.
	 * @param element element in question
	 * @return true if element is in list, false otherwise
	 */
	public boolean elementIsContained(E element)
	{
		ListNode currentNode = front;
		for (int i = 0; i < size(); i++) {
			if(currentNode.data.equals(element))
			{
				return true;
			}
			currentNode = currentNode.next;
		}
		return false;
	}
	
	public int indexAt(E element)
	{
		ListNode currentNode = front;
		for (int i = 0; i < size(); i++) {
			if(currentNode.data.equals(element))
			{
				return i;
			}
			currentNode = currentNode.next;
		}
		return -1;
	}
	
	/**
	 * Inner class which represents a node in the linked list.
	 *
	 */
	public class ListNode {
		
		
		public E data; //data contained in node.
		
		public ListNode next; //node which this links to.
		
		
		/**
		 * Constructor of ListNode. 'next' is null
		 * @param data element contained in node
		 */
		public ListNode(E data) {
			this.data = data; 
		}
		
		/**
		 * Constructor of ListNode.
		 * @param data element contained in node
		 * @param next node which is linked
		 */
		public ListNode(E data, ListNode next) {
			this.data = data; 
			this.next = next;
		}
		
	}

}
