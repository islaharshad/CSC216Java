package edu.ncsu.csc216.bbtp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.bbtp.util.LinkedList;

/**
 * Creates a list of TestCases that can be accessed and changed, and can be
 * updated immediately through the observer pattern]]]]
 * 
 * @author Samuel Ritter
 *
 */
public class TestCaseList extends Observable implements Tabular, Serializable, Observer {

	/** Serial version UID. */
	private static final long serialVersionUID = 98734509L;
	/** The name of the test */
	private String name;
	/** The list of test cases */
	private LinkedList list;
	/** The next test case number to be added to the end of the id */
	private int nextTestCaseNum;
	/** The test case list's id */
	private String testCaseListID;

	/**
	 * Creates a new test case list object
	 * 
	 * @param name
	 *            The name of the test case list
	 * @param testCaseListID
	 *            the test case list's id string
	 */
	public TestCaseList(String name, String testCaseListID) {
		if (testCaseListID == null || testCaseListID.equals("")) {
			throw new IllegalArgumentException();
		}
		nextTestCaseNum = 1;
		list = new LinkedList();
		setName(name);
		setTestCaseListID(testCaseListID);
		setChanged();
		notifyObservers(this);

	}

	/**
	 * Returns the test case list's name
	 * 
	 * @return The test case list's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the test case list's name
	 * 
	 * @param name
	 *            The name that name is to be set to
	 */
	public void setName(String name) {
		if (name == null || name.equals("")) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		setChanged();
		notifyObservers(this);

	}

	/**
	 * Returns the test case list's id
	 * 
	 * @return The test case list's id
	 */
	public String getTestCaseListID() {
		return testCaseListID;
	}

	/**
	 * Sets the test case list's id as long as it is not null or empty
	 * 
	 * @param testCaseListID
	 *            The id to set the test case list's id to
	 */
	private void setTestCaseListID(String testCaseListID) {
		if (testCaseListID == null || testCaseListID.equals("")) {
			throw new IllegalArgumentException();
		}

		this.testCaseListID = testCaseListID;

	}

	/**
	 * Returns the number to add to the end of the next test case's id
	 * 
	 * @return The number to add to the end of the next test case's id
	 */
	private int getNextTestCaseNum() {
		return nextTestCaseNum;
	}

	/**
	 * Increases the number to add to the end of the next test case's id by one
	 */
	private void incNextTestCaseNum() {
		nextTestCaseNum++;
	}

	/**
	 * Adds a test case to this list of test cases and notifies the observer
	 * 
	 * @param desc
	 *            The test case's description
	 * @param type
	 *            The test case's type
	 * @param creation
	 *            The test case's creation date
	 * @param exp
	 *            The test case's expected results
	 * @param tested
	 *            If the test case has been tested
	 * @param lastTestDate
	 *            The test case's last tested date
	 * @param act
	 *            The test case's actual results
	 * @param pass
	 *            If the test case has passed
	 * @return True if the test case was added
	 */
	public boolean addTestCase(String desc, TestingType type, Date creation, String exp, boolean tested,
			Date lastTestDate, String act, boolean pass) {

		TestCase t = new TestCase(getTestCaseListID() + "-TC" + getNextTestCaseNum(), desc, type, creation, exp, tested,
				lastTestDate, act, pass);
		t.addObserver(this);
		incNextTestCaseNum();
		for (int i = 0; i < list.size(); i++) {
			if (((TestCase) list.get(i)).getLastTestedDateTime() != null
					&& ((TestCase) list.get(i)).getLastTestedDateTime().compareTo(lastTestDate) > 0) {
				list.add(i, t);
				this.notifyObservers();
				return true;
			}
		}
		list.add(t);
		setChanged();
		notifyObservers(this);
		return true;
	}

	/**
	 * Returns the test case at the given index in the list
	 * 
	 * @param index
	 *            The given index
	 * @return TestCase The test case at the given index in the list
	 */
	public TestCase getTestCaseAt(int index) {
		if (index < 0 || index >= list.size()) {
			throw new IndexOutOfBoundsException();
		}
		return (TestCase) list.get(index);
	}

	/**
	 * Returns the index of the test case given its id
	 * 
	 * @param id
	 *            The given id
	 * @return The index of the test case
	 */
	public int indexOf(String id) {
		for (int i = 0; i < list.size(); i++) {
			if (((TestCase) list.get(i)).getTestCaseID().equals(id)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returns the size of the list of test cases
	 * 
	 * @return The size of the list of test cases
	 */
	public int size() {
		return list.size();
	}

	/**
	 * Returns if the test case list is empty or not
	 * 
	 * @return True if the list is empty
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Removes the test case from this list at the given index
	 * 
	 * @param index
	 *            The given index
	 * @return The test case removed from this list at the given index
	 */
	public TestCase removeTestCaseAt(int index) {
		if (index < 0 || index >= list.size()) {
			throw new IndexOutOfBoundsException();
		}
		TestCase t = (TestCase) list.remove(index);
		t.deleteObserver(this);
		setChanged();
		notifyObservers(this);
		return t;
	}

	/**
	 * Removes the test case from this list given the case's id
	 * 
	 * @param testCaseID
	 *            The given id
	 * @return True if the test case was removed from this list
	 */
	public boolean removeTestCase(String testCaseID) {
		for (int i = 0; i < list.size(); i++) {
			if (((TestCase) list.get(i)).getTestCaseID().equals(testCaseID)) {
				TestCase t = (TestCase) list.remove(i);
				t.deleteObserver(this);
				setChanged();
				notifyObservers(this);
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a 2d array of the test cases within this list
	 * 
	 * @return A 2d array of the test cases within this list
	 */
	@Override
	public Object[][] get2DArray() {
		Object[][] s = new Object[list.size()][9];
		for (int i = 0; i < list.size(); i++) {
			s[i][0] = ((TestCase) list.get(i)).getTestCaseID();
			s[i][1] = ((TestCase) list.get(i)).getDescription();
			s[i][2] = ((TestCase) list.get(i)).getTestingType();
			s[i][3] = ((TestCase) list.get(i)).getCreationDateTime();
			s[i][4] = ((TestCase) list.get(i)).getLastTestedDateTime();
			s[i][5] = ((TestCase) list.get(i)).tested();
			s[i][6] = ((TestCase) list.get(i)).getExpectedResults();
			s[i][7] = ((TestCase) list.get(i)).getActualResults();
			s[i][8] = ((TestCase) list.get(i)).pass();

		}
		return s;
	}

	/**
	 * Updates the observers on the status of what they are observing
	 * 
	 * @param o
	 *            That which is being observed
	 * @param arg
	 *            An argument to be passed to the observers if what they are
	 *            observing is a test case
	 */
	public void update(Observable o, Object arg) {
		if (o instanceof TestCase) {
			if (this.indexOf(((TestCase) o).getTestCaseID()) != -1) {
				setChanged();
				notifyObservers(this);
			}
		} else {
			notifyObservers();
		}
	}

}
