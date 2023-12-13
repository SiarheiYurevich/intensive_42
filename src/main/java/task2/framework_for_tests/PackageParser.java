package framework_for_tests;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


/**
 * a class for searching for classes in specified packages and
 * under packages. Uses recursion to traverse directories.
 * The API returns an array of classes in the specified package
 */
public class PackageParser {

    /**
     This method retrieves an array of classes in a given package.
     It takes a String parameter packageName which represents the
     name of the package to scan for classes. The method uses the
     current thread's class loader to get the resources (directory URLs)
     for the given package. It then iterates through each directory and calls
     the findClasses method to find the classes in the directory.
     The method returns an array of Class objects representing the classes
     found in the package. If a class cannot be found or loaded, a ClassNotFoundException
     is thrown. If an I/O error occurs while scanning the package, an IOException is thrown.
     * @param packageName the package that the search starts with
     * @return an array with all classes found in packages and subpackages
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }
    /**
     This method recursively searches a given directory and
     its subdirectories for class files. It returns a list
     of Class objects representing the found classes.
     The method takes a File object representing the directory
     to search and a String representing the base package name
     for the classes found. If the directory doesn't exist, an
     empty list is returned. If a class cannot be found or loaded,
     a ClassNotFoundException is thrown.
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
