package task_2;

import task_1.ArrayList_MaximBulychev;
import task_1.IntensiveList;

import java.util.Comparator;

/**
 * Класс, содержащий тесты для списка {@link ArrayList_MaximBulychev}.
 *
 * @author Максим Булычев
 */
public class IntensiveArrayListTest_MaximBulychev {

    private Comparator<Integer> comparator;

    /**
     * Конструктор без аргументов. В нём определяется компаратор, с помощью которого будут
     * сравниваться элементы списка(ов).
     */
    public IntensiveArrayListTest_MaximBulychev() {
        comparator = (obj1, obj2) -> {
            if (obj1 == null) {
                return obj2 == null ? 0 : 1;
            }
            return obj1.compareTo(obj2);
        };
    }

    /**
     * {@code main} метод, с которого начинается работа программы. В нём создаётся экземпляр класса
     * {@link TestRunner_MaximBulychev} и передаётся имя пакета для сканирования, после чего запускаются
     * тесты.
     */
    public static void main(String[] args) {
        TestRunner_MaximBulychev runner = new TestRunner_MaximBulychev("task_2");
        runner.run();
    }

    /**
     * Создаётся неотсортированный список и проверяется, что метод {@code isSorted()}
     * вернёт {@code false}.
     */
    @IntensiveTest_MaximBulychev
    public void testNotSorted() {
        IntensiveList<Integer> notSortedList = new ArrayList_MaximBulychev<>();
        notSortedList.add(2);
        notSortedList.add(3);
        notSortedList.add(5);
        notSortedList.add(1);
        notSortedList.add(2);
        notSortedList.add(9);
        Assertions_MaximBulychev.assertFalse(notSortedList.isSorted(comparator));
    }

    /**
     * Создаётся изначально отсортированный список и проверяется, что метод {@code isSorted()}
     * вернёт {@code true}.
     */
    @IntensiveTest_MaximBulychev
    public void testSortedFromCreation() {
        IntensiveList<Integer> sortedList = new ArrayList_MaximBulychev<>();
        sortedList.add(1);
        sortedList.add(3);
        sortedList.add(5);
        sortedList.add(6);
        sortedList.add(9);
        sortedList.add(11);
        Assertions_MaximBulychev.assertTrue(sortedList.isSorted(comparator));
    }

    /**
     * Создаётся неотсортированный список, после чего сортируется методом {@code quickSort()}
     * и проверяется, что метод {@code isSorted()} вернёт {@code true}.
     */
    @IntensiveTest_MaximBulychev
    public void testSortedAfterQuickSort() {
        IntensiveList<Integer> sortedList = new ArrayList_MaximBulychev<>();
        sortedList.add(2);
        sortedList.add(3);
        sortedList.add(5);
        sortedList.add(1);
        sortedList.add(2);
        sortedList.add(9);
        sortedList.quickSort(comparator);
        Assertions_MaximBulychev.assertTrue(sortedList.isSorted(comparator));
    }

    /**
     * Создаются 2 неодинаковых списка, и проверяется, что метод {@code equals()} вернёт
     * {@code true} (проверка вывода ошибки).
     */
    @IntensiveTest_MaximBulychev
    public void testEquals() {
        IntensiveList<Integer> list1 = new ArrayList_MaximBulychev<>();
        IntensiveList<Integer> list2 = new ArrayList_MaximBulychev<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list2.add(1);
        list2.add(4);
        list2.add(1, 2);
        Assertions_MaximBulychev.assertEquals(list1, list2);
    }
}
