package edu.ncsu.csc216.backlog.scrum_backlog;
import java.util.ArrayList;
import java.util.List;
import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.task.TaskItem;
import edu.ncsu.csc216.backlog.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.Task;
/**
 * This class handles the list of the�
 * tasks in the scrum backlog
 * @author Islahuddin Arshad, Hiru
 *
 */
public class TaskItemList {
	/**
	 * The initial counter value of the task in process
	 */
	private static final int INITIAL_COUNTER_VALUE = 1;

	/** List of tasks */
	private List<TaskItem> tasks;
	
	
	/**
	 * TaskItemList constructor
	 */
	public TaskItemList() {
		emptyList();
	}
	/**
	 * Signifies the empty list
	 */
	private void emptyList() {
		tasks = new ArrayList<TaskItem>();
		TaskItem.setCounter(INITIAL_COUNTER_VALUE);
	}

	/**
	 * Adds and Item to list based on certain criteria including title, type
	 * creator, and the note
	 *�
	 * @param title the title of the task
	 * @param type the type of the task
	 * @param creator the creator of the task
	 * @param note the note associated with the task
	 * @return int the added task item
	 */
	public int addTaskItem(String title, Type type, String creator, String note) {
		
		TaskItem item = new TaskItem(title, type, creator, note);
		tasks.add(item);
		return item.getTaskItemId();
	}
		
	

	/**
	 * Adds the XML files in the task list
	 * @param taskList the list where the XML files are added
	 */
	public void addXMLTasks(List<Task> taskList) {
		int max = 0;
		for (int i = 0; i < taskList.size(); i++) {
			TaskItem item = new TaskItem(taskList.get(i));
			if (item.getTaskItemId() > max) {
				max = item.getTaskItemId();
			}
			tasks.add(item);
			
		}
		max++;
	}

	/**
	 * Gets the task from list of task items
	 *�
	 * @return List<TaskItem> the list of task item where the task is located
	 */
	public List<TaskItem> getTaskItems() {
		return tasks;
	}

	/**
	 * gets the tasks by the owner's name and puts it into the list containing
	 * the task items
	 *�
	 * @param owner the owner of the task item
	 * @return List<TaskItem> the list of task items of the owner
	 */
	public List<TaskItem> getTaskItemsByOwner(String owner) {
		if (owner == null) {
			throw new IllegalArgumentException();
		}
		List<TaskItem> tasksOne = new ArrayList<TaskItem>();
		for(int i = 0; i < tasks.size(); i++) {
			
				if(owner.equals(tasks.get(i).getOwner())) {
					tasksOne.add(tasks.get(i));	
				}
			
		}
		return tasksOne;
	
	}

	/**
	 * gets the tasks by the creator's name and puts it into the list containing
	 * the task items
	 *�
	 * @param creator the creator of the task item
	 * @return List<TaskItem> the list of task items of the creator
	 */
	public List<TaskItem> getTasksByCreator(String creator) { //not sure
		if (creator == null) {
			throw new IllegalArgumentException();
		}
		List<TaskItem> tasksOne = new ArrayList<TaskItem>();
		for(int i = 0; i < tasks.size(); i++) {
			if(creator.equals(tasks.get(i).getCreator())) {
				tasksOne.add(tasks.get(i));
				
			}
		}
		return tasksOne;
	}

	/**
	 * gets the tasks by the ID and puts it into the list containing the task
	 * items
	 *�
	 * @param id the id of the task item
	 * @return TaskItem the task gained from the id
	 */
	public TaskItem getTaskItemById(int id) {
		
		for(int i = 0; i < tasks.size(); i++) {
			if(tasks.get(i).getTaskItemId() == id) {
				return tasks.get(i);
			}
		}
		return null;
	}

	/**
	 * Executes the command based on the id of the task
	 * @param id the id of the task of which the command is executed
	 * @param command the command that is executed
	 */
	public void executeCommand(int id, Command command) {		
		if(getTaskItemById(id) != null) {
			getTaskItemById(id).update(command);
		}	
	}

	/**
	 * Searches through the ID and deletes the task item based on that
	 * @param id the id that is searched through of which the task is deleted off
	 */
	public void deleteTaskItemById(int id) {

		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getTaskItemId() == id) {
				tasks.remove(i);
			}
		}
	}
}