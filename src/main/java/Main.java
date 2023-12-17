import MyArrList.ArrayList_AlexandrTelezhkin;

public class Main {
    public static void main(String[] args) {
        ArrayList_AlexandrTelezhkin<Integer> myList = new ArrayList_AlexandrTelezhkin<>();
        System.out.println("Размер " + myList.getClass().getName() + " = " + myList.size());
        System.out.println(myList);
        System.out.println("----------");

        for (int i = 0, j = 24; i < 24; i++, j--) {
            myList.add(j);
        }
        System.out.println("Размер " + myList.getClass().getName() + " = " + myList.size());
        System.out.println(myList);
        System.out.println("----------");

        myList.add(7, 777);
        System.out.println("Добавляем элемент " + myList.get(7) + " в " + myList.getClass().getName());
        System.out.println("Размер " + myList.getClass().getName() + " = " + myList.size());
        System.out.println(myList);
        System.out.println("----------");

        System.out.println("Изменяем элемент " + myList.get(7) + " в " + myList.getClass().getName());
        myList.set(7, 17);
        System.out.println("На элемент " + myList.get(7) + " в " + myList.getClass().getName());
        System.out.println("Размер " + myList.getClass().getName() + " = " + myList.size());
        System.out.println(myList);
        System.out.println("----------");

        System.out.println("Удаляем элемент " + myList.get(7) + " в " + myList.getClass().getName());
        myList.remove(7);
        System.out.println("Размер " + myList.getClass().getName() + " = " + myList.size());
        System.out.println(myList);
        System.out.println("----------");

        System.out.println("Проверяем отсортирован ли " + myList.getClass().getName() + "? " + myList.isSorted());
        System.out.println(myList);
        System.out.println("----------");

        System.out.println("Сортируем " + myList.getClass().getName());
        myList.quickSort(Integer::compareTo);
        System.out.println(myList);
        System.out.println("----------");

        System.out.println("Проверяем отсортирован ли " + myList.getClass().getName() + "? " + myList.isSorted());
        System.out.println(myList);
        System.out.println("----------");

        System.out.println("Урезаем " + myList.getClass().getName() + " до 3 элементов");
        myList.split(3);
        System.out.println(myList);
        System.out.println("----------");

        System.out.println("Очищаем "+ myList.getClass().getName());
        myList.clear();
        System.out.println(myList);
        System.out.println("----------");

    }
}