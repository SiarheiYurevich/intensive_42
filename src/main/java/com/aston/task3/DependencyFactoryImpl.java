package com.aston.task3;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

/**
 * This class there is implementation of interface {@code DependencyFactory}
 * and implements functionality creating instances classes
 * which marked annotation {@code IntensiveComponent_VitaliBoshko}.
 */
public class DependencyFactoryImpl implements DependencyFactory {
    private final Map<String, Object> objectFactory = new HashMap<>();
    private final InjectionService injectionService = new InjectionServiceImpl();

    /**
     * This method creates objects of specified type if it or implementation contains in list {@code classes}.
     *
     * @param type    object which need to create if it or implementation contains in list {@code classes}
     * @param classes list objects of type {@code Class<?>} classes which marked annotation {@code IntensiveComponent_VitaliBoshko}.
     * @return array of objects of type {@code Object} which were created by this factory
     * @throws Exception if there is more than one implementation of the class requested
     *                   from the context {@code IntensiveContext_VitaliBoshko} or if there is no implementation
     */
    public Object[] createObject(Class<?> type, List<Class<?>> classes) throws Exception {
        Object instance = null;
        int count = 0;

        for (Class<?> clazz : classes) {
            if (clazz.getName().equals(type.getName()) || type.isAssignableFrom(clazz)) {
                try {
                    instance = clazz.getConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException("Exception when creating an instance of an object of the specified type " + type.getName());
                }
                if (!isNull(instance)) {
                    count++;
                }
            }
        }

        if (count == 1) {
            objectFactory.put(instance.getClass().getSimpleName(), instance);
            recursionFindFieldDependency(instance, classes);
            return objectFactory.values().toArray();
        } else if (count > 1) {
            throw new Exception("There are more than one implementation of type " + type.getName());
        }
        throw new Exception("Implementation of type " + type.getName() + " doesn't exist");
    }

    private void recursionFindFieldDependency(Object rootObject, List<Class<?>> classes) {
        Field[] fields = rootObject.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (classes.contains(field.getType())) {
                Object instanceField;
                try {
                    instanceField = field.getType().getConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException("Exception when recursion looked for dependencies for object " + rootObject);
                }

                recursionFindFieldDependency(instanceField, classes);

                objectFactory.put(field.getType().getSimpleName(), instanceField);
                injectionService.inject(rootObject, instanceField);
            }
        }
    }
}
