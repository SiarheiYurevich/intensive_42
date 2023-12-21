package task_2;

import task_2.test_runner.TestRunner_AleksandrZmeyev;

public class Main {

    /**
     * Создаёт экземпляр класса {@link TestRunner_AleksandrZmeyev}
     * и передаёт ему имена пакетов для сканирования на наличие тестов.
     * Производит запуск тестов.
     */
    public static void main(String[] args) {
        TestRunner_AleksandrZmeyev runner = new TestRunner_AleksandrZmeyev("task_2");
        runner.run();
    }
}
