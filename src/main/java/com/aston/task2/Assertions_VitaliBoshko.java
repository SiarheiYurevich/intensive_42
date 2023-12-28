package com.aston.task2;

import com.aston.task1.IntensiveList;
import com.aston.task1.ArrayList_VitaliBoshko;

import java.util.Comparator;

/**
 * The class with static methods for check specified lists
 *
 * @author Vitali Boshko
 * @see ArrayList_VitaliBoshko
 */
public class Assertions_VitaliBoshko {

    private Assertions_VitaliBoshko() {
    }

    /**
     * The method checks assertion that two lists is equal
     *
     * @param list1 first compared list
     * @param list2 second compared list
     * @param <E>   the type of elements in the list
     * @return true if the specified lists is equal
     */
    public static <E> boolean assertEquals(IntensiveList<E> list1, IntensiveList<E> list2) {
        if (list1 == list2) {
            return true;
        }

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
     * The method checks assertion tha specified list is sorted
     *
     * @param list       specified for checking
     * @param comparator object used for compare of the list data
     * @param <E>        the type of elements in the list
     * @return true if the specified list is sorted
     */
    public static <E> boolean assertIsSorted(IntensiveList<E> list, Comparator<E> comparator) {
        return list.isSorted(comparator);
    }

    /**
     * The method checks assertion tha specified list is empty
     *
     * @param list specified for checking
     * @param <E>  the type of elements in the list
     * @return true if the specified list is empty
     */
    public static <E> boolean assertIsEmpty(IntensiveList<E> list) {
        return list.size() == 0;
    }
}
