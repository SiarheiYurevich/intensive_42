package task_1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        IntensiveList<Integer> list = new ArrayList_KonstantinZverev<>();

        var elements = List.of(9,8,7,6,5,4,3,2,1,0);
        elements.forEach(list::add);

        printElements(list);

        System.out.println(list.isSorted(Integer::compare));

        list.quickSort(Integer::compare);
        printElements(list);
        System.out.println(list.isSorted(Integer::compare));

        list.remove(1);
        list.remove(5);
        list.remove(6);
        printElements(list);

        list.set(0, 10);
        list.split(3);
        printElements(list);
    }

    private static void printElements(IntensiveList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
        }
        System.out.println();
    }
}
