package task_3.exception;

public class NoSuchComponentClassException extends RuntimeException {
    public NoSuchComponentClassException() {
        super("Данный класс не определен в качестве компонента");
    }
}
