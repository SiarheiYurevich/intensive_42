import org.junit.jupiter.api.*;

import java.util.Comparator;


public class ArrayList_NikitaNazarjevTest {

    private ArrayList_NikitaNazarjev<Integer> myList;

    @BeforeEach
    public void createMyList(){
        myList = new ArrayList_NikitaNazarjev<>();
    }
    @Test
    public void constructor_test(){
        Assertions.assertThrows(IndexOutOfBoundsException.class,()->new ArrayList_NikitaNazarjev<>(-1));
    }

    @Test
    public void addMethod_test(){
        int initListSize = myList.size();
        int expectedInitListSize = 0;
        Assertions.assertEquals(initListSize,expectedInitListSize);

        myList.add(1);

        int expectedAfterAddListSize = 1;
        int actualListSize = myList.size();
        Assertions.assertEquals(expectedAfterAddListSize,actualListSize);
    }

    @Test
    public void index_addition_and_equals_test(){
        ArrayList_NikitaNazarjev<String> testList1 = new ArrayList_NikitaNazarjev<>();
        testList1.add("Hello");
        testList1.add("my");
        testList1.add("world");

        ArrayList_NikitaNazarjev<String> testList2 = new ArrayList_NikitaNazarjev<>();
        testList2.add("Hello");
        testList2.add("world");

        testList2.add(1,"my");

        Assertions.assertTrue(testList1.equals(testList2));
    }

    @Test
    public void remove_element_test(){
        myList.add(1);
        myList.remove(0);
        Assertions.assertEquals(0,myList.size());
    }

    @Test
    public void get_test(){
        myList.add(3);
        Assertions.assertEquals(3,myList.get(0));
    }

    @Test
    public void quickSort_test(){
        myList.add(2);
        myList.add(1);
        myList.add(5);
        myList.add(3);
        myList.add(6);
        myList.add(4);

        ArrayList_NikitaNazarjev<Integer> testList = new ArrayList_NikitaNazarjev<>();
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        testList.add(5);
        testList.add(6);

        Assertions.assertFalse(myList.equals(testList));
        Assertions.assertFalse(myList.isSorted());
        myList.quickSort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return (int)o1-(int)o2;
            }
        });

        Assertions.assertTrue(myList.equals(testList));
        Assertions.assertTrue(myList.isSorted());
    }

}
