package task_2;

import java.io.File;

import java.lang.reflect.Method;

import java.net.URL;
import java.util.ArrayList;

import java.util.Enumeration;
import java.util.List;


/**
 * Класс {@code TestRunner_TatyanaSharova} сканирует указанные пакеты на наличие классов, методы которых отмечены
 * аннотацией {@link IntensiveTest_TatyanaSharova}, после чего запускает данные методы
 *
 * @Author Tatyana Sharova
 */
public class TestRunner_TatyanaSharova {
    private List<String> packagesList;

    public TestRunner_TatyanaSharova(List<String> packagesList) {
        this.packagesList = packagesList;
    }

    /**
     * Основной метод для запуска сканирования пакетов на наличие классов
     */
    public void run() {
        for (String pack : packagesList) {
            manualScan(pack);
        }

    }

    /**
     * Сканирует все классы, доступные из контекстного загрузчика классов, которые принадлежат данному пакету и подпакетам
     *
     * @param packageName - сканируемый пакет
     */
    private static void manualScan(String packageName) {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        Enumeration<URL> resources = null;
        try {
            resources = classLoader.getResources(path);
            Iterable<URL> urls = resources::asIterator;
            List<File> dirls = new ArrayList<>();
            for (URL url : urls) {
                dirls.add(new File(url.toURI().getPath()));
            }
            List<Class> classes = dirls.stream()
                    .flatMap((File d) -> findClasses(d, packageName).stream())
                    .toList();


            for (Class cls : classes) {

                for (Method method : cls.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(IntensiveTest_TatyanaSharova.class)) {
                        method.invoke(cls.getDeclaredConstructor().newInstance());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Рекурсивный метод, используемый для поиска всех классов в заданном каталоге и вложенных каталогах
     *
     * @param directory   - cканируемая директория
     * @param packageName - cканируемый пакет
     * @return список файлов-классов
     */
    private static List<Class> findClasses(File directory, String packageName) {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }


        File[] files = directory.listFiles();
        if (files == null) {
            return classes;
        }

        for (File file : files) {
            String fileName = file.getName();
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + fileName));
            } else if (fileName.endsWith(".class")) {
                try {
                    String className = packageName + "." + fileName.substring(0, fileName.length() - 6);
                    className = className.startsWith(".")? className.substring(1) : className;
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return classes;
    }
}
