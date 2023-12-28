package task_3.configurators;

import task_3.context.ApplicationContext;

/**
 * Интерфейс, предоставляющий методы для
 * настройки объекта во время его создания
 * фабрикой {@link task_3.objectFactories.ObjectFactory}.
 *
 * @author Aleksandr Zmeyev
 */
public interface ObjectConfigurator {

    /**
     * Метод для настройки объекта перед вызовом метода,
     * помеченного аннотацией {@link task_3.annotations.IntensivePostConstruct}.
     *
     * @param object объект, который необходимо настроить
     * @param context контекст
     */
    void configureBeforeInitialization(Object object, ApplicationContext context);

    /**
     * Метод для настройки объекта после вызова метода,
     * помеченного аннотацией {@link task_3.annotations.IntensivePostConstruct}.
     *
     * @param object объект, который необходимо настроить
     * @param context контекст
     * @return настроенный объект
     */
    Object configureAfterInitialization(Object object, ApplicationContext context);
}
