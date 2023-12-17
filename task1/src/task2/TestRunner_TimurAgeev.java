package task2;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * класс сканирует указанные пакеты на наличие
 * методов, аннотированных аннотацией @IntensiveTest_NameSurname, и выполняет проверку тестов -
 * запускает тестовые методы
 */
public class TestRunner_TimurAgeev {
    /**
     * Находит список классов в переданных пакетах и запускает тесты
     *
     * @param packages массив имен пакетов, в которых будет осуществлен поиск
     */
    public void run(String... packages) {
        List<Class<?>> classes = getClasses(packages);

        for (Class<?> currentClass : classes) {
            runTests(currentClass);
        }
    }

    /**
     * В переданном классе находит все методы и проверяет, если ли среди них методы помеченные аннотацией  IntensiveTest_TimurAgeev
     * Если такой метод найден, он запускается
     *
     * @param currentClass класс, в котором будет осуществлен поиск
     */
    private void runTests(Class<?> currentClass) {
        Method[] methods = currentClass.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(IntensiveTest_TimurAgeev.class)) {
                runTest(method, currentClass);
            }
        }
    }

    /**
     * Создает экземпляр класса и запускает вызов метода
     *
     * @param method       метод, подлежащий выполнения
     * @param currentClass класс, экзмепляр которого будет создан
     */
    private void runTest(Method method, Class<?> currentClass) {
        try {
            Object testInstance = currentClass.getDeclaredConstructor().newInstance();
            method.invoke(testInstance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Создает список классов и добавляет в него все найденные классы, которые были найдены в пакетах
     *
     * @param packages имя пакетов, в которых будет осуществлен поиск классов
     * @return список классов найденных в пакетах
     */
    private List<Class<?>> getClasses(String... packages) {
        if (packages == null) {
            return Collections.emptyList();
        }

        List<Class<?>> classes = new ArrayList<>();

        for (String packageName : packages) {
            List<Class<?>> currentClasses = findClassesInPackage(packageName);
            classes.addAll(currentClasses);
        }

        return classes;
    }


    /**
     * Создает пустой список классов
     * В найденном пакете создает массив файлов и получает их имена
     * После чего из имени получает Class и добавляет его в список
     *
     * @param packageName имя пакета, в котором будет осуществлен поиск классов
     * @return список классов найденных в пакете
     */
    private List<Class<?>> findClassesInPackage(String packageName) {
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

                try {
                    Class<?> currentClass = Class.forName(packageName + "." + fileName);
                    classes.add(currentClass);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return classes;
    }
}
