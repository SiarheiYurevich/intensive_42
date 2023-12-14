package task2;

import task1_2.IntensiveList;

import java.util.List;

/**
 * Класс с кастомизированными утверждениями, расчитанными на работу с классами, имплементирующими IntensiveList.
 */
public class Assertions_LevDurov {

    /**
     * Утверждает, что два списка эквивалентны.
     * @param list1 Первый список.
     * @param list2 Второй список.
     * @param <E> Тип объектов, хранящихся в списке.
     */
    public static <E> void assertEquals(IntensiveList<E> list1, IntensiveList<E> list2) {
        if(!list1.equals(list2))
            throw new AssertionError("assertEquals id failed");
    }

    /**
     * Утверждает,что два списка не эквивалентны.
     * @param list1 Первый список.
     * @param list2 Второй список.
     * @param <E> Тип объектов, хранящихся в списке.
     */
    public static <E> void assertNotEquals(List<E> list1, List<E> list2) {
        if(list1.equals(list2))
            throw new AssertionError("assertNotEquals id failed");
    }

    /**
     * Утверждает, что условие истинно.
     * @param b Условие.
     */
    public static void assertTrue(boolean b) {
        if(!b)
            throw new AssertionError("assertTrue id failed");
    }

    /**
     * Утверждает, что условие ложно.
     * @param b Условие.
     */
    public static void assertFalse(boolean b) {
        if(b)
            throw new AssertionError("assertFalse id failed");
    }
}

