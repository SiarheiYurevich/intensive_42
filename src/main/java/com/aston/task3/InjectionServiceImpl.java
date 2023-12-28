package com.aston.task3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * The class there is implementation of interface {@code InjectionService}
 * and implements functionality of injecting dependencies.
 */
public class InjectionServiceImpl implements InjectionService {

    /**
     * This method injects dependency in the specified target object.
     *
     * @param rootObject       target object for injecting dependency
     * @param dependencyObject object which inject to target object
     */
    @Override
    public void inject(Object rootObject, Object dependencyObject) {
        String setterName = "set" + dependencyObject.getClass().getSimpleName().substring(0, 1).toUpperCase()
                            + dependencyObject.getClass().getSimpleName().substring(1);

        try {
            Method setter = rootObject.getClass().getMethod(setterName, dependencyObject.getClass());
            setter.invoke(rootObject, dependencyObject);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Exception when injecting dependency " + dependencyObject + " into an object " + rootObject);
        }
    }
}
