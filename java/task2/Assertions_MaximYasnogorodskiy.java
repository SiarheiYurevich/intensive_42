package task2;

import task1.IntensiveList;

import java.util.Comparator;

public class Assertions_MaximYasnogorodskiy {


    public static <E> boolean assertEquals(IntensiveList<E> list1, IntensiveList<E> list2) {
        return list1.equals(list2);
    }

    public static <E> boolean assertSorted(Comparator<E> comparator, IntensiveList<E> list) {
        return list.isSorted(comparator);
    }

}
