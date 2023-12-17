package task2;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("intensive_42/java");
        Path path1 = Path.of("intensive_42/java");
        TestRunner_MaximYasnogorodskiy testRunner = new TestRunner_MaximYasnogorodskiy(path, path1);
        testRunner.run();
    }
}
