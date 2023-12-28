package task_3.component;

import task_3.context.IntensiveComponent_SlavaSles;

import java.util.Scanner;

/**
 * Тестовый класс для проверки работы приложения
 */
@IntensiveComponent_SlavaSles
public class InputMessage {
    String message = "";
    PrintMessage printMessage;

    public InputMessage() {
    }

    public void inputMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите сообщение для печати: ");
        message = scanner.nextLine();
    }

    public String getMessage() {
        return message;
    }

    public PrintMessage getPrintMessage() {
        return printMessage;
    }

    public void setPrintMessage(PrintMessage printMessage) {
        this.printMessage = printMessage;
    }
}
