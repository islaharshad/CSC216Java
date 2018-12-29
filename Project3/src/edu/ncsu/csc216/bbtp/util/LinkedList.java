package edu.ncsu.csc216.bbtp.util;

import java.io.Serializable;

/**
 * Creates a Linked list which uses nodes to link objects together
 * 
 * @author Samuel Ryne Ritter
 *
 */
public class LinkedList implements List, Serializable {

	/** Serial version UID. */
	private static final long serialVersionUID = 349987L;
	/** The front node */
	private Node front;
	/** The size of the list */
	private int size;

	/**
	 * Creates a new empty Linked list
	 */
	public LinkedList() {
		front = null;
	}

	/**
	 * Returns the size of the list
	 * 
	 * @return The size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns if the list is empty
	 * 
	 * @return True if the list is empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns if the list contains the given object
	 * 
	 * @param o
	 *            The given object
	 * @return True if the list contains the object
	 */
	@Override
	public boolean contains(Object o) {
		Node current = front;
		if (size == 0 || o == null) {
			return false;
		}
		while (current != null) {
			if (current.value.equals(o)) {
				return true;
			} else {
				current = current.next;
			}
		}
		return false;
	}

	/**
	 * Adds the given object to the end of the list
	 * 
	 * @param o
	 *            The given object
	 * @return Returns True if the object was added
	 */
	@Override
	public boolean add(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		if (contains(o)) {
			throw new IllegalArgumentException();
		}
		if (front == null) {
			front = new Node(o, null);
			size++;
			return true;
		}
		Node current = front;
		while (current.next != null) {
			current = current.next;
		}
		current.next = new Node(o, null);
		size++;
		return true;
	}

	/**
	 * Returns the object at the given index
	 * 
	 * @param index
	 *            The given index
	 * @return The object at the given index
	 */
	@Override
	public Object get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			return front.value;
		}
		Node current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.value;

	}

	/**
	 * Adds the given object to the list at the given index
	 * 
	 * @param index
	 *            The given index
	 * @param element
	 *            The given object
	 */
	@Override
	public void add(int index, Object element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		Node temp = null;
		if (index == 0) {
			temp = front;
			front = new Node(element, temp);
			size++;
		} else {
			Node current = front;
			for (int i = 0; i < index; i++) {
				temp = current;
				current = current.next;
			}
			Node newNode = new Node(element, current);
			temp.next = newNode;
			size++;
		}
	}

	/**
	 * Removes the object at the given index
	 * 
	 * @param index
	 *            The given index
	 * @return The removed object
	 */
	@Override
	public Object remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		Node temp = null;
		if (index == 0) {
			temp = front;
			front = front.next;
			size--;
			return temp.value;
		}
		Node current = front;
		for (int i = 0; i < index; i++) {
			temp = current;
			current = current.next;
		}
		temp.next = current.next;
		size--;
		return current.value;
	}

	/**
	 * Returns the index of the given object
	 * 
	 * @param o
	 *            The given object
	 * @return The index of the given object
	 */
	@Override
	public int indexOf(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		Node current = front;
		for (int i = 0; i < size; i++) {
			if (current.value.equals(o)) {
				return i;
			}
			current = current.next;
		}
		return -1;
	}

	/**
	 * The nodes that hold the data within the linked list
	 * 
	 * @author Samuel Ritter
	 *
	 */
	public class Node implements Serializable {

		/** Serial version UID */
		private static final long serialVersionUID = 484909840L;
		/** The data the node holds */
		private Object value;
		/** The next node in the list */
		private Node next;

		/**
		 * Creates a node that holds data and a link to the next node
		 * 
		 * @param o
		 *            The data
		 * @param n
		 *            The next node
		 */
		public Node(Object o, Node n) {
			value = o;
			next = n;
		}
	}
}
