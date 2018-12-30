package edu.ncsu.csc216.pack_scheduler.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;


import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * The Faculty Schedule panel which displays the 
 * faculty schedule panel
 * @author Islahuddin Arshad
 *
 */
public class FacultySchedulePanel extends JPanel {
	
	/** ID number used for object serialization. */
	private static final long serialVersionUID = 1L;
	/** JTable for displaying the catalog of Courses */
	private JTable tableCatalog;
	/** TableModel for catalog */
	private CourseTableModel catalogTableModel;
	/** Panel for displaying Course Details */
	private JPanel pnlCourseDetails;
	/** Label for Course Details name title */
	private JLabel lblNameTitle = new JLabel("Name: ");
	/** Label for Course Details section title */
	private JLabel lblSectionTitle = new JLabel("Section: ");
	/** Label for Course Details title title */
	private JLabel lblTitleTitle = new JLabel("Title: ");
	/** Label for Course Details instructor title */
	private JLabel lblInstructorTitle = new JLabel("Instructor: ");
	/** Label for Course Details credit hours title */
	private JLabel lblCreditsTitle = new JLabel("Credits: ");
	/** Label for Course Details meeting title */
	private JLabel lblMeetingTitle = new JLabel("Meeting: ");
	/** Label for Course Details enrollment cap title */
	private JLabel lblEnrollmentCapTitle = new JLabel("Enrollment Cap: ");
	/** Label for Course Details open seats title */
	private JLabel lblOpenSeatsTitle = new JLabel("Open Seats: ");
	/** Label for Course Details name */
	private JLabel lblName = new JLabel("");
	/** Label for Course Details section */
	private JLabel lblSection = new JLabel("");
	/** Label for Course Details title */
	private JLabel lblTitle = new JLabel("");
	/** Label for Course Details instructor */
	private JLabel lblInstructor = new JLabel("");
	/** Label for Course Details credit hours */
	private JLabel lblCredits = new JLabel("");
	/** Label for Course Details meeting */
	private JLabel lblMeeting = new JLabel("");
	/** Label for Course Details enrollment cap */
	private JLabel lblEnrollmentCap = new JLabel("");
	/** Label for Course Details open seats */
	private JLabel lblOpenSeats = new JLabel("");
	/** Component for Course Roll */
	private JComponent scrollStudentDirectory;
	/** Table for CourseRoll */
	private JTable tableStudentDirectory;
	/** JScrollPane for Course catalog*/
	private JScrollPane scrollCatalog;
	/** Current user */
	private Faculty currentUser;
	/** Current user's schedule */
	private FacultySchedule schedule;
	
	
	

	
	/**
	 * Gets to create the schedule panel
	 * for display
	 */
	public FacultySchedulePanel() {
		super(new GridBagLayout());
		
		JPanel pnlActions = new JPanel();
		pnlActions.setLayout(new GridLayout(3, 1));
		
		
		//Set up Catalog table
				catalogTableModel = new CourseTableModel(true);
				tableCatalog = new JTable(catalogTableModel) {
					private static final long serialVersionUID = 1L;
					
					/**
					 * Set custom tool tips for cells
					 * @param e MouseEvent that causes the tool tip
					 * @return tool tip text
					 */
					public String getToolTipText(MouseEvent e) {
						java.awt.Point p = e.getPoint();
						int rowIndex = rowAtPoint(p);
						int colIndex = columnAtPoint(p);
						int realColumnIndex = convertColumnIndexToModel(colIndex);
						
						return (String)catalogTableModel.getValueAt(rowIndex, realColumnIndex);
					}
				};
				tableCatalog.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tableCatalog.setPreferredScrollableViewportSize(new Dimension(500, 500));
				tableCatalog.setFillsViewportHeight(true);
				tableCatalog.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						String name = tableCatalog.getValueAt(tableCatalog.getSelectedRow(), 0).toString();
						String section = tableCatalog.getValueAt(tableCatalog.getSelectedRow(), 1).toString();
						Course c = RegistrationManager.getInstance().getCourseCatalog().getCourseFromCatalog(name, section);
						updateCourseDetails(c);
					}
					
				});
		
		
		//Set up the course details panel
		pnlCourseDetails = new JPanel();
		pnlCourseDetails.setLayout(new GridLayout(5, 1));
		JPanel pnlName = new JPanel(new GridLayout(1, 4));
		pnlName.add(lblNameTitle);
		pnlName.add(lblName);
		pnlName.add(lblSectionTitle);
		pnlName.add(lblSection);
		
		JPanel pnlTitle = new JPanel(new GridLayout(1, 1));
		pnlTitle.add(lblTitleTitle);
		pnlTitle.add(lblTitle);
		
		JPanel pnlInstructor = new JPanel(new GridLayout(1, 4));
		pnlInstructor.add(lblInstructorTitle);
		pnlInstructor.add(lblInstructor);
		pnlInstructor.add(lblCreditsTitle);
		pnlInstructor.add(lblCredits);
		
		JPanel pnlMeeting = new JPanel(new GridLayout(1, 1));
		pnlMeeting.add(lblMeetingTitle);
		pnlMeeting.add(lblMeeting);
		
		JPanel pnlEnrollment = new JPanel(new GridLayout(1, 4));
		pnlEnrollment.add(lblEnrollmentCapTitle);
		pnlEnrollment.add(lblEnrollmentCap);
		pnlEnrollment.add(lblOpenSeatsTitle);
		pnlEnrollment.add(lblOpenSeats);
		
		pnlCourseDetails.add(pnlName);
		pnlCourseDetails.add(pnlTitle);
		pnlCourseDetails.add(pnlInstructor);
		pnlCourseDetails.add(pnlMeeting);
		pnlCourseDetails.add(pnlEnrollment);
		
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder borderCourseDetails = BorderFactory.createTitledBorder(lowerEtched, "Course Details");
		pnlCourseDetails.setBorder(borderCourseDetails);
		pnlCourseDetails.setToolTipText("Course Details");
		
		scrollCatalog = new JScrollPane(tableCatalog, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		catalogTableModel.updateData();
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollCatalog, c);
		
