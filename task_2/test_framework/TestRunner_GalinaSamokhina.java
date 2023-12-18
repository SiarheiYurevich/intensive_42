package test_framework;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс для запуска тестов.
 */
public class TestRunner_GalinaSamokhina {

    /**
     * Запуск тестов в указанном пакете.
     * @param packageName имя пакета, содержащего тесты.
     */
    public void run(String packageName) {

        try {
            List<Class<?>> classes = new ArrayList<>(getClasses(packageName));

            for (Class<?> testClass : classes) {
                Method[] methods = testClass.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(IntensiveTest_GalinaSamokhina.class)) {
                        System.out.println("Running test: " + method.getName());
                        method.invoke(testClass.getDeclaredConstructor().newInstance());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Получение классов в указанном пакете
     * @param packageName имя пакета из которого будем получать классы
     * @return список классов в пакете
     * @throws Exception если при получении классов произошла ошибка
     */
    private List<Class<?>> getClasses(String packageName) throws Exception {

        List<Class<?>> classes = new ArrayList<>();
        String path = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(path);

        if (resource == null) {
            throw new ClassNotFoundException("Package " + packageName + " not found on the classpath");
        }

        try {
            Stream<Path> fileStream = Files.list(Paths.get(resource.toURI()));
            List<Path> files = fileStream.collect(Collectors.toList());

            for (Path file : files) {
                String fileName = file.getFileName().toString();
                if (Files.isDirectory(file)) {
                    classes.addAll(getClasses(packageName + "." + fileName));
                } else if (fileName.endsWith(".class")) {
                    String className = packageName + '.' + fileName.substring(0, fileName.length() - 6);
                    classes.add(ClassLoader.getSystemClassLoader().loadClass(className));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }
}