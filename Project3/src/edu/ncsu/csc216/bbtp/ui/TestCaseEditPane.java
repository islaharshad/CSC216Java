package edu.ncsu.csc216.bbtp.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;

import edu.ncsu.csc216.bbtp.model.TestingType;
import edu.ncsu.csc216.bbtp.model.TestingTypeList;

/**
 * The test case edit pane deals with edit button and the edit functionalities
 * of the blackbox test checker
 * 
 * @author Samuel Ritter
 * @author Islahuddin Arshad
 *
 */
public class TestCaseEditPane extends JPanel implements Observer, Serializable {

	/** Serial version UID. */
	private static final long serialVersionUID = 5479139338455751629L;

	/** The test case data */
	private TestCaseData data;
	/** The list of testing types */
	private TestingTypeList testingTypes;
	/** The field for test case id */
	private JTextField testCaseID;
	/** The list option for testing types */
	private JComboBox<TestingType> tcTestingType;
	/** The text area for expected results */
	private JTextArea expectedResults;
	/** The text area for actual results */
	private JTextArea actualResults;
	/** The text area for test case description */
	private JTextArea testCaseDescription;
	/** The spinner for test creation data */
	private JSpinner testCreationData;
	/** The spinner for last tested data */
	private JSpinner testLastTestedData;
	/** The check box for tested status */
	private JCheckBox tested;
	/** The check box for passing status */
	private JCheckBox pass;
	/** Decision to add */
	private boolean add;
	/** Decision to edit */
	private boolean edit;

	/**
	 * Creates a test case pane using a list
	 * 
	 * @param list
	 *            the list of testing types needed to create the test case edit
	 *            pane
	 */
	public TestCaseEditPane(TestingTypeList list) {
		this(new TestCaseData(), list);

	}

	/**
	 * Uses both the test case data and the testing type list to create the edit
	 * pane
	 * 
	 * @param data
	 *            the test case data to be used in test case edit pane
	 * @param list
	 *            the testing type list to be used in test case edit pane
	 */
	public TestCaseEditPane(TestCaseData data, TestingTypeList list) {
		super();
		this.data = data;
		this.testingTypes = list;
		testingTypes.addObserver(this);
		add = false;
		edit = false;
		init();
	}

