import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * <h4>Helper class for finding all project packages.</h4>
 * <br>
 * <p>The class scans the project using the system ClassLoader
 * and tries to initialize the found classes to obtain information
 * about the package in which they are located.
 * @author Stanislav Fedin
 */
public class ReflectionUtil {
    private static final String CLASS_FILE_SUFFIX = ".class";

    /**
     * Scans a project and returns set of packages found.
     * @return set of <code>Package</code>
     * @throws IOException If I/O errors occur
     */
    public static Set<Package> findPackage() throws IOException {
        Enumeration<URL> urls = ClassLoader.getSystemClassLoader().getResources("");
        URL scannedUrl = urls.nextElement();

        File[] files = new File(scannedUrl.getFile()).listFiles();

        Set<Package> set = new HashSet<>();

        tryToLoadPackage(set, files);

        return set;
    }

    private static void tryToLoadPackage(Set<Package> set, File[] files) {
        Arrays.stream(files).filter(file -> !file.getName().contains(".idea")).forEach(file -> {
            String fromSourceRoot = "";

            if (file.isDirectory()) {
                fromSourceRoot = file.getName();
                tryToLoadPackage(set, file.listFiles(), fromSourceRoot);
            } else if (file.getName().endsWith(CLASS_FILE_SUFFIX)) {
                loadClassAndAddPackage(set, file, fromSourceRoot);
            }
        });

    }

    private static void tryToLoadPackage(Set<Package> set, File[] files, String fromSourceRoot) {

        Arrays.stream(files).forEach(file -> {
            if (file.isDirectory()) {
                String newFromSourceRoot = fromSourceRoot + "." + file.getName();
                tryToLoadPackage(set, file.listFiles(), newFromSourceRoot);
            } else if (file.getName().endsWith(CLASS_FILE_SUFFIX)) {
                loadClassAndAddPackage(set, file, fromSourceRoot);
            }
        });
    }

    private static void loadClassAndAddPackage(Set<Package> set, File file, String fromSourceRoot) {
        try {
            int trimLengthFileName = file.getName().length() - CLASS_FILE_SUFFIX.length();

            String pathClassName = fromSourceRoot + "." + file.getName().substring(0, trimLengthFileName);

            Class<?> cls = Class.forName(pathClassName);

            set.add(cls.getPackage());
        } catch (ClassNotFoundException ignored) {
        }
    }
}
