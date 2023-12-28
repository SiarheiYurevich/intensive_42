package task_3;

import task_3.exception.FindComponentException;
import task_3.exception.NoDefaultConstructorException;
import task_3.exception.NoSuchComponentClassException;
import task_3.service.DepedencyFinder;
import task_3.service.SearchService;
import task_3.service.impl.DependencyFinderImpl;
import task_3.service.impl.SearchServiceImpl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IntensiveContext_SlavaSles {
//    private String packageName;
    private final Map<Class<?>, String> annotatedClassesFromPackage;


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

        if (!annotatedClassesFromPackage.containsKey(type)) {
            throw new NoSuchComponentClassException(type);

        } else {
            try {

                DepedencyFinder dependencyFactory = new DependencyFinderImpl(annotatedClassesFromPackage);
                Map<Class<?>, Set<Class<?>>> dependenciesForClass = dependencyFactory.findDependencies(type);

                while (intensiveContext.size() != dependenciesForClass.size()) {
                    for (Class<?> injectionClass : dependenciesForClass.keySet()) {
                        if (dependenciesForClass.get(injectionClass).isEmpty()) {
                            Object object = createComponentInstanceWithoutDependencies(injectionClass);
                            intensiveContext.put(injectionClass, object);
                        } else if (intensiveContext.keySet().containsAll(dependenciesForClass.get(injectionClass)) ) {
                            Object object = createComponentInstanceWithoutDependencies(injectionClass);

                            Field[] fields = injectionClass.getDeclaredFields();

                            for (Field field : fields) {

                                Class<?> fieldClass = field.getType();
                                if (fieldClass.isAnnotationPresent(IntensiveComponent_SlavaSles.class)) {
                                    field.setAccessible(true);
                                    field.set(object, intensiveContext.get(fieldClass));
                                }

                            }
                            intensiveContext.put(injectionClass, object);
                        }
                    }
                }
                boolean flag = false;
                for (Class<?> clazz : dependenciesForClass.keySet()) {
                    if (flag) {
                        break;
                    }
                    flag = true;
                    clazzInstance = (T) intensiveContext.get(clazz);
                }
//                Class<T> implementation = (Class<T>) dependencyFactory.findDependencies(type);
//                clazzInstance = implementation.getConstructor().newInstance();

            } catch (InstantiationException | IllegalAccessException |
                     InvocationTargetException | NoSuchMethodException exception) {
                throw new NoDefaultConstructorException(exception);
            }
        }
        return clazzInstance;
    }

    private Object createComponentInstanceWithoutDependencies(Class<?> injectionClass) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        Object object = injectionClass.getConstructor().newInstance();
        return object;
    }
}
