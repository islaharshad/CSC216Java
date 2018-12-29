package edu.ncsu.csc216.backlog.command;

/**
 * Command creates objects that encapsulate user actions (or transitions) 
 * from data entered in the GUI that correspond to a button click that cause 
 * the state of a TaskItem to update
 * @author Hiruni Nelakkutti
 *
 */
public class Command {
	
	/** Note for task */
	private String note;
	
	/** Note author of task */
	private String noteAuthor;
	
	/** Command value: backlog, claim, process, verify, complete, reject */
	private CommandValue c;
	
	/** 
	 * Constructor for Command takes in a command value, note author, and a note
	 * @param c command value
	 * @param noteAuthor name of the note author
	 * @param noteText text of the note
	 */
	public Command(CommandValue c, String noteAuthor, String noteText) {
		if(c == null || noteAuthor == null || noteAuthor.equals("") || 
				noteText == null || noteText.equals("")) {
			throw new IllegalArgumentException();
		}
		
		this.c = c;
		this.noteAuthor = noteAuthor;
		this.note = noteText;
	}
	
	/**
	 * Gets command value
	 * @return c command value
	 */
	public CommandValue getCommand() {
		return c;
	}
	
	/** 
	 * Gets note text
	 * @return note note
	 */
	public String getNoteText() {
		return note;
	}
	
	/**
	 * Gets author of note
	 * @return noteAuthor author of note
	 */
	public String getNoteAuthor() {
		return noteAuthor;
	}
	
	/**
	 * Contains possible commands (which corresponds to a button click by user)
	 * Predifined constant values - Commands
	 * @author Hiruni Nelakkutti 
	 *
	 */
	public static enum CommandValue {
		/** Backlog command */
		BACKLOG, 
		
		/** Claim command */
		CLAIM,
		
		/** Process command */
		PROCESS,
		
		/** Verify command */
		VERIFY,
		
		/** Complete command */
		COMPLETE, 
		
		/** Reject command */
		REJECT;
		
		/** 
		 * Constructor for command value(empty)
		 */
		CommandValue() { //is this right?
			
		}
	}

}
