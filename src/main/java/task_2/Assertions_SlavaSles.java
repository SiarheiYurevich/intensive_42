package task_2;

import task_1.IntensiveList;

//    Метод проверки сортировки списков нужно реализовывать здесь?
//    В методы проверки равенства нужно передавать Comparator?

/**
 * Статический класс, в котором реализованы проверки утверждений (assertions) о равенстве/неравенстве объектов
 */
public class Assertions_SlavaSles {

    /**
     * Метод, проверяющий равенство списков IntensiveList между собой
     * @param expected ожидаемый список
     * @param actual проверяемый список
     */
    public static void assertEqualsLists(IntensiveList<?> expected, IntensiveList<?> actual) {
        if (checkListEquals(expected, actual)) {
            System.out.println("Списки IntensiveList равны.");
        } else {
            System.err.println("Списки IntensiveList не равны.");
            printErrListFalseResult("Expected", expected);
            printErrListFalseResult("Actual", actual);
        }
    }

    /**
     * Метод, проверяющий, списки IntensiveList и аргументо, не равны между собой
     * @param expected ожидаемый список
     * @param actual проверяемый список
     */
    public static void assertNotEqualsLists(IntensiveList<?> expected, IntensiveList<?> actual) {
        if (!checkListEquals(expected, actual)) {
            System.out.println("Списки IntensiveList не равны.");
            printStdListFalseResult("Expected", expected);
            printStdListFalseResult("Actual", actual);
        } else {
            System.err.println("Списки IntensiveList равны.");
        }
    }

    private static boolean checkListEquals(IntensiveList<?> expected, IntensiveList<?> actual) {
        if (expected == actual) {
            return true;
        } else if (expected != null && actual != null && expected.size() == actual.size()) {
            return checkListElements(expected, actual);
        }
        return false;
    }

    private static boolean checkListElements(IntensiveList<?> expected, IntensiveList<?> actual) {
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(actual.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static void printErrListFalseResult(String name, IntensiveList<?> list) {
        System.err.println(name + ": ");
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                System.err.println(list.get(i));
            }
        } else {
            System.err.println("null");
        }
    }

    private static void printStdListFalseResult(String name, IntensiveList<?> list) {
        System.out.println(name + ": ");
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        } else {
            System.out.println("null");
        }
    }

    /**
     * Метод, сравнивающий объекты между собой
     * @param expected ожидаемый объект
     * @param actual проверяемый объект
     */
    public static void assertEquals(Object expected, Object actual) {
        if (checkObjectEquals(expected, actual)) {
            System.out.println("Ссылки на объекты совпадают или объекты равны.");
        } else {
            System.err.println("Объекты не равны или ссылки на объекты не совпадают.");
        }
    }

    /**
     * Метод проверяющий, что объекты не равны между собой
     * @param expected ожидаемый объект
     * @param actual проверяемый объект
     */
    public static void assertNotEquals(Object expected, Object actual) {
        if (!checkObjectEquals(expected, actual)) {
            System.out.println("Объекты не равны или ссылки на объекты не совпадают.");
        } else {
            System.err.println("Ссылки на объекты совпадают или объекты равны.");
        }
    }

    private static boolean checkObjectEquals(Object expected, Object actual) {
        return expected == actual;
    }
}
