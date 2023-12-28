package src.main.java.task_3.service;

import java.util.Map;

/**
 * An interface that provides the ability to install dependencies into instances.
 * @author Stanislav Fedin
 */
public interface InjectionService {
    /**
     * Depending on the implementation, provides the ability to install
     * dependencies marked with an annotation into the passed object.
     * @param object Instance into which dependencies are injected
     * @param annotatedClasses List of annotated classes
     */
    void inject(Object object, Map<String, Class> annotatedClasses);
}
