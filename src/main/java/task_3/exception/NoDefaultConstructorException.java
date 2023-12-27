package task_3.exception;

public class NoDefaultConstructorException extends RuntimeException {
    public NoDefaultConstructorException(Exception exception) {
        super("Конструктор без параметров не найден", exception);
    }
}