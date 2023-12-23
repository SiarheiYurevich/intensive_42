package org.example;

import org.example.services.factory.DependencyFactory;
import org.example.services.factory.DependencyFactoryImpl;
import org.example.services.injection.InjectionService;
import org.example.services.injection.InjectionServiceImpl;
import org.example.services.search.SearchService;
import org.example.services.search.SearchServiceImpl;

import java.util.List;

public class IntensiveContext_TimurAgeev {
    private InjectionService injectionService = new InjectionServiceImpl();
    private SearchService searchService = new SearchServiceImpl();
    private DependencyFactory dependencyFactory = new DependencyFactoryImpl();
    private String packageName;

    private List<Class<?>> classes;

    public IntensiveContext_TimurAgeev(String packageName) {
        this.packageName = packageName;
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> type) {
        classes = searchService.findClasses(packageName);

        for (Class<?> clazz : classes) {
            if (clazz.equals(type)) {
                return (T) dependencyFactory.createInstance(type);
            }
        }

        return null;
    }
}
