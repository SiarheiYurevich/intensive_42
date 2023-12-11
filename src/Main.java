import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        System.out.println(intList.toString());
    }
}
