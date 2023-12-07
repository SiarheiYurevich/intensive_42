import com.yaromich.arraylist.ArrayList_AnastasiaYaromich;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        ArrayList_AnastasiaYaromich<Integer> list
                = new ArrayList_AnastasiaYaromich<Integer>(5);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(6);
        list.add(7);

        list.display();
        list.add(4, 15);
        list.display();
        list.add(0, 33);
        list.display();
        list.add(9, 105);
        list.display();

        System.out.println(list.get(9));
        System.out.println(list.get(0));
        System.out.println(list.get(5));

        list.set(0, -8);
        list.display();
        list.set(4, -12);
        list.display();
        list.set(9, -16);
        list.display();

        list.display();

        list.quickSort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        list.display();

        list.split(7);
        list.display();
    }
}