//		c.gridx = 0;
//		c.gridy = 1;
//		c.gridwidth = 1;
//		c.weightx = 1;
//		c.weighty = 1;
//		c.anchor = GridBagConstraints.FIRST_LINE_START;
//		c.fill = GridBagConstraints.BOTH;
//		add(pnlActions, c);
		
//		c.gridx = 0;
//		c.gridy = 2;
//		c.gridwidth = 1;
//		c.weightx = 1;
//		c.weighty = 1;
//		c.anchor = GridBagConstraints.FIRST_LINE_START;
//		c.fill = GridBagConstraints.BOTH;
//		add(scrollSchedule, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(pnlCourseDetails, c);
		
		//Set up Directory table for Course Roll
		StudentDirectoryTableModel studentDirectoryTableModel = new StudentDirectoryTableModel();
		tableStudentDirectory = new JTable(studentDirectoryTableModel);
		tableStudentDirectory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableStudentDirectory.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableStudentDirectory.setFillsViewportHeight(true);
				
		scrollStudentDirectory = new JScrollPane(tableStudentDirectory, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
		TitledBorder boarder = BorderFactory.createTitledBorder(lowerEtched, "Course Roll");
		scrollStudentDirectory.setBorder(boarder);
		scrollStudentDirectory.setToolTipText("Student Directory");
				
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollStudentDirectory, c);
	}
	
	
	/**
	 * Updates the pnlCourseDetails with full information about the most
	 * recently selected course.
	 */
	private void updateCourseDetails(Course c) {
		if (c != null) {
			lblName.setText(c.getName());
			lblSection.setText(c.getSection());
			lblTitle.setText(c.getTitle());
			lblInstructor.setText(c.getInstructorId());
			lblCredits.setText("" + c.getCredits());
			lblMeeting.setText(c.getMeetingString());
			lblEnrollmentCap.setText("" + c.getCourseRoll().getEnrollmentCap());
			lblOpenSeats.setText("" + c.getCourseRoll().getOpenSeats());
		}
	}
	
	
	
	/**
	 * {@link CourseTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of {@link Course}s to the user.
	 * @author Sarah Heckman
	 */
	private class CourseTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"Name", "Section", "Title", "Meeting Days", "Open Seats"};
		/** Data stored in the table */
		private Object [][] data;
		/** Boolean flag if the model applies to the catalog or schedule */
		private boolean isCatalog;
		
		/**
		 * Constructs the {@link CourseTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public CourseTableModel(boolean isCatalog) {
			this.isCatalog = isCatalog;
			updateData();
		}

		/**
		 * Returns the number of columns in the table.
		 * @return the number of columns in the table.
		 */
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Returns the number of rows in the table.
		 * @return the number of rows in the table.
		 */
		public int getRowCount() {
			if (data == null) 
				return 0;
			return data.length;
		}
		
		/**
		 * Returns the column name at the given index.
		 * @return the column name at the given column.
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}

		/**
		 * Returns the data at the given {row, col} index.
		 * @return the data at the given location.
		 */
		public Object getValueAt(int row, int col) {
			if (data == null)
				return null;
			return data[row][col];
		}
		
		/**
		 * Sets the given value to the given {row, col} location.
		 * @param value Object to modify in the data.
		 * @param row location to modify the data.
		 * @param column location to modify the data.
		 */
		public void setValueAt(Object value, int row, int col) {
			if (data[row][col] != null) {
				data[row][col] = value;
			}
			//data[row][col] = value;
			fireTableCellUpdated(row, col);
		}	
		
		/**
		 * Updates the given model with {@link Course} information from the {@link WolfScheduler}.
		 */
		private void updateData() {
			int i = 1;
			if (i == 1) {
				currentUser = (Faculty)RegistrationManager.getInstance().getCurrentUser();
				if (currentUser != null) {
					schedule = currentUser.getSchedule();
					data = schedule.getScheduledCourses();
					FacultySchedulePanel.this.repaint();
					FacultySchedulePanel.this.validate();
				}
				
			} else {
				currentUser = (Faculty)RegistrationManager.getInstance().getCurrentUser();
				if (currentUser != null) {
					schedule = currentUser.getSchedule();
//					txtScheduleTitle.setText(schedule.getTitle());
//					borderSchedule.setTitle(schedule.getTitle());
//					scrollSchedule.setToolTipText(schedule.getTitle());
					data = schedule.getScheduledCourses();
					
					FacultySchedulePanel.this.repaint();
					FacultySchedulePanel.this.validate();
					scrollCatalog.revalidate();
					scrollCatalog.repaint();
					//tableStudentDirectory.update();
					scrollStudentDirectory.revalidate(); 
					scrollStudentDirectory.repaint();
				}
			}
		}
		
	}
	
	/**
	 * {@link StudentDirectoryTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of Students to the user.
	 * @author Sarah Heckman
	 */
	private class StudentDirectoryTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"First Name", "Last Name", "Student ID"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructs the {@link StudentDirectoryTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public StudentDirectoryTableModel() {
			updateData();
		}

		/**
		 * Returns the number of columns in the table.
		 * @return the number of columns in the table.
		 */
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Returns the number of rows in the table.
		 * @return the number of rows in the table.
		 */
		public int getRowCount() {
			if (data == null) 
				return 0;
			return data.length;
		}
		
		/**
		 * Returns the column name at the given index.
		 * @return the column name at the given column.
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}

		/**
		 * Returns the data at the given {row, col} index.
		 * @return the data at the given location.
		 */
		public Object getValueAt(int row, int col) {
			if (data == null)
				return null;
			return data[row][col];
		}
		
		/**
		 * Sets the given value to the given {row, col} location.
		 * @param value Object to modify in the data.
		 * @param row location to modify the data.
		 * @param column location to modify the data.
		 */
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}
		
		/**
		 * Updates the given model with {@link Student} information from the {@link StudentDirectory}.
		 */
		public void updateData() {
			//data = studentDirectory.getStudentDirectory();
		}
	}
	
}
