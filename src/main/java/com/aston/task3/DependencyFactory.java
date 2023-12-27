package com.aston.task3;

import java.util.List;

/**
 * Interface factory asks for creating instances classes
 * which marked annotation {@code IntensiveComponent_VitaliBoshko}.
 */
public interface DependencyFactory {
    /**
     * This method creates objects of specified type if it or implementation contains in list {@code classes}.
     *
     * @param type    object which need to create if it or implementation contains in list {@code classes}
     * @param classes list objects of type {@code Class<?>} classes which marked annotation {@code IntensiveComponent_VitaliBoshko}.
     * @return array of objects of type {@code Object} which were created by this factory
     * @throws Exception if there is more than one implementation of the class requested
     *                   from the context {@code IntensiveContext_VitaliBoshko} or if there is no implementation
     */
    Object[] createObject(Class<?> type, List<Class<?>> classes) throws Exception;
}
