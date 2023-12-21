package task_2;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class TestRunner_AnastasiaYaromich {

    private final String[] packagesToScan;

    public TestRunner_AnastasiaYaromich(String... packagesToScan) {
        this.packagesToScan = packagesToScan;
    }


    void run() {
        if (packagesToScan == null) {
            throw new IllegalArgumentException("");
        }
        List<Method> methods = findAnnotatedMethods(findClassesInPackages(packagesToScan));

        methods.forEach(m -> {
            try {
                m.invoke(m.getDeclaringClass().getDeclaredConstructor().newInstance());
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private List<Method> findAnnotatedMethods(List<Class<?>> classes) {
        List<Method> methods = new ArrayList<>();
        for (Class<?> c : classes) {
            for (Method method : c.getDeclaredMethods()) {
                if (method.isAnnotationPresent(InvensiveTest_AnastasiaYaromich.class)) {
                    methods.add(method);
                }
            }
        }
        return methods;
    }

    private List<Class<?>> findClassesInPackages(String... packagesToScan) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        List<Class<?>> classes = new ArrayList<>();

        for (String currentPackage : packagesToScan) {
            URL resource = classLoader.getResource(currentPackage);
            try {
                File file = new File(Objects.requireNonNull(resource).toURI());
                if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    assert files != null;
                    Arrays.stream(files).filter(f -> f.isFile() && f.getName().endsWith(".class"))
                            .forEach(f -> {
                                String className = currentPackage + "." + f.getName().substring(0, f.getName().length() - 6);
                                Class<?> aClass = null;
                                try {
                                    aClass = classLoader.loadClass(className);
                                } catch (ClassNotFoundException e) {
                                    throw new RuntimeException(e);
                                }
                                classes.add(aClass);
                            });
                }
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        return classes;
    }
}


