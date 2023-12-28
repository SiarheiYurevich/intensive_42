package task_3.exception;

/**
 * Класс исключения, выбрасываемого при отсутствии имплементаций для искомого интерфейса.
 */
public class NoSuchImplementationException extends IntensiveContextException {
    /**
     * Конструктор класса.
     * @param type тип интерфейса, для которого не найдено имплементаций
     */
    public NoSuchImplementationException(Class<?> type) {
        super("В контексте приложения отсутствует компонент для реализации интерфейса " + type.getSimpleName());
    }
}
