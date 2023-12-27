package task_3;

import task_3.exception.FindComponentException;
import task_3.exception.NoDefaultConstructorException;
import task_3.exception.NoSuchComponentClassException;
import task_3.service.DepedencyFactory;
import task_3.service.SearchService;
import task_3.service.impl.DependencyFactoryImpl;
import task_3.service.impl.SearchServiceImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class IntensiveContext_SlavaSles {
//    private String packageName;
    private final Map<Class<?>, String> annotatedClassesFromPackage;

    public IntensiveContext_SlavaSles(String packageName) {
//        this.packageName = packageName;
        SearchService searchService = new SearchServiceImpl();
        try {
            annotatedClassesFromPackage = searchService.getAnnotatedClassesFromPackage(packageName);
        } catch (IOException | ClassNotFoundException exception) {
            throw new FindComponentException(exception);
        }

    }

    public <T> T getObject(Class<T> type) {
        T clazz = null;

        if (annotatedClassesFromPackage.containsKey(type)) {

            try {

                DepedencyFactory dependencyFactory = new DependencyFactoryImpl(annotatedClassesFromPackage);
                Class<T> implementation = (Class<T>) dependencyFactory.findDependencies(type);
                clazz = implementation.getConstructor().newInstance();

            } catch (InstantiationException | IllegalAccessException |
                     InvocationTargetException | NoSuchMethodException exception) {
                throw new NoDefaultConstructorException(exception);
            }
        } else {
            throw new NoSuchComponentClassException(type);
        }
        return clazz;
    }
}
