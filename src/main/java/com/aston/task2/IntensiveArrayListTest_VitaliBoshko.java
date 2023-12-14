package com.aston.task2;

import com.aston.task1.ArrayList_VitaliBoshko;
import com.aston.task1.IntensiveList;

import java.util.Random;

/**
 * This class consists of testing methods for checking lists is its sorted
 *
 * @author Vitali Boshko
 */
public class IntensiveArrayListTest_VitaliBoshko {

    /**
     * The test method for check the list is not sorted
     */
    @IntensiveTest_VitaliBoshko
    public void testIsNotSortedList() {
        IntensiveList<Integer> intensiveList = new ArrayList_VitaliBoshko<>();
        Random random = new Random();
        for (int i = 0; i < 25; i++) {
            intensiveList.add(random.nextInt(100));
        }

        System.out.println(intensiveList);

        boolean isSorted = Assertions_VitaliBoshko.assertIsSorted(intensiveList, Integer::compareTo);

        if (isSorted) {
            System.out.println("List is sorted");
        } else {
            System.out.println("List isn't sorted");
        }
    }

    /**
     * The test method for check the list is sorted
     */
    @IntensiveTest_VitaliBoshko
    public void testIsSortedList() {
        IntensiveList<Integer> intensiveList = new ArrayList_VitaliBoshko<>();
        Random random = new Random();
        for (int i = 0; i < 25; i++) {
            intensiveList.add(random.nextInt(100));
        }
        intensiveList.quickSort(Integer::compareTo);
        System.out.println(intensiveList);

        boolean isSorted = Assertions_VitaliBoshko.assertIsSorted(intensiveList, Integer::compareTo);

        if (isSorted) {
            System.out.println("List is sorted");
        } else {
            System.out.println("List isn't sorted");
        }
    }

    public static void main(String[] args) {
        TestRunner_VitaliBoshko testRunnerVitaliBoshko = new TestRunner_VitaliBoshko("com.aston.task2");
        testRunnerVitaliBoshko.run();
    }
}
