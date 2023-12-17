package task2;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("intensive_42/java");
        TestRunner_MaximYasnogorodskiy testRunner = new TestRunner_MaximYasnogorodskiy(path);
        testRunner.run();
    }
}
