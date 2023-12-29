package service.impl;

import service.SearchService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import java.util.Enumeration;
import java.util.List;

/**
 * Класс SearchServiceImpl является реализацией интерфейса SearchService.
 * Он предоставляет методы для поиска классов в указанном пакете.
 */
public class SearchServiceImpl implements SearchService {
    /**
     * Поле packageName представляет имя пакета для поиска.
     */
    private final String packageName;

    /**
     * Конструктор для создания нового объекта SearchServiceImpl с указанным именем пакета.
     *
     * @param packageName имя пакета для поиска
     */

    public SearchServiceImpl(String packageName) {
        this.packageName = packageName;
    }

    /**
     * Возвращает список классов, найденных в указанном пакете.
     *
     * @return список классов, найденных в пакете
     * @throws IOException            если произошла ошибка ввода-вывода при поиске классов
     * @throws ClassNotFoundException если класс не может быть найден
     */

    public List<Class<?>> getClasses() throws IOException, ClassNotFoundException {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }
    /**
     * Рекурсивно ищет классы в указанной директории и пакете.
     *
     * @param directory директория для поиска классов
     * @param packageName имя пакета для поиска
     * @return список классов, найденных в указанной директории и пакете
     * @throws ClassNotFoundException если класс не может быть найден
     */

    private List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);

                classes.add(Class.forName(className));

            }
        }
        return classes;
    }
}