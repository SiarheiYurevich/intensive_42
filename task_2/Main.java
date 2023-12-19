package task_2;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
       List<String> packages = new ArrayList<>();
        packages.add(Main.class.getPackage().getName());
        TestRunner_TatyanaSharova testRunnerTatyanaSharova = new TestRunner_TatyanaSharova(packages);
        testRunnerTatyanaSharova.run();

    }
}
