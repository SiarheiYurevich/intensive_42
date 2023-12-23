package org.example.services.search;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchServiceImpl implements SearchService {
    public List<Class<?>> findClasses(String packageName) {
        if (packageName == null) {
            return Collections.emptyList();
        }

        List<Class<?>> classes = new ArrayList<>();

        String packagePath = packageName.replace(".", "/");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        File packageDir = new File(classLoader.getResource(packagePath).getFile());
        File[] files = packageDir.listFiles();

        if (files != null) {
            for (File file : files) {
                String fileName = file.getName().replace(".class", "");

                Class<?> currentClass;

                try {
                    currentClass = Class.forName(packageName + "." + fileName);
                } catch (ClassNotFoundException e) {
                    continue;
                }

                classes.add(currentClass);
            }
        }

        return classes;
    }
}
