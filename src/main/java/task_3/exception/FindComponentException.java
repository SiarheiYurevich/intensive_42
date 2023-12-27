package task_3.exception;

public class FindComponentException extends RuntimeException {
    public FindComponentException(Exception exception) {
        super("Ошибка поиска компонентов контекста: не найден файл или данные о классе", exception);
    }
}
