package task_3.component;

import task_3.IntensiveComponent_SlavaSles;

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
