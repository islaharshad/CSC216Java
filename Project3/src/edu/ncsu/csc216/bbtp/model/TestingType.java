package edu.ncsu.csc216.bbtp.model;

import java.io.Serializable;
import java.util.Observable;

/**
 * The testing type class creates the testing type of each test case and deals
 * with the blackbox tests further on
 * 
 * @author Islahuddin Arshad
 *
 */
public class TestingType extends Observable implements Serializable {
	/** Serial version UID. */
	private static final long serialVersionUID = 459188L;
	/** Name of the testing type */
	private String name;
	/** Description of the testing type */
	private String description;
	/** The id of the testing type */
	private String testingTypeID;

	/**
	 * Constructs a testing type by id, name, and description and tests if any
	 * of the parameter are null or empty space. If they are. an IAE is thrown
	 * 
	 * @param id
	 *            the id of the testing type
	 * @param name
	 *            the name of the testing type
	 * @param desc
	 *            the description of the testing type
	 */
	public TestingType(String id, String name, String desc) {

		if (id == null || id.equals("") || name == null || name.equals("") || desc == null || desc.equals("")) {
			throw new IllegalArgumentException();
		}

		this.setTestingTypeID(id);
		this.setName(name);
		this.setDescription(desc);

	}

	/**
	 * Gets the name of the testing type
	 * 
	 * @return the name of the testing type
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the testingtype by checking if the name is null or empty
	 * string. If it is, an IAE is thrown
	 * 
	 * @param name
	 *            the name to be set
	 */
	public void setName(String name) {
		if (name == null || name.equals("")) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		setChanged();
		notifyObservers(this);
		/// TestCase observers are notified of the change.
	}

	/**
	 * Gets the description of the testing type
	 * 
	 * @return the description of the testing type
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the testingtype by checking if the description is
	 * null or empty string. If it is, an IAE is thrown
	 * 
	 * @param description
	 *            the description to be set
	 */
	public void setDescription(String description) {
		if (description == null || description.equals("")) {
			throw new IllegalArgumentException();
		}
		this.description = description;
		setChanged();
		notifyObservers(this);
		/// TestCase observers are notified of the change.
	}

	/**
	 * Gets the id of the testing type
	 * 
	 * @return the id of the testing type
	 */
	public String getTestingTypeID() {
		return testingTypeID;
	}

	/**
	 * Sets the id of the testingtype by checking if the id is null or empty
	 * string. If it is, an IAE is thrown
	 * 
	 * @param id
	 *            the id to be set
	 */
	private void setTestingTypeID(String id) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException();
		}
		this.testingTypeID = id;
		setChanged();
		notifyObservers(this);
		/// TestCase observers are notified of the change.
	}

	/**
	 * Checks if two testing types are equal due to their IDs
	 * 
	 * @param o
	 *            the object to be equaled with
	 * @return true of two test types are equal
	 */
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (!(o instanceof TestingType))
			return false;
		TestingType other = (TestingType) o;
		if (testingTypeID == null) {
			if (other.testingTypeID != null)
				return false;
		} else if (!testingTypeID.equals(other.testingTypeID))
			return false;
		return true;
		// Two TestingTypes are considered the same if their ids are an exact
		// match.
		// The match is case sensitive.
	}

	/**
	 * Compares two test cases through the testing type id
	 * 
	 * @param c
	 *            the test type to be compared to
	 * @return if the id is less, return -1, greater, 1, equal, 0
	 */
	public int compareTo(TestingType c) {
		if (this.getTestingTypeID() == null) {
			if (c.getTestingTypeID() == null) {
				return 0;
			}
			return -1;
		}
		if (c.getTestingTypeID() == null) {
			return 1;
		}

		return this.getTestingTypeID().compareTo(c.getTestingTypeID());
	}

	/**
	 * Generates the hash code for TestingType
	 * 
	 * @return The hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((testingTypeID == null) ? 0 : testingTypeID.hashCode());
		return result;
	}

	/**
	 * Returns the name of the testing type
	 * 
	 * @return the name of the testing type
	 */
	public String toString() {
		return name;
		// ?
	}

}
