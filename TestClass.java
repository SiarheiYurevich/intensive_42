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
//        arrayList.remove(2);
//        arrayList.split(2);
//
//
       Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        };
        arrayList.quickSort(comparator);

        System.out.println(arrayList.toString());
        System.out.println(arrayList.isSorted());
        System.out.println(arrayList.size());

    }
}
