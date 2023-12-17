package task2;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Stream;

/**
 * Класс, реализующий логику посещения файла для метода {@code Files.walkFileTree()}
 */
public class FileVisitorImpl extends SimpleFileVisitor<Path> {

    /**
     * Метод, исполняющийся при посещении файла.
     * @param file Путь к файлу. Преобразуется в бинарный формат для
     *             загрузки класса. Информация о пакете, в котором содержится класс,
     *             берется из самого файла.
     * @param attrs атрибуты файла.
     *
     * @return результат посещения файла.
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        Objects.requireNonNull(file);
        Objects.requireNonNull(attrs);

        if(!file.toString().endsWith(".java")) {
            return FileVisitResult.CONTINUE;
        }


        Optional<String> packageString = getPackageString(file);
        String className = file.getFileName().toString();

        String binaryClassName = packageString
                .map(s -> getBinaryClassName(s, className))
                .orElse(className.substring(0, className.lastIndexOf(".")));

        Class<?> currentClass;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try {
            currentClass = classLoader.loadClass(binaryClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Не удалось загрузить класс из файла: " + file);
        }

        for (Method m : currentClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(IntensiveTest_MaximYasnogorodskiy.class)) {
                try {
                    m.invoke(currentClass.getDeclaredConstructor().newInstance());
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException |
                         NoSuchMethodException e) {
                    throw new RuntimeException("Не удалось запустить метод: " + m.getName());
                }
            }
        }

        return FileVisitResult.CONTINUE;
    }


    private Optional<String> getPackageString(Path path) {
        Optional<String> packageString = Optional.empty();
        try (Stream<String> lines = Files.lines(path)) {
            packageString = lines
                    .filter(string -> string.contains("package"))
                    .filter(string -> !string.contains("//"))
                    .findFirst();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла");
        }
        return packageString;
    }

    private String getBinaryClassName(String packageFullString, String className) {
        String packageName = packageFullString
                        .substring(packageFullString.indexOf(" "), packageFullString.indexOf(";"))
                        .trim();

        return packageName + "." + className.substring(0, className.lastIndexOf("."));
    }
}
