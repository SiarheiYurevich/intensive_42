package task2;

import task1.IntensiveList;

import java.util.Comparator;

public class Assertions_TimurAgeev<E> {
    /**
     * Выполняет проверку эквивалентности двух списков путем проверки размера списков,
     * если размеры списков равны, то поэлементно сравнивает их.
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
     * Выполняет проверку сортировки списка используя компаратор.
     */
    public static <E> boolean assertIsSorted(IntensiveList<E> list, Comparator<E> comparator) {
        return list.isSorted(comparator);
    }
}
