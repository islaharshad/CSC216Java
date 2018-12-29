package edu.ncsu.csc216.backlog.task;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.command.Command.CommandValue;
import edu.ncsu.csc216.backlog.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.NoteItem;
import edu.ncsu.csc216.task.xml.NoteList;
import edu.ncsu.csc216.task.xml.Task;

/**
 * Tests all methods in task item class
 * @author Hiruni N., Isla A. 
 *
 */
public class TaskItemTest {

	/**
	 * Set up method allows each task created to be set to 1
	 * @throws Exception generic exception
	 */
	@Before
	public void setUp() throws Exception {
		TaskItem.setCounter(1);
	}

	/**
	 * Tests task item constructor
	 */
	@Test
	public void testTaskItem() {
		
		//tries to create a task item with null title
		try {
			@SuppressWarnings("unused")
			TaskItem first = new TaskItem(null, Type.FEATURE, "jep", "hhh");
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
		
		//tries to create a task item with empty string title
		try {
			@SuppressWarnings("unused")
			TaskItem first = new TaskItem("", Type.FEATURE, "jep", "hhh");
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
		
		//tries to create a task item with  with null type
		try {
			@SuppressWarnings("unused")
			TaskItem first = new TaskItem("title", null, "jep", "hhh");
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
		
		
		//tries to create a task item with null creator
		try {
			@SuppressWarnings("unused")
			TaskItem first = new TaskItem("title", Type.BUG, null, "hhh");
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
		
		//tries to create a task item with empty string creator
		try {
			@SuppressWarnings("unused")
			TaskItem first = new TaskItem("title", Type.BUG, "", "hhh");
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
		
		//tries to create a task with null note
		try {
			@SuppressWarnings("unused")
			TaskItem first = new TaskItem("title", Type.BUG, "jep", null);
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
		
		//tries to create a task with empty string note
		try {
			@SuppressWarnings("unused")
			TaskItem first = new TaskItem("title", Type.BUG, "jep", "");
			fail();
		} catch (IllegalArgumentException e) {
			// skip
		}
		
		//creates a valid TaskItem
		TaskItem second = new TaskItem("title", Type.FEATURE, "jep", "hello");
		assertEquals("title", second.getTitle());
		assertEquals(Type.FEATURE, second.getType());
		assertEquals("jep", second.getCreator());
		assertEquals("Backlog" , second.getStateName());
		assertEquals("hello", second.getNotes().get(0).getNoteText());
		assertEquals("F", second.getTypeString());
		assertEquals("Feature", second.getTypeFullString());
		
		
		//all commands are created
		Command c = new Command(CommandValue.CLAIM, "a", "note");
		Command b = new Command(CommandValue.BACKLOG, "b", "note");
		Command p = new Command(CommandValue.PROCESS, "c", "note");
		Command v = new Command(CommandValue.VERIFY, "d", "note");
		Command cm = new Command(CommandValue.COMPLETE, "e", "note");
		Command r = new Command(CommandValue.REJECT, "f", "note");
		
		//creates TaskItem - change states
		TaskItem third = new TaskItem("title", Type.FEATURE, "jep", "hello");
		//claim
		third.update(c);
		assertEquals("Owned", third.getStateName());
		//go to backlog
		third.update(b);
		assertEquals("Backlog", third.getStateName());
		//claim
		third.update(c);
		assertEquals("Owned", third.getStateName());
		//reject
		third.update(r);
		assertEquals("Rejected", third.getStateName());
		assertNull(third.getOwner());
		//go to backlog
		third.update(b);
		assertEquals("Backlog", third.getStateName());
		//invalid transition test 1
		try {
			third.update(v);
			fail();
		} catch(UnsupportedOperationException e) {
			//skip
		}
		//claim
		third.update(c);
		assertEquals("Owned", third.getStateName());
		//invalid transition test 2
		try {
			third.update(c);
			fail();
		} catch (UnsupportedOperationException e) {
			// skip
		}
		//process task
		third.update(p);
		assertEquals("Processing", third.getStateName());
		//process task again
		third.update(p);
		assertEquals("Processing", third.getStateName());
		//go to backlog
		third.update(b);
		assertEquals("Backlog", third.getStateName());
		assertNull(third.getOwner());
		//claim
		third.update(c);
		assertEquals("Owned", third.getStateName());
		//process task
		third.update(p);
		assertEquals("Processing", third.getStateName());
		//verify task
		third.update(v);
		assertEquals("Verifying", third.getStateName());
		//process task again
		third.update(p);
		assertEquals("Processing", third.getStateName());
		//complete task: because it's a feature type it should go to verifying state
		try {
			third.update(cm);
			fail();
		} catch(UnsupportedOperationException e) {
			//skip
		}
		//verify task
		third.update(v);
		assertEquals("Verifying", third.getStateName());
		//complete task
		third.update(cm);
		assertEquals("Done", third.getStateName());
		//process task
		third.update(p);
		assertEquals("Processing", third.getStateName());
		//verify task
		third.update(v);
		assertEquals("Verifying", third.getStateName());
		try {
			third.update(v); //van't verify again if it's in the verifying state
			fail();
		} catch(UnsupportedOperationException e) {
			//skip
		}
		//complete task
		third.update(cm);
		assertEquals("Done", third.getStateName());
		//invalid transition test 2
		try {
			third.update(v);
			fail();
		} catch(UnsupportedOperationException e) {
			//skip
		}
		//go to backlog
		third.update(b);
		assertEquals("Backlog", third.getStateName());

		//reject task
		third.update(r);
		assertEquals("Rejected", third.getStateName());
		try {
			//process task
			third.update(p);
			fail();
		} catch(UnsupportedOperationException e) {
			//skip
		}
		
		//taskitem with type knowledge acquisition
		TaskItem fourth = new TaskItem("title", Type.KNOWLEDGE_ACQUISITION, "jep", "hello");
		fourth.update(c); //claim
		fourth.update(p); //process
		try {
			fourth.update(v);
			fail();
		} catch(UnsupportedOperationException e) {
			//skip
		}
		fourth.update(cm);
		assertEquals("Done", fourth.getStateName());
				
	}
	
	/**
	 * Tests get notes array method
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testGetNotesArray() {
		TaskItem number = new TaskItem("hello", Type.BUG, "hiru", "awesome");
		assertEquals("hiru", number.getNotesArray()[0][0]);
		assertEquals("awesome", number.getNotesArray()[0][1]);
		try {
			number.setCounter(0);
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
		try {
			number.setCounter(3);
		} catch(IllegalArgumentException e) {
			fail();
		}
		Command x = new Command(CommandValue.CLAIM, "hfg", "yes");
		number.update(x);
		assertEquals(1, number.getTaskItemId());
		TaskItem numberOne = new TaskItem("hello", Type.BUG, "hiru", "awesome");
		assertEquals(3, numberOne.getTaskItemId()); 
		TaskItem numberTwo = new TaskItem("title", Type.KNOWLEDGE_ACQUISITION, "hiru", "awesome");
		assertEquals(4, numberTwo.getTaskItemId());
		
		assertEquals("hfg", number.getOwner()); //says it's null

	}
	
	/**
	 * Tests two methods : setState and setType
	 */
	@SuppressWarnings("unused")
	@Test
	public void testSetStateAndType() { //how can you test set state
		Task one = new Task(); //creates a new XML task
		one.setId(1); //sets Id to 1
		assertEquals(1, one.getId());
		one.setCreator("hiru"); //sets creator
		assertEquals("hiru", one.getCreator());
		one.setOwner("isla"); //sets owner
		assertEquals("isla", one.getOwner());
		one.setVerified(false); //sets verified to false
		
		try {
			one.setState(null);
			
			TaskItem first = new TaskItem(one);
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
		
		try {
			one.setState("");
			
			TaskItem first = new TaskItem(one);
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
		one.setState("Owned"); //sets state to owned
		assertEquals("Owned", one.getState());
		one.setType("B"); //sets type of task
		assertEquals("B", one.getType());
		one.setNoteList(new NoteList()); //creates a new notelist for Task one
		List<NoteItem> notes = one.getNoteList().getNotes(); //creates a new note list of note item
		NoteItem item = new NoteItem(); //creates first note item
		item.setNoteAuthor("jack"); //sets note author to first note item
		item.setNoteText("says hi"); //sets note text to first note item
		NoteItem item1 = new NoteItem(); //creates the second note item
		item1.setNoteAuthor("john"); //sets note author to second note item
		item1.setNoteText("says hi back"); //sets note text to second note item
		notes.add(item); //adds the first note item
		notes.add(item1); //adds the second note item
		
		TaskItem second = new TaskItem(one);
		
		//create new task to test setType and setState (different conditions)
		Task two = new Task();
		two.setState("Processing");
		assertEquals("Processing", two.getState());
		two.setType("F"); 
		assertEquals("F", two.getType());
		List<NoteItem> notes1 = one.getNoteList().getNotes(); //creates a new note list of note item
		NoteItem item2 = new NoteItem(); //creates first note item
		item2.setNoteAuthor("jack"); //sets note author to first note item
		item2.setNoteText("says hi"); //sets note text to first note item
		two.setNoteList(new NoteList()); //creates a new notelist for Task one
		NoteItem item3 = new NoteItem(); //creates the second note item
		item3.setNoteAuthor("john"); //sets note author to second note item
		item3.setNoteText("says hi back"); //sets note text to second note item
		notes1.add(item2); //adds the first note item
		notes1.add(item3); //adds the second note item
		
		TaskItem third = new TaskItem(two);
		
		//create new task test type
		Task three = new Task();
		three.setCreator("hiru");
		three.setId(3);
		three.setOwner("john");
		three.setState("Backlog");
		three.setTitle("new title");
		three.setType("KA");
		three.setVerified(false);
		List<NoteItem> notes2 = one.getNoteList().getNotes();
		three.setNoteList(new NoteList());
		NoteItem item4 = new NoteItem();
		item4.setNoteAuthor("ben");
		item4.setNoteText("I got it");
		NoteItem item5 = new NoteItem();
		item5.setNoteAuthor("sam");
		item5.setNoteText("I got this one");
		notes2.add(item4);
		notes2.add(item5);
		TaskItem fourth = new TaskItem(three);
		three.setState("Rejected");
		TaskItem fifth = new TaskItem(three);
		
		three.setState("Verifying");
		TaskItem sixth = new TaskItem(three);
		three.setState("Done");
		TaskItem seventh = new TaskItem(three);
		try {
			three.setState("something"); //need to check this! it's going through even though it should throw IAE
			TaskItem badState = new TaskItem(three);
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
		
		
	}
	
	/**
	 * Tests get XML task method 
	 */
	@Test
	public void testGetXMLTask() {
		
		Task t = new Task();
		t.setId(1); //sets Id to 1
		assertEquals(1, t.getId());
		t.setCreator("hiru"); //sets creator
		assertEquals("hiru", t.getCreator());
		t.setOwner("isla"); //sets owner
		assertEquals("isla", t.getOwner());
		t.setType("B");
		t.setState("Owned");
		t.setVerified(false); //sets verified to false
		t.setNoteList(new NoteList()); //creates a new notelist for Task one
		List<NoteItem> notes = t.getNoteList().getNotes(); //creates a new note list of note item
		NoteItem item = new NoteItem(); //creates first note item
		item.setNoteAuthor("jack"); //sets note author to first note item
		item.setNoteText("says hi"); //sets note text to first note item
		NoteItem item1 = new NoteItem(); //creates the second note item
		item1.setNoteAuthor("john"); //sets note author to second note item
		item1.setNoteText("says hi back"); //sets note text to second note item
		notes.add(item); //adds the first note item
		notes.add(item1); //adds the second note item
		
		TaskItem third = new TaskItem(t);
		assertEquals("hiru", third.getXMLTask().getCreator());
	}
	
	/**
	 * More tests updated for processing state
	 */
	@SuppressWarnings("unused")
	@Test
	public void testMoreProcessingState() {
		TaskItem a = new TaskItem("title", Type.BUG, "bob", "text");
		assertEquals("Backlog", a.getStateName());
		Command one = new Command(CommandValue.CLAIM, "sam", "note");
		a.update(one);
		Command two = new Command(CommandValue.PROCESS, "sam", "note");
		a.update(two);
		assertEquals("Processing", a.getStateName());
		Command three = new Command(CommandValue.VERIFY, "sam", "note");
		
	}

	/**
	 * Test get type string method
	 */
	@Test
	public void testGetTypeString() {
		TaskItem a = new TaskItem("title", Type.BUG, "bob", "text");
		assertEquals("B", a.getTypeString());
		
		TaskItem b = new TaskItem("title", Type.KNOWLEDGE_ACQUISITION, "bob", "text");
		assertEquals("KA", b.getTypeString());
		
		TaskItem c = new TaskItem("title", Type.TECHNICAL_WORK, "bob", "text");
		assertEquals("TW", c.getTypeString());
	}
	
	/**
	 * Test get type full string method
	 */
	@Test
	public void testGetTypeFullString() {
		TaskItem a = new TaskItem("title", Type.BUG, "bob", "text");
		assertEquals("Bug", a.getTypeFullString());
		
		TaskItem b = new TaskItem("title", Type.KNOWLEDGE_ACQUISITION, "bob", "text");
		assertEquals("Knowledge Acquisition", b.getTypeFullString());
		
		TaskItem c = new TaskItem("title", Type.TECHNICAL_WORK, "bob", "text");
		assertEquals("Technical Work", c.getTypeFullString());
	}
	
	/**
	 * More tests to test setType
	 */
	@SuppressWarnings("unused")
	@Test
	public void testSetType() {
		Task a = new Task();
		a.setCreator("hiru");
		a.setId(1);
		a.setOwner("hiru");
		a.setState("Done");
		a.setTitle("note");
		a.setType(null); //try to make a taskitem with type null
		a.setVerified(false); //sets verified to false
		a.setNoteList(new NoteList()); //creates a new notelist for Task one
		List<NoteItem> notes = a.getNoteList().getNotes(); //creates a new note list of note item
		NoteItem item = new NoteItem(); //creates first note item
		item.setNoteAuthor("jack"); //sets note author to first note item
		item.setNoteText("says hi"); //sets note text to first note item
		NoteItem item1 = new NoteItem(); //creates the second note item
		item1.setNoteAuthor("john"); //sets note author to second note item
		item1.setNoteText("says hi back"); //sets note text to second note item
		notes.add(item); //adds the first note item
		notes.add(item1); //adds the second note item
		
		try {
			TaskItem one = new TaskItem(a);
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
		a.setState("");
		try {
			TaskItem one = new TaskItem(a);
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
		
		//type test extra
		assertEquals(Type.FEATURE, Type.valueOf("FEATURE"));
	}
}
