package test;

import util.CustomCollection.CustomArrayList;


public class Assertions_YaroslavRulev {

    /**
     *  Метод сравнивает две коллекци на размер и соответсвие элементов.
     *  Если результат отрицательный, возникает исключение {@link AssertionError}
     *
     * @param expected ожидаемая коллекция.
     * @param actual фактическая коллекция.
     */
    public static void assertionsEquals(CustomArrayList<?> expected, CustomArrayList<?> actual) {
        if (expected.size() != actual.size()) {
            throw new AssertionError("Expected size " + expected.size() + " not equal actual size " + actual.size());
        }

        for (int i = 0; i < expected.size(); i++) {
            Object expectedElement = expected.get(i);
            Object actualElement = actual.get(i);

            if (!expectedElement.equals(actualElement)) {
                throw new AssertionError(i + " index elements are not equal");
            }
        }
        System.out.println("Assertion passed: Lists are equal.");
    }


}
