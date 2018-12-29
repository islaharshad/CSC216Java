package edu.ncsu.csc216.backlog.scrum_backlog;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.task.TaskItem;


import edu.ncsu.csc216.task.xml.TaskIOException;
import edu.ncsu.csc216.task.xml.TaskReader;



import edu.ncsu.csc216.backlog.task.TaskItem.Type;

import edu.ncsu.csc216.task.xml.TaskWriter;

/**
 * This class takes care of the scrumbacklog model
 * functionality that includes the dealing with tasks
 * like deletion, addition etc.
 * @author Islahuddin Arshad, Hiruni N.
 * 
 */
public class ScrumBacklogModel {
 
	/** One TaskItemList */
	private TaskItemList taskItemList;
	
	/** Instance of singleton */
	private static ScrumBacklogModel singleton;
	 
	
	private ScrumBacklogModel() {
		taskItemList = new TaskItemList();
	}
	
	
	/** 
	  * Gets a particular instance of the 
	  * scrumBackLogModel that is called by 
	  * other classes where scrumBackLogModel
	  * is later called
	  * @return scrum backlog model
	  */
	public static ScrumBacklogModel getInstance() {

		if(singleton == null) {
			singleton = new ScrumBacklogModel();
		}	
		return singleton;

	}
 
	 /**
	  * Creates the new task item list in the
	  * scrumBackLog
	  */
	 public void createNewTaskItemList() { //?
		 taskItemList = new TaskItemList();  
	 }
	 
	 /**
	  * Loads the tasks from file where they are 
	  * saved to be loaded
	  * @param fileName the file where the tasks
	  * are stored
	  */
	 public void loadTasksFromFile(String fileName) {
	  
			try {
				TaskReader reader = new TaskReader(fileName);
				taskItemList.addXMLTasks(reader.getTasks());
				
			} catch (TaskIOException e) {
				throw new IllegalArgumentException();
			}

	 } 
	  
	 /**
	  * Saves the tasks to a file in the 
	  * system
	  * @param fileName file where tasks are being saved to
	  */
	 public void saveTasksToFile(String fileName) { 
		 
		 TaskWriter writer = new TaskWriter(fileName);
		 for (int i = 0; i < taskItemList.getTaskItems().size(); i++) {
			 writer.addItem(taskItemList.getTaskItems().get(i).getXMLTask());
			 
		 }
	     try {
			writer.marshal();
		} catch (TaskIOException e) {
			
			e.printStackTrace();
		}
	 }
	 
	 /**
	  * Checks in an ID of a person and then
	  * deletes the task associated with that 
	  * person
	  * @param id of the person the task is deleted off
	  */
	 public void deleteTaskItemById(int id) {
		 taskItemList.deleteTaskItemById(id);
	  
	 }
	 
	 /**
	  * Checks through the ID and retrieves the
	  * tasks by it
	  * @param id of the person from whom the task is retrieved off
	  * @return a task 
	  */
	 public TaskItem getTaskItemById(int id) { //?
		 return taskItemList.getTaskItemById(id);
		 
	 }
	 
	 /**
	  * Returns the tasks as a list of the owner
	  * @param owner the owner whose tasklist is called
	  * @return Object[][] the double-array of task item list
	  */
	 public Object[][] getTaskItemListByOwnerAsArray(String owner) {
		 if (owner == null) { 
			 throw new IllegalArgumentException();
		 }
	     int row = taskItemList.getTaskItemsByOwner(owner).size();
		 Object[][] taskOwner = new Object[row][3];
		 
		 for (int i = 0; i < row; i++) {
			 taskOwner[i][0] = taskItemList.getTaskItemsByOwner(owner).get(i).getTaskItemId();
			 taskOwner[i][1] = taskItemList.getTaskItemsByOwner(owner).get(i).getStateName();
			 taskOwner[i][2] = taskItemList.getTaskItemsByOwner(owner).get(i).getTitle();
		 }
		 return taskOwner;
	 }
	 
	 /**
	  * Returns the tasks as a list of the creator
	  * @param creator the creator whose tasklist is called
	  * @return Object[][] the double-array of task item list
	  */
	 public Object[][] getTaskItemListByCreatorAsArray(String creator) {
		 if (creator == null) {
			 throw new IllegalArgumentException();
		 }
	     int row = taskItemList.getTasksByCreator(creator).size();
		 Object[][] taskCreator = new Object[row][3];
		 
		 for (int i = 0; i < row; i++) { 
			 taskCreator[i][0] = taskItemList.getTasksByCreator(creator).get(i).getTaskItemId();
			 taskCreator[i][1] = taskItemList.getTasksByCreator(creator).get(i).getStateName();
			 taskCreator[i][2] = taskItemList.getTasksByCreator(creator).get(i).getTitle();
		 }
		 return taskCreator;
	 }
	 
	 /**
	  * Returns the taskItems as full array
	  * @return Object[][] the double-array of task item list
	  */
	 public Object[][] getTaskItemListAsArray() {
		 int row = taskItemList.getTaskItems().size();
		 Object[][] taskItem = new Object[row][3];
		 
		 for (int i = 0; i < row; i++) {
			 taskItem[i][0] = taskItemList.getTaskItems().get(i).getTaskItemId();
			 taskItem[i][1] = taskItemList.getTaskItems().get(i).getStateName();
			 taskItem[i][2] = taskItemList.getTaskItems().get(i).getTitle();
		 }
	  return taskItem;
	 }
	 
	 /**
	  * Executes the command based on the taskItemId
	  * @param id the id of the task whose command is executed
	  * @param c the command which is executed based
	  * the taskItemId
	  */
	 public void executeCommand(int id, Command c) { ///confirmed, I think
		 getTaskItemById(id).update(c);	  
	 }
	 
	 /**
	  * Adds and item to list based on certain criterias
	  * including title, type creator, and the note
	  * @param title the title of the task
	  * @param type the type of the task
	  * @param creator the creator of the task
	  * @param note the note associated with the task
	  */
	 public void addTaskItemToList(String title, Type type, String creator,
	   String note) { 
		 taskItemList.addTaskItem(title, type, creator, note);
		 
		}
	 
	}