package task3.service;

import task3.annotation.IntensiveComponent_MaximYasnogorodskiy;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация интерфейса {@link SearchService}
 */
public class SearchServiceImpl implements SearchService {
    private String packageName;

    private List<Class<?>> resultClassesList = new ArrayList<>();

    /**
     * Метод выполняет поиск аннотированных классов из переданного пакета
     * с помощью {@code Files.walkFileTree()}
     * @param packageName имя пакета
     * @return список аннотированных классов
     */
    @Override
    public List<Class<?>> searchAnnotatedClasses(String packageName) {
        assert packageName != null;
        this.packageName = packageName.replaceAll("/", ".");

        Path packagePath = Path.of(packageName);

        try {
            Files.walkFileTree(packagePath, new MyFileVisitor());
        } catch (IOException e) {
            throw new RuntimeException("Не удалось осуществить обход по переданному пути: " + packageName);
        }

        return resultClassesList;
    }


    private class MyFileVisitor extends SimpleFileVisitor<Path> {

        private final Class<? extends Annotation> annotation
                = IntensiveComponent_MaximYasnogorodskiy.class;

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (!file.toString().endsWith(".java")) {
                return FileVisitResult.CONTINUE;
            }

            String fullPathName = file.toString().replaceAll("/", ".");
            String className = fullPathName.replace(packageName + ".", "").replace(".java", "");

            Class<?> classFromClassName;
            try {
                classFromClassName = Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Не удалось загрузить класс: " + className);
            }


            if(classFromClassName.getAnnotation(annotation) != null) {
                resultClassesList.add(classFromClassName);
            }

            return FileVisitResult.CONTINUE;

        }
    }
}
