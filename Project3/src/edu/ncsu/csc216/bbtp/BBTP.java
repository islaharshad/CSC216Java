package edu.ncsu.csc216.bbtp;

import edu.ncsu.csc216.bbtp.model.TestingTypeList;
import edu.ncsu.csc216.bbtp.util.ArrayList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.bbtp.model.TestCaseList;

/**
 * The main class for the BBTP tools. Holds references to the top-level data
 * structures that contain TestCase and TestingType objects and acts as the
 * controller between the model and the GUI presentation view.
 * 
 * @author Islahuddin Arshad
 * @author Samuel Ryne Ritter
 */
public class BBTP extends Observable implements Serializable, Observer {

	/** Serial version UID. */
	private static final long serialVersionUID = 34992L;

	/**
	 * Increment for increasing the capacity of the array of TestCaseLists
	 */
	private static final int RESIZE = 3;
	/** A collection of TestCaseList */
	private TestCaseList[] testCases;
	/** Number of TestCaseList */
	private int numLists;
	/** A collection of TestingTypes */
	private TestingTypeList testingTypes;
	/** Filename for saving the bbtp information */
	private String filename;
	/** True if bbtp has changed since last save */
	private boolean changed;
	/** The next number for a task list id */
	private int nextTestCaseListNum;

	/**
	 * Constructs the array of test cases and constructs the testcaselist that
	 * is added into the test cases array
	 */
	public BBTP() {
		nextTestCaseListNum = 1;
		testCases = new TestCaseList[RESIZE];
		testCases[0] = new TestCaseList("New List", "TCL" + getNextTestCaseListNum());
		testCases[0].addObserver(this);
		testingTypes = new TestingTypeList();
		testingTypes.addObserver(this);
		changed = false;
		numLists++;
		incNextTestCaseListNum();
	}

	/**
	 * Returns whether the value stored is changed or not
	 * 
	 * @return the change of value
	 */
	public boolean isChanged() {
		return changed;
	}

	/**
	 * Sets whether the value is changed
	 * 
	 * @param changed
	 *            the value's state
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	/**
	 * This method gets the file name
	 * 
	 * @return String the file name to be returned
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * The file name is set here so it is recorded
	 * 
	 * @param fileName
	 *            the file name to be set
	 */
	public void setFilename(String fileName) {
		if (fileName == null || fileName.equals("")) {
			throw new IllegalArgumentException();
		}
		this.filename = fileName;
	}

	/**
	 * Gets the number of the list of test cases to be archived and processed
	 * 
	 * @return the number of the next test case list
	 */
	private int getNextTestCaseListNum() {
		return nextTestCaseListNum;
	}

	/**
	 * Adds one to to the list number id so that test case lists can be
	 * processed correctly
	 */
	private void incNextTestCaseListNum() {
		nextTestCaseListNum++;
	}

	/**
	 * Returns the number of test case lists in the program
	 * 
	 * @return the number of test case lists
	 */
	public int getNumTestCaseLists() {
		return numLists;
	}

	/**
	 * Returns the test case list by searching the list of test case list
	 * 
	 * @param index
	 *            the index in the list of test case lists
	 * @return the test case list called from the list in the given index
	 */
	public TestCaseList getTestCaseList(int index) {
		if (index < 0 || index >= numLists) {
			throw new IndexOutOfBoundsException();
		}
		return testCases[index];
	}

	/**
	 * Returns the list of testing type
	 * 
	 * @return the testing type list to be returned
	 */
	public TestingTypeList getTestingTypeList() {
		return testingTypes;
	}

