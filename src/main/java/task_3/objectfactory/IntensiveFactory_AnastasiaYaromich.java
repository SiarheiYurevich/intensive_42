package task_3.objectfactory;

import java.lang.reflect.InvocationTargetException;

public interface IntensiveFactory_AnastasiaYaromich {
    <T> T createObject(Class<T> implClass);
    <T> T applyAfterInitializationObjectPostProcessors(Class<T> implClass, T t);
    <T> void invokeInit(Class<T> implClass, T t);
    <T> void applyBeforeInitializationObjectPostProcessors(T t);
    <T> T create(Class<T> implClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
