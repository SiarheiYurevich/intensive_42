package com.yuriybishel.task_2;

import com.yuriybishel.task_2.IntensiveTest_YuriyBishel;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * Класс TestRunner_YuriyBishel предназначен для поиска и запуска тестовых методов.
 * Осуществляет сканирование указанных пакетов на наличие методов с аннотацией @IntensiveTest_YuriyBishel и запуск найденных методов.
 */
public class TestRunner_YuriyBishel {
    public void runTests(String... packageNames) {

        for (String packageName : packageNames) {

            List<Method> methods = findTestMethods(packageName);

            for (Method method : methods) {
                runTestMethod(method);
            }
        }

    }

    /**
     * Производит поиск методов с аннотацией IntensiveTest_YuriyBishel
     * @param packageName выбранный пакет, в котором производим поиск
     * @return список методов с аннотацией
     */
    private List<Method> findTestMethods(String packageName) {

        List<Method> methods = new ArrayList<>();

        List<Class> classes = getClasses(packageName);

        for (Class clazz : classes) {

            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(IntensiveTest_YuriyBishel.class)) {
                    methods.add(method);
                }
            }

        }
        return methods;

    }

    /**
     * Запускает тестовый метод
     * @param method тестовый метод
     */
    private void runTestMethod(Method method) {

        try {

            Object testClassInstance = method.getDeclaringClass().getConstructor().newInstance();
            method.invoke(testClassInstance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получает классы из пакета
     * @param packageName выбранный пакет
     * @return найденный список классов
     */
    private List<Class> getClasses(String packageName) {
        List<Class> classes = new ArrayList<>();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');

        try {

            Enumeration<URL> resources = classLoader.getResources(path);

            while (resources.hasMoreElements()) {

                URL resource = resources.nextElement();

                File file = new File(resource.getFile());

                for (File f : file.listFiles()) {

                    if (f.getName().endsWith(".class")) {

                        String className = packageName + '.' + f.getName().replace(".class", "");
                        classes.add(Class.forName(className));
                    }

                }

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return classes;

    }

}