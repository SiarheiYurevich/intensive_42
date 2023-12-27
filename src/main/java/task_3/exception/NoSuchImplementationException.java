package task_3.exception;

public class NoSuchImplementationException extends RuntimeException {
    public NoSuchImplementationException(Class<?> type) {
        super("В контексте приложения отсутствует компонент для реализации интерфейса " + type.getSimpleName());
    }
}
