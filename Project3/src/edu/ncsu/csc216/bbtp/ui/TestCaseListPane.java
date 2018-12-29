package edu.ncsu.csc216.bbtp.ui;

import java.awt.Color;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
//import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import edu.ncsu.csc216.bbtp.model.TestCaseList;
import edu.ncsu.csc216.bbtp.ui.TestCaseTableModel;

/**
 * The TestCaseListPane holds the test case lists and displays them in the gui
 * 
 * @author Islahuddin Arshad
 *
 */
public class TestCaseListPane extends JScrollPane implements Observer, Serializable {
	/** Serial version UID. */
	private static final long serialVersionUID = -2210716111020406799L;
	/** The list of test cases */
	private TestCaseList testCases;
	/** TestingTypeTableModel which displays the list of TestingTypes */
	private TestCaseTableModel tctm;
	/** The table holding the test cases */
	private JTable table;
	/** The width of the table columns */
	private int[] colWidths = { 50, 250, 250, 250, 250, 250, 250, 250, 250 };

	/**
	 * Creates a test case list
	 * 
	 * @param testCaseList
	 *            the test case list of which the pane is being created of
	 */
	public TestCaseListPane(TestCaseList testCaseList) {
		super();
		this.testCases = testCaseList;
		this.testCases.addObserver(this);
		tctm = new TestCaseTableModel(testCases.get2DArray());
		initView();

	}

	/**
	 * Gets the test case table model created in the GUI
	 * 
	 * @return the test case table model is returned
	 */
	public TestCaseTableModel getTestCaseTableModel() {
		return tctm;
	}

	/**
	 * Gets the table of test case list
	 * 
	 * @return JTable the table containing test case list
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * Shows the initial view of test case list table
	 */
	private void initView() {
		table = new JTable(tctm);
		for (int i = 0; i < colWidths.length; i++) {
			TableColumn col = table.getColumnModel().getColumn(i);
			col.setPreferredWidth(colWidths[i]);
		}

		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(false);
		setViewportView(table);
		setBorder(BorderFactory.createLineBorder(Color.black));

	}

	/**
	 * Clears the entire selection of test case list
	 */
	public void clearSelection() {
		table.clearSelection();
	}

	/**
	 * updates the test case list table due to observable types and the
	 * respective objects
	 * 
	 * @param o
	 *            the observable to be updated
	 * @param arg
	 *            the object in respect
	 */
	public void update(Observable o, Object arg) {
		if (o instanceof TestCaseList) {
			TestCaseList tcl = (TestCaseList) o;
			if (tcl.size() != tctm.getRowCount()) {
				tctm = new TestCaseTableModel(tcl.get2DArray());
				table.setModel(tctm);
			} else {
				Object[][] arr = tcl.get2DArray();
				for (int i = 0; i < arr.length; i++) {
					for (int j = 0; j < tctm.getColumnCount(); j++) {
						tctm.setValueAt(arr[i][j], i, j);
					}
				}
			}
		}
	}

}
