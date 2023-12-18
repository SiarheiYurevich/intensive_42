package com.dziamyanau.task2;

import com.dziamyanau.task1.ArrayList_YauheniDziamyanau;
import com.dziamyanau.task1.IntensiveList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *Класс состоит из тестовых статических методов , которые проверяют на сортировку,
 * выбрасываются ли исключения и равенство заданных коллекций
 */
public class Assertions_YauheniDziamyanau {



    /**
     *Метод проверяет на равенство коллекции ожидаемой
     * @param expected ожидаемая коллекция
     * @param actual актуальная коллекция
     */
    public static <E> boolean assertEquals(IntensiveList<E> expected, IntensiveList<E> actual) {
        if (expected.size() != actual.size()) {
            return false;
        }
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(actual.get(i)))
                return false;

        }
        return true;
    }
    /**
     *Метод проверяет на не  равенство коллекции ожидаемой
     * @param expected ожидаемая коллекция
     * @param actual актуальная коллекция
     */
    public static <E> boolean assertNotEquals(IntensiveList<E> expected, IntensiveList<E> actual) {
        if (expected.size() != actual.size()) {
            return true;
        }
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(actual.get(i)))
                return true;

        }
        return false;
    }

    /**
     *Метод проверяет на выброс исключения при вызове метода get
     * @param list данная  коллекция
     * @param illegalArgument аргумент при котором будет выброшено исключение
     */
    public static <E> boolean assertThrowIllegalArgumentExceptionByMethodGet(IntensiveList<E> list, int illegalArgument){
        try{
            list.get(illegalArgument);
            return false;
        }
        catch (IllegalArgumentException e){
            return true;
        }
    }


    /**
     *Метод проверяет на выброс исключения при вызове метода split
     * @param list данная  коллекция
     * @param illegalSize аргумент при котором будет выброшено исключение
     */
    public static <E> boolean assertRuntimeExceptionByMethodSplit(IntensiveList<E> list, int illegalSize){
        try{
            list.split(illegalSize);
            return false;
        }
        catch (RuntimeException e){
            return true;
        }
    }
    /**
     *Метод проверяет отсортирована ли коллекция
     *
     */
    public static <E> boolean assertIsSorted(IntensiveList<E> list, Comparator<E>comparator ){
       return list.isSorted(comparator);
    }



}

