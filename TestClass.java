package com.dziamyanau.task1;

import java.util.Comparator;

public class TestClass {
    public static void main(String[] args) {

        ArrayList_YauheniDziamyanau <Integer> arrayList= new ArrayList_YauheniDziamyanau<>();
        arrayList.set(0,1);
        System.out.println(arrayList.size());
        arrayList.add(2);
        System.out.println(arrayList.size());
       arrayList.add(7);
        System.out.println(arrayList.size());
        arrayList.add(6);
        arrayList.add(9);
        arrayList.add(1,4);
        System.out.println(arrayList.toString());
        System.out.println(arrayList.isSorted(Integer::compareTo));
        System.out.println(arrayList.size());

       Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        };
        arrayList.quickSort(comparator);
        System.out.println(arrayList);

        arrayList.split(5);
        System.out.println(arrayList);

        arrayList.remove(3);
        System.out.println(arrayList);

        arrayList.clear();
        System.out.println(arrayList);



    }
}
