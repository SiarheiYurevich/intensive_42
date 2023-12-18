package task_2;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;

/**
 * Класс {@code TestRunner_MaximBulychev} производит сканирование указанных
 * пакетов и их подпакетов на наличие классов, методы которых отмечены
 * аннотацией {@link IntensiveTest_MaximBulychev}, после чего запускает данные методы.
 * Класс, содержащий тестовые методы, обязательно должен иметь конструктор без аргументов,
 * а сами тестовые методы должны быть {@code public} и не {@code static}.
 *
 * @author Максим Булычев
 */
public class TestRunner_MaximBulychev {

    static final String PACKAGE_SEPARATOR = ".";
    static final String CLASS_FILE_EXTENSION = ".class";
    private List<String> packagesToScan;

    /**
     * Конструктор класса {@link TestRunner_MaximBulychev} по-умолчанию.
     */
    public TestRunner_MaximBulychev() {
        this.packagesToScan = new ArrayList<>();
    }

    /**
     * Данный конструктор принимает имена пакетов, которые необходимо просканировать
     * на наличие тестовых методов.
     *
     * @param packagesToScan имена пакетов для сканирования
     */
    public TestRunner_MaximBulychev(String ... packagesToScan) {
        this.packagesToScan = new ArrayList<>();
        this.packagesToScan.addAll(List.of(packagesToScan));
    }

    /**
     * Задаёт пакеты, которые необходимо просканировать.
     *
     * @param packagesToScan имена пакетов для сканирования
     */
    public void setPackagesToScan(String ... packagesToScan) {
        this.packagesToScan.clear();
        this.packagesToScan.addAll(List.of(packagesToScan));
    }

    /**
     * Добавляет в список пакетов для сканирования ещё пакеты.
     *
     * @param packagesToScan имена пакетов для сканирования
     */
    public void addPackagesToScan(String ... packagesToScan) {
        this.packagesToScan.addAll(List.of(packagesToScan));
    }

    /**
     * Запускает тестовые методы.
     */
    public void run() {
        List<Class<?>> classes = scanPackages();
        for (Class<?> clazz : classes) {
            try {
                List<Method> annotatedMethods = getTestAnnotatedMethods(clazz);
                if (!annotatedMethods.isEmpty()) {
                    Object instance = clazz.getConstructor().newInstance();
                    for (Method method : annotatedMethods) {
                        runTestAndShowResult(method, instance);
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Сканирует указанные пакеты и их подпакеты на наличие классов, которые не являются
     * {@code abstract} или {@code interface}.
     *
     * @return список классов
     */
    private List<Class<?>> scanPackages() {
        List<Class<?>> classes = new ArrayList<>();
        for (String packageToScan : packagesToScan) {
            classes.addAll(getClasses(packageToScan));
        }
        removeNotInstantiatable(classes);
        return classes;
    }

    /**
     * Сканирует указанный пакет на наличие классов и добавляет их в список.
     *
     * @param packageToScan имя пакета для сканирования
     * @return список классов
     * @throws IllegalArgumentException в случае, если пакет с указанным именем не существует
     */
    private List<Class<?>> getClasses(String packageToScan) {
        URL directoryURL = ClassLoader.getSystemResource(packageToScan);
        if (directoryURL == null) {
            throw new IllegalArgumentException("No package with name " + packageToScan + " found.");
        }

        List<Class<?>> classes = new ArrayList<>();
        File dir = new File(directoryURL.getFile());
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                classes.addAll(getClasses(file, packageToScan));
            }
        }
        return classes;
    }

    /**
     * Проверяет указанный {@code File}, и если он является директорией, рекурсивно
     * вызывает её сканирование. Если же {@code File} оказывается файлом с расширением
     * .class, добавляет его в список.
     *
     * @param file проверяемый файл
     * @param packageToScan имя сканируемой директории
     * @return список классов
     */
    private List<Class<?>> getClasses(File file, String packageToScan) {
        List<Class<?>> classes = new ArrayList<>();
        String resource = packageToScan + PACKAGE_SEPARATOR + file.getName();

        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                classes.addAll(getClasses(child, resource));
            }
        } else if (resource.endsWith(CLASS_FILE_EXTENSION)) {
            int endIndex = resource.length() - CLASS_FILE_EXTENSION.length();
            String className = resource.substring(0, endIndex);
            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException exception) {
                // Класс обязательно будет найден.
            }
        }
        return classes;
    }

    /**
     * Удаляет из списка классов абстрактные классы и интерфейсы.
     *
     * @param classes полный список классов в отсканированных пакетах
     */
    private void removeNotInstantiatable(List<Class<?>> classes) {
        classes.removeIf(a -> !checkModifiers(a));
    }

    /**
     * Проверяет класс на отсутствие модификаторов {@code abstract} и {@code interface}.
     *
     * @param clazz проверяемый класс
     * @return {@code true}, если класс не является абстрактным или интерфейсом, иначе {@code false}
     */
    private boolean checkModifiers(Class<?> clazz) {
        int modifiers = clazz.getModifiers();
        return !(Modifier.isInterface(modifiers) ||
                Modifier.isAbstract(modifiers));
    }

    /**
     * Проверяет методы указанного класса. Если находит метод, отмеченный аннотацией
     * {@link IntensiveTest_MaximBulychev}, добавляет его в список.
     *
     * @param clazz класс, методы которого проверяются
     * @return список тестовых методов
     */
    private List<Method> getTestAnnotatedMethods(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        ArrayList<Method> annotatedMethods = new ArrayList<>();

        for (Method method : methods) {
            IntensiveTest_MaximBulychev annotation = method.getAnnotation(
                    IntensiveTest_MaximBulychev.class);
            if (annotation != null) {
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }

    /**
     * Запускает переданный тестовый метод и выводит информацию о выполнении теста.
     *
     * @param method тестовый метод
     * @param instance экземпляр класса, обладающий данным тестовым методом
     */
    private void runTestAndShowResult(Method method, Object instance) {
        System.out.println("Starting test " + method.getName() + "...");
        try {
            method.invoke(instance);
            System.out.println("Test: OK");

        } catch (InvocationTargetException exception) {
            System.out.println(exception.getCause().getMessage());

        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
        }
        System.out.println("Test " + method.getName() + " completed.\n");
    }
}
