package task_3.exception;

public class NoUniqueImplementationException extends RuntimeException {
    public NoUniqueImplementationException(Class<?> type) {
        super("В контексте приложения указано несколько компонентов для реализации интерфейса " + type.getSimpleName());
    }
}
