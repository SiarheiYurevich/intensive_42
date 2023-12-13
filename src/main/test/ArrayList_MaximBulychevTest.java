package src.main.test;

import src.main.java.array_list.ArrayList_MaximBulychev;

import java.util.ArrayList;
import java.util.Comparator;

public class ArrayList_MaximBulychevTest {
    public static void main(String[] args) {
        ArrayList_MaximBulychev<TestClass> list1 = new ArrayList_MaximBulychev<>();
        list1.add(new TestClass("Апельсины"));
        list1.add(new TestClass("Бананы"));
        list1.add(new TestClass("Лимоны"));
        list1.add(new TestClass("Грейпфруты"));
        list1.add(new TestClass("Гранаты"));
        list1.add(new TestClass("Яблоки"));
        list1.add(new TestClass("Персики"));
        list1.add(new TestClass("Мандарины"));
        list1.add(new TestClass("Груши"));
        list1.add(new TestClass("Манго"));
        System.out.println("list1: " + list1 + " Размер: " + list1.size());

        list1.add(4, new TestClass("Помидоры"));
        list1.add(list1.size(), new TestClass("Абрикосы"));
        TestClass lemons = list1.set(2, new TestClass("Нектарины"));
        System.out.println("list1: " + list1 + " Размер: " + list1.size());
        System.out.println("Заменили лимоны: " + lemons);
        TestClass tomatoes = list1.remove(4);
        System.out.println("Удалили помидоры: " + tomatoes);
        System.out.println();

        System.out.println("Содержит сливы: " + list1.contains(new TestClass("Сливы")));
        System.out.println("Содержит манго: " + list1.contains(new TestClass("Манго")));
        System.out.println();

        System.out.println("list1: " + list1 + " Размер: " + list1.size());
        System.out.println("Отсортирован: " + list1.isSorted(Comparator.comparing(TestClass::getStr)));
        list1.quickSort(Comparator.comparing(TestClass::getStr));
        System.out.println("Sorting...");
        System.out.println("list1: " + list1 + " Размер: " + list1.size());
        System.out.println("Отсортирован: " + list1.isSorted(Comparator.comparing(TestClass::getStr)));
        System.out.println();

        ArrayList_MaximBulychev<TestClass> list2 = new ArrayList_MaximBulychev<>(2);
        list2.add(new TestClass("Апельсины"));
        list2.add(new TestClass("Бананы"));
        list2.add(new TestClass("Абрикосы"));
        list2.add(new TestClass("Грейпфруты"));
        list2.add(new TestClass("Гранаты"));
        list2.add(new TestClass("Нектарины"));
        System.out.println("list2: " + list2 + " Размер: " + list2.size());

        System.out.println("list equals list1 (expected false): " + list1.equals(list2));
        System.out.println();

        list1.remove(10);
        list2.add(new TestClass("Персики"));
        list2.add(new TestClass("Мандарины"));
        list2.add(new TestClass("Груши"));
        list2.add(new TestClass("Манго"));
        list2.quickSort(Comparator.comparing(TestClass::getStr));
        System.out.println("list1: " + list1 + " Размер: " + list1.size());
        System.out.println("list2: " + list2 + " Размер: " + list2.size());
        System.out.println("list1 equals list2 (expected true): " + list1.equals(list2));
    }
}

