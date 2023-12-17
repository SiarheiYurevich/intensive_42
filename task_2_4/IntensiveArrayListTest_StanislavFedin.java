package task_2_4;

import task_1.ArrayList_StanislavFedin;
import task_2.IntensiveTest_StanislavFedin;

import java.util.Comparator;

public class IntensiveArrayListTest_StanislavFedin {

    @IntensiveTest_StanislavFedin
    public static void test() {
        ArrayList_StanislavFedin<Integer> list = new ArrayList_StanislavFedin<>();
        list.add(1);
        list.add(7);
        list.add(12);
        list.add(20);
        list.add(21);

        System.out.println("Проверка отсортированного массива");
        assert list.isSorted(Comparator.comparingInt(o -> o));
        System.out.println("Успех!");

        list.clear();

        list.add(3);
        list.add(4);
        list.add(1);
        list.add(-5);
        list.add(-2);

        System.out.println("Проверка неотсортированного массива");
        assert !list.isSorted(Comparator.comparingInt(o -> o));
        System.out.println("Успех!");
    }
}
