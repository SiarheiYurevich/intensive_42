package test_framework;

import arrayList.ArrayList_GalinaSamokhina;
import java.util.Objects;

/**
 * Этот класс предоставляет методы утверждения для сравнения
 * и проверки ожидаемых и фактических значений во время тестирования.
 */
public class Assertions_GalinaSamokhina {
    /**
     * Утверждает, что заданное условие true.
     * @param condition условие для проверки
     * @throws Exception если условие false
     */
    public static void assertTrue(boolean condition) throws Exception {
        if (!condition) {
            throw new Exception("'assertTrue' Conditional: " + false);
        }
    }

    /**
     * Утверждает, что заданное условие false.
     * @param condition условие для проверки
     * @throws Exception если условие true
     */
    public static void assertFalse(boolean condition) throws Exception {
        if (condition) {
            throw new Exception("'assertFalse' Conditional: " + true);
        }
    }

    /**
     * Утверждает, что expected и actual объекты равны/одинаковы
     * @param expected ожидаемый объект
     * @param actual фактический объект
     * @throws Exception если объекты не равны
     */
    public static void assertEquals(Object expected, Object actual) throws Exception {
        if (!Objects.equals(expected, actual)) {
            throw new Exception("Expected and Actual Objects are not equal");
        }
    }

    /**
     * Утверждает, что expected и actual объекты НЕ равны/одинаковы
     * @param expected ожидаемый объект
     * @param actual фактический объект
     * @throws Exception если объекты равны
     */
    public static void assertNotEquals(Object expected, Object actual) throws Exception {
        if (Objects.equals(expected, actual)) {
            throw new Exception("Expected and Actual Objects are equal");
        }
    }

    /**
     * Утверждает, что expected и actual
     * ArrayList_GalinaSamokhina равны/одинаковы
     * @param expected ожидаемый ArrayList_GalinaSamokhina
     * @param actual фактический ArrayList_GalinaSamokhina
     * @throws Exception если ArrayList_GalinaSamokhina'ы НЕ равны
     */
    public static void assertArrayEquals(ArrayList_GalinaSamokhina expected, ArrayList_GalinaSamokhina actual) throws Exception {
        if (expected.equals(actual)) {
            return;
        }

        if (Objects.isNull(expected) || Objects.isNull(actual)) {
            throw new Exception("One of arrays is null");
        }
        if (expected.size() != actual.size()) {
            throw new Exception("Length of expected and actual arrays is not the same");
        }

        for (int i = 0; i < expected.size(); i++) {
            if (!Objects.equals(expected.get(i), actual.get(i))) {
                throw new Exception("Elements with index " + i + " in arrays expected and actual are not equals");
            }
        }
    }
}