package task_3.service.impl;

import task_3.IntensiveComponent_SlavaSles;
import task_3.exception.NoSuchImplementationException;
import task_3.exception.NoUniqueImplementationException;
import task_3.service.DependencyFinder;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class DependencyFinderImpl implements DependencyFinder {
    Map<Class<?>, Set<Class<?>>> dependenciesForClass = new LinkedHashMap<>();
    Set<Class<?>> annotatedClassesFromPackage;

    public DependencyFinderImpl(Set<Class<?>> annotatedClassesFromPackage) {
        this.annotatedClassesFromPackage = annotatedClassesFromPackage;
    }

    public Map<Class<?>, Set<Class<?>>> findDependencies(Class<?> type) {

        Class<?> requiredClass = type;
        Set<Class<?>> dependenciesSet = new HashSet<>();

        if (type.isInterface()) {
            requiredClass = getImplementation(type);
        }

        Field[] fields = requiredClass.getDeclaredFields();

        dependenciesForClass.put(requiredClass, dependenciesSet);

        for (Field field : fields) {

            Class<?> fieldClass = field.getType();
            if (fieldClass.isAnnotationPresent(IntensiveComponent_SlavaSles.class)) {
                dependenciesSet.add(fieldClass);
            }

        }

        if (!dependenciesSet.isEmpty()) {
            for (Class<?> dependencyClass : dependenciesSet) {
                findDependencies(dependencyClass);
            }
        }

        return dependenciesForClass;
    }

    private Class<?> getImplementation(Class<?> type) {
        int implCount = 0;

        Class<?> implementationClass = null;

        for (Class<?> clazz : annotatedClassesFromPackage) {

//            Интерфейс тоже может имплементировать нужный нам интерфейс, а тот в свою очередь иметь реализацию!
            if (!clazz.isInterface()) {

                Class<?> implementation = checkImplementation(clazz, type);

                if (implementation != null) {
                    implementationClass = implementation;
                    implCount++;
                }
            }
        }

        if (implCount == 0) {
            throw new NoSuchImplementationException(type);

        } else if (implCount == 1) {
            return implementationClass;

        } else {
            throw new NoUniqueImplementationException(type);
        }
    }

    private Class<?> checkImplementation(Class<?> clazz, Class<?> type) {
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> classInterface : interfaces) {

            if (classInterface.getName().equals(type.getName())) {
                return clazz;
            }
        }
        return null;
    }
}
