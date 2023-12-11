import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        IntensiveList<Integer> intList = new ArrayList_LevDurov<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(1);
        intList.add(2);
        intList.add(3);

        intList.add(4, 999);
        intList.set(8, 777);
        System.out.println(intList.get(8));
        System.out.println(intList.toString());
        intList.remove(3);
        intList.remove(6);
        System.out.println(intList.toString());

        System.out.println(intList.toString() + " отсортирован? " + intList.isSorted(Comparator.comparingInt(x -> x)));
        //intList.quickSort(Comparator.comparingInt(x -> x));
        System.out.println(intList.toString() + " отсортирован? " + intList.isSorted(Comparator.comparingInt(x -> x)));
        intList.clear();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        System.out.println(intList.toString() + " отсортирован? " + intList.isSorted(Comparator.comparingInt(x -> x)));

        System.out.println(intList.toString());
    }
}
