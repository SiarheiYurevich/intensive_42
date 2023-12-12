package src.java;


import java.util.Comparator;


public class Main {
    public static void main(String[] args) {
        ArrayList_TatyanaSh<Integer> list = new ArrayList_TatyanaSh<>();
        list.add(7);
        list.add(5);
        list.add(6);
        list.add(8);
        list.add(2);
        list.add(4);
        list.add(7);
        list.add(7);
        list.add(10);
        list.add(8, 20);
        list.print();

        list.add(9,12);
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
        System.out.println(list.isSorted());
        list.split(6);
        list.print();
        list.clear();
        list.print();

    }
}


