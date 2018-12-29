package edu.ncsu.csc216.backlog.task;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.command.Command.CommandValue;
import edu.ncsu.csc216.task.xml.NoteItem;
import edu.ncsu.csc216.task.xml.NoteList;
import edu.ncsu.csc216.task.xml.Task;

/**
 * Task Item represents one task
 * Constructs a task using title, type, creator, and note
 * Has getters and setters for states and other mentioned fields
 * @author Hiruni Nelakkutti and Isla Arshad
 *
 */
public class TaskItem {

	/** Task ID */
	private int taskId;
	/** State field used to change states */
	private TaskItemState state;
	/** Title of the task */
	private String title;
	/** Creator of the task */
	private String creator;
	/** Owner of the task */
	private String owner;
	/** Boolean check for verify state */
	private boolean isVerified;
	/** Creates new back log state */
	private final TaskItemState backlogState = new BacklogState();
	/** Creates new owned state */
	private final TaskItemState ownedState = new OwnedState();
	/** Creates new processing state */
	private final TaskItemState processingState = new ProcessingState();
	/** Creates new verifying state */
	private final TaskItemState verifyingState = new VerifyingState();
	/** Creates new done state */
	private final TaskItemState doneState = new DoneState();
	/** Creates new rejected state */
	private final TaskItemState rejectedState = new RejectedState();
	/** Back log name */
	public static final String BACKLOG_NAME = "Backlog";
	/** Owned name */
	public static final String OWNED_NAME = "Owned";
	/** Processing name */
	public static final String PROCESSING_NAME = "Processing";
	/** Verifying name */
	public static final String VERIFYING_NAME = "Verifying";
	/** Done name */
	public static final String DONE_NAME = "Done";
	/** Rejected name */
	public static final String REJECTED_NAME = "Rejected";
	/** Feature type of task */
	public static final String T_FEATURE = "F";
	/** Bug type of task */
	public static final String T_BUG = "B";
	/** Technical work type of task */
	public static final String T_TECHNICAL_WORK = "TW";
	/** Knowledge acquisition type of task */
	public static final String T_KNOWLEDGE_ACQUISITION = "KA";
	/** Counter: starts with 1: maintains taskId number of next TaskItem */
	private static int counter = 1;
	/** Notes field */
	private ArrayList<Note> notes = new ArrayList<Note>();

	/** Type of task container */
	public static enum Type {
		/** Feature type */
		FEATURE,
		
		/** Bug type */
		BUG, 
		
		/** Technical work type */
		TECHNICAL_WORK, 
		
		/** Knowledge acquisition type */
		KNOWLEDGE_ACQUISITION
	} 

	/** Type called type */
	private Type type;

	/**
	 * Constructor for Task item
	 * 
	 * @param title of task
	 * @param type of task
	 * @param creator of task
	 * @param note of task
	 */
	public TaskItem(String title, Type type, String creator, String note) {
		if (title == null || title.equals("") || type == null || creator == null || creator.equals("") || note == null 
				|| note.equals("")) {
			throw new IllegalArgumentException();
		}

		state = backlogState;
		this.type = type;
		this.title = title;
		this.creator = creator;
		notes.add(new Note(creator, note));
		taskId = counter;
		incrementCounter();
	}

	/**
	 * Constructor for TaskItem
	 * 
	 * @param task task that taskItem takes in
	 */
	public TaskItem(Task task) {
		this.taskId = task.getId();
		this.setState(task.getState());
		this.setType(task.getType());
		this.title = task.getTitle();
		this.creator = task.getCreator();	
		this.owner = task.getOwner();
		this.isVerified = task.isVerified();
		notes = new ArrayList<Note>();
		List<NoteItem> list = task.getNoteList().getNotes();
		for(int i = 0; i < list.size(); i++) {
			Note n = new Note(list.get(i).getNoteAuthor(), list.get(i).getNoteText());
			notes.add(n);
		}	
	}

	/**
	 * Increments counter after current counter value is assigned to new
	 * TaskItem's taskId
	 */
	public static void incrementCounter() {
		counter++;
	}

	/**
	 * Gets task item Id
	 * 
	 * @return taskId of task item
	 */
	public int getTaskItemId() {
		return taskId;
	}

