package task_2.test_classes;

import task_1.intensive_list_implementations.ArrayList_AleksandrZmeyev;
import task_1.intensive_list_interface.IntensiveList;
import task_2.annotations.IntensiveTest_AleksandrZmeyev;
import task_2.assertions.Assertions_AleksandrZmeyev;

import java.util.Comparator;

/**
 * {@code IntensiveArrayListTest_AleksandrZmeyev} является тестовым
 * классом и содержит методы, выполняющие проверку, является ли
 * список {@code ArrayList_AleksandrZmeyev} отсортированным.
 * Тестовые методы должны иметь аннотацию {@code IntensiveTest_AleksandrZmeyev}.
 *
 * @author Александр Змеев
 */
public class IntensiveArrayListTest_AleksandrZmeyev {

    /**
     * Конструктор {@code IntensiveArrayListTest_AleksandrZmeyev} по умолчанию.
     */
    public IntensiveArrayListTest_AleksandrZmeyev() {
    }

    /**
     * Проверяется, что метод {@code isSorted()}, вызванный у отсортированного
     * списка {@code ArrayList_AleksandrZmeyev}, вернёт {@code true}.
     */
    @IntensiveTest_AleksandrZmeyev
    public void testSorted() {
        IntensiveList<Integer> sortedList = new ArrayList_AleksandrZmeyev<>();
        sortedList.add(1);
        sortedList.add(2);
        sortedList.add(4);
        sortedList.add(5);
        sortedList.add(6);
        sortedList.add(8);

        Assertions_AleksandrZmeyev.assertTrue(sortedList.isSorted());
    }

    /**
     * Проверяется, что метод {@code isSorted()}, вызванный у не отсортированного
     * списка {@code ArrayList_AleksandrZmeyev}, вернёт {@code false}.
     */
    @IntensiveTest_AleksandrZmeyev
    public void testNotSorted() {
        IntensiveList<Integer> notSortedList = new ArrayList_AleksandrZmeyev<>();
        notSortedList.add(1);
        notSortedList.add(4);
        notSortedList.add(2);
        notSortedList.add(7);
        notSortedList.add(8);
        notSortedList.add(5);

        Assertions_AleksandrZmeyev.assertFalse(notSortedList.isSorted());
    }

    /**
     * Проверяется, что метод {@code isSorted()}, вызванный у пустого
     * списка {@code ArrayList_AleksandrZmeyev}, вернёт {@code true}.
     */
    @IntensiveTest_AleksandrZmeyev
    public void testEmptyListSorted() {
        IntensiveList<Integer> sortedList = new ArrayList_AleksandrZmeyev<>();

        Assertions_AleksandrZmeyev.assertTrue(sortedList.isSorted());
    }

    /**
     * Проверяется, что метод {@code isSorted()}, вызванный у списка
     * {@code ArrayList_AleksandrZmeyev} с одним элементом,
     * вернёт {@code true}.
     */
    @IntensiveTest_AleksandrZmeyev
    public void testOneElementSorted() {
        IntensiveList<Integer> sortedList = new ArrayList_AleksandrZmeyev<>();
        sortedList.add(5);

        Assertions_AleksandrZmeyev.assertTrue(sortedList.isSorted());
    }

    /**
     * Проверяется, что метод {@code isSorted()}, вызванный у отсортированного
     * списка {@code ArrayList_AleksandrZmeyev} с повторяющимися элементами,
     * вернёт {@code true}.
     */
    @IntensiveTest_AleksandrZmeyev
    public void testRepeatElementSorted() {
        IntensiveList<Integer> sortedList = new ArrayList_AleksandrZmeyev<>();
        sortedList.add(2);
        sortedList.add(2);
        sortedList.add(5);
        sortedList.add(5);
        sortedList.add(8);
        sortedList.add(8);
        sortedList.add(8);

        Assertions_AleksandrZmeyev.assertTrue(sortedList.isSorted());
    }

    /**
     * Проверяется, что метод {@code isSorted()}, вызванный у списка
     * {@code ArrayList_AleksandrZmeyev}, отсортированного с помощью метода
     * {@code quickSort()}, вернёт {@code true}.
     */
    @IntensiveTest_AleksandrZmeyev
    public void testSortedAfterQuickSort() {
        IntensiveList<Integer> sortedList = new ArrayList_AleksandrZmeyev<>();
        sortedList.add(8);
        sortedList.add(2);
        sortedList.add(5);
        sortedList.add(4);
        sortedList.add(9);
        sortedList.add(3);

        sortedList.quickSort(Comparator.naturalOrder());

        Assertions_AleksandrZmeyev.assertTrue(sortedList.isSorted());
    }
}
