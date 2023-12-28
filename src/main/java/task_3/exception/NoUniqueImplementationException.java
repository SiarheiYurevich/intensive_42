package task_3.exception;

/**
 * Класс исключения, выбрасываемого при нахождении более одной имплементации для искомого интерфейса.
 */
public class NoUniqueImplementationException extends IntensiveContextException {
    /**
     * Конструктор класса.
     * @param type тип интерфейса, для которого найдено несколько имплементаций
     */
    public NoUniqueImplementationException(Class<?> type) {
        super("В контексте приложения указано несколько компонентов для реализации интерфейса " + type.getSimpleName());
    }
}
