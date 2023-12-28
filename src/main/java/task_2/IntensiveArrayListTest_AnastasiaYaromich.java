package task_2;

public class IntensiveArrayListTest_AnastasiaYaromich {

    @InvensiveTest_AnastasiaYaromich
    void test() {
        ArrayList_AnastasiaYaromich<Integer> list1 = new ArrayList_AnastasiaYaromich<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        ArrayList_AnastasiaYaromich<Integer> list2 = new ArrayList_AnastasiaYaromich<Integer>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        Assertions_AnastasiaYaromich.assertEquals(list1, list2);
        System.out.println(Assertions_AnastasiaYaromich.assertEquals(list1, list2));
    }
}
