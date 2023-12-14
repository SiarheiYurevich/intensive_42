package task2;

import task1_2.ArrayList_LevDurov;
import task1_2.IntensiveList;

import java.util.Comparator;

/**
 * Класс с тестами для методов класса IntensiveArrayList_LevDurov.
 */
public class IntensiveArrayListTest_LevDurov {

    /**
     * Тест, проверяющий работу метода isSorted().
     */
    @IntensiveTest_LevDurov
    public static void test() {
        IntensiveList<Integer> intList = new ArrayList_LevDurov<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);

        try {
            Assertions_LevDurov.assertTrue(intList.isSorted(Comparator.comparingInt(x -> x)));
            intList.add(0);
            Assertions_LevDurov.assertFalse(intList.isSorted(Comparator.comparingInt(x -> x)));
            System.out.println("Test: IntensiveArrayListTest_LevDurov.test() is done.");
        } catch (AssertionError e) {
            System.out.println("Test: IntensiveArrayListTest_LevDurov.test() is failed. Reason: "
                    + e.getMessage());
        }
    }
}
