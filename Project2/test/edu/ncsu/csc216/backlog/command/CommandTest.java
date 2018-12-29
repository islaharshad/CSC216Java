package edu.ncsu.csc216.backlog.command;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.backlog.command.Command.CommandValue;

/**
 * Tests all methods in command class methods
 * @author Hiruni N., Isla A.
 *
 */
public class CommandTest {

	/**
	 * Tests all methods in command
	 * Tests constructing command, gets command, gets note text,
	 * gets note author.
	 */
	@Test
	public void testCommand() {
		
		//tests constructing command with null Command value
		try {
			@SuppressWarnings("unused")
			Command c = new Command(null, "jes", "hi");
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
		
		//tries constructing command with null noteAuthor
		try {
			@SuppressWarnings("unused")
			Command c = new Command(CommandValue.BACKLOG, null, "hi");
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
		
		//tries constructing command with empty string noteAuthor
		try {
			@SuppressWarnings("unused")
			Command c = new Command(CommandValue.CLAIM, "", "hi");
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
		
		//tries constructing command with null noteText
		try {
			@SuppressWarnings("unused")
			Command c = new Command(CommandValue.COMPLETE, "jes", null);
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
		
		//tries constructing command with empty string noteText
		try {
			@SuppressWarnings("unused")
			Command c = new Command(CommandValue.COMPLETE, "jes", "");
			fail();
		} catch (IllegalArgumentException e) {
			// skip
		}
		
		//constructs a valid constructor
		Command x = new Command(CommandValue.BACKLOG, "jes", "hello");
		assertEquals(CommandValue.BACKLOG, x.getCommand());
		assertEquals("jes", x.getNoteAuthor());
		assertEquals("hello", x.getNoteText());
		
		assertEquals(CommandValue.BACKLOG, CommandValue.valueOf("BACKLOG"));
	}

}
