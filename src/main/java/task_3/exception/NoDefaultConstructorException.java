package task_3.exception;

/**
 * Класс исключения, выбрасываемого при отсутствии пустого конструктора у класса, экземпляр которого необходимо создать.
 */
public class NoDefaultConstructorException extends IntensiveContextException {

    /**
     * Конструктор класса
     * @param exception исключение, выброшенное методами других классов
     */
    public NoDefaultConstructorException(Exception exception) {
        super("Конструктор без параметров не найден", exception);
    }
}