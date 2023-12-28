package src.main.java.task_3.service;

import java.util.Map;

/**
 * Interface for searching for implementations of passed interfaces.
 * @author Stanislav Fedin
 */
public interface SearchClassService {
    /**
     * Searches for implementations of the passed interface.
     * @param interfaceClass Interface whose implementations you need to look for
     * @param annotatedClasses List of annotated classes
     * @return Implementation of the specified interface
     * @throws RuntimeException Interface has 0 or more than one implementation
     */
    <T> Class<T> getImplClass(Class<T> interfaceClass, Map<String, Class> annotatedClasses);
}
