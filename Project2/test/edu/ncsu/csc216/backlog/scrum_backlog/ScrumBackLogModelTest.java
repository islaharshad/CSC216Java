package edu.ncsu.csc216.backlog.scrum_backlog;

import static org.junit.Assert.*;



import org.junit.Test;
import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.command.Command.CommandValue;
import edu.ncsu.csc216.backlog.task.TaskItem.Type;


/**
 * Tests all methods in ScrumbBacklogModel
 * @author Isla A., Hiruni N. 
 *
 */
public class ScrumBackLogModelTest {

	/**
	 * Tests get instance method in scrumbacklogmodel
	 */
	@Test
	public void testScrumBacklogModel() {
		assertNotNull(ScrumBacklogModel.getInstance());
	}

	/**
	 * Tests load tasks from file method
	 */
	@Test
	public void testLoadTasksFromFile() {
		ScrumBacklogModel s = ScrumBacklogModel.getInstance();
		assertNotNull(s);
		s.loadTasksFromFile("test-files/tasks_valid.xml"); // this is throwing an IllegalArgumentException when it shouldn't!
		try {
			s.loadTasksFromFile("test-files/kdf.xml"); //invalid file - does not exist!
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
	}
	
	/**
	 * Tests save tasks to file method
	 */
	@Test
	public void testSaveTasksToFile() {
		ScrumBacklogModel s = ScrumBacklogModel.getInstance();
		assertNotNull(s);
		s.createNewTaskItemList();
		s.addTaskItemToList("blabla", Type.BUG, "hiru", "hello");	
		
		s.saveTasksToFile("test_files/new_task.xml"); 
		
	}


	/**
	 * Tests delete task item by id method
	 */
	@Test
	public void testDeleteTaskItemById() {
		ScrumBacklogModel s = ScrumBacklogModel.getInstance();
		assertNotNull(s);
		s.createNewTaskItemList();
		s.addTaskItemToList("blabla", Type.BUG, "hiru", "hello");
		s.addTaskItemToList("ababa", Type.TECHNICAL_WORK, "isla", "bye");		
		s.deleteTaskItemById(2);		
	}
	
	/**
	 * Tests get task item by id method
	 */
	@Test
	public void testGetTaskItemById() {
		ScrumBacklogModel s = ScrumBacklogModel.getInstance();
		assertNotNull(s);
		s.createNewTaskItemList();
		s.addTaskItemToList("blabla", Type.BUG, "hiru", "hello");

		assertEquals("blabla", s.getTaskItemById(1).getTitle());	
	}
	
	/**
	 * Tests get task item list by creator as array method
	 */
	@Test 
	public void testGetTaskItemListByCreatorAsArray() {
		
		ScrumBacklogModel s = ScrumBacklogModel.getInstance();
		assertNotNull(s);
		s.createNewTaskItemList();
		s.addTaskItemToList("blabla", Type.BUG, "hiru", "hello");
		s.addTaskItemToList("test", Type.FEATURE , "isla", "bye");
		assertEquals("blabla", s.getTaskItemListByCreatorAsArray("hiru")[0][2]);
		try {
			assertEquals("blabla", s.getTaskItemListByCreatorAsArray(null)[0][2]);
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
	
	}
	
	/**
	 * Tests get task item list by owner as array method
	 */
	@Test
	public void testGetTaskItemListByOwnerAsArray() {
		ScrumBacklogModel s = ScrumBacklogModel.getInstance();
		assertNotNull(s);
		s.createNewTaskItemList();
		s.addTaskItemToList("blabla", Type.BUG, "hiru", "hello");
		s.addTaskItemToList("test", Type.FEATURE , "isla", "bye");
		Command c = new Command(CommandValue.CLAIM, "bob", "updated");
		s.getTaskItemById(2).update(c);
		assertEquals("test", s.getTaskItemListByOwnerAsArray("bob")[0][2]);
		try {
			assertEquals("blabla", s.getTaskItemListByOwnerAsArray(null)[0][2]);
			fail();
		} catch(IllegalArgumentException e) {
			//skip
		}
	}
	
	/**
	 * Tests get task item list as array method
	 */
	@Test
	public void testGetTaskItemListAsArray() {
		ScrumBacklogModel s = ScrumBacklogModel.getInstance();
		assertNotNull(s);
		s.createNewTaskItemList();
		s.addTaskItemToList("blabla", Type.BUG, "hiru", "hello");
		s.addTaskItemToList("test", Type.FEATURE , "isla", "bye");
		assertEquals("blabla", s.getTaskItemListAsArray()[0][2]);
	}
	
	/**
	 * Tests execute command method
	 */
	@Test
	public void testExecuteCommand() {
		ScrumBacklogModel s = ScrumBacklogModel.getInstance();
		assertNotNull(s);
		s.createNewTaskItemList();
		s.addTaskItemToList("blabla", Type.BUG, "hiru", "hello");
		s.addTaskItemToList("test", Type.FEATURE , "isla", "bye");
		assertEquals("blabla", s.getTaskItemListAsArray()[0][2]);
		Command c = new Command(CommandValue.CLAIM, "bob", "note");
		s.executeCommand(1, c);
	}
		
}
