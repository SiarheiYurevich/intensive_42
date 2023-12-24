package service;

import java.util.Map;

/**
 * This interface is needed to embed dependencies into an object
 */
public interface InjectionService {

    /**
     * the method implements dependencies in the Map of the passed object
     * @param object the object to embed dependencies in
     * @param beans which stores (The class of the object in the string representation and its object)
     */
    void injectDependency(Object object, Map<String, Object> beans);

}
