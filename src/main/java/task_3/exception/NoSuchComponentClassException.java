package task_3.exception;

public class NoSuchComponentClassException extends RuntimeException {
    public NoSuchComponentClassException(Class<?> type) {
        super("Класс " + type.getSimpleName() + " не определен в качестве компонента");
    }
}