	/**
	 * Initializing the test case edit pane
	 */
	void init() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.black));
		initView();
		fillFields();
	}

	/**
	 * The initial view of the test case edit
	 */
	void initView() {
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Test Case ID: ", SwingConstants.LEFT));
		p.add(getTestCaseID());
		this.add(p);
		p.add(new JLabel("Testing Type: ", SwingConstants.LEFT));
		p.add(getTestingType());
		this.add(p);
		p.add(new JLabel("Creation Date: ", SwingConstants.LEFT));
		p.add(getTestCreationDateSpinner());
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Description: ", SwingConstants.LEFT));
		p.add(getTestCaseDescription());
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Tested Status: ", SwingConstants.LEFT));
		p.add(tested());
		this.add(p);
		p.add(new JLabel("Last Tested Date: ", SwingConstants.LEFT));
		p.add(getLastTestedDateSpinner());
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Expected Results: ", SwingConstants.LEFT));
		p.add(getExpectedResults());
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Actual Results: ", SwingConstants.LEFT));
		p.add(getActualResults());
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Pass: ", SwingConstants.LEFT));
		p.add(pass());
		this.add(p);

	}

	/**
	 * Gets the last creation date to be in a spinner
	 * 
	 * @return spinner showing the last creation dates
	 */
	JSpinner getTestCreationDateSpinner() {
		if (null == testCreationData) {
			Calendar calendar = Calendar.getInstance();
			SpinnerDateModel s = new SpinnerDateModel(calendar.getTime(), null, calendar.getTime(),
					Calendar.DAY_OF_WEEK_IN_MONTH);
			testCreationData = new JSpinner(s);
			testCreationData.setVisible(true);
			testCreationData.setEnabled(false);

		}
		return testCreationData;
	}

	/**
	 * Gets the last tested date to be in a spinner
	 * 
	 * @return spinner showing the last tested dates
	 */
	JSpinner getLastTestedDateSpinner() {
		if (null == testLastTestedData) {
			Calendar calendar = Calendar.getInstance();
			SpinnerDateModel s = new SpinnerDateModel(calendar.getTime(), null, calendar.getTime(),
					Calendar.DAY_OF_MONTH);
			testLastTestedData = new JSpinner(s);
			testLastTestedData.setEnabled(false);
			testLastTestedData.setVisible(true);

		}
		return testLastTestedData;
	}

	/**
	 * Gets the creation date
	 * 
	 * @return the creation date
	 */
	Date getTestCreationDate() {
		return (Date) testCreationData.getValue();
	}

	/**
	 * Gets the last tested date
	 * 
	 * @return the last test date
	 */
	Date getLastTestedDate() {
		return (Date) testLastTestedData.getValue();
	}

	/**
	 * Gets the test case id
	 * 
	 * @return the test case id is returned in a text field
	 */
	JTextField getTestCaseID() {
		if (null == testCaseID) {
			testCaseID = new JTextField(10);
			testCaseID.setEditable(false);
			testCaseID.setVisible(true);
			testCaseID.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return testCaseID;
	}

	/**
	 * Gets the test case description
	 * 
	 * @return the test case description is returned in a text area
	 */
	JTextArea getTestCaseDescription() {
		if (null == testCaseDescription) {
			testCaseDescription = new JTextArea(5, 70);
			testCaseDescription.setEditable(false);
			testCaseDescription.setVisible(true);
			testCaseDescription.setLineWrap(true);
			testCaseDescription.setAutoscrolls(true);
		}
		return testCaseDescription;
	}

	/**
	 * Gets the testing types
	 * 
	 * @return presents the testing type in a combo box
	 */
	JComboBox<TestingType> getTestingType() {
		if (null == tcTestingType) {
			tcTestingType = new JComboBox<TestingType>();
			for (int i = 0; i < testingTypes.size(); i++) {
				tcTestingType.addItem(testingTypes.getTestingTypeAt(i));
			}
			tcTestingType.setEditable(false);
			tcTestingType.setEnabled(false);
		}
		return tcTestingType;
	}

	/**
	 * Gets the expected results
	 * 
	 * @return expected results are given in a text area
	 */
	JTextArea getExpectedResults() {
		if (null == expectedResults) {
			expectedResults = new JTextArea(5, 70);
			expectedResults.setEditable(false);
			expectedResults.setVisible(true);
			expectedResults.setLineWrap(true);
			expectedResults.setAutoscrolls(true);
		}
		return expectedResults;
	}

	/**
	 * Gets the actual results
	 * 
	 * @return actual results are given in a text area
	 */
	JTextArea getActualResults() {
		if (null == actualResults) {
			actualResults = new JTextArea(5, 70);
			actualResults.setEditable(false);
			actualResults.setVisible(true);
			actualResults.setLineWrap(true);
			actualResults.setAutoscrolls(true);
		}
		return actualResults;
	}

	/**
	 * Checks the whether the check box highlights the passing of the test case
	 * 
	 * @return the check box signifying the change
	 */
	JCheckBox pass() {
		if (null == pass) {
			pass = new JCheckBox();
			pass.setEnabled(false);
		}
		return pass;
	}

	/**
	 * Checks the whether the check box highlights the test of the test case
	 * 
	 * @return the check box signifying the change
	 */
	JCheckBox tested() {
		if (null == tested) {
			tested = new JCheckBox();
			tested.setEnabled(false);
		}
		return tested;
	}

	/**
	 * Sets the creation date time
	 * 
	 * @param date
	 *            the date to be set
	 */
	void setCreationDateTime(Date date) {
		testCreationData.setValue(date);
	}

	/**
	 * Sets the last tested date time
	 */
	void setLastTestedDateTime() {
		Calendar calendar = Calendar.getInstance();
		testLastTestedData.setValue(calendar.getTime());
	}

	/**
	 * Checks whether the model is to be added or not
	 * 
	 * @return boolean if the model is to be added
	 */
	boolean isAddModel() {
		return add;
	}

	/**
	 * Checks whether the model is to be edited or not
	 * 
	 * @return boolean if the model is to be edited
	 */
	boolean isEditModel() {
		return edit;
	}

	/**
	 * Enables the add feature
	 */
	void enableAdd() {
		if (!add) {
			add = true;
			edit = false;
			clearFields();
		}
	}

	/**
	 * Disables the add feature
	 */
	void disableAdd() {
		add = false;
		clearFields();
	}

	/**
	 * Takes in data and enables the edit of it
	 * 
	 * @param data
	 *            the date to be edited
	 */
	void enableEdit(TestCaseData data) {
		if (!edit) {
			edit = true;
			add = false;
			this.data = data;
			fillFields();
		}
	}

	/**
	 * Disables the edit feature
	 */
	void disableEdit() {
		edit = false;
		clearFields();
	}

	/**
	 * Checks if the test case fields are not empty
	 * 
	 * @return whether test case fields are empty or not
	 */
	boolean fieldsNotEmpty() {
		return getTestCaseDescription().getDocument().getLength() != 0
				&& getExpectedResults().getDocument().getLength() != 0
				&& getActualResults().getDocument().getLength() != 0;
	}

	/**
	 * Sets the test case data.
	 * 
	 * @param data
	 *            the data to be set
	 */
	void setTestCaseData(TestCaseData data) {
		this.data = data;
	}

	/**
	 * Adds the action listener for the buttons to be pressed
	 * 
	 * @param listener
	 *            manipulates the button
	 */
	void addFieldListener(EventListener listener) {
		getTestCaseID().getDocument().addDocumentListener((DocumentListener) listener);
		getExpectedResults().getDocument().addDocumentListener((DocumentListener) listener);
		getActualResults().getDocument().addDocumentListener((DocumentListener) listener);
		getTestCaseDescription().getDocument().addDocumentListener((DocumentListener) listener);
		getTestCreationDateSpinner().addChangeListener((ChangeListener) listener);
		getLastTestedDateSpinner().addChangeListener((ChangeListener) listener);
		getTestingType().addActionListener((ActionListener) listener);
		tested().addActionListener((ActionListener) listener);
		pass().addActionListener((ActionListener) listener);
	}

	/**
	 * Fills the field of test case
	 */
	void fillFields() {
		if (null == data) {
			testCaseID.setText("");
			testCaseID.setEditable(false);
			tcTestingType.setEditable(false);
			Calendar calendar = Calendar.getInstance();
			testCreationData.setValue(calendar.getTime());
			expectedResults.setText("");
			expectedResults.setEditable(false);
			actualResults.setText("");
			actualResults.setEditable(false);
			testCaseDescription.setText("");
			testCaseDescription.setEditable(false);
			testCreationData.setEnabled(false);
			testLastTestedData.setEnabled(false);
			tested.setSelected(false);
			tested.setEnabled(false);
			pass.setSelected(false);
			pass.setEnabled(false);
		} else {
			testCaseID.setText(data.getTestCaseID());
			// TestingType t[] = new TestingType[testingTypes.size()];
			// for(int i = 0; i < testingTypes.size(); i++) {
			// t[i] = testingTypes.getTestingTypeAt(i);
			// }
			tcTestingType.setSelectedItem(data.getTestingType());
			expectedResults.setText(data.getExpectedResults());
			actualResults.setText(data.getActualResults());
			testCaseDescription.setText(data.getDescription());
			if (data.getCreationDateTime() != null) {
				testCreationData.setValue(data.getCreationDateTime());
			}
			if (data.getLastTestedDateTime() != null) {
				testLastTestedData.setValue(data.getLastTestedDateTime());
			}
			tested.setSelected(data.tested());
			pass.setSelected(data.pass());
		}
		if (add || edit) {
			testCaseID.setEditable(true);
			tcTestingType.setEditable(true);
			expectedResults.setEditable(true);
			actualResults.setEditable(true);
			testCaseDescription.setEditable(true);
			testCreationData.setEnabled(true);
			testLastTestedData.setEnabled(true);
			tcTestingType.setEnabled(true);
			tcTestingType.setEditable(false);
			tested.setEnabled(true);
			pass.setEnabled(true);
		}
	}

	/**
	 * Clears the test case fields
	 */
	void clearFields() {
		data = null;
		fillFields();
	}

	/**
	 * Gets the fields of test case data
	 * 
	 * @return the test case data of which fields are gotten
	 */
	TestCaseData getFields() {
		return new TestCaseData(testCaseID.getText(), testCaseDescription.getText(),
				(TestingType) tcTestingType.getSelectedItem(), getTestCreationDate(), getLastTestedDate(),
				tested.isSelected(), expectedResults.getText(), actualResults.getText(), pass.isSelected());
	}

	/**
	 * Updates the observable
	 * 
	 * @param o
	 *            the observable to be updated
	 * @param arg
	 *            the object acted upon
	 */
	public void update(Observable o, Object arg) {
		if (o instanceof TestingTypeList) {
			tcTestingType.removeAllItems();
			for (int i = 0; i < testingTypes.size(); i++) {
				tcTestingType.addItem(testingTypes.getTestingTypeAt(i));
			}
		}
	}

}
