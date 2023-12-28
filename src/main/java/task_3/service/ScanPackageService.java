package src.main.java.task_3.service;

import java.util.List;

/**
 * Interface for searching for annotated classes in a passed package.
 * @author Stanislav Fedin
 */
public interface ScanPackageService {
    /**
     * Searches through ClassLoader for all classes marked with an annotation
     * @param packageToScan Name of the package in which scanning will be performed
     * @return List of found classes
     */
    List<Class> getAnnotatedClasses(String packageToScan);
}
