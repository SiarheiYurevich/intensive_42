package com.aston.task3;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The class there is implementation of interface {@code SearchService}
 * and implements functionality of scanning the specified path for
 * the presence of classes marked with an annotation {@code IntensiveComponent_VitaliBoshko}.
 */
public class SearchServiceImpl implements SearchService {
    private final int NUMBER_OF_CHARACTERS_IN_EXTENSION_CLASS = 6;

    /**
     * This method scans the specified path for the presence of classes marked
     * with an annotation {@code IntensiveComponent_VitaliBoshko}.
     *
     * @param path the string value of the path of the scanned package
     * @return list objects {@code Class}
     */
    @Override
    public List<Class<?>> search(String path) {
        List<Class<?>> classes = new ArrayList<>();
        try {
            classes.addAll(getClasses(path));
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Exception when scanning the specified path " + path);
        }
        return getClassesWithAnnotation(classes, IntensiveComponent_VitaliBoshko.class);
    }

    private List<Class<?>> getClassesWithAnnotation(List<Class<?>> classesList, Class<? extends Annotation> annotation) {
        return classesList.stream()
                .filter(clazz -> clazz.isAnnotationPresent(annotation))
                .collect(Collectors.toList());
    }

    private List<Class<?>> getClasses(String packageName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);

        List<File> directories = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            directories.add(new File(resource.getFile()));
        }

        List<Class<?>> classes = new ArrayList<>();
        for (File directory : directories) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }

        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - NUMBER_OF_CHARACTERS_IN_EXTENSION_CLASS)));
            }
        }
        return classes;
    }
}
