package task_3.exception;

/**
 * Класс исключения, выбрасываемого при нахождении более одной имплементации для искомого интерфейса.
 */
public class NoSuchComponentClassException extends IntensiveContextException {

    /**
     * Конструктор класса.
     * @param type тип класса, для которого отсутствует компонент
     */
    public NoSuchComponentClassException(Class<?> type) {
        super("Класс " + type.getSimpleName() + " не определен в качестве компонента");
    }
}
