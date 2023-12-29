package exception;

/**
 * Исключение, выбрасываемое в случае, если не удалось найти соответствующий бин.
 */
public class NoSuchBeanException extends RuntimeException {

    /**
     * Конструктор с одним параметром, принимающий сообщение об ошибке.
     *
     * @param message сообщение об ошибке
     */
    public NoSuchBeanException(String message) {
        super(message);
    }
}
