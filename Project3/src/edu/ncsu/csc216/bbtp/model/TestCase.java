package edu.ncsu.csc216.bbtp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;

/**
 * The Test Case class deals with making test cases which are later checked to
 * pass the BBT tests
 * 
 * @author Islahuddin Arshad
 *
 */
public class TestCase extends Observable implements Serializable {
	/** Serial version UID. */
	private static final long serialVersionUID = 7459L;
	/** The test case id */
	private String testCaseID;
	/** Creation of the test case */
	private Date creationDateTime;
	/** The description of the test case */
	private String description;
	/** The test case's expected results */
	private String expectedResults;
	/** The test case's actual results */
	private String actualResults;
	/** The last tested date time of the test case */
	private Date lastTestedDateTime;
	/** Whether the test cases is tested or not */
	private boolean testedStatus;
	/** Whether the test case passes or not */
	private boolean pass;
	/** The testing type of the test case */
	private TestingType testingType;

	/**
	 * Constructs a test case with its id, description etc. and sets each
	 * parameter as well. Also checks if each paramer is null or empty string.
	 * If it is, then an IAE is thrown
	 * 
	 * @param id
	 *            the test case id
	 * @param description
	 *            the test case description
	 * @param testingtype
	 *            the test case's testingType
	 * @param creationDateTime
	 *            the test case creation time
	 * @param expectedResults
	 *            the test case expected results
	 * @param tested
	 *            the tested status of the test case
	 * @param lastTestedDate
	 *            when the last tested case happened
	 * @param actualResults
	 *            the actual result of the test case
	 * @param pass
	 *            whether the test case passes or not
	 */
	public TestCase(String id, String description, TestingType testingtype, Date creationDateTime,
			String expectedResults, boolean tested, Date lastTestedDate, String actualResults, boolean pass) {

		if (id == null || id.equals("") || description == null || description.equals("") || testingtype == null
				|| creationDateTime == null || expectedResults == null || expectedResults.equals("")) {
			throw new IllegalArgumentException();
		}
		this.setTestCaseID(id);
		this.setDescription(description);
		this.setCreationDateTime(creationDateTime);
		this.setTestingType(testingtype);
		this.setExpectedResults(expectedResults);
		this.setTestedStatus(tested);
		this.setLastTestedDateTime(lastTestedDate);
		this.setActualResults(actualResults);
		this.setPass(pass);

	}

	/**
	 * Gets the description of the test case
	 * 
	 * @return the description of the test case
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the test case and checks if it is null or empty
	 * string. If yes, an IAE is thrown
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
	 * Gets the expected results of the test case
	 * 
	 * @return the expected results of the test case
	 */
	public String getExpectedResults() {
		return expectedResults;
	}

	/**
	 * Sets the expected results of the test case and checks if it is null or
	 * empty string. If yes, an IAE is thrown
	 * 
	 * @param expectedResults
	 *            the expected results to be set
	 */
	public void setExpectedResults(String expectedResults) {
		if (expectedResults == null || expectedResults.equals("")) {
			throw new IllegalArgumentException();
		}
		this.expectedResults = expectedResults;
		setChanged();
		notifyObservers(this);
		/// TestCase observers are notified of the change.
	}

	/**
	 * Gets the actual results of the test case
	 * 
	 * @return the actual results of the test case
	 */
	public String getActualResults() {
		return actualResults;
	}

