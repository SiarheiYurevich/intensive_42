package task_3.service.impl;

import task_3.IntensiveComponent_SlavaSles;
import task_3.service.SearchService;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.*;

public class SearchServiceImpl implements SearchService {

    @Override
    public Set<Class<?>> getAnnotatedClassesFromPackage(String packageName) throws IOException, ClassNotFoundException {

        Set<Class<?>> annotatedClassesFromPackage = new HashSet<>();

        if (packageName == null) {
            return annotatedClassesFromPackage;
        }

        ClassLoader classLoader = SearchServiceImpl.class.getClassLoader();

        String path = packageName.replaceAll("[.]", "/");

        Enumeration<URL> resources = classLoader.getResources(path);

        List<File> files = getFiles(resources);

        for (File file : files) {
            annotatedClassesFromPackage.addAll(findClasses(file, packageName));
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

    private Set<Class<?>> findClasses(File file, String packageName) throws ClassNotFoundException {
        Set<Class<?>> annotatedClassesFromDirectory = new HashSet<>();

        File[] subFiles = file.listFiles();

        if (subFiles == null) {
            return annotatedClassesFromDirectory;
        }

        for (File subFile : subFiles) {

            if (!subFile.isDirectory() && subFile.getName().endsWith(".class")) {
                String className = packageName + "." +
                        subFile.getName().substring(0, subFile.getName().lastIndexOf('.'));
                Class<?> clazz = Class.forName(className);

                addAnnotatedClassToSet(annotatedClassesFromDirectory, clazz);

            } else {
                String subPackageName = packageName + "." + subFile.getName();
                annotatedClassesFromDirectory.addAll(findClasses(subFile, subPackageName));
            }

        }
        return annotatedClassesFromDirectory;
    }

    private void addAnnotatedClassToSet(Set<Class<?>> annotatedClassesFromDirectory,
                                        Class<?> clazz) {
        if (clazz.isAnnotationPresent(IntensiveComponent_SlavaSles.class)) {
            annotatedClassesFromDirectory.add(clazz);
        }
    }
}
