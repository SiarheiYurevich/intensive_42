package framework_for_tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<String> packages = new ArrayList<>();
        packages.add("intensiveTest");
        TestRunner_NikitaNazarjev.run(packages);
    }
}
