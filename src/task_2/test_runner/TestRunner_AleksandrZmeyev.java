package task_2.test_runner;

import task_2.annotations.IntensiveTest_AleksandrZmeyev;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * {@code TestRunner_AleksandrZmeyev} сканирует указанные пакеты на наличие
 * тестовых классов, методы которых имеют аннотацию {@code IntensiveTest_AleksandrZmeyev},
 * затем запускает данные методы.
 *
 * @author Александр Змеев
 */
public class TestRunner_AleksandrZmeyev {


    private final String SEPARATOR = ".";

    /**
     * Расширение искомых файлов.
     */
    private final String EXTENSION = ".class";

    /**
     * Массив {@code String}, содержащий имена пакетов,
     * в которых необходимо искать тестовые классы.
     */
    private String[] packageNames;

    /**
     * Конструктор {@code TestRunner_AleksandrZmeyev}.
     * В параметре принимает список имен пакетов, которые необходимо
     * просканировать на наличие тестовых методов.
     *
     * @param packageNames список пакетов для сканирования
     */
    public TestRunner_AleksandrZmeyev(String... packageNames) {
        this.packageNames = packageNames;
    }

    /**
     * Находит тестовые методы и производит их запуск.
     */
    public void run() {
        List<Class<?>> classList = getClassesFromPackages();
        removeAbstractions(classList);

        if (classList.isEmpty()) {
            System.out.println("Classes not found.");
            return;
        }

        for (Class<?> clazz : classList) {
            try {
                List<Method> methods = getTestMethods(clazz);

                if (!methods.isEmpty()) {
                    Object instance = clazz.getConstructor().newInstance();

                    for (Method method : methods) {
                        runTestMethod(method, instance);
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Находит классы из пакетов, указанных в {@code packageNames}.
     *
     * @return список классов
     */
    private List<Class<?>> getClassesFromPackages() {
        List<Class<?>> classList = new ArrayList<>();
        ClassLoader classLoader = this.getClass().getClassLoader();

        for (String packageName : packageNames) {
            URL resource = classLoader.getResource(packageName);
            if (resource == null) {
                throw new IllegalArgumentException("Package named " + packageName + " not found.");
            }

            File file = new File(resource.getFile());
            if (file.isDirectory()) {
                classList.addAll(getClassesFromDirectory(file, ""));
            }
        }

        return classList;
    }

    /**
     * Вспомогательный метод для метода {@code getClassesFromPackages()},
     * который находит классы в пакетах и их подпакетах.
     *
     * @return список классов
     */
    private List<Class<?>> getClassesFromDirectory(File directory, String packageName) {
        List<Class<?>> classList = new ArrayList<>();
        String path;

        if (packageName.isEmpty()) {
            path = directory.getName();
        } else {
            path = packageName + SEPARATOR + directory.getName();
        }

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {

                if (file.isDirectory()) {
                    classList.addAll(getClassesFromDirectory(file, path));

                } else if (file.getName().endsWith(EXTENSION)) {
                    try {
                        int endIndex = file.getName().length() - EXTENSION.length();
                        String className = path + SEPARATOR + file.getName().substring(0, endIndex);

                        classList.add(Class.forName(className));
                    } catch (ClassNotFoundException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }

        return classList;
    }

    /**
     * Находит методы, аннотированные {@code IntensiveTest_AleksandrZmeyev}.
     *
     * @return список методов
     */
    private List<Method> getTestMethods(Class<?> clazz) {
        List<Method> testMethods = new ArrayList<>();

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            IntensiveTest_AleksandrZmeyev annotation =
                    method.getAnnotation(IntensiveTest_AleksandrZmeyev.class);

            if (annotation != null) {
                testMethods.add(method);
            }
        }

        return testMethods;
    }

    /**
     * Запускает тестовые методы.
     */
    private void runTestMethod(Method method, Object instance) {
        System.out.println("Running the test " + method.getName() + ".");
        try {
            method.invoke(instance);
        } catch (InvocationTargetException exception) {
            System.out.println(exception.getCause().getMessage());
        } catch (IllegalAccessException exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println("Test " + method.getName() + " completed successfully.\n");
    }

    /**
     * Удаляет из списка классов абстрактные классы и интерфейсы.
     */
    private void removeAbstractions(List<Class<?>> classes) {
        classes.removeIf((clazz) -> {
            int modifiers = clazz.getModifiers();

            return (Modifier.isAbstract(modifiers) || Modifier.isInterface(modifiers));
        });
    }
}