	/**
	 * TestCaseList is added to the testcase array which may need to be resized
	 * 
	 * @return the index of the added TestCaseList
	 */
	public int addTestCaseList() {
		if (getNumTestCaseLists() >= 3) {
			TestCaseList temp[] = new TestCaseList[testCases.length + 1];
			for (int i = 0; i < testCases.length; i++) {
				temp[i] = testCases[i];
			}
			temp[testCases.length] = new TestCaseList("New List", "TCL" + getNextTestCaseListNum());
			incNextTestCaseListNum();
			temp[testCases.length].addObserver(this);
			temp[testCases.length].notifyObservers();
			testCases = temp;
			numLists++;
			return testCases.length;
		} else {
			for (int i = 0; i < testCases.length; i++) {
				if (testCases[i] == null) {
					testCases[i] = new TestCaseList("New List", "TCL" + getNextTestCaseListNum());
					incNextTestCaseListNum();
					testCases[i].addObserver(this);
					testCases[i].notifyObservers();
					numLists++;
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * The tescaseList is removed at the given index
	 * 
	 * @param index
	 *            the index of the test case
	 */
	public void removeTestCaseList(int index) {
		TestCaseList temp[] = null;
		if (index < 0 || index >= numLists) {
			throw new IndexOutOfBoundsException();
		}
		if (numLists > 3) {
			temp = new TestCaseList[numLists - 1];
		} else {
			temp = new TestCaseList[RESIZE];
		}
		for (int i = 0; i <= index - 1; i++) {
			temp[i] = testCases[i];
		}
		testCases[index].deleteObserver(this);
		TestCaseList removed = testCases[index];
		for (int i = index; i < numLists - 1; i++) {
			temp[i] = testCases[i + 1];
		}

		numLists--;
		// testCases[index].deleteObserver(this);
		notifyObservers(removed);
		setChanged();
		testCases[index] = null;
		testCases = temp;
		// numLists--;
	}

	/**
	 * BBTP changes are updated and it is propagated to all of its observers.
	 * 
	 * @param o
	 *            the observable to be updated
	 * @param arg
	 *            the object in respect
	 */
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(arg);
		changed = true;
	}

	/**
	 * Saves the TestingTypeList and the array of TestCaseLists to the given
	 * file using object serialization.
	 * 
	 * @param fname
	 *            filename to save BBTP information to.
	 * @return true is saved successfully
	 */
	public boolean saveDataFile(String fname) {
		if (fname == null || fname.trim().equals("")) {
			System.err.println("Invalid filename" + fname);
			return false;
		} else {
			try {
				FileOutputStream fileOut = new FileOutputStream(fname);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				for (int i = 0; i < numLists; i++) {
					out.writeObject(testCases[i]);
				}
				out.writeObject(testingTypes);
				out.writeObject(filename);
				out.writeInt(nextTestCaseListNum);
				changed = false;
				out.close();
				fileOut.close();
				return true;
			} catch (IOException e) {
				System.err.println("An error occurred while saving file " + fname);
				e.printStackTrace(System.err);
				return false;
			}
		}
	}

	/**
	 * Opens a data file with the given name and creates the data structures
	 * from the serialized objects in the file.
	 * 
	 * @param fname
	 *            filename to create BBTP information from.
	 * @return true is opened successfully
	 */
	public boolean openDataFile(String fname) {
		if (changed) {
			saveDataFile(filename);
		}
		try {
			FileInputStream fileIn = new FileInputStream(fname);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ArrayList temp = new ArrayList();
			Object tl = in.readObject();
			while (tl instanceof TestCaseList) {
				TestCaseList l = (TestCaseList) tl;
				l.addObserver(this);
				temp.add(l);
				tl = in.readObject();
			}
			testCases = new TestCaseList[temp.size()];
			for (int i = 0; i < temp.size(); i++) {
				testCases[i] = (TestCaseList) temp.get(i);
			}
			numLists = temp.size();
			testingTypes = (TestingTypeList) tl;
			testingTypes.addObserver(this);
			filename = (String) in.readObject();
			nextTestCaseListNum = (int) in.readInt();
			for (int i = 0; i < numLists; i++) {
				TestCaseList list = testCases[i];
				for (int j = 0; j < list.size(); j++) {
					list.getTestCaseAt(j).addObserver(list);
				}
			}
			for (int i = 0; i < testingTypes.size(); i++) {
				testingTypes.getTestingTypeAt(i).addObserver(testingTypes);
			}
			changed = false;
			in.close();
			fileIn.close();
			return true;
		} catch (IOException e) {
			System.err.println("An error occurred while reading file " + fname);
			e.printStackTrace(System.err);
			return false;
		} catch (ClassNotFoundException c) {
			System.err.println("Error reconstructing BBTP from file " + fname);
			c.printStackTrace(System.err);
			return false;
		}
	}

}
