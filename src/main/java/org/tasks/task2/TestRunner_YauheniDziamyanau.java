package org.tasks.task2;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Этот класс создан для сканирования заданного пакета
 * и поиска антации для @IntensiveTest_YauheniDziamyanau
 * и запуска методов с этой анатацией
 */
public class TestRunner_YauheniDziamyanau {

    /**
     *Этот класс принимает на вход
     * @param packageList
     * нвызывает метод getClassesList,
     * и ищет в них методы с анатацией "IntensiveTest_YauheniDziamyanau,
     * которые потом вызывает
     */
    public void run(String[] packageList) {
        List<Class<?>> classes = getClassesList(packageList);

        for (Class<?> currentClass : classes) {
            Method[] methods = currentClass.getDeclaredMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(IntensiveTest_YauheniDziamyanau.class)) {
                    try {
                        Object testInstance = currentClass.getDeclaredConstructor().newInstance();
                        method.invoke(testInstance);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }



    /**
     *Этот класс принимает на вход
     * @param packageList
     * делает список из классов в пакетах packageList
     */
    private List<Class<?>> getClassesList(String[] packageList) {
        List<Class<?>> classes = new ArrayList<>();

        for (String name : packageList) {
            List<Class<?>> currentClasses = findClassesInPackage(name);
            classes.addAll(currentClasses);

        }


        if (packageList == null) {
            return Collections.emptyList();
        }

        return classes;


    }
    /**
     *Этот класс принимает на вход
     * @param packageName
     * нахожит в этом пакете все классы
     */
    private List<Class<?>> findClassesInPackage(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        String path = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(path);

        if (resource == null) {
            return classes;
        }

        File directory = new File(resource.getFile());
        File[] files = directory.listFiles();

        if (files == null) {

            return classes;
        }

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                try {
                    Class<?> clazz = Class.forName(className);
                    classes.add(clazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return classes;
    }
    }
