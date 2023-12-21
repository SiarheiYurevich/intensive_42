package task_1;


import task_2.Assertions_TatyanaSharova;

import java.util.Comparator;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        ArrayList_TatyanaSh<Integer> list = new ArrayList_TatyanaSh<>();
        list.add(7);
        list.add(5);
        list.add(6);
        List<Integer> l = List.of(7, 5, 6);
        System.out.println(list.get(1) == l.get(1));
        list.add(8);
        list.add(2);
        list.add(4);
        list.add(7);
        list.add(7);
        list.add(10);
        list.add(8, 20);
        list.print();

        list.add(9, 12);
        list.add(65);
        list.print();
        System.out.println("size = " + list.size() + "; capacity = " + list.length());

        System.out.println(list.get(3));
        list.set(1, 15);
        list.remove(5);
        list.print();
        list.quickSort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        list.print();
        list.add(4);
        System.out.println(list.isSorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        }));

        list.split(6);
        list.print();
        list.clear();
        list.print();

    }
}


