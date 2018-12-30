package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * LinkedList maintain a Linked list of elements
 * 
 * @author Jonathan Aloba
 *
 * @param <E> a given generic element
 */
public class LinkedList<E>  extends AbstractSequentialList<E> {

    /**The front node*/
	private ListNode front;
	/**The back node*/
	private ListNode back;
	/**The size of the linkedList*/
	private int size;
	
	/**
	 * Constructs an instance of LinkedList with values of for all fields.
	 */
	public LinkedList() {
		
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.previous = front;
		this.size = 0;
		
	}
	
	/**
	 * Returns an instance of LinkedListIterator.
	 * @param index The index that LinkedListIterator iterates to upon construction 
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		LinkedListIterator iterator = new LinkedListIterator(index); 
		return iterator;
	}

	/**
	 * Adds an element at a specific index.
	 * @param index The index where the element will be added
	 * @param element The element to be added
	 * @throws IllegalArgumentException if given element is a duplicate
	 */
	@Override
	public void add(int index, E element) {
		if(this.contains(element)) {
			throw new IllegalArgumentException();
		}
		
		listIterator(index).add(element);
	}

	@Override
	public E set(int index, E element) {
		E replaced = null;
		if(this.contains(element)) {
			throw new IllegalArgumentException();
		}
		
		if(front.next.equals(back) && back.previous.equals(front)) {
			throw new IndexOutOfBoundsException();
		}
		
		ListIterator<E> iterator = listIterator(index);
		if(index == 0) {
			ListNode temp = front.next;
			replaced = front.next.getData();
			front.next = new ListNode(element, front, temp.next);
			
		} else if(index == size) {
			replaced = back.previous.getData();
			ListNode temp = back.previous;
			back.previous = new ListNode(element, temp.previous, back);
		} else {
			iterator.next();
			replaced = iterator.previous();
			iterator.set(element);
		} 
		
		return replaced;
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Iterator for LinkedList.
	 * 
	 * @author Jonathan Aloba
	 *
	 */
	private class LinkedListIterator implements ListIterator<E> {
		
		/**the ListNode returned when called previous method*/
		private ListNode previous;
		/**the ListNode returned when called the next method*/
		private ListNode next;
		/**The index returned when called the previousIndex method*/
		private int  previousIndex;
		/**The index returned when called the nextIndex method*/
		private int nextIndex;
		/**represents last ListNode that was returned when called either the next, previous or null nodes*/
		private ListNode lastRetrieved;
		
		/**
		 * Constructs an instance of LinkedListIterator at specific index such that
		 * a call to LinkedListIterator.next() would return the element at the given
		 * index
		 * @param index The index that LinkedListIterator iterates to upon construction
		 */
		public LinkedListIterator(int index) {
			if (index < 0 || index > size){
				throw new IndexOutOfBoundsException();
			}
			
			ListNode current = front;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			
			previous = current;
			previousIndex = index - 1;
			next = current.next;
			nextIndex = index;
			lastRetrieved = null;
 		}
		
		/**
		 * Adds an element to the LinkedList such that a call to LinkedListIterator.next()
		 * would return the added element.
		 * @param element The element to be added
		 */
		@Override
		public void add(E element) {
			if(element == null) {
				throw new NullPointerException();
			}
			ListNode temp = next;
			ListNode l = new ListNode(element, temp.previous, temp.next);
			l.previous = temp.previous;
			l.next = temp;
			l.next.previous = l;
			l.previous.next = l;
			previous = l.previous;
			next = l;
			size++;
			lastRetrieved = null;
		}

		/**
		 * Returns whether or not the next element in LinkedListIterator is null
		 * @return false if element is null, true if it is not null
		 */
		@Override
		public boolean hasNext() {
			if(next.data == null) {
				return false;
			}
			return true;
		}

		/**
		 * Returns whether or not the previous element in LinkedListIterator is null
		 * @return false if element is null, true if it is not null
		 */
		@Override
		public boolean hasPrevious() {
			if(previous.data == null) {
				return false;
			}
			return true;
		}

		/**
		 * Returns the next element.
		 * @return the next element
		 * @throws NoSuchElementException if next element is null
		 */
		@Override
		public E next() {
			if(next.data == null) {
				throw new NoSuchElementException();
			}
			
			lastRetrieved = next;
			previous = next;
			next = next.next;
			nextIndex++;
			previousIndex++;
			return previous.data;
		}

		/**
		 * Returns the index of the next element.
		 * @return the index of the next element
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}

		/**
		 * Returns the previous element
		 * @return the previous element
		 */
		@Override
		public E previous() {
			if(previous.data == null) {
				throw new NoSuchElementException();
			}
			
			lastRetrieved = previous;
			next = previous;
			previous = previous.previous;
			nextIndex--;
			previousIndex--;
			return next.data;
		}

		/**
		 * Returns the index of the previous element.
		 * @return the index of the previous element
		 */
		@Override
		public int previousIndex() {
			return previousIndex;
		}

		/**
		 * Removes the lastRetrieved element from the list
		 * @throws IllegalStateException if there has been no call to next() or previous() 
		 * prior to calling this method.
		 */
		@Override
		public void remove() {
			if(lastRetrieved == null) {
				throw new IllegalStateException();
			}
			
			next = lastRetrieved.next;
			previous = lastRetrieved.previous;
			lastRetrieved.next.previous = lastRetrieved.previous;
			previous.next = next;
			size--;
			lastRetrieved = null;
			
		}

		/**
		 * Set's the value of the lastRetrieved element to a given element
		 * @param element The element to be set
		 * @throws IllegalStateException if there has been no call to next() or previous() 
		 * prior to calling this method.
		 * @throws NullPointerException if element is null
		 */
		@Override
		public void set(E element) {
			if(lastRetrieved == null) {
				throw new IllegalStateException();
			}
			if(element == null) {
				throw new NullPointerException();
			}
			
			lastRetrieved.data = element;
		}
		
	}
	
	
	/** The inner class that holds element data and points to the next node
	 * @author Islahuddin Arshad
	 */
	private class ListNode {
		/** The data that is held in the ListNode */
		private E data;
		/** The path to the next ListNode */
		private ListNode next;
		/**The path to the previous ListNode*/
		private ListNode previous;
		
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
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.next = next;
			this.previous = prev;
		}
		
		@SuppressWarnings("unused")
		public E getData() {
			return data;
		}
	}


}
