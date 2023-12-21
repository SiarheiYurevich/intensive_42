package task_2.assertions;

import java.util.Objects;

/**
 * {@code Assertions_AleksandrZmeyev} является набором статических методов,
 * служащих для поддержки утверждений в тестах. В случае, если утверждение ложно,
 * методы данного класса генерируют ошибку {@link AssertionError}.
 *
 * @author Александр Змеев
 */
public class Assertions_AleksandrZmeyev {

    /**
     * Конструктор {@code Assertions_AleksandrZmeyev}.
     * Имеет модификатор доступа {@code private},
     * чтобы было нельзя создать экземпляр класса.
     */
    private Assertions_AleksandrZmeyev() {
    }

    /**
     * Проверяет утверждение, что переданные объекты эквивалентны друг другу.
     */
    public static void assertEquals(Object expected, Object actual) {
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError("Assertion failed: " +
                    "Expected " + expected + " not equals to actual " + actual);
        }
    }

    /**
     * Проверяет утверждение, что переданные объекты не эквивалентны друг другу.
     */
    public static void assertNotEquals(Object expected, Object actual) {
        if (Objects.equals(expected, actual)) {
            throw new AssertionError("Assertion failed: " +
                    "Expected " + expected + " equals to actual " + actual);
        }
    }

    /**
     * Проверяет утверждение, что переданное состояние является {@code true}.
     */
    public static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Assertion failed: Expected true");
        }
    }

    /**
     * Проверяет утверждение, что переданное состояние является {@code false}.
     */
    public static void assertFalse(boolean condition) {
        if (condition) {
            throw new AssertionError("Assertion failed: Expected false");
        }
    }

    /**
     * Проверяет утверждение, что переданная ссылка является {@code null}.
     */
    public static void assertNull(Object actual) {
        if (actual != null) {
            throw new AssertionError("Assertion failed: Expected null, actual " + actual);
        }
    }

    /**
     * Проверяет утверждение, что переданная ссылка не является {@code null}.
     */
    public static void assertNotNull(Object actual) {
        if (actual == null) {
            throw new AssertionError("Assertion failed: Expected not null");
        }
    }
}
