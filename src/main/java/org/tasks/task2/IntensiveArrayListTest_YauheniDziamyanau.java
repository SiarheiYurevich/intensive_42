package org.tasks.task2;


import org.tasks.task1.ArrayList_YauheniDziamyanau;

import java.util.Comparator;
import java.util.Random;
/**
 *Этот класс тестирует как работает наши тестовые методы
 */
public class IntensiveArrayListTest_YauheniDziamyanau {
    private static final Comparator<Integer> comparator = Integer::compareTo;

    public static void main(String[] args) {
        TestRunner_YauheniDziamyanau testRunner_yauheniDziamyanau= new TestRunner_YauheniDziamyanau();
        testRunner_yauheniDziamyanau.run(new String[]{"org.tasks.task2"});


    }

    private static ArrayList_YauheniDziamyanau<Integer> generateRandomArray(int size) {
        ArrayList_YauheniDziamyanau<Integer> array = new ArrayList_YauheniDziamyanau<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array.add(random.nextInt(100));
        }

        return array;
    }

    @IntensiveTest_YauheniDziamyanau
    public static  void testSorting() {

        ArrayList_YauheniDziamyanau<Integer> array=new ArrayList_YauheniDziamyanau<Integer>();
        array.add(1);
        array.add(2);
        array.add(3);


        boolean isSorted = Assertions_YauheniDziamyanau.assertIsSorted(array, comparator);

        if (isSorted) {
            System.out.println("Массив отсортирован");
        } else {
            System.out.println("Массив не отсортирован");
        }
    }

    @IntensiveTest_YauheniDziamyanau
    public static <E> void testNotSorting() {
        ArrayList_YauheniDziamyanau<Integer> array= generateRandomArray(10);

        boolean isSorted = Assertions_YauheniDziamyanau.assertIsSorted(array, comparator);

        if (isSorted) {
            System.out.println("Массив отсортирован");
        } else {
            System.out.println("Массив не отсортирован");
        }
    }

    @IntensiveTest_YauheniDziamyanau
    public static  void testEquals() {

        ArrayList_YauheniDziamyanau<Integer> expected= new ArrayList_YauheniDziamyanau<Integer>();
        expected.add(1);
        expected.add(2);



        ArrayList_YauheniDziamyanau<Integer> actual= new ArrayList_YauheniDziamyanau<Integer>();
        actual.add(1);
        actual.add(2);



        boolean isEqual = Assertions_YauheniDziamyanau.assertEquals(expected, actual);

        if (isEqual) {
            System.out.println("Тест пройден, массив равен заданному");
        } else {
            System.out.println("Тест не пройден, массив не равен заданному ");
        }
    }

    @IntensiveTest_YauheniDziamyanau
    public static  void testNotEquals() {
        ArrayList_YauheniDziamyanau<Integer> expected= generateRandomArray(10);

        ArrayList_YauheniDziamyanau<Integer> actual= new ArrayList_YauheniDziamyanau<Integer>();
        expected.add(1);
        expected.add(2);
        expected.add(7);


        boolean isEqual = Assertions_YauheniDziamyanau.assertEquals(expected, actual);

        if (isEqual) {
            System.out.println("Тест пройден, массив равен заданному");
        } else {
            System.out.println("Тест не пройден, массив не равен заданному");
        }
    }


    @IntensiveTest_YauheniDziamyanau
    public static void testThrowRuntimeException(){
        ArrayList_YauheniDziamyanau<Integer> arrayList_yauheniDziamyanau=generateRandomArray(10);

        boolean isThrowException= Assertions_YauheniDziamyanau.assertRuntimeExceptionByMethodSplit(arrayList_yauheniDziamyanau,-1);

        if (isThrowException) {
            System.out.println("Тест пройден, исключение выброшено");
        } else {
            System.out.println("Тест не пройден, исключение  не выброшено");
        }


    }
}