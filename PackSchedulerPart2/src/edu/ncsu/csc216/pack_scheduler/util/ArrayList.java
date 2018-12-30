package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Creates a list that is able to store and manipulate arrays.
 * 
 * @author Addison Garrigus
 *
 * @param <E> the generic object in the list
 */
public class ArrayList<E> extends AbstractList<Object> {

	private static final int INIT_SIZE = 10;
	
	private E[] list;
	
	private int size = 0;
	
	
	/** Sets the size to zero and initialize the list array
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		size = 0;
		list = (E[]) new Object[INIT_SIZE];
	}
	
	/** Returns the object at the selected index
	 *  @return the object at the desired index
	 */
	@Override
	public E get(int idx) {
		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		return (E) list[idx];
	}

	/**
	 * Adds a new item to the list. 
	 * 
	 * @param idx The index where the new item will be added
	 * @param thing The item that will be added
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void add(int idx, Object thing) {
		if (thing == null) {
			throw new NullPointerException();
		}
		for (int j = 0; j < size; j++) {
			if (thing == list[j]) {
				throw new IllegalArgumentException();
			}
		}
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		} 
		if (list.length == size) {
			growArray();
		}
		if (idx <= size) {
			for (int i = size; i > idx; i--) {
				list[i] = list[i - 1];
			}
			list[idx] = (E) thing;
			size++;
		} else if (idx == list.length) {
			growArray();
			list[idx] = (E) thing;
			size++;
		}		
	}

	/** Adds the selected Object to the end of the ArrayList
	 *  @return true if it was successfully added
	 */
	@Override
	public boolean add(Object o) {
		this.add(size, o);
		return true;
	}

	
	/** Removes the element at the selected index and decreases the size of the ArrayList, lowering the index of all following elements by 1
	 *  @return - the object that has been removed from the ArrayList
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E remove(int idx) {
		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		Object o = list[idx];
		list[idx] = null;
		for(int i = idx; i < size; i++) {
			list[i] = list[i + 1];
		}
		size--;
		
		return (E) o;
	}

	/** Replaces the element at a selected index with an element of the clients choosing
	 *  @return - the object that is at the desired index, should be the desired object
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E set(int idx, Object o) {
		if(o == null) {
			throw new NullPointerException();
		}
		if(idx < 0 || idx > size || size == 0) {
			throw new IndexOutOfBoundsException();
		}
		
		for(int i = 0; i < size; i++) {
			if(o.equals(list[i])) {
				throw new IllegalArgumentException("Object is duplicate of element already in ArrayList");
			}
		}
		Object c = (E) list[idx];
		list[idx] = (E) o;
		return (E) c;
		
	}


	/** Returns the size of the ArrayList
	 * @return size - the number of elements in the ArrayList
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Doubles the size of the array to add more capacity.
	 * 
	 * @param array The array that we are doubling the size of
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		E[] newArray = (E[]) new Object[(list.length * 2)];
		for(int i = 0; i < list.length; i++) {
			newArray[i] = list[i];
		}
		list = newArray;
	}

}
