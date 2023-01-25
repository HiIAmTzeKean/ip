package parser;

public class EmptyCommandException extends Exception {
    /**
     * Custom exception for empty message or message with only blank spaces
     * being passed into console.
     * @param message Error message to raise
     */
    public EmptyCommandException(String message) {
        super(message);
    }
}
