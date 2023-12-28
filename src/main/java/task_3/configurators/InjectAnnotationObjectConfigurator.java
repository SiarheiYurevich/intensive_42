package task_3.configurators;

import task_3.annotations.IntensiveComponent_AleksandrZmeyev;
import task_3.annotations.IntensiveInject;
import task_3.context.ApplicationContext;
import java.lang.reflect.Field;


/**
 * Реализация интерфейса {@link ObjectConfigurator}.
 * Содержит методы для настройки объекта
 * во время его создания фабрикой {@link task_3.objectFactories.ObjectFactory}.
 *
 * @author Aleksandr Zmeyev
 */
@IntensiveComponent_AleksandrZmeyev
public class InjectAnnotationObjectConfigurator implements ObjectConfigurator {

    /**
     * Метод для настройки объекта перед вызовом метода,
     * помеченного аннотацией {@link task_3.annotations.IntensivePostConstruct}.
     *
     * @param object объект, который необходимо настроить
     * @param context контекст
     */
    @Override
    public void configureBeforeInitialization(Object object, ApplicationContext context) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(IntensiveInject.class)) {
                field.setAccessible(true);
                Object fieldValue = context.getObject(field.getType());

                try {
                    field.set(object,fieldValue);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Метод для настройки объекта после вызова метода,
     * помеченного аннотацией {@link task_3.annotations.IntensivePostConstruct}.
     *
     * @param object объект, который необходимо настроить
     * @param context контекст
     * @return настроенный объект
     */
    @Override
    public Object configureAfterInitialization(Object object, ApplicationContext context) {
        return object;
    }
}
