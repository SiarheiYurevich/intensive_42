import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        ArrayList_PloshkinPavel<Integer> test = new ArrayList_PloshkinPavel<>();

        System.out.println("Add");
        test.add(3);
        test.add(2);
        test.add(7);
        test.add(3);
        test.add(4, 5);
        test.set(0, 55);
        for (int i=0; i < test.size();i++){
            System.out.println(test.get(i));
        }
        System.out.println(test.isSorted());

        System.out.println("Sort");
        test.quickSort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

        for (int i=0; i < test.size();i++){
            System.out.println(test.get(i));
        }
        System.out.println(test.isSorted());

        System.out.println("Split (2)");
        test.split(2);
        for (int i=0; i < test.size();i++){
            System.out.println(test.get(i));
        }

        System.out.println("Remove (0)");
        test.remove(0);
        System.out.println(test.get(0));

        System.out.println("Clear");
        test.clear();
        for (int i=0; i < test.size();i++){
            System.out.println(test.get(i));
        }

    }
}