package test;

import arrayList.ArrayList_GalinaSamokhina;
import test_framework.Assertions_GalinaSamokhina;
import test_framework.IntensiveTest_GalinaSamokhina;
import java.util.Comparator;

/**
 *  Этот класс содержит тестовые методы для класса
 *  ArrayList_GalinaSamokhina,
 *  ориентированные на интенсивное тестирование.
 *  Он включает тест, проверяющий, что массив
 *  отсортирован после выполнения быстрой сортировки,
 *  и различные утверждения для сравнения ожидаемых
 *  и фактических результатов.
 */
public class IntensiveArrayListTest_GalinaSamokhina {

    /**
     * Тест, проверяющий, что массив отсортирован после выполнения быстрой сортировки
     * @throws Exception, если во время теста произошла ошибка.
     */
    @IntensiveTest_GalinaSamokhina
    public void testArrayIsSorted() throws Exception {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };

        ArrayList_GalinaSamokhina<String> actual = new ArrayList_GalinaSamokhina<>();
        ArrayList_GalinaSamokhina<String> expected = new ArrayList_GalinaSamokhina<>();

        String string1 = "апартаменты";
        String string2 = "бумага";
        String string3 = "хворост";

        actual.add(string2);
        actual.add(string3);
        actual.add(string1);

        Assertions_GalinaSamokhina.assertFalse(actual.isSorted());

        actual.quickSort(comparator);

        Assertions_GalinaSamokhina.assertTrue(actual.isSorted());

        expected.add(string1);
        expected.add(string2);
        expected.add(string3);

        Assertions_GalinaSamokhina.assertArrayEquals(expected, actual);

        Assertions_GalinaSamokhina.assertEquals(expected.get(0), actual.get(0));
        Assertions_GalinaSamokhina.assertNotEquals(expected.get(0), actual.get(1));
    }
}