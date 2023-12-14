import task2.TestRunner_LevDurov;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> packages = new ArrayList<>();
        packages.add(Main.class.getPackageName());

        TestRunner_LevDurov testRunner = new TestRunner_LevDurov(packages);
        testRunner.run();
    }
}