	/**
	 * Sets the actual results of the test case and checks if it is null or
	 * empty string. If yes, an IAE is thrown
	 * 
	 * @param actualResults
	 *            the actual results to be set
	 */
	public void setActualResults(String actualResults) {
		if ((actualResults == null || actualResults.equals("")) && testedStatus) {
			throw new IllegalArgumentException();

			/// TestCase observers are notified of the change.
		}
		this.actualResults = actualResults;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Gets the creation date time of the test case
	 * 
	 * @return the creation date time of the test case
	 */
	public Date getCreationDateTime() {
		return creationDateTime;
	}

	/**
	 * Sets the creation date time of the test case and checks if it is null or
	 * empty string. If yes, an IAE is thrown
	 * 
	 * @param creationDateTime
	 *            the creation date time to be set
	 */
	public void setCreationDateTime(Date creationDateTime) {
		if (creationDateTime == null) {
			throw new IllegalArgumentException();
		}
		this.creationDateTime = creationDateTime;
		setChanged();
		notifyObservers(this);
		/// TestCase observers are notified of the change.
	}

	/**
	 * Gets the last creation date time of the test case
	 * 
	 * @return the last tested date time of the test case
	 */
	public Date getLastTestedDateTime() {
		return lastTestedDateTime;
	}

	/**
	 * Sets the last creation date time of the test case and sets the last
	 * testDateTime to null if not the test case is not tested yet
	 * 
	 * @param lastTestedDateTime
	 *            the last tested date time to be set
	 */
	public void setLastTestedDateTime(Date lastTestedDateTime) {
		if (lastTestedDateTime == null && testedStatus) {
			throw new IllegalArgumentException();
		}
		if (!testedStatus) {
			lastTestedDateTime = null;
		}
		this.lastTestedDateTime = lastTestedDateTime;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Whether the test case is tested or not
	 * 
	 * @return the tested status
	 */
	public boolean tested() {
		return testedStatus;
	}

	/**
	 * Sets the testing status
	 * 
	 * @param testedStatus
	 *            the testing status to be set
	 */
	public void setTestedStatus(boolean testedStatus) {
		this.testedStatus = testedStatus;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Whether the test case passes or not
	 * 
	 * @return pass the test case passing status
	 */
	public boolean pass() {
		return pass;
	}

	/**
	 * Sets the pass of the test case
	 * 
	 * @param pass
	 *            the passing status of the test case
	 */
	public void setPass(boolean pass) {
		this.pass = pass;
		setChanged();
		notifyObservers(this);
		/// TestCase observers are notified of the change.
	}

	/**
	 * Sets the testing type of the test case. If the testing type is null, an
	 * IAE is thrown
	 * 
	 * @param t
	 *            the testingType of the test case
	 */
	public void setTestingType(TestingType t) {
		if (t == null) {
			throw new IllegalArgumentException();
		}
		this.testingType = t;
		setChanged();
		notifyObservers(this);

	}

	/**
	 * Gets the testingType of the test case
	 * 
	 * @return the testing type of the test case
	 */
	public TestingType getTestingType() {
		return testingType;
	}

	/**
	 * Gets the testing id of the test case
	 * 
	 * @return the test id
	 */
	public String getTestCaseID() {
		return testCaseID;
	}

	/**
	 * Sets the id of the test case. If the id is null, an IAE is thrown
	 * 
	 * @param id
	 *            the ID of the test case
	 */
	private void setTestCaseID(String id) {
		if (id == null) {
			throw new IllegalArgumentException();
		}
		this.testCaseID = id;
		setChanged();
		notifyObservers(this);
		/// TestCase observers are notified of the change.
	}

	/**
	 * Checks if two test cases are equal due to their IDs
	 * 
	 * @return true of two test cases are equal
	 * @param obj
	 *            the object being checked equaled to
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TestCase))
			return false;
		TestCase other = (TestCase) obj;
		if (testCaseID == null) {
			if (other.testCaseID != null)
				return false;
		} else if (!testCaseID.equals(other.testCaseID))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((testCaseID == null) ? 0 : testCaseID.hashCode());
		return result;
	}

	/**
	 * Compares two test cases through the last tested date time
	 * 
	 * @param tc
	 *            the test case to be compared to
	 * @return if the date is less, return -1, greater, 1, equal, 0
	 */
	public int compareTo(TestCase tc) { /// on the instructions, the parameter
										/// was a "testingType" object

		if (this.getLastTestedDateTime() == null) {
			if (tc.getLastTestedDateTime() == null) {
				return 0;
			}
			return -1;
		}
		if (tc.getLastTestedDateTime() == null) {
			return 1;
		}

		return this.getLastTestedDateTime().compareTo(tc.lastTestedDateTime);

		// Two TestingTypes are compared on their lastTestedDateTime field.
		// Delegate to the Date's compareTo method.
	}

}
