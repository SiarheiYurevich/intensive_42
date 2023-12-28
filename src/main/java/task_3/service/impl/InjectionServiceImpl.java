package task_3.service.impl;

import task_3.IntensiveComponent_SlavaSles;
import task_3.service.InjectionService;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

public class InjectionServiceImpl implements InjectionService {

    private final Map<Class<?>, Set<Class<?>>> dependenciesForClass;
    private final Map<Class<?>, Object> intensiveContext;

    public InjectionServiceImpl(Map<Class<?>, Set<Class<?>>> dependenciesForClass, Map<Class<?>,
            Object> intensiveContext) {
        this.dependenciesForClass = dependenciesForClass;
        this.intensiveContext = intensiveContext;
    }

    @Override
    public void injectDependencies() throws InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException {

        while (intensiveContext.size() != dependenciesForClass.size()) {

            createObjects();

        }
    }

    private void createObjects() throws InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        for (Class<?> injectionClass : dependenciesForClass.keySet()) {

            if (dependenciesForClass.get(injectionClass).isEmpty()) {
                Object object = createComponentInstanceWithoutDependencies(injectionClass);
                intensiveContext.put(injectionClass, object);

            } else if (intensiveContext.keySet().containsAll(dependenciesForClass.get(injectionClass)) ) {
                Object object = createComponentInstanceWithDependencies(injectionClass);
                intensiveContext.put(injectionClass, object);
            }
        }
    }

    private Object createComponentInstanceWithoutDependencies(Class<?> injectionClass) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        Object object = injectionClass.getConstructor().newInstance();
        return object;
    }

    private Object createComponentInstanceWithDependencies(Class<?> injectionClass) throws InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        Object object = createComponentInstanceWithoutDependencies(injectionClass);

        Field[] fields = injectionClass.getDeclaredFields();
        for (Field field : fields) {

            Class<?> fieldClass = field.getType();

            if (fieldClass.isAnnotationPresent(IntensiveComponent_SlavaSles.class)) {
                field.setAccessible(true);
                field.set(object, intensiveContext.get(fieldClass));
            }

        }
        return object;
    }
}
