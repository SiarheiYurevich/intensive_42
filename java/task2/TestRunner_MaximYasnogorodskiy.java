package task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.Objects.nonNull;

public class TestRunner_MaximYasnogorodskiy {

    private final Path[] paths;

    public TestRunner_MaximYasnogorodskiy(Path... path) {
        this.paths = path;
    }

    public void run() {
        invokeAnnotatedMethods();
    }

    /**
     * Запуск обхода файлов.
     */
    private void invokeAnnotatedMethods() {
        if (nonNull(paths)) {
            for (Path p : paths) {
                try {
                    Files.walkFileTree(p, new FileVisitorImpl());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
