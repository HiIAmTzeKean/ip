package task;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import Parser.Argument;
import Parser.Command;


public class TaskController implements ITaskController{
    ArrayList<Task> list = new ArrayList<Task>();
    public void addTask(String description) {
        list.add(new Task(description));
    }
    public ArrayList<Task> getTask() throws EmptyTaskList {
        if (list.isEmpty()) {
            throw new EmptyTaskList("*** Empty List");
        }
        return list;
    }
    public String toString() {
        String Text = IntStream.range(0, list.size())
                                .mapToObj(index -> String.format("%d. %s\n", index+1, list.get(index)))
                                .collect(Collectors.joining("\n"));
        return Text;
    }
    @Override
    public String markTask(Argument arg) throws IllegalArgumentException {
        if (arg.getIndex() < 0 || arg.getIndex()>list.size()) {
            // Bad index input by user
            throw new IllegalArgumentException("Invalid index given to mark!");
        }
        String dukeMessage;
        if (arg.getCommand().equals(Command.MARK)) {
            list.get(arg.getIndex()-1).setMark(true);
            dukeMessage = "Nice! I've marked this task as done:";
        }
        else {
            list.get(arg.getIndex()-1).setMark(false);
            dukeMessage = "OK, I've marked this task as not done yet:";
        }
        return String.format("%s\n [%c] %s",
                        dukeMessage,
                        list.get(arg.getIndex()-1).getStatusIcon(),
                        list.get(arg.getIndex()-1).getDescription());
    }
}
