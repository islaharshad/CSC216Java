package edu.ncsu.csc216.bbtp.model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.bbtp.util.ArrayList;

/**
 * The testing type list composes of the the testing types of the black box test
 * cases to be tested
 * 
 * @author Islahuddin Arshad
 * @author Samuel Ryne Ritter
 *
 */
public class TestingTypeList extends Observable implements Tabular, Observer, Serializable {
	/** Serial version UID. */
	private static final long serialVersionUID = 984509L;
	/** Name of the testing type list */
	private String name;
	/** The number of the next testing type */
	private int nextTestingTypeNum;
	/** The list of Testing types */
	private ArrayList list;

	/**
	 * The testing type list is created
	 */
	public TestingTypeList() {
		list = new ArrayList();
		nextTestingTypeNum = 1;
		name = "Testing Types";
	}

	/**
	 * The name of testing type list is gotten
	 * 
	 * @return the name of testing type list
	 */
	public String getName() {
		return name;
	}

	/**
	 * Adds the testing type list to the array with name and description
	 * 
	 * @param name
	 *            the name of the testing type list
	 * @param desc
	 *            the description of the testing type list
	 * @return boolean whether the testing type list can be added or not
	 */
	public boolean addTestingType(String name, String desc) {
		TestingType t = new TestingType("TT" + getNextTestingTypeNum(), name, desc);
		t.addObserver(this);
		incNextTestingTypeNum();
		for (int i = 0; i < list.size(); i++) {
			if (((TestingType) list.get(i)).getTestingTypeID().compareTo(t.getTestingTypeID()) > 0) {
				list.add(i, t);
				setChanged();
				notifyObservers(this);
				return true;
			}
		}
		list.add(t);
		setChanged();
		notifyObservers(this);
		return true;
	}

	/**
	 * Gets the testing type from a given index from the list
	 * 
	 * @param index
	 *            the index of the testing type in the list
	 * @return TestingType the testing type gained from the index
	 */
	public TestingType getTestingTypeAt(int index) {
		if (index < 0 || index >= list.size()) {
			throw new IndexOutOfBoundsException();
		}
		return (TestingType) list.get(index);
	}

	/**
	 * The index of the testing type through the id is returned here
	 * 
	 * @param id
	 *            the id of the testing type
	 * @return the index of the testing type
	 */
	public int indexOf(String id) {
		for (int i = 0; i < list.size(); i++) {
			if (((TestingType) list.get(i)).getTestingTypeID().equals(id)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * The index of the testing type through the name is returned here
	 * 
	 * @param name
	 *            the name of the testing type
	 * @return the index of the testing type
	 */
	public int indexOfName(String name) {
		for (int i = 0; i < list.size(); i++) {
			if (((TestingType) list.get(i)).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * The size of the testing type list is returned here
	 * 
	 * @return the size of the testing type list
	 */
	public int size() {
		return list.size();
	}

	/**
	 * Whether the testing type list is empty
	 * 
	 * @return boolean the emptiness of the testing type list
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Removes the testing type at a given index
	 * 
	 * @param index
	 *            the index where the testing type is removed at
	 * @return TestingType the testing type being removed
	 */
	public TestingType removeTestingTypeAt(int index) {
		if (index < 0 || index >= list.size()) {
			throw new IndexOutOfBoundsException();
		}
		TestingType t = (TestingType) list.remove(index);
		t.deleteObserver(this);
		setChanged();
		notifyObservers(this);
		return t;
	}

	/**
	 * Removes the testing type by the id
	 * 
	 * @param id
	 *            The id of the testing type being removed
	 * @return TestingType the testing type being removed
	 */
	public boolean removeTestingType(String id) {
		for (int i = 0; i < list.size(); i++) {
			if (((TestingType) list.get(i)).getTestingTypeID().equals(id)) {
				TestingType t = (TestingType) list.remove(i);
				t.deleteObserver(this);
				setChanged();
				notifyObservers(this);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the next testing type number
	 * 
	 * @return the number of the next testing type
	 */
	private int getNextTestingTypeNum() {
		return nextTestingTypeNum;
	}

	/**
	 * increments the next testing type number
	 */
	private void incNextTestingTypeNum() {
		nextTestingTypeNum++;
	}

	/**
	 * Returns the 2d objects array of the testing type list
	 * 
	 * @return Object[][] the array of testing type list
	 */
	public Object[][] get2DArray() {
		Object[][] s = new String[list.size()][9];
		for (int i = 0; i < list.size(); i++) {
			s[i][0] = ((TestingType) list.get(i)).getTestingTypeID();
			s[i][1] = ((TestingType) list.get(i)).getName();
			s[i][2] = ((TestingType) list.get(i)).getDescription();
		}
		return s;
	}

	/**
	 * Testing type list changes are updated and it is propagated to all of its
	 * observers.
	 * 
	 * @param o
	 *            the observable to be updated
	 * @param arg
	 *            the object in respect
	 */
	public void update(Observable o, Object arg) {
		if (o instanceof TestingType) {
			if (this.indexOf(((TestingType) o).getTestingTypeID()) != -1) {
				setChanged();
				notifyObservers(this);
			}
		} else {
			notifyObservers();
		}
	}

}