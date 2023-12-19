package task_2;

import task_1.ArrayList_TatyanaSh;
import task_1.IntensiveList;

import java.util.Comparator;

/**
 * {@code IntensiveArrauListTest_TatyanaSharova} является тестовым классом
 *
 * @Author Tatyana Sharova
 */

public class IntensiveArrayListTest_TatyanaSharova {

    /**
     * Тест проверяет, отсортирован ли список
     */
    @IntensiveTest_TatyanaSharova
    public void isSortedTest() {
        IntensiveList<Integer> list = new ArrayList_TatyanaSh<>();
        list.add(89);
        list.add(50);
        list.add(8);
        list.add(189);
        list.add(459);
        list.quickSort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        Assertions_TatyanaSharova.assertTrue(list.isSorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        }));
    }

    /**
     * Тест проверяет  два списка на равенство
     */
    @IntensiveTest_TatyanaSharova
    public void equalsLists() {
        IntensiveList<String> list = new ArrayList_TatyanaSh<>();
        list.add("Anna");
        list.add("Vasya");
        list.add("Petya");
        list.add("Olya");
        IntensiveList<String> list2 = new ArrayList_TatyanaSh<>();
        list2.add("Sveta");
        list2.add("Anna");
        list2.add("Vasya");
        list2.add("Petya");
        list2.add("Olya");
        list2.remove(0);
        Assertions_TatyanaSharova.assertEquals(list, list2);
    }
}
