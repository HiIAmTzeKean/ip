package parser;

public class Argument {
    private Command command;
    private int index;
    public Argument(Command command, int index) {
        this.command = command;
        this.index = index;
    }
    public Argument() {
        this(null,-1);
    }
    public Command getCommand() {
        return command;
    }
    public void setCommand(Command command) {
        this.command = command;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
