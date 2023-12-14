package task2;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * Класс, отвечающий за обработку аннотации @IntensiveTest_LevDurov.
 */
public class TestRunner_LevDurov {

    /**
     * Список пакетов, внутри которых будет обрабатываться аннтоция.
     */
    List<String> packagesList;

    /**
     * Создает объект класса.
     * @param packagesList Список пакетов, с которыми будет работать класс.
     */
    public TestRunner_LevDurov(List<String> packagesList) {
        this.packagesList = packagesList;
    }

    /**
     * Запускает все аннотированные методы.
     */
    public void run() {
        for (String pack: packagesList) {
            runForPackage(pack);
        }
    }

    /**
     * Запускает все аннотированные методы в конкретном пакете.
     * @param packageName Пакет.
     */
    private void runForPackage(String packageName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources;
        try {
            resources = classLoader.getResources(packageName);
            Iterable<URL> urls = resources::asIterator;
            List<File> dirs = new ArrayList<>();
            for (URL url: urls) {
                dirs.add(new File(url.toURI().getPath()));
            }
            List<Class> classes = dirs.stream()
                    .flatMap((File d) -> findClasses(d, packageName).stream())
                    .toList();

            for (Class cl: classes) {
                for (Method method: cl.getMethods()) {
                    if (method.isAnnotationPresent(IntensiveTest_LevDurov.class))
                    {
                        method.invoke(cl);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("В task2.TestRunner_LevDurov.run() возникла ошибка.");
            e.printStackTrace();
        }
    }

    /**
     * Составляет и возвращает список файлов *.class.
     * @param dir Сканируемая директория.
     * @param packegeName Сканируемый пакет.
     * @return Список файлов-классов.
     */
    private List<Class> findClasses(File dir, String packegeName) {
        if (!dir.exists()) {
            return Collections.emptyList();
        }

        List<Class> classes = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files == null) {
            return Collections.emptyList();
        }

        for (File file: files) {
            String fileName = file.getName();
            if (file.isDirectory()){
                classes.addAll(findClasses(file, packegeName + "." + fileName));
            } else if (fileName.endsWith(".class")) {
                try {
                    classes.add(Class.forName((packegeName
                            + "."
                            + fileName.substring(0, fileName.length() - 6)).substring(1)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return classes;
    }
}
