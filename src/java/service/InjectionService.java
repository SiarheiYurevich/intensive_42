package service;

import java.util.Map;

public interface InjectionService {
    void injectDependency(Object object, Map<String, Object> beans);
}
