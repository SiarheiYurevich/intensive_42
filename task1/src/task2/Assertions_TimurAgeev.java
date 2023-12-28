package task2;

import task1.IntensiveList;

import java.util.Comparator;

public class Assertions_TimurAgeev<E> {
    /**
     * Выполняет проверку эквивалентности двух списков
     *
     * @param list1 первый список для сравнения
     * @param list2 второй список для сравнения
     * @return true, если списки эквивалентны, иначе - false
     */
    public static <E> boolean assertIntensiveListEquals(IntensiveList<E> list1, IntensiveList<E> list2) {
        if (list1 == null || list2 == null || list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != null || list2.get(i) != null || !list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Выполняет проверку сортировки списка используя компаратор
     *
     * @param list       список для проверки на отсортированность
     * @param comparator компаратор, по которому происходит сравнение элементов списка
     * @return true, если список отсортрован, иначе - false
     */
    public static <E> boolean assertIntensiveListIsSorted(IntensiveList<E> list, Comparator<E> comparator) {
        return list.isSorted(comparator);
    }
}
