package task;

import java.util.ArrayList;

import Parser.Argument;

public interface ITaskController {
	public ArrayList<Task> getTask() throws EmptyTaskList;
	public void addTask(String description);
	public String markTask(Argument arg) throws IllegalArgumentException;
}
