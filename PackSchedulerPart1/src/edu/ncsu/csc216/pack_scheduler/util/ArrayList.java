package edu.ncsu.csc216.pack_scheduler.util;



import java.util.AbstractList;



/**

 * ArrayList sub class of AbstractList, it is the list of all

 * the courses in the university

 * @author Islahuddin, Edwerdo, premsubedi

 * @param <E> type of element in an arrayList.

 */

public class ArrayList<E> extends AbstractList<E> {

	

	private static final int INIT_SIZE = 10;

	

	private E[] list;

	

	private int size;

	

	/** Unchecked exception warnings */

	@SuppressWarnings("unchecked")

	public ArrayList() {

		Object[] obj = new Object[INIT_SIZE];

		list = (E[]) obj;

	}



	@Override

	public E get(int index) {

		if (index < size) {

			return list[index];

		}

		else {

			throw new IndexOutOfBoundsException();



	   }

	}

	



	@Override

	public int size() {

		return size;

	}



	/** Updates the side of the list 

	 * @param index index of a list

	 * @param e element that is added in the list.

	 */

	private boolean contains(int index, E e)

	{

		for(int i = 0; i < size; i++)

		{

			if(e.equals(list[i]))

			{

				return true;

			}

		}

		return false;

	}

	public void add(int index, E e) {

		

		if (index < 0 || index > size()) {

			throw new IndexOutOfBoundsException();

		} 

		

		if (size == list.length) {

			resize();

			

		}

		if (e == null) {

			throw new NullPointerException();

		}

		if(this.contains(e))

		{

			throw new IllegalArgumentException();

		}

		for (int i = size; i >= index + 1; i--) { 

			

			list[i] = list[i - 1];

		}

		

		list[index] = e;

		size++;

		

		

		

	} 

		

	/** Public method that resizes the array before an item is added. */

	public void resize() {

		Object[] data = new Object[list.length * 2];

	    @SuppressWarnings("unchecked")

		E[] array1 = (E[]) data;

	    

		for (int i = 0; i < size; i++) {

			array1[i] = list[i];

		}



		list = array1;

	}

	

	/** Warnings is suppresed, since it is an unchecked 

	 * @return (E) obj casted obj that is in arraylist.

	 * @param index index of a list.

	 */

	@SuppressWarnings("unchecked")

	public E remove(int index) {

		if (index < 0 || index >= size()) {

			throw new IndexOutOfBoundsException();

		}
		E obj = list[index];

        int tmp = index;

        while(tmp < size) {

        	list[tmp] = list[tmp + 1]; 

        	tmp++;

		}



		list[size - 1] = null;
		size--;
		return obj;
	}

	

	/**

	 * Sets index of an item in the list.

	 * @return e element that is returned.

	 * @param e element in the list that is passed as parameter

	 * @param index index of a list.

	 */

	public E set(int index, E e) {

		if (index < 0 || index >= size()) {

			throw new IndexOutOfBoundsException();

		}

		

		if (e == null) {

			throw new NullPointerException();

		}

		

		if(this.contains(e))

		{

			throw new IllegalArgumentException();

		}

		E tmp = list[index];
		list[index] = e;

		return tmp;

		

	}

}