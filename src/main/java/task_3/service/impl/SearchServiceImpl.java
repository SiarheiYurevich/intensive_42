package task_3.service.impl;

import task_3.IntensiveComponent_SlavaSles;
import task_3.service.SearchService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SearchServiceImpl implements SearchService {

    @Override
    public Map<Class<?>, String> getAnnotatedClassesFromPackage(String packageName) throws IOException, ClassNotFoundException {

        Map<Class<?>, String> annotatedClassesFromPackage = new HashMap<>();

        if (packageName == null) {
            return annotatedClassesFromPackage;
        }

        ClassLoader classLoader = SearchServiceImpl.class.getClassLoader();

        String path = packageName.replaceAll("[.]", "/");

        Enumeration<URL> resources = classLoader.getResources(path);

        List<File> files = getFiles(resources);

        for (File file : files) {
            annotatedClassesFromPackage.putAll(findClasses(file, packageName));
        }

        return annotatedClassesFromPackage;
    }

    private List<File> getFiles(Enumeration<URL> resources) {
        List<File> files = new ArrayList<>();

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();

            File file = new File(resource.getFile());

            files.add(file);
        }
        return files;
    }

    private Map<Class<?>, String> findClasses(File file, String packageName) throws ClassNotFoundException {
        Map<Class<?>, String> annotatedClassesFromDirectory = new HashMap<>();

        File[] subFiles = file.listFiles();

        if (subFiles == null) {
            return annotatedClassesFromDirectory;
        }

        for (File subFile : subFiles) {

            if (!subFile.isDirectory() && subFile.getName().endsWith(".class")) {
                String className = packageName + "." +
                        subFile.getName().substring(0, subFile.getName().lastIndexOf('.'));
                Class<?> clazz = Class.forName(className);

                addAnnotatedClassToMap(annotatedClassesFromDirectory, clazz, packageName);

            } else {
                String subPackageName = packageName + "." + subFile.getName();
                annotatedClassesFromDirectory.putAll(findClasses(subFile, subPackageName));
            }

        }
        return annotatedClassesFromDirectory;
    }

    private void addAnnotatedClassToMap(Map<Class<?>, String> annotatedClassesFromDirectory,
                                        Class<?> clazz, String packageName) {
        if (clazz.isAnnotationPresent(IntensiveComponent_SlavaSles.class)) {
            annotatedClassesFromDirectory.put(clazz, packageName);
        }
    }
}
