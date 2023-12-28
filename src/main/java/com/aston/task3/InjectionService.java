package com.aston.task3;

/**
 * The interface provides functionality injecting dependencies
 */
public interface InjectionService {
    /**
     * This method injects dependency in the specified target object
     *
     * @param rootObject       target object for injecting dependency
     * @param dependencyObject object which inject to target object
     */
    void inject(Object rootObject, Object dependencyObject);
}
