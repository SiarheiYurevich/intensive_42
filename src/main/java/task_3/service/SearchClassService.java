package src.main.java.task_3.service;

import java.util.Map;

public interface SearchClassService {
    <T> Class<T> getImplClass(Class<T> interfaceClass, Map<String, Class> annotatedClasses);
}
