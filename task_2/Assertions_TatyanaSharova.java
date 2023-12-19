package task_2;


import task_1.IntensiveList;


/**
 * {@code Assertions_TatyanaSharova} является набором статических методов. Assertions
 * (утверждения) позволяют сравнить ожидаемый результат с фактическим результатом теста
 *
 * @Author Tatyana Sharova
 */
public class Assertions_TatyanaSharova {
    private Assertions_TatyanaSharova() {
    }

    /**
     * Метод  проверят условие. Если условие ложно, выбрасывается {@link AssertionError}
     * @param condition - проверяемое условие
     * @throws AssertionError
     */
    public static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Test was failed. Expected: <true> but was: <false>");
        }
    }

    /**
     * Метод проверяет на равенство два списка. Сначала сравниваются размеры списков,
     * если размеры не совпадают, выбрасывается {@link AssertionError}.
     * Далее проверяются объекты в списках, если было найдено несовпадение,  выбрасывается {@link AssertionError}
     * @param expected - первый список
     * @param actual   - второй список
     * @throws AssertionError
     */
    public static <E> void assertEquals(IntensiveList<E> expected, IntensiveList<E> actual) {
        if (expected.size() != actual.size()) {

            throw new AssertionError("Assertions_TatyanaSharovaFailedError: expected.size: " + expected.size() + " but was: " + actual.size());
        }
        for (int i = 0; i < actual.size(); i++) {
            if (!expected.get(i).equals(actual.get(i))) {

                throw new AssertionError("Assertions_TatyanaSharovaFailedError: expected: " + expected + " but was: " + actual);
            }
        }

    }
}



