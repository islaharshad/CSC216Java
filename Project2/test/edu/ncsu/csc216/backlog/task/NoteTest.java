package edu.ncsu.csc216.backlog.task;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests task item test
 * Tests all the methods in task item
 * @author Hiru and Isla
 *
 */
public class NoteTest {

	/**
	 * Set up method
	 * @throws Exception exception
	 */
	@Before
	public void setUp() throws Exception {
		//skip
	}

	/** 
	 * Test that is not yet implemented
	 */
	@Test
	public void testNote() {
		//tests note that is being created using a null note author
		try {
			@SuppressWarnings("unused")
			Note n = new Note(null, "hello");
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
		
		//tests note that is being created using an empty string note author
		try {
			@SuppressWarnings("unused")
			Note n = new Note("", "hello");
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
		
		//tests note that is being created using a null note text
		try {
			@SuppressWarnings("unused")
			Note n = new Note("jep", null);
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
		
		//tests note that is being created using an empty note text
		try {
			@SuppressWarnings("unused")
			Note n = new Note("jep", "");
			fail();
		} catch (IllegalArgumentException e) {
			// skip
		}

		//valid note constructed and get note author
		Note x = new Note("jep", "hello");
		assertEquals("jep", x.getNoteAuthor());
		assertEquals("hello", x.getNoteText());
		
		//accesses note array
		Note b = new Note("bb", "shdf");
		Note c = new Note("st", "hs");
		assertEquals("bb", b.getNoteArray()[0]);
		assertEquals("shdf", b.getNoteArray()[1]);
		assertEquals("st", c.getNoteArray()[0]);
		assertEquals("hs", c.getNoteArray()[1]);
	}

}
