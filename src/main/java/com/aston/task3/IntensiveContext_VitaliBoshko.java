package com.aston.task3;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

/**
 * This class provide functional creating saving and receiving objects on request.
 */
public class IntensiveContext_VitaliBoshko {
    private Map<String, Object> mapContext = new HashMap<>();
    private final SearchService searchService = new SearchServiceImpl();
    private final DependencyFactory dependencyFactory = new DependencyFactoryImpl();
    private final String path;

    /**
     * This constructor creates an instance of the class {@code IntensiveContext_VitaliBoshko}
     * and initializes the class variable with the string value of the path of the scanned package.
     *
     * @param path the string value of the path of the scanned package
     */
    public IntensiveContext_VitaliBoshko(String path) {
        this.path = path;
    }

    /**
     * The method checks there is object of type {@code type} in context and creates new instance if
     * object specified of type there isn't in context.
     *
     * @param type of argument declared by this generic declaration
     * @param <T>  type of object requested from the context
     * @return object of specified type {@code T}
     * @throws Exception if there is more than one implementation of the class requested
     *                   from the context {@code IntensiveContext_VitaliBoshko} or if there is no implementation
     */
    public <T> T getObject(Class<T> type) throws Exception {
        assert type != null;

        Object objectFromContext = findFromContext(type);

        if (objectFromContext == null) {
            Object[] objects = dependencyFactory.createObject(type, searchService.search(path));
            if (objects != null) {
                for (Object object : objects) {
                    mapContext.put(object.getClass().getSimpleName(), object);
                }
                return (T) findFromContext(type);
            }
        }
        return (T) objectFromContext;
    }

    private <T> Object findFromContext(Class<T> type) {
        if (type.isInterface()) {

            for (Object object : mapContext.values()) {
                if (type.isAssignableFrom(object.getClass())) {
                    return object;
                }
            }
        }
        return mapContext.get(type.getSimpleName());
    }

    public static void main(String[] args) {
        IntensiveContext_VitaliBoshko intensiveContextVitaliBoshko = new IntensiveContext_VitaliBoshko("com.aston.task3");

        TestClass exampleClassObject;
        try {
            exampleClassObject = intensiveContextVitaliBoshko.getObject(TestClass.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (!isNull(exampleClassObject)) {
            exampleClassObject.run();
        }

        TestForInjectClass testForInjectClass = null;
        try {
            testForInjectClass = intensiveContextVitaliBoshko.getObject(TestForInjectClass.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (!isNull(testForInjectClass)) {
            testForInjectClass.run();
        }
    }
}
