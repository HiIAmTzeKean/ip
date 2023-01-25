package task;

public class EmptyTaskList extends Exception {
    /**
     * Custom exception for empty message or message with only blank spaces
     * being passed into console.
     * @param message Error message to raise
     */
    public EmptyTaskList(String message) {
        super(message);
    }
}