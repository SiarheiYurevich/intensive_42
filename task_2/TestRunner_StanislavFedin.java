package task_2;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Scans the specified packages for methods annotated with the <code>@IntensiveTest_StanislavFedin</code>
 * annotation and performs test validation.
 * @author Stanislav Fedin
 */
public class TestRunner_StanislavFedin {

    private static final char PKG_SEPARATOR = '.';
    private static final String CLASS_FILE_SUFFIX = ".class";

    /**
     * Accepts an array of packages that will search
     * for classes with static methods marked with the <code>@IntensiveTest_StanislavFedin</code> annotation,
     * and then invoke these methods.
     * <br><br>
     * <p>Note: A method marked with an annotation must not accept arguments
     * @param packages Packages in which scanning will be performed
     *
     */
    public static void testRunner(List<Package> packages){
        List<Class<?>> classes = scannedClasses(packages);

        classes.forEach(cls -> Arrays.stream(cls.getDeclaredMethods())
                .forEachOrdered(md -> {
                    if (md.isAnnotationPresent(IntensiveTest_StanislavFedin.class)) {
                        try {
                            /*
                            For now, a method call without parameters has been implemented
                             */
                            switch (md.getParameterCount()) {
                                case 0 -> md.invoke(null);
                            }
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            System.out.println(e.getMessage());
                            System.out.printf("Test %s has been failed", md.getName());
                        }
                    }
                }));
    }

    private static List<Class<?>> scannedClasses(List<Package> packages) {
        List<Class<?>> classes = new ArrayList<>();

        for (Package pak : packages) {
            URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(pak.getName());

            if (scannedUrl == null) continue;

            File scannedDir = new File(scannedUrl.getFile());

            Arrays.stream(Objects.requireNonNull(scannedDir.listFiles()))
                    .forEach(file -> findAndAdd(classes, file, pak.getName()));
        }
        return classes;
    }

    private static void findAndAdd(List<Class<?>> classes, File file, String packageName) {
        String resource = packageName + PKG_SEPARATOR + file.getName();

        if (file.isDirectory()) {
            Arrays.stream(Objects.requireNonNull(file.listFiles()))
                    .forEach(innerFile -> findAndAdd(classes, innerFile, resource));
        } else if (resource.endsWith(CLASS_FILE_SUFFIX)) {

            int classPathLength = resource.length() - CLASS_FILE_SUFFIX.length();
            String className = resource.substring(0, classPathLength);

            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException ignored) {
            }
        }
    }

}
