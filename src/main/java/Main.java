import task_1.impl.ArrayList_SlavaSles;
import task_1.IntensiveList;
import task_2.Assertions_SlavaSles;
import task_2.TestRunner_SlavaSles;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IntensiveList<String> expected = new ArrayList_SlavaSles<>();
        expected.add("1");
        expected.add("35");
        IntensiveList<String> actual = new ArrayList_SlavaSles<>();
        actual.add("1");
        actual.add("3" + "5");
        Assertions_SlavaSles.assertEqualsLists(expected, actual);
        System.out.println();
        Assertions_SlavaSles.assertEquals(5, 15 / 3);
        Assertions_SlavaSles.assertEquals("53", "5" + "3");
        Assertions_SlavaSles.assertNotEquals(5, 15 / 5);

        TestRunner_SlavaSles testRunner = new TestRunner_SlavaSles();
        List<String> packages = new ArrayList<>();
        packages.add("task_2");
        packages.add("task_1.impl");
        packages.forEach(testRunner::run);
    }
}
