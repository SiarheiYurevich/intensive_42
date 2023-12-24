package exception;

/**
 * It is recommended to throw this exception when there are several implementations of the bean we need
 */
public class MultipleImplementationsException extends Exception{
    public MultipleImplementationsException() {
    }

    public MultipleImplementationsException(String message) {
        super(message);
    }

    public MultipleImplementationsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultipleImplementationsException(Throwable cause) {
        super(cause);
    }

    public MultipleImplementationsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
