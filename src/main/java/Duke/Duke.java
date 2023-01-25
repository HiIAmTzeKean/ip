package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

import Parser.Command;
import Parser.EmptyCommandException;
import Parser.IParser;
import Parser.Parser;
import Task.EmptyTaskList;
import Task.ITaskController;
import Task.TaskController;

public class Duke {
    static String LINEBREAK = "________________________________________\n";
    static String LINE_TAB_STRING = "    ";
    static String COMMAND_TAB_STRING = "     ";
    private static Scanner sc = new Scanner(System.in);
    private static IParser parser = new Parser(sc);
    private static ITaskController taskController = new TaskController();
    /**
     * Main program that runs the Duke program.
     * Greets users and exits.
     * @param args
     */
    public static void main(String[] args) {
        greet();
        ArrayList<?> list;
        boolean isExit = false;
        do {
            try {
                switch (parser.getCommand()) {
                case EXIT:
                    isExit = true;
                    break;
                case LIST:
                    list = taskController.getTask();
                    printSystemMessage(list);
                    break;
                case TASK:
                    taskController.addTask(parser.getMessage());
                    printSystemMessage("added: " + parser.getMessage());
                    break;
                case UNMARK:
                    printSystemMessage(taskController.markTask(parser.getCommandArguments(Command.UNMARK)));
                    break;
                case MARK:
                    printSystemMessage(taskController.markTask(parser.getCommandArguments(Command.MARK)));
                    break;
                default:
                    break;
                }
            } catch (EmptyCommandException e) {
                printSystemMessage("You passed an illegal command!\n\tI will stop here because I am angry Duke");
                break;
            } catch (EmptyTaskList e) {
                printSystemMessage(e.getMessage());
            }
            catch (IllegalArgumentException e) {
                printSystemMessage(e.getMessage());
            }
        } while(!isExit);
        bye();
    }
    /**
     * Streams message and prints each line of message based
     * on new line feed.
     * @param message Output message to print
     */
    private static void printSystemMessage(String message) {
        System.out.println(LINE_TAB_STRING + LINEBREAK.substring(0, LINEBREAK.length()-1));
        Arrays.stream(message.split("\n"))
                .map(item -> COMMAND_TAB_STRING + item + '\n')
                .forEach(System.out::print);
        System.out.println(LINE_TAB_STRING + LINEBREAK);
    }
    private static <T> void printSystemMessage(ArrayList<T> list) {
        System.out.println(LINE_TAB_STRING + LINEBREAK.substring(0, LINEBREAK.length()-1));
        IntStream.range(0, list.size())
            .mapToObj(index -> String.format("%s%d. %s", COMMAND_TAB_STRING, index+1, list.get(index)))
            .forEach(System.out::println);
        System.out.println(LINE_TAB_STRING + LINEBREAK);
    }
    /**
     * Prints greet message to user
     */
    private static void greet() {
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        printSystemMessage("Hello! I'm Duke\nWhat can I do for you?");
    }
    /**
     * Prints bye messgae to user
     */
    private static void bye() {
        printSystemMessage("Bye. Hope to see you again soon!");
    }
}