package edu.ncsu.csc216.bbtp.ui;

import java.io.Serializable;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.bbtp.model.TestingType;

/**
 * The Test case table model holds the test cases for display and displays it as
 * a table
 * 
 * @author Islahuddin Arshad, Samuel Ritter
 *
 */
public class TestCaseTableModel extends AbstractTableModel implements Serializable {

	/** Serial version UID. */
	private static final long serialVersionUID = 5954551753060998701L;
	/** The column names of the test case table */
	private String[] colNames = { "ID", "Description", "Testing Type", "Creation Date", "Last Tested Date", "Tested",
			"Expected Results", "Actual Results", "Pass" };
	/** The data of the test case table */
	private Object[][] data;

	/**
	 * The 2D object array is called to the construct the case table model
	 * 
	 * @param data
	 *            the model of the 2D array
	 */
	public TestCaseTableModel(Object[][] data) {
		super();
		this.data = data;

	}

	/**
	 * Gets the number of rows in the table
	 * 
	 * @return the number rows
	 */
	public int getRowCount() {
		return data.length;
	}

	/**
	 * Gets the number of columns in the table
	 * 
	 * @return the number columns
	 */
	public int getColumnCount() {
		return colNames.length;
	}

	/**
	 * Gets the name of the column in the table
	 * 
	 * @param col
	 *            the column whose name is looked at
	 * @return String name of the column
	 */
	public String getColumnName(int col) {
		return colNames[col];
	}

	/**
	 * Returns the value at a given row and a column
	 * 
	 * @param row
	 *            the row of the value
	 * @param col
	 *            the columns of the value
	 * @return Object the row and the column
	 */
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	/**
	 * Sets the value at a given row and a column
	 * 
	 * @param o
	 *            the value to be set
	 * @param row
	 *            the row of the value
	 * @param col
	 *            the columns of the value
	 */
	public void setValueAt(Object o, int row, int col) {
		data[row][col] = o;
		fireTableCellUpdated(row, col);

	}

	/**
	 * Gets the value from the table row
	 * 
	 * @param row
	 *            the row from where the value is returned
	 * @return TestCaseData being returned from that row
	 */
	public TestCaseData getTestCaseRowData(int row) {

		TestCaseData newData = new TestCaseData((String) data[row][0], (String) data[row][1],
				(TestingType) data[row][2], (Date) data[row][3], (Date) data[row][4], (boolean) data[row][5],
				(String) data[row][6], (String) data[row][7], (boolean) data[row][8]);
		return newData;
	}

	/**
	 * Sets the test case date at a particular row
	 * 
	 * @param row
	 *            the row where the test case data is set at
	 * @param data
	 *            the data being set
	 */
	public void setTaskRowData(int row, TestCaseData data) {
		setValueAt(data.getTestCaseID(), row, 0);
		setValueAt(data.getDescription(), row, 1);
		setValueAt(data.getTestingType(), row, 2);
		setValueAt(data.getCreationDateTime(), row, 3);
		setValueAt(data.getLastTestedDateTime(), row, 4);
		setValueAt(data.tested(), row, 5);
		setValueAt(data.getExpectedResults(), row, 6);
		setValueAt(data.getActualResults(), row, 7);
		setValueAt(data.pass(), row, 8);

	}

}
