package task;

public class Task{
    private String description;
    private boolean mark;
    public Task(String description) {
        this.description = description;
        mark = false;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String newDescription) {
        description = newDescription;
    }
    public String toString() {
        return String.format("[%c] %s", getStatusIcon(), description);
    }
    public char getStatusIcon() {
        // mark done task with X
        return (mark ? 'X' : ' ');
    }
    public boolean isMark() {
        return mark;
    }
    public void setMark(boolean newMark) {
        this.mark = newMark;
    }
}
