package test_framework;

/**
 * Класс содержит основной метод для запуска тестов
 * с помощью TestRunner_GalinaSamokhina.
 */
public class Main {

    /**
     * Метод main для запуска тестов.
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        TestRunner_GalinaSamokhina testRunner = new TestRunner_GalinaSamokhina();
        testRunner.run("test");
    }
}