package test;

import annotation.IntensiveTest_YaroslavRulev;
import util.CustomCollection.CustomArrayList;



/**
 * Класс CustomArrayListTest представляет собой тестовый класс для класса CustomArrayList.
 * Он включает в себя положительные и отрицательные тесты для сравнения списков.
 */
public class CustomArrayListTest {

    private final CustomArrayList<String> actual;
    private final CustomArrayList<String> expected;
    private final CustomArrayList<String> negativeExpected;

    /**
     * Инициализирует тестовый класс, создавая экземпляры CustomArrayList и заполняя их значениями.
     */
    public CustomArrayListTest() {
        this.negativeExpected = new CustomArrayList<>();
        this.actual = new CustomArrayList<>();
        this.expected = new CustomArrayList<>();
        actual.add("value");
        actual.add("value");
        expected.add("value");
        expected.add("value");
        negativeExpected.add("value");
    }

    /**
     * Тестирование положительного сравнения списков.
     */
    @IntensiveTest_YaroslavRulev
    public void positiveCompareLists() {
        Assertions_YaroslavRulev.assertionsEquals(actual, expected);
    }

    /**
     * Тестирование отрицательного сравнения списков.
     */
    @IntensiveTest_YaroslavRulev
    public void negativeCompareLists() {
        Assertions_YaroslavRulev.assertionsEquals(actual, negativeExpected);
    }

}
