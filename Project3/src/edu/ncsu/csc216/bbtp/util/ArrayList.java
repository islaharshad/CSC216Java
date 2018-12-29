package edu.ncsu.csc216.bbtp.util;

import java.io.Serializable;

/**
 * Creates an arraylist of objects that acts like a regular arraylist
 * 
 * @author Samuel Ryne Ritter
 *
 */
public class ArrayList implements List, Serializable {

	/** Serial version UID */
	private static final long serialVersionUID = 28592L;
	/** The size of the list */
	private int size;
	/** The array used as the object list */
	private Object[] list;
	/** Integer for resizing */
	private static final int RESIZE = 1000; // ????????

	/**
	 * Constructs a new empty ArrayList
	 */
	public ArrayList() {
		list = new Object[RESIZE];
	}

	/**
	 * Creates a new empty ArrayList
	 * 
	 * @param initialize
	 *            The initializing index
	 */
	public ArrayList(int initialize) {
		if (initialize <= 0) {
			throw new IllegalArgumentException();
		}
		list = new Object[initialize];
	}

	/**
	 * Returns the size of the list
	 * 
	 * @return The size of the list
	 */
	@Override
	public int size() {
		if (size > Integer.MAX_VALUE) {
			size = Integer.MAX_VALUE;
		}
		return size;
	}

	/**
	 * Returns if the list is empty
	 * 
	 * @return True if the list is empty
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
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
		// if (o == null) {
		//
		// }

		for (int i = 0; i < size(); i++) {
			if (list[i] == o) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Appends the specified element to the end of this list (optional
	 * operation).
	 *
	 * Lists that support this operation may place limitations on what elements
	 * may be added to this list. In particular, some lists will refuse to add
	 * null elements, and others will impose restrictions on the type of
	 * elements that may be added. List classes should clearly specify in their
	 * documentation any restrictions on what elements may be added.
	 *
	 * @param o
	 *            element to be appended to this list
	 * @return true
	 */
	public boolean add(Object o) {

		this.add(size, o);
		return true;

	}

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index
	 *            index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range
	 */
	public Object get(int index) {

		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		return list[index];

	}

	/**
	 * Inserts the specified element at the specified position in this list
	 * (optional operation). Shifts the element currently at that position (if
	 * any) and any subsequent elements to the right (adds one to their
	 * indices).
	 *
	 * @param index
	 *            index at which the specified element is to be inserted
	 * @param element
	 *            element to be inserted
	 * @throws NullPointerException
	 *             if the specified element is null and this list does not
	 *             permit null elements
	 * @throws IllegalArgumentException
	 *             if some property of the specified element prevents it from
	 *             being added to this list
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range
	 */
	public void add(int index, Object element) {
		if (element == null) {
			throw new NullPointerException();
		}
		for (int j = 0; j < size; j++) {
			if (element == list[j]) {
				throw new IllegalArgumentException();
			}
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (list.length == size) {
			growArray();
		}
		if (index <= size) {
			for (int i = size; i > index; i--) {
				list[i] = list[i - 1];
			}
			list[index] = element;
			size++;
		} else if (index == list.length) {
			growArray();
			list[index] = element;
			size++;
		}

	}

	/**
	 * Doubles the size of the array to add more capacity.
	 * 
	 * @param array
	 *            The array that we are doubling the size of
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {

		Object newArray[] = new Object[(list.length * 2)];
		for (int i = 0; i < list.length; i++) {
			newArray[i] = list[i];
		}
		list = newArray;
	}

	/**
	 * Removes the element at the specified position in this list (optional
	 * operation). Shifts any subsequent elements to the left (subtracts one
	 * from their indices). Returns the element that was removed from the list.
	 *
	 * @param index
	 *            the index of the element to be removed
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range
	 */
	public Object remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		Object o = list[index];
		list[index] = null;
		for (int i = index; i < size(); i++) {
			list[i] = list[i + 1];
		}
		size--;
		return o;

	}

	/**
	 * Returns the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element. More
	 * formally, returns the lowest index i such that (o==null ? get(i)==null :
	 * o.equals(get(i))), or -1 if there is no such index.
	 *
	 * @param o
	 *            element to search for
	 * @return the index of the first occurrence of the specified element in
	 *         this list, or -1 if this list does not contain the element
	 */
	public int indexOf(Object o) {
		for (int i = 0; i < size(); i++) {
			if (list[i].equals(o)) {
				return i;
			}

		}
		return -1;
	}

}