	/**
	 * Gets state name accordingly
	 * 
	 * @return backlog name if state is in backlog state; owned if state is in
	 * owned state; processing if state is in processing state;
	 * verifying if state is in verifying state; done if state is in
	 * done state; rejected if state is in rejected state
	 */
	public String getStateName() {
		return state.getStateName(); 
	}

	/**
	 * Sets state accordingly 
	 * throw IAE if state value is not one of the name of the states or if it's null
	 * @param stateValue represents value of state
	 * @throws if state value is null or does not match any one of the state names
	 */
	private void setState(String stateValue) { // not sure
		if (stateValue == null || stateValue.equals("")) {
			throw new IllegalArgumentException();
		} else if (stateValue.equals(BACKLOG_NAME)) {
				state = backlogState;
		} else if (stateValue.equals(OWNED_NAME)) {
				state = ownedState;
		} else if (stateValue.equals(PROCESSING_NAME)) {
				state = processingState;
		} else if (stateValue.equals(VERIFYING_NAME)) {
				state = verifyingState;
		} else if (stateValue.equals(DONE_NAME)) {
				state = doneState;
		} else if (stateValue.equals(REJECTED_NAME)){
				state = rejectedState;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Sets type of task Types are feature, bug, technical work, and knowledge
	 * acquisition if typeValue does not equal to any one of these or if it's
	 * null throws an IAE
	 * @param typeValue
	 * @throws IllegalArgumentException if type value is wrong or null
	 */
	private void setType(String typeValue) {
		if (typeValue == null || typeValue.equals("")) {
			throw new IllegalArgumentException();
		} else if (typeValue.equals(T_FEATURE)) {
			type = Type.FEATURE;
		} else if (typeValue.equals(T_BUG)){
			type = Type.BUG;
		} else if (typeValue.equals(T_TECHNICAL_WORK)) {
			type = Type.TECHNICAL_WORK;
		} else if (typeValue.equals(T_KNOWLEDGE_ACQUISITION)) {
			type = Type.KNOWLEDGE_ACQUISITION;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Gets the type of the task
	 * @return type of task
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Gets type string of task
	 * @return type string (there's 4 types)
	 */
	public String getTypeString() {
		if (type == Type.FEATURE) {
			return T_FEATURE;
		}
		if (type == Type.BUG) {
			return T_BUG;
		}
		if (type == Type.KNOWLEDGE_ACQUISITION) {
			return T_KNOWLEDGE_ACQUISITION;
		}
		if (type == Type.TECHNICAL_WORK) {
			return T_TECHNICAL_WORK;
		}
		return null; 
	} 
 
	/**
	 * Gets full type string - not shorthand of each type
	 * @return full type string of each type
	 */
	public String getTypeFullString() { // not sure if I have to use XML file here
		if (type == Type.FEATURE) {
			return "Feature";
		}
		if (type == Type.BUG) {
			return "Bug";
		}
		if (type == Type.KNOWLEDGE_ACQUISITION) {
			return "Knowledge Acquisition";
		}
		if (type == Type.TECHNICAL_WORK) {
			return "Technical Work";
		}
		return null; 
	}

	/**
	 * Gets owner of task
	 * @return owner of task
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Gets title of task
	 * @return title of task
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets creator of task
	 * @return creator of task
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * Gets an array list of notes
	 * @return array list of notes 
	 */
	public ArrayList<Note> getNotes() { 
		return notes;
	}
  
	/**
	 * Updates task item with given command through note
	 * @param command command given
	 */
	public void update(Command command) { 
		this.state.updateState(command);
		if (command.getNoteAuthor() != null && command.getNoteText() != null) {
			Note n = new Note(command.getNoteAuthor(), command.getNoteText());
			this.notes.add(n);
			
		}
	}

	/**
	 * Gets XML task. 
	 * Sets Id, creator, owner, state, title, type, verified
	 * Creates new note list and sets new note list
	 * @return a task
	 */
	public Task getXMLTask() {
		Task t = new Task();
		t.setId(this.taskId);
		t.setCreator(this.creator);
		t.setOwner(this.owner);
		t.setState(this.getStateName());
		t.setTitle(this.title);
		t.setType(this.type.name());
		t.setVerified(isVerified);
		
		NoteList noteList = new NoteList();
		t.setNoteList(noteList);
		
		for (int i = 0; i < notes.size(); i++) {
			NoteItem noteItem = new NoteItem();
			Note n = notes.get(i);
			noteItem.setNoteAuthor(n.getNoteAuthor());
			noteItem.setNoteText(n.getNoteText());
			noteList.getNotes().add(noteItem);			
		}
		return t;
	}

	/**
	 * Sets the counter
	 * @param count counter number of task
	 * @throws IllegalArgumentException if counter is less than or equal to 0
	 */
	public static void setCounter(int count) {
		if (count <= 0) {
			throw new IllegalArgumentException();
		}
		counter = count;
	}

	/**
	 * Returns a 2D array of notes At 0 index is the author and at index 1 is
	 * the text
	 * @return 2D array of notes
	 */
	public String[][] getNotesArray() {
		
		String[][] notesArray = new String[notes.size()][2];
		
		for (int i = 0; i < notesArray.length; i++) {
			notesArray[i][0] = notes.get(i).getNoteAuthor();
			notesArray[i][1] = notes.get(i).getNoteText();
		}
		return notesArray;
	}

	/**
	 * Backlog state implements TaskItemState Updates it's state when given a
	 * command and knows its name
	 * @author Hiruni Nelakkutti
	 *
	 */
	private class BacklogState implements TaskItemState {

		/**
		 * Constructs Backlog state(empty)
		 */
		private BacklogState() {

		}

		/**
		 * Updates backlog state when given a command
		 * Throws an unsupported operation exception if it's an invalid state transition
		 * @param command command given
		 * @throws unsupported operation exception if invalid state transition
		 */
		public void updateState(Command command) {
			if (command.getCommand() == CommandValue.CLAIM) {
				owner = command.getNoteAuthor();
				state = ownedState;
			}
			else if (command.getCommand() == CommandValue.REJECT) {
				state = rejectedState;
				owner = null;
			}
			else {
				throw new UnsupportedOperationException();
			}	
		}

		/**
		 * Gets name of backlog state
		 * 
		 * @return name of backlog state
		 */
		public String getStateName() {				
			return BACKLOG_NAME;
		}
	}

	/**
	 * Owned state implements TaskItemState Updates it's state when given a
	 * command and knows its name
	 * 
	 * @author Hiruni Nelakkutti
	 *
	 */
	private class OwnedState implements TaskItemState {

		/**
		 * Constructs owned state(empty)
		 */
		private OwnedState() {

		}

		/**
		 * Updates owned state when given a command
		 * Throws an unsupported operation exception if it's an invalid state transition
		 * @param command command given
		 * @throws unsupported operation exception if invalid state transition
		 */
		public void updateState(Command command) {
			if (state == ownedState) {
				if (command.getCommand() == CommandValue.BACKLOG) {
					owner = null;
					state = backlogState;
				}
				else if (command.getCommand() == CommandValue.PROCESS) {
					state = processingState;
				}
				else if (command.getCommand() == CommandValue.REJECT) {
					owner = null;
					state = rejectedState;
				}
				else {
					throw new UnsupportedOperationException();
				}
			}		
		}

		/**
		 * Gets name of owned state
		 * 
		 * @return name of owned state
		 */
		public String getStateName() {
			return OWNED_NAME;

		}
	}

	/**
	 * Processing state implements TaskItemState Updates it's state when given a
	 * command and knows its name
	 * 
	 * @author Hiruni Nelakkutti
	 *
	 */
	private class ProcessingState implements TaskItemState {

		/**
		 * Constructs processing state(empty)
		 */
		private ProcessingState() {

		}

		/**
		 * Updates processing state when given a command
		 * Throws an unsupported operation exception if it's an invalid state transition
		 * @param command command given
		 * @throws unsupported operation exception if invalid state transition
		 */
		public void updateState(Command command) {
			if (command.getCommand() == CommandValue.PROCESS) {
//				owner = command.getNoteAuthor();
				Note note = new Note(owner, command.getNoteText());
				notes.add(note);
				state = processingState;
			}
			else if (command.getCommand() == CommandValue.BACKLOG) {
				state = backlogState;
				owner = null;
			}
			else if (command.getCommand() == CommandValue.VERIFY) {
				if(type == Type.KNOWLEDGE_ACQUISITION) {
					throw new UnsupportedOperationException();
				}
				state = verifyingState;
//				owner = null; //this was added
			}
			else if (command.getCommand() == CommandValue.COMPLETE) {
				if(type == Type.KNOWLEDGE_ACQUISITION) {
					state = doneState;
				}
				else {
					throw new UnsupportedOperationException();
				}
				//owner = null;
			}
			else {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Gets name of processing state
		 * 
		 * @return name of processing state
		 */
		public String getStateName() {
			return PROCESSING_NAME;

		}
	}

	
	
	
	/**
	 * Verifying state implements TaskItemState Updates it's state when given a
	 * command and knows its name
	 * 
	 * @author Hiruni Nelakkutti
	 *
	 */
	private class VerifyingState implements TaskItemState {

		/**
		 * Constructs verifying state(empty)
		 */
		private VerifyingState() {

		}

		/**
		 * Updates verifying state when given a command
		 * Throws an unsupported operation exception if it's an invalid state transition
		 * @param command command given
		 * @throws unsupported operation exception if invalid state transition
		 */
		public void updateState(Command command) {
			if (command.getCommand() == CommandValue.PROCESS) {
				
				state = processingState;
				
			}
			else if (command.getCommand() == CommandValue.COMPLETE) {
				state = doneState;
			}
			else {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Gets name of verifying state
		 * 
		 * @return name of verifying state
		 */
		public String getStateName() {
			return VERIFYING_NAME;

		}
	}

	/**
	 * Done state implements TaskItemState Updates it's state when given a
	 * command and knows its name
	 * 
	 * @author Hiruni Nelakkutti
	 *
	 */
	private class DoneState implements TaskItemState {

		/**
		 * Constructs done state(empty)
		 */
		private DoneState() {

		}

		/**
		 * Updates done state when given a command
		 * Throws an unsupported operation exception if it's an invalid state transition
		 * @param command command given
		 * @throws unsupported operation exception if invalid state transition
		 */
		public void updateState(Command command) {
			if (command.getCommand() == CommandValue.PROCESS) {
				state = processingState;
				
			}
			else if (command.getCommand() == CommandValue.BACKLOG) {
				state = backlogState;
				owner = null; //this was added
			}
			else {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Gets name of done state
		 * 
		 * @return name of done state
		 */
		public String getStateName() {
			return DONE_NAME;

		}
	}

	/**
	 * Rejected state implements TaskItemState Updates it's state when given a
	 * command and knows its name
	 * 
	 * @author Hiruni Nelakkutti
	 *
	 */
	private class RejectedState implements TaskItemState {

		/**
		 * Constructs Reject state(empty)
		 */
		private RejectedState() {

		}

		/**
		 * Updates rejected state when given a command
		 * Throws an unsupported operation exception if it's an invalid state transition
		 * @param command command given
		 * @throws unsupported operation exception if invalid state transition
		 */
		public void updateState(Command command) {
			
			if (state == rejectedState) {
				if (command.getCommand() == CommandValue.BACKLOG) {
					state = backlogState;
					owner = null;
				}
				else {
					throw new UnsupportedOperationException();
				}
			}
		}

		/**
		 * Gets name of rejected state
		 * 
		 * @return name of rejected state
		 */
		public String getStateName() {
			return REJECTED_NAME;
		}
	}


	/**
	 * Interface for states in the Task State Pattern. All concrete task states must
	 * implement the TaskState interface.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
	 */
	private interface TaskItemState {

		/**
		 * Update the {@link TaskItem} based on the given {@link Command}. An
		 * {@link UnsupportedOperationException} is throw if the
		 * {@link CommandValue} is not a valid action for the given state.
		 * @param c {@link Command} describing the action that will update the {@link TaskItem}'s state.
		 * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action for the given state.
		 */
		void updateState(Command c);

		/**
		 * Returns the name of the current state as a String.
		 * 
		 * @return the name of the current state as a String.
		 */
		String getStateName();

	}
}