package task_2;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TestRunner_KonstantinZverev {
    public void run(String... packages) {
        List<Class<?>> classes = getClassesWithTestAnnotation(packages);
    }

    private List<Class<?>> getClassesWithTestAnnotation(String[] packages) {
        List<Class<?>> classes = getClasses(packages);
        return null;
        
    }

    private List<Class<?>> getClasses(String[] packages) {
        if (packages == null) {
            return Collections.emptyList();
        }

        List<Class<?>> result = new ArrayList<>();
        for (String pack : packages) {
            result.addAll(getPackageClasses(pack));
        }

        return result;
    }

    private List<Class<?>> getPackageClasses(String pack) {
        if (pack == null) {
            return Collections.emptyList();
        }

        List<Class<?>> result = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File packageDir = new File(Objects.requireNonNull(classLoader.getResource(pack)).getFile());
        File[] files = packageDir.listFiles();

        if (files != null) {
            for (File file : files) {
                String fileName = file.getName().replace(".class", "");

                try {
                    Class<?> currentClass = Class.forName(pack + "." + fileName);
                    result.add(currentClass);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return result;
    }
}
