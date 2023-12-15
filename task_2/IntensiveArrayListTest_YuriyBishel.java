package com.yuriybishel.task_2;

import com.yuriybishel.task_1.ArrayList_YuriyBishel;

import java.util.Random;
import java.util.logging.Logger;

public class IntensiveArrayListTest_YuriyBishel {
    private static final Logger logger = Logger.getLogger("TEST LOGGER");

    @IntensiveTest_YuriyBishel
    public static void testSorted() {
        logger.info("****************************************************");
        logger.info("Начало теста на проверку сортировки списка.");
        ArrayList_YuriyBishel<Integer> testList = new ArrayList_YuriyBishel<>();
        logger.info("Создан пустой список");

        for (int i = 0; i < 10; i++) {
            testList.add(new Random().nextInt(10));
        }
        logger.info("Заполняем список случайными числами");

        boolean isSorted = Assertions_YuriyBishel.assertIsSorted(testList, Integer::compareTo);
        logger.info("Произведена проверка сортировки.");

        if (isSorted) {
            logger.info("Список отсортирован.");
        } else {
            logger.info("Список не отсортирован.");
        }

        logger.info("Конец теста на проверку сортировки списка.");
        logger.info("***********************************************");
    }

    @IntensiveTest_YuriyBishel
    public static void equalsSorted() {
        logger.info("***********************************************");
        logger.info("Начало теста на проверку эквивалентости списков.");

        ArrayList_YuriyBishel<Integer> testList1 = new ArrayList_YuriyBishel<>();
        ArrayList_YuriyBishel<Integer> testList2 = new ArrayList_YuriyBishel<>();
        logger.info("Созданы два пустых списка");

        for (int i = 0; i < 10; i++) {
            testList1.add(new Random().nextInt(10));
            testList2.add(new Random().nextInt(10));
        }
        logger.info("Заполняем список случайными числами");

        boolean isEqual = Assertions_YuriyBishel.assertEquals(testList1, testList2);
        logger.info("Произведена проверка эквивалентности'.");

        if (isEqual) {
            logger.info("Списки эквивалентны.");
        } else {
            logger.info("Списки не эквивалентны.");
        }

        logger.info("Конец теста на проверку эквивалентности списков.");
        logger.info("**********************************************");
    }
}

