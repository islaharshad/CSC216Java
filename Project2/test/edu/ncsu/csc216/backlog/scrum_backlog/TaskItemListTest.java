package edu.ncsu.csc216.backlog.scrum_backlog;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.command.Command.CommandValue;
import edu.ncsu.csc216.backlog.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.NoteItem;
import edu.ncsu.csc216.task.xml.NoteList;
import edu.ncsu.csc216.task.xml.Task;

/**
 * Tests each method of task item list class
 * @author Hiruni N.
 * @author Isla A
 */
public class TaskItemListTest {

	/**
	 * Tests get task items method
	 */
	@Test
	public void testGetTaskItems(){
		TaskItemList til1 = new TaskItemList();
			
		assertNull(til1.getTaskItemById(1));
		
		til1.addTaskItem("Checking", Type.BUG, "isla", "note");
		
		assertEquals("Checking", til1.getTaskItemById(1).getTitle());
		assertEquals(Type.BUG, til1.getTaskItemById(1).getType());
		
		assertNull(til1.getTaskItemById(2));
		
		TaskItemList a = new TaskItemList();
		
		a.addTaskItem("title", Type.BUG, "hiru", "hello");
		a.addTaskItem("title2", Type.FEATURE, "hiru", "hello");
		
		TaskItemList til2 = new TaskItemList();
		Task x = new Task();
		x.setId(1);
		x.setCreator("hiru");
		x.setOwner("df");
		x.setState("Owned");
		x.setType("B");
		x.setVerified(false);
		x.setNoteList(new NoteList());
		List<NoteItem> notes1 = x.getNoteList().getNotes(); //creates a new note list of note item
		NoteItem item2 = new NoteItem(); //creates first note item
		item2.setNoteAuthor("jack"); //sets note author to first note item
		item2.setNoteText("says hi"); //sets note text to first note item
		x.setNoteList(new NoteList()); //creates a new notelist for Task one
		NoteItem item3 = new NoteItem(); //creates the second note item
		item3.setNoteAuthor("john"); //sets note author to second note item
		item3.setNoteText("says hi back"); //sets note text to second note item
		notes1.add(item2); //adds the first note item
		notes1.add(item3); //adds the second note item
		List<Task> list = new ArrayList<Task>();
		list.add(x);
		til2.addXMLTasks(list);		
	}
	
	/**
	 * Tests getTasksItemsByOwner()
	 */
	@Test
	public void testGetTasksItemsByOwner() {
		
		TaskItemList x = new TaskItemList();
		x.addTaskItem("a", Type.BUG, "hiru", "note1");
		x.addTaskItem("b", Type.FEATURE, "bob", "note2");
		x.addTaskItem("c", Type.FEATURE, "hiru", "note3");
		assertEquals(3, x.getTaskItems().size());
		System.out.println(x);
		assertEquals("a", x.getTasksByCreator("hiru").get(0).getTitle());
		Command c = new Command(CommandValue.CLAIM, "jack", "claimed");
		x.getTasksByCreator("hiru").get(0).update(c);
		assertEquals("jack", x.getTaskItemsByOwner("jack").get(0).getOwner());
		Command d = new Command(CommandValue.REJECT, "dog", "claimed1");
		x.executeCommand(2, d);
		x.deleteTaskItemById(3);
		
		try {
			assertEquals("a", x.getTaskItemsByOwner(null).get(0).getTitle());
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
	}
	
	/**
	 * Tests getTasksByCreator()
	 */
	@Test
	public void testGetTasksByCreator() {
		TaskItemList x = new TaskItemList();
		x.addTaskItem("a", Type.BUG, "hiru", "note1");
		x.addTaskItem("b", Type.FEATURE, "bob", "note2");
		x.addTaskItem("c", Type.FEATURE, "hiru", "note3");
		assertEquals(3, x.getTaskItems().size());
		System.out.println(x);
		assertEquals("a", x.getTasksByCreator("hiru").get(0).getTitle());
		Command d = new Command(CommandValue.REJECT, "dog", "claimed1");
		x.executeCommand(2, d);
		x.deleteTaskItemById(3);
		try {
			assertEquals("b", x.getTasksByCreator(null).get(0).getTitle());
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
	}
	
	/**
	 * Tests executeCommand()
	 */
	@Test
	public void testExecuteCommand() {
		TaskItemList x = new TaskItemList();
		x.addTaskItem("a", Type.BUG, "hiru", "note1");
		x.addTaskItem("b", Type.FEATURE, "bob", "note2");
		x.addTaskItem("c", Type.FEATURE, "hiru", "note3");
		assertEquals(3, x.getTaskItems().size());
		System.out.println(x);
		assertEquals("a", x.getTasksByCreator("hiru").get(0).getTitle());
		Command d = new Command(CommandValue.REJECT, "dog", "claimed1");
		x.executeCommand(2, d);
	}
	
	/**
	 * Tests deleteTaskItemByID()
	 */
	@Test
	public void testDeleteTaskItemById() {
		TaskItemList x = new TaskItemList();
		x.addTaskItem("a", Type.BUG, "hiru", "note1");
		x.addTaskItem("b", Type.FEATURE, "bob", "note2");
		x.addTaskItem("c", Type.FEATURE, "hiru", "note3");
		assertEquals(3, x.getTaskItems().size());
		System.out.println(x);
		assertEquals("a", x.getTasksByCreator("hiru").get(0).getTitle());
		Command d = new Command(CommandValue.REJECT, "dog", "claimed1");
		x.executeCommand(2, d);
		x.deleteTaskItemById(3);
	}
}