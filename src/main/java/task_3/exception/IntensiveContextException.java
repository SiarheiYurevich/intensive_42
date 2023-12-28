package task_3.exception;

/**
 * Абстрактный класс для реализации исключений приложения.
 */
public abstract class IntensiveContextException extends RuntimeException{

    /**
     * Конструктор класса исключения с сообщением ошибке и данными о выброшенном исключении.
     * @param message сообщение об ошибке
     * @param exception исключение, выброшенное методами других классов
     */
    public IntensiveContextException(String message , Exception exception) {
        super(message, exception);
    }

    /**
     * Конструктор класса исключения с сообщением об ошибке
     * @param message сообщение об ошибке
     */
    public IntensiveContextException(String message) {
        super(message);
    }
}
