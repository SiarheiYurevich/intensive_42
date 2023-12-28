package src.main.java.task_3.service;

import src.main.java.task_3.annotation.IntensiveComponent_StanislavFedin;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of a service for scanning packets marked with an annotation {@link IntensiveComponent_StanislavFedin}
 * @author Stanislav Fedin
 */
public class ScanPackageServiceImpl implements ScanPackageService {
    private static final char PKG_SEPARATOR = '.';
    private static final String CLASS_FILE_SUFFIX = ".class";
    private static final String PATH_TO_PACKAGE = "src.main.java.task_3.";

    /**
     * Searches through ClassLoader for all classes marked with an annotation {@link IntensiveComponent_StanislavFedin}
     * @param packageToScan Name of the package in which scanning will be performed
     * @return List of found classes
     */
    @Override
    public List<Class> getAnnotatedClasses(String packageToScan) {
        List<Class> classes = new ArrayList<>();
        String fullPathToPackage = PATH_TO_PACKAGE + packageToScan;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        fullPathToPackage = fullPathToPackage.replace(".", "/");

        URL resource = classLoader.getResource(fullPathToPackage);

        assert resource != null;
        File file = new File(resource.getFile());


        Arrays.stream(Objects.requireNonNull(file.listFiles()))
                .forEach(elementOfArray -> findClassAndAddToList(classes, elementOfArray, PATH_TO_PACKAGE + packageToScan));

        return classes;
    }

    private static void findClassAndAddToList(List<Class> classes, File file, String packageName) {
        String resource = packageName + PKG_SEPARATOR + file.getName();

        if (file.isDirectory()) {
            Arrays.stream(Objects.requireNonNull(file.listFiles()))
                    .forEach(innerFile -> findClassAndAddToList(classes, innerFile, resource));
        } else if (resource.endsWith(CLASS_FILE_SUFFIX)) {

            int classPathLength = resource.length() - CLASS_FILE_SUFFIX.length();
            String className = resource.substring(0, classPathLength);

            try {
                Class clazz = Class.forName(className);
                if (isAnnotated(clazz))
                    classes.add(Class.forName(className));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Cannot get the Class object for " + className + " " + e);
            }
        }
    }

    private static boolean isAnnotated(Class clazz) {
        return clazz.isAnnotationPresent(IntensiveComponent_StanislavFedin.class);
    }
}
