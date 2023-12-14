package task_2;

import task_1.IntensiveList;
import task_1.impl.ArrayList_SlavaSles;

import java.util.Comparator;

public class IntensiveArrayListTest_SlavaSles {

    public IntensiveArrayListTest_SlavaSles() {
    }

    @IntensiveTest_SlavaSles
    public void testInteger() {
        IntensiveList<Integer> arrayList = new ArrayList_SlavaSles<>();
        arrayList.add(5);
        arrayList.add(9);
        arrayList.add(11);
        checkListIsSorted(arrayList, Comparator.comparing(Integer::valueOf));
    }

    @IntensiveTest_SlavaSles
    public void testString() {
        IntensiveList<String> arrayList = new ArrayList_SlavaSles<>();
        arrayList.add("а");
        arrayList.add("бв");
        arrayList.add("жз");
        checkListIsSorted(arrayList, Comparator.comparing(String::valueOf));
    }

    private <E> void checkListIsSorted(IntensiveList<E> list, Comparator<E> comparator) {
        boolean isSorted = false;
        if (list != null && list.size() <= 1) {
            isSorted = true;
        } else if (list != null && comparator != null) {
            isSorted = compareElements(list, comparator);
        }
        if (isSorted) {
            System.out.println("Массив отсортирован.");
        } else {
            System.err.println("Массив не отсортирован.");
        }
    }

    private <E> boolean compareElements(IntensiveList<E> list, Comparator<E> comparator) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (comparator.compare(list.get(i), list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }
}
