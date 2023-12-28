package task_3;

import task_3.exception.FindComponentException;
import task_3.exception.NoDefaultConstructorException;
import task_3.exception.NoSuchComponentClassException;
import task_3.service.DependencyFinder;
import task_3.service.InjectionService;
import task_3.service.SearchService;
import task_3.service.impl.DependencyFinderImpl;
import task_3.service.impl.InjectionServiceImpl;
import task_3.service.impl.SearchServiceImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IntensiveContext_SlavaSles {

    private final Set<Class<?>> annotatedClassesFromPackage;


    public IntensiveContext_SlavaSles(String packageName) {

        SearchService searchService = new SearchServiceImpl();

        try {
            annotatedClassesFromPackage = searchService.getAnnotatedClassesFromPackage(packageName);
        } catch (IOException | ClassNotFoundException exception) {
            throw new FindComponentException(exception);
        }

    }

    public <T> T getObject(Class<T> type) {
        Map<Class<?>, Object> intensiveContext = new HashMap<>();

        T clazzInstance = null;

        if (!annotatedClassesFromPackage.contains(type)) {
            throw new NoSuchComponentClassException(type);

        } else {
            try {

                DependencyFinder dependencyFactory = new DependencyFinderImpl(annotatedClassesFromPackage);
                Map<Class<?>, Set<Class<?>>> dependenciesForClass = dependencyFactory.findDependencies(type);

                InjectionService injectionService = new InjectionServiceImpl(dependenciesForClass, intensiveContext);
                injectionService.injectDependencies();

                boolean firstDependencyClass = false;
                for (Class<?> clazz : dependenciesForClass.keySet()) {
                    if (firstDependencyClass) {
                        break;
                    }
                    firstDependencyClass = true;
                    clazzInstance = (T) intensiveContext.get(clazz);
                }

            } catch (InstantiationException | IllegalAccessException |
                     InvocationTargetException | NoSuchMethodException exception) {
                throw new NoDefaultConstructorException(exception);
            }
        }
        return clazzInstance;
    }
}
