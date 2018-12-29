package edu.ncsu.csc216.pack_scheduler.util;

/**
 * LinkedListRecursive maintains a Linked list of elements
 * using recursive methods 
 * @author Samuel Ryne Ritter
 *
 * @param <E> a given generic element
 */
public class LinkedListRecursive<E> {

	/**The front node*/
	private ListNode front;
	/**The size of the linkedList*/
	private int size;
	
	/**
	 * Constructs this linked list
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}
	
	/**
	 * Tells if the list contains the passed element
	 * @param element The element to check if it is in the list
	 * @return True if the element is contained in the list
	 */
	public boolean contains(E element) {
		if(isEmpty()) {
			return false;
		}
		ListNode temp = front;
		if(temp.data.equals(element)) { //checks if the front is empty
			return true;
		} else {
			return temp.contains(element);
		}
	}
	
	/**
	 * Adds an element to the end of the linked list
	 * @param element The element to be added
	 * @return True if the element was added
	 * @throws IllegalArgumentException if given element is a duplicate
	 */
	public boolean add(E element) {
		if(contains(element)) {
			throw new IllegalArgumentException();
		}
		
		if(isEmpty()) {
			front = new ListNode(element, null);///base case is if the list is empty
			size++;
			return true;
		} else {
			return front.add(element);
		}
	}
	
	/**
	 * Adds an element at a specific index.
	 * @param index The index where the element will be added
	 * @param element The element to be added
	 * @return True if the element was added
	 * @throws IllegalArgumentException if given element is a duplicate
	 */
	public boolean add(int index, E element) {
		if(contains(element)) {
			throw new IllegalArgumentException();
		}
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if(element == null) {
			throw new NullPointerException();
		}
		
		if(index == 0) {    //base case is if index is 0
			ListNode temp = front;
			front = new ListNode(element, temp);
			size++;
			return true;
		} else {
			return front.add(index, element);
		}
	}
	
	/**
	 * Returns the value of an element at the given index 
	 * @param index The given index
	 * @return The value of an element at the given index
	 */
	public E get(int index) {
		if(index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0) {
			return front.data; ///base case is if index is 0
		}
		return front.get(index);
	}
	
	/**
	 * Removes the element at the given index
	 * @param index The given index
	 * @return The element removed
	 */
	public E remove(int index) {
		if(index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0) { ///base case is if index is 0
			ListNode temp = front;
			front = front.next;
			size--;
			return temp.data;
		} else {
			return front.remove(index);
		}
	}
	
	/**
	 * Removes the given element from the list
	 * @param element The given element
	 * @return True if the element was removed
	 */
	public boolean remove(E element) {
		if(element == null || isEmpty()) {
			return false;
		}
		
		if(front.data.equals(element)) {///checks if the front element is to be removed
			front = front.next;
			size--;
			return true;
		} else {
			return front.remove(element);
		}
	}
	
	/**
	 * Sets the element at the given index to the given element
	 * @param index The given index
	 * @param element The given element
	 * @return The element that was removed
	 */
	public E set(int index, E element) {
		if(index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		if(element == null) {
			throw new NullPointerException();
		}
		if(contains(element)) {
			throw new IllegalArgumentException();
		}
		
		if(index == 0){
			
			E temp = front.data;
			front.data = element; //base case is if index is 0
			return temp;
		} else {
			return front.set(index, element);
		}
	}
	
	/**
	 * Returns if the list is empty
	 * @return True if the list is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns the size of the list
	 * @return The size of the list
	 */
	public int size() {
		return size;
	}
	
	
	/** The inner class that holds element data and points to the next node
	 * @author Islahuddin Arshad
	 */
	private class ListNode {
		/** The data that is held in the ListNode */
		private E data;
		/** The path to the next ListNode */
		private ListNode next;
		
		
		/** Creates a new ListNode and also sets the next field to a desired ListNode
		 * @param data - the desired element data
		 * @param next - the following ListNode
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
		/**
		 * Uses recursion to tell if the list contains the passed element
		 * @param element The element to check if it is in the list
		 * @return True if the element is contained in the list
		 */
		private boolean contains(E element) {
			if(next != null) {
				if(next.data.equals(element)) {
					return true;
				} else {
					return next.contains(element);
				}
				
			} 
			return false;
		}
		
		/**
		 * Uses recursion to add an element to the end of the linked list
		 * @param element The element to be added
		 * @return True if the element was added
		 */
		private boolean add(E element) {
			if(this.next != null) {
				return next.add(element);
			} else {
				next = new ListNode(element, null);
				size++;
				return true;
			}
		}
		
		/**
		 * Uses recursion to add an element at a specific index.
		 * @param index The index where the element will be added
		 * @param element The element to be added
		 * @return True if the element was added
		 */
		private boolean add(int index, E element) {
			if(index != 1){
				return next.add(index - 1, element);
			} else {
				ListNode temp = next;
				next = new ListNode(element, temp);
				size++;
				return true;
			}
		}
		
		/**
		 * Uses recursion to return the value of an element at the given index 
		 * @param index The given index
		 * @return The value of an element at the given index
		 */
		private E get(int index) {
			if(index != 1 && next.data != null) {
				return next.get(index - 1);
			} else {
				return next.data;
			}
		}
		
		/**
		 * Uses recursion to remove the element at the given index
		 * @param index The given index
		 * @return The element removed
		 */
		private E remove(int index) {
			if(index != 1) {
				return next.remove(index - 1);
			} else {
				ListNode temp = next;
				this.next = next.next;
				size--;
				return temp.data;				
			}
		}
		
		/**
		 * Uses recursion to remove the given element from the list
		 * @param element The given element
		 * @return True if the element was removed
		 */
		private boolean remove(E element) {
			if(next != null && !next.data.equals(element)) {
				return next.remove(element);
			} else {
				if(next != null && next.data.equals(element)) {
					this.next = next.next;
					size--;
					return true;
				} else {
					return false;
				}
								
			}
		}
		
		/**
		 * Uses recursion to set the element at the given index to the given element
		 * @param index The given index
		 * @param element The given element
		 * @return The element that was removed
		 */
		private E set(int index, E element) {
			if(index != 1) {
				return next.set(index - 1, element);
			} else {
				E temp = next.data;
				next.data = element;
				return temp;
			}
		}
		
		@SuppressWarnings("unused")
		public E getData() {
			return data;
		}
	}
}
