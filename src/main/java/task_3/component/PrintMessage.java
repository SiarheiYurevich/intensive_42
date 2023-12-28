package task_3.component;

import task_3.context.IntensiveComponent_SlavaSles;

/**
 * Тестовый класс для проверки работы приложения
 */
@IntensiveComponent_SlavaSles
public class PrintMessage {
    String message;

    public PrintMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void printMessage() {
        System.out.println(message);
    }
}
