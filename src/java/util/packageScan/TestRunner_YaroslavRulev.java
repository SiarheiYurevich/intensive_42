package util.packageScan;

import annotation.IntensiveTest_YaroslavRulev;


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestRunner_YaroslavRulev {


    /**
     * Метод сканирует  указанные директории, извлекает из них клаасы, формируя список методов.
     * Затем он вызывает каждый каждый метод, создавая его инстанс через рефлексию.
     * @param packagesScan массив директорий для сканирования
     */
    public void scanningPackageAndInvokeMethod(String[] packagesScan) {
        List<Method> methods = scanningMethodInClasses(loadClassesInPackages(packagesScan));
        for (Method method : methods) {
            try {
                Object instance = method.getDeclaringClass().getDeclaredConstructor().newInstance();
                method.invoke(instance);
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException |
                     NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * метод сканирует классы, извлекая из них методы,
     * содержащих в себе аннотацию.
     * @param classList лист классов.
     * @return {@link Method} возвращает список методов
     */

    private List<Method> scanningMethodInClasses(List<Class<?>> classList) {
        List<Method> methods = new ArrayList<>();

        for (Class<?> aClass : classList) {
            for (Method method : aClass.getMethods()) {
                if (method.isAnnotationPresent(IntensiveTest_YaroslavRulev.class)) {
                    methods.add(method);
                }
            }
        }

        return methods;
    }

    /**
     *  метод сканирует директории и  извлекает из них классы,
     *  тем самым формируя из них список.
     *
     * @param arrPackages массив дирректорий.
     * @return {@link Class} список классов,найденных в указанных директориях.
     */

    private List<Class<?>> loadClassesInPackages(String[] arrPackages) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        List<Class<?>> classList = new ArrayList<>();

        for (String packageName : arrPackages) {
            URL resource = classLoader.getResource(packageName);

            try {
                File file = new File(Objects.requireNonNull(resource).toURI());
                if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    if (files != null) {
                        for (File element : files) {
                            if (element.isFile() || element.getName().endsWith(".class")) {
                                String className = packageName + "." + element.getName().substring(0, element.getName().length() - 6);
                                Class<?> aClass = classLoader.loadClass(className);
                                classList.add(aClass);
                            }

                        }
                    }
                }
            } catch (URISyntaxException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return classList;
    }
}



