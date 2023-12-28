package com.aston.task2;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * This class scans the specified packages for the existence classes with methods
 * marked with annotation {@code IntensiveTest_VitaliBoshko} and runs them
 *
 * @author Vitali Boshko
 */
public class TestRunner_VitaliBoshko {
    private final String[] packagesArray;

    /**
     * Creates instance of class for scanning the specified packages
     *
     * @param packagesArray the list of packages the specified for later scanning
     */
    public TestRunner_VitaliBoshko(String... packagesArray) {
        this.packagesArray = packagesArray;
    }

    /**
     * The method scans the specified packages for the existence classes with methods
     * marked with annotation {@code IntensiveTest_VitaliBoshko} and runs them
     */
    public void run() {
        List<Class<?>> classList = new ArrayList<>();
        for (String s : packagesArray) {
            try {
                classList.addAll(getClasses(s));
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        List<Method> methodList = new ArrayList<>();
        for (Class<?> clazz : classList) {
            methodList.addAll(Arrays.asList(clazz.getMethods()));
        }

        findAndRunTestMethods(methodList, IntensiveTest_VitaliBoshko.class);
    }

    private void findAndRunTestMethods(List<Method> methodList, Class<? extends Annotation> annotation) {
        methodList.stream()
                .filter(method -> method.isAnnotationPresent(annotation))
                .forEach(method -> {
            try {
                method.invoke(method.getDeclaringClass().getDeclaredConstructor().newInstance());
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
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
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
