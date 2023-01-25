package parser;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Parser implements IParser {
    private Scanner sc;
    private String message;
    private Command currentCommand;
    public Parser(Scanner sc) {
        this.sc = sc;
    }
    @Override
    public String getMessage() {
        return message;
    }
    private boolean isList() {
        return (message.split(" ")[0].toLowerCase().equals("list"));
    }
    private boolean isExit() {
        return (message.split(" ")[0].toLowerCase().equals("bye"));
    }
    private boolean isMark(){
        return (message.split(" ")[0].toLowerCase().equals("mark"));
    }
    private boolean isUnmark(){
        return (message.split(" ")[0].toLowerCase().equals("unmark"));
    }
    /**
     * Gets the a message from console that user inputs.
     * Checks if the input message is empty and raises Exception if empty.
     * @exception EmptyCommandException
     */
    private void getNextMessage() throws EmptyCommandException {
        try {
            message = sc.nextLine();
        } catch (NoSuchElementException e) {
            throw new EmptyCommandException("Empty command");
        }
        if (message.isBlank()){
            throw new EmptyCommandException("Empty command");
        }
    }
    @Override
    public Command getCommand() throws EmptyCommandException {
        getNextMessage();
        if (message == null) {
            throw new EmptyCommandException("Empty command");
        }
        if (isExit()) {
            currentCommand = Command.EXIT;
        }
        else if (isList()) {
            currentCommand = Command.LIST;
        }
        else if (isUnmark()) {
            currentCommand = Command.UNMARK;
        }
        else if (isMark()) {
            currentCommand =  Command.MARK;
        }
        else {
            currentCommand =  Command.TASK;
        }
        return currentCommand;
    }
    @Override
    public Argument getCommandArguments(Command command) {
        if (currentCommand.compareTo(command) != 0){
            // Mismatch in request
            // TODO Throw error
        }

        Argument arg = new Argument();
        if (command.equals(Command.MARK) || command.equals(Command.UNMARK)) {
            arg.setCommand(command);
            arg.setIndex(Integer.parseInt(message.split(" ")[1]));
        }
        else {
            // TODO Handle error
        }
        return arg;
    }
}