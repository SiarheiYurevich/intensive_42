package task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.Objects.nonNull;

public class TestRunner_MaximYasnogorodskiy {

    private final Path path;

    public TestRunner_MaximYasnogorodskiy(Path path) {
        this.path = path;
    }

    public void run() {
        invokeAnnotatedMethods();
    }

    private void invokeAnnotatedMethods()  {
        if(nonNull(path)) {
            try {
                Files.walkFileTree(path, new FileVisitorImpl());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
