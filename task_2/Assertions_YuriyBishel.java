package com.yuriybishel.task_2;

import com.yuriybishel.task_1.IntensiveList;

import java.util.Comparator;


public class Assertions_YuriyBishel {
    /**
     * Выполняет проверку эквивалентности двух списков путем проверки размера списков,
     * если размеры списков равны, то поэлементно сравнивает их.
     * @param list1 первый список
     * @param list2 второй список
     * @return результат сравнения, {@code true} при равенстве, {@code false} при неравенстве
     * @param <E>
     */

    public static <E> boolean assertEquals(IntensiveList<E> list1, IntensiveList<E> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Проверяет отсортирован ли список
     * @param list данный нам список
     * @param comparator компаратор для сравнения
     * @return
     * @param <E>
     */
    public static <E> boolean assertIsSorted(IntensiveList<E> list, Comparator<E> comparator) {
        return list.isSorted(comparator);
    }
}