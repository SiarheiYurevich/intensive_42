package task_2;


import java.util.Objects;

/**
 * {@code Assertions_MaximBulychev} является набором служебных методов, которые поддерживают
 * устверждения в тестах. Все методы данного класса генерируют ошибку {@link AssertionError}
 * в случае, если утверждение ложно.
 *
 * @author Максим Булычев
 */
public class Assertions_MaximBulychev {

    /**
     * Конструктор класса объявлен приватным, чтобы предотвратить возможность создания
     * объектов данного класса.
     */
    private Assertions_MaximBulychev() {
    }

    /**
     * Проверяет утверждение, что переданное состояние является истиной.
     */
    public static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("assertTrue failed");
        }
    }

    /**
     * Проверяет утверждение, что переданное состояние является ложью.
     */
    public static void assertFalse(boolean condition) {
        if (condition) {
            throw new AssertionError("assertFalse failed");
        }
    }

    /**
     * Проверяет утверждение, что переданные объекты эквивалентны друг другу.
     */
    public static void assertEquals(Object expected, Object actual) {
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError("assertEquals failed: expected: " +
                    expected.toString() + ", got: " + actual.toString());
        }
    }

    /**
     * Проверяет утверждение, что переданные объекты не эквивалентны друг другу.
     */
    public static void assertNotEquals(Object unexpected, Object actual) {
        if (Objects.equals(unexpected, actual)) {
            throw new AssertionError("assertNotEquals failed: " +
                    unexpected.toString() + " and " + actual.toString() + "are equal");
        }
    }

    /**
     * Проверяет утверждение, что переданная ссылка является {@code null}.
     */
    public static void assertNull(Object actual) {
        if (actual != null) {
            throw new AssertionError("assertNull failed: actual: " + actual);
        }
    }

    /**
     * Проверяет утверждение, что переданная ссылка не является {@code null}.
     */
    public static void assertNotNull(Object actual) {
        if (actual == null) {
            throw new AssertionError("assertNotNull failed: actual: null");
        }
    }
}
