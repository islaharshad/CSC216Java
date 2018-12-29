package edu.ncsu.csc216.backlog.task;

/**
 * Note class can construct a note with an author and note text 
 * It can get a note author and it can get a note text
 * @author Hiruni Nelakkutti and Isla Arshad
 *
 */
public class Note {

	/** Author of note */
	private String noteAuthor;
	
	/** Note text */
	private String noteText;
	
	/**
	 * Constructor for note: constructs with note author and note text
	 * @param noteAuthor author of note
	 * @param noteText note text
	 */
	public Note(String noteAuthor, String noteText) {
		
		if(noteAuthor == null || noteAuthor.equals("") || noteText == null || noteText.equals("")) {
			throw new IllegalArgumentException();
		}
		this.noteAuthor = noteAuthor;
		this.noteText = noteText;	
	}
	
	/** 
	 * Gets author of note
	 * @return note author
	 */
	public String getNoteAuthor() {
		return noteAuthor;
	}
	
	/**
	 * Gets actual note text
	 * @return note text
	 */
	public String getNoteText() {
		return noteText;
	}
	
	/**
	 * Gets note array with note author at first position and note text at second position
	 * @return note array with note author and note text in that order
	 */
	public String[] getNoteArray() {
		String[] noteArray = new String[2];
		noteArray[0] = noteAuthor;
		noteArray[1] = noteText;	
		return noteArray;
	}
}
