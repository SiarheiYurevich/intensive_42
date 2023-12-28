package task2;

import task1.MyArrayList;

public class IntensiveArrayListTest_MaximYasnogorodskiy {

    MyArrayList<Integer> list = new MyArrayList<>();

    {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
    }

    /**
     * Тест, проверяющий, отсортировано ли хранилище в MyArrayList
     */
    @IntensiveTest_MaximYasnogorodskiy
    public void test() {

        boolean isSorted = Assertions_MaximYasnogorodskiy.assertSorted(Integer::compareTo, list);

        System.out.println("Array is " + (isSorted? "sorted" : "unsorted"));
    }

    /**
     * Тест, проверяющий, равны ли два экземпляра MyArrayList между собой.
     */
    @IntensiveTest_MaximYasnogorodskiy
    public void testEquals() {
        MyArrayList<Integer> list1 = new MyArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        boolean b = Assertions_MaximYasnogorodskiy.assertEquals(list, list1);

        System.out.println("Equals result: " + b);

    }
}
