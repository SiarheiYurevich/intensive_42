package org.example.context;

import org.example.services.factory.DependencyFactory;
import org.example.services.factory.DependencyFactoryImpl;
import org.example.services.search.SearchService;
import org.example.services.search.SearchServiceImpl;

import java.util.HashMap;
import java.util.List;

public class IntensiveContext_TimurAgeevImpl implements IntensiveContext_TimurAgeev {
    private final SearchService searchService = new SearchServiceImpl();
    private final DependencyFactory dependencyFactory = new DependencyFactoryImpl();
    private final String packageName;
    private final HashMap<String, Object> beansHashMap = new HashMap<>();

    public IntensiveContext_TimurAgeevImpl(String packageName) {
        this.packageName = packageName;
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> type) {
        List<Class<?>> classes = searchService.findClasses(packageName);

        for (Class<?> clazz : classes) {
            if (clazz.equals(type)) {
                String className = clazz.getName();

                if (beansHashMap.containsKey(className)) {
                    return (T) beansHashMap.get(className);
                }

                T instance = (T) dependencyFactory.createInstance(type);
                beansHashMap.put(className, instance);

                return instance;
            }
        }

        return null;
    }
}
