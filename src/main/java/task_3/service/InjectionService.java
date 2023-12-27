package src.main.java.task_3.service;

import java.util.Map;

public interface InjectionService {
    void inject(Object object, Map<String, Class> annotatedClasses);
}
