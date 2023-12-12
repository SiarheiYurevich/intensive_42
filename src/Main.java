import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        IntensiveList<Integer> intList = new ArrayList_LevDurov<>();
        intList.add(1);
        intList.add(3);
        intList.add(2);
        intList.add(4);
        intList.add(5);
        intList.add(3);
        intList.add(-2);
        intList.add(-4);

        System.out.println(intList.toString() + " отсортирован? " + intList.isSorted(Comparator.comparingInt(x -> x)));
        System.out.println("Быстрая сортировка");
        intList.quickSort(Comparator.comparingInt(x -> x));
        System.out.println(intList.toString() + " отсортирован? " + intList.isSorted(Comparator.comparingInt(x -> x)));
        intList.clear();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        System.out.println(intList.toString() + " отсортирован? " + intList.isSorted(Comparator.comparingInt(x -> x)));

        System.out.println(intList.toString());
    }
}
