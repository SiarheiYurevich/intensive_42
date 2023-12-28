package task2;

import task1.ArrayList_TimurAgeev;

import java.util.Random;
import java.util.logging.Logger;

public class IntensiveArrayListTest_TimurAgeev {
    private static final Logger logger = Logger.getLogger("TEST LOGGER");

    @IntensiveTest_TimurAgeev
    public static void testSorted() {
        logger.info("****************************************************");
        logger.info("Старт теста на проверку сортировки списка.");
        ArrayList_TimurAgeev<Integer> testList = new ArrayList_TimurAgeev<>();
        logger.info("Создан пустой список");

        testList.add(1);
        testList.add(2);
        testList.add(3);
        logger.info("Список заполнен тремя числами");

        boolean isSorted = Assertions_TimurAgeev.assertIntensiveListIsSorted(testList, Integer::compareTo);
        logger.info("Произведена проверка сортировки.");

        if (isSorted) {
            logger.info("Список отсортирован.");
        } else {
            logger.info("Список не отсортирован.");
        }

        logger.info("Конец теста на проверку сортировки списка.");
        logger.info("****************************************************");
    }

    @IntensiveTest_TimurAgeev
    public static void equalsSorted() {
        logger.info("****************************************************");
        logger.info("Старт теста на проверку эквивалентости списков.");

        ArrayList_TimurAgeev<Integer> testList1 = new ArrayList_TimurAgeev<>();
        ArrayList_TimurAgeev<Integer> testList2 = new ArrayList_TimurAgeev<>();
        logger.info("Созданы два пустых списка");


        testList1.add(1);
        testList1.add(2);
        testList1.add(3);
        testList2.add(1);
        testList2.add(2);
        testList2.add(3);
        logger.info("Список заполнен тремя числами");

        boolean isEqual = Assertions_TimurAgeev.assertIntensiveListEquals(testList1, testList2);
        logger.info("Произведена проверка эквивалентности'.");

        if (isEqual) {
            logger.info("Списки эквивалентны.");
        } else {
            logger.info("Списки не эквивалентны.");
        }

        logger.info("Конец теста на проверку эквивалентности списков.");
        logger.info("****************************************************");
    }
}
