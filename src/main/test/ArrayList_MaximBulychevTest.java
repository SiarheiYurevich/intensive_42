package src.main.test;

import src.main.java.array_list.ArrayList_MaximBulychev;

import java.util.Comparator;

public class ArrayList_MaximBulychevTest {
    public static void main(String[] args) {
        ArrayList_MaximBulychev<TestClass> list = new ArrayList_MaximBulychev<>();
        list.add(new TestClass("Апельсины"));
        list.add(new TestClass("Бананы"));
        list.add(new TestClass("Лимоны"));
        list.add(new TestClass("Грейпфруты"));
        list.add(new TestClass("Гранаты"));
        list.add(new TestClass("Яблоки"));
        list.add(new TestClass("Персики"));
        list.add(new TestClass("Мандарины"));
        list.add(new TestClass("Груши"));
        list.add(new TestClass("Манго"));
        System.out.println(list + " Размер: " + list.size());

        list.add(4, new TestClass("Помидоры"));
        list.add(list.size(), new TestClass("Абрикосы"));
        TestClass lemons = list.set(2, new TestClass("Нектарины"));
        System.out.println(list + " Размер: " + list.size());
        System.out.println("Заменили лимоны: " + lemons);

        TestClass tomatoes = list.remove(4);
        System.out.println("Заменили помидоры: " + tomatoes);
        System.out.println("Содержит сливы: " + list.contains(new TestClass("Сливы")));
        System.out.println("Содержит манго: " + list.contains(new TestClass("Манго")));
        System.out.println(list + " Размер: " + list.size());
        System.out.println("Отсортирован: " + list.isSorted(Comparator.comparing(TestClass::getStr)));
        list.quickSort(Comparator.comparing(TestClass::getStr));
        System.out.println(list + " Размер: " + list.size());
        System.out.println("Отсортирован: " + list.isSorted(Comparator.comparing(TestClass::getStr)));

        list.split(6);
        System.out.println(list + " Размер: " + list.size());
        list.clear();
        System.out.println(list + " Размер: " + list.size());

        list = new ArrayList_MaximBulychev<>(2);
        list.add(new TestClass("Апельсины"));
        list.add(new TestClass("Бананы"));
        list.add(new TestClass("Лимоны"));
        list.add(new TestClass("Грейпфруты"));
        list.add(new TestClass("Гранаты"));
        list.add(new TestClass("Яблоки"));
        System.out.println(list + " Размер: " + list.size());
    }
}

