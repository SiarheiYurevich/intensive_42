import arraylist.impl.ArrayList_SlavaSles;
import arraylist.IntensiveList;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        arrayList.add(12);
//        arrayList.add(1);
//        arrayList.add(3);
//        arrayList.add(3, 10);
//        arrayList.add(2, 88);
//        arrayList.forEach(System.out::println);
//        System.out.println();
//        System.out.println(arrayList.get(4));
//        System.out.println(arrayList.set(1, 11));
//        System.out.println();
//        System.out.println(arrayList.remove(3));
//        System.out.println();
//        arrayList.forEach(System.out::println);

        IntensiveList<Integer> arrayListSlavaSles = new ArrayList_SlavaSles<>();
        System.out.println(arrayListSlavaSles.size());
        arrayListSlavaSles.add(5);
        System.out.println(arrayListSlavaSles.get(0));
        arrayListSlavaSles.add(1, 3);
        System.out.println(arrayListSlavaSles.get(1));
        arrayListSlavaSles.add(2, 19);
        System.out.println(arrayListSlavaSles.get(2));
        arrayListSlavaSles.add(1, 48);
        System.out.println();
        for (int i = 0; i < arrayListSlavaSles.size(); i++) {
            System.out.println(arrayListSlavaSles.get(i));
        }
        System.out.println();
        System.out.println(arrayListSlavaSles.set(2, 55));
        System.out.println();
        System.out.println(arrayListSlavaSles.remove(0));
        System.out.println();
        for (int i = 0; i < arrayListSlavaSles.size(); i++) {
            System.out.println(arrayListSlavaSles.get(i));
        }
        System.out.println();
        for (int i = 0; i < 8; i++) {
            arrayListSlavaSles.add(i);
        }
        System.out.println();
        for (int i = 0; i < arrayListSlavaSles.size(); i++) {
            System.out.println(arrayListSlavaSles.get(i));
        }
        System.out.println(arrayListSlavaSles.isSorted());
        arrayListSlavaSles.split(8);
        arrayListSlavaSles.quickSort(Comparator.comparing(Integer::valueOf));
        System.out.println(arrayListSlavaSles.isSorted());
        for (int i = 0; i < arrayListSlavaSles.size(); i++) {
            System.out.println(arrayListSlavaSles.get(i));
        }
        arrayListSlavaSles.add(5, 100);
        arrayListSlavaSles.add(9, 2);
        System.out.println(arrayListSlavaSles.isSorted());
        arrayListSlavaSles.quickSort(Comparator.comparing(Integer::valueOf));
        System.out.println(arrayListSlavaSles.isSorted());
        for (int i = 0; i < arrayListSlavaSles.size(); i++) {
            System.out.println(arrayListSlavaSles.get(i));
        }
        arrayListSlavaSles.clear();
        System.out.println(arrayListSlavaSles.size());
        arrayListSlavaSles = new ArrayList_SlavaSles<>(8);
        System.out.println(arrayListSlavaSles.size());
    }
}
