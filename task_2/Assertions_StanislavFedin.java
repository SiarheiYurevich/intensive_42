package task_2;

import task_1.ArrayList_StanislavFedin;
import task_1.IntensiveList;

import java.util.Random;

/**
 * A class with basic tests that check the correctness of the custom ArrayList implementation.
 * @author Stanislav Fedin
 */
public class Assertions_StanislavFedin {

    @IntensiveTest_StanislavFedin
    public static void createArrayTest() {
        System.out.println("Создание task_1.ArrayList_StanislavFedin с вместимостью по умолчанию...");
        IntensiveList<Object> initArray = new ArrayList_StanislavFedin<>();
        System.out.println("Успех!");

        System.out.println("Создание task_1.ArrayList_StanislavFedin с кастомной вместимостью...");
        IntensiveList<Object> customCapacityArray = new ArrayList_StanislavFedin<>(5);
        System.out.println("Успех!");

        System.out.println("Создание task_1.ArrayList_StanislavFedin с отрицательной вместимостью...");
        try {
            IntensiveList<Object> negativeCapacityArray = new ArrayList_StanislavFedin<>(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Отловили ошибку\nУспех!");
        }
    }

    @IntensiveTest_StanislavFedin
    public static void addElementTest() {
        IntensiveList<Integer> initArray = new ArrayList_StanislavFedin<>();

        assert initArray.size() == 0;

        System.out.println("Добавление элемента...");
        initArray.add(15);
        assert initArray.size() == 1;
        System.out.println("Успех!");

        System.out.println("Наполнение массива...");
        initArray.add(12);
        initArray.add(19);
        initArray.add(9);
        initArray.add(5);
        initArray.add(25);
        initArray.add(17);
        System.out.println("Успех!");

        System.out.println("Добавление элемента в указанное место...");
        initArray.add(5, 15);
        assert initArray.get(5) == 15;
        assert initArray.get(6) == 25;
        System.out.println("Успех!");

        System.out.println("Добавление элемента в последний слот текущей вместимости...");
        initArray.add(9, 10);
        assert initArray.get(9) == 10;
        assert initArray.get(8) == null;
        assert initArray.size() == 9;
        System.out.println("Успех!");
    }

    @IntensiveTest_StanislavFedin
    public static void setElementTest() {
        IntensiveList<String> initArray = new ArrayList_StanislavFedin<>();
        initArray.add("testString");

        System.out.println("Заменяем элемент в массиве...");
        String res = initArray.set(0, "newString");
        assert initArray.get(0).equals("newString");
        assert res.equals("testString");
        System.out.println("Успех!");
    }

    @IntensiveTest_StanislavFedin
    public static void removeElementTest() {
        IntensiveList<String> initArray = new ArrayList_StanislavFedin<>();
        initArray.add("testString");
        initArray.add("anotherTestString");

        System.out.println("Удаляем элемент из массива...");
        String res = initArray.remove(1);
        assert initArray.size() == 1;
        assert res.equals("anotherTestString");
        System.out.println("Успех!");
    }

    @IntensiveTest_StanislavFedin
    public static void clearArrayTest() {
        IntensiveList<Integer> initArray = new ArrayList_StanislavFedin<>(15);
        Random rand = new Random();
        for (int i = 0; i < 15; i++) {
            initArray.add(rand.nextInt(1, 100));
        }

        assert initArray.get(14) != null;

        System.out.println("Очищаем массив...");
        initArray.clear();
        assert initArray.get(0) == null;
        assert initArray.size() == 0;
        System.out.println("Успех!");

        System.out.println("Проверка вместимости...");
        try {
            initArray.get(11);
        } catch (IllegalArgumentException e) {
            System.out.println("Вместимость приведена к значению по умолчанию\nУспех!");
        }
    }

    @IntensiveTest_StanislavFedin
    public static void splitArrayTest() {
        IntensiveList<Integer> initArray = new ArrayList_StanislavFedin<>(20);

        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            initArray.add(rand.nextInt(1, 50));
        }
        assert initArray.size() == 20;

        System.out.println("Обрезаем массив...");
        initArray.split(17);

        assert initArray.size() == 17;

        try {
            initArray.get(18);
        } catch (IllegalArgumentException e) {
            System.out.println("Успех!");
        }
    }
}
