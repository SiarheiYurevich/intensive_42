package task_2;

import java.util.Comparator;
import java.util.List;

public class Assertions_AnastasiaYaromich {

    static <E> boolean assertEquals(IntensiveList<E> list1, IntensiveList<E> list2) {
        if((list1 == null || list2 == null) || (list1.size() != list2.size())) {
            throw  new AssertionError("List has different size");
        }
        for (int i = 0; i < list1.size(); i++) {
            E list1Element = list1.get(i);
            E list2Element = list2.get(i);

            if(!list1Element.equals(list2Element)) {
                throw  new AssertionError("Element " + list1Element + " from list1 are not equal element " + list2Element + "from list2");
            }
        }
        return true;
    }
}
