package intensiveTest;

import arrayList.ArrayList_NikitaNazarjev;
import framework_for_tests.Assertions_NikitaNazarjev;
import framework_for_tests.IntensiveTest_NikitaNazarjev;

import java.util.Comparator;

public class IntensiveArrayListTest_NikitaNazarjev {

    @IntensiveTest_NikitaNazarjev
    public static void test_equals_arrayList_NikitaNazarjevTest(){
        ArrayList_NikitaNazarjev<String> first = new ArrayList_NikitaNazarjev<>();
        first.add("Hello");
        first.add("world");
        ArrayList_NikitaNazarjev<String> second = new ArrayList_NikitaNazarjev<>();
        second.add("Hello");
        second.add("world");

        Assertions_NikitaNazarjev.assertEquals(first,second);
        second.remove(1);
        Assertions_NikitaNazarjev.assertNotEquals(first,second);
    }


    @IntensiveTest_NikitaNazarjev
    public static void test_sorted_arrayList_NikitaNazarjevTest(){
        ArrayList_NikitaNazarjev<Integer> lst = new ArrayList_NikitaNazarjev<>();
        lst.add(1);
        lst.add(3);
        lst.add(4);
        lst.add(5);
        lst.add(2);
        Assertions_NikitaNazarjev.assertFalse(lst.isSorted());
        lst.quickSort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return (int)o1 - (int)o2;
            }
        });
        Assertions_NikitaNazarjev.assertTrue(lst.isSorted());
        ArrayList_NikitaNazarjev<Integer> lst2 = new ArrayList_NikitaNazarjev<>();
        lst2.add(1);
        lst2.add(2);
        lst2.add(3);
        lst2.add(4);
        lst2.add(5);
        Assertions_NikitaNazarjev.assertEquals(lst,lst2);
    }
}
