package task_3.objectFactories;

import task_3.objectDefinitionScanners.ObjectDefinitionScanner;
import task_3.annotations.IntensivePostConstruct;
import task_3.configurators.ObjectConfigurator;
import task_3.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Фабрика для создания объектов, хранимых контекстом {@link ApplicationContext}
 *
 * @author Aleksandr Zmeyev
 */
public class ObjectFactory {

    /**
     * Поле, хранящее объект контекста.
     */
    private ApplicationContext context;

    /**
     * Список конфигураций {@link ObjectConfigurator},
     * необходимых для настройки объектов во время создания.
     */
    private List<ObjectConfigurator> configurators = new ArrayList<>();

    /**
     * Конструктор фабрики объектов.
     * С помощью {@link ObjectDefinitionScanner}
     * находит классы конфигураторов {@link ObjectConfigurator}
     * и помещает их в {@code configurators}.
     *
     * @param objectDefinitionScanner сканер для поиска конфигураторов
     */
    public ObjectFactory(ObjectDefinitionScanner objectDefinitionScanner){

        for ( Class<?> objectDefinition : objectDefinitionScanner.getObjectDefinitions()) {
            if (ObjectConfigurator.class.isAssignableFrom(objectDefinition)) {
                try {

                    configurators.add((ObjectConfigurator) objectDefinition.getDeclaredConstructor().newInstance());

                } catch (InstantiationException | IllegalAccessException |
                         InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Сеттер для поля {@code context}
     */
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    /**
     * Метод для создания и настройки объекта
     * класса, переданного в параметре {@code type}.
     *
     * @param type класс объекта
     * @return настроенный объект
     */
    public <T> T getObject(Class<T> type) {
        try {
            T object = type.getDeclaredConstructor().newInstance();

            configureBeforeInit(object);
            invokeInitMethod(type, object);
            configureAfterInit(object);

            return object;

        } catch (InstantiationException | IllegalAccessException |
                 InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод для настройки объекта перед вызовом метода,
     * помеченного аннотацией {@link task_3.annotations.IntensivePostConstruct}.
     *
     * @param object объект, который необходимо настроить
     */
    private void configureBeforeInit(Object object) {
        for (ObjectConfigurator configurator : configurators) {
            configurator.configureBeforeInitialization(object, context);
        }
    }

    /**
     * Метод вызывает у объекта методы, помеченные
     * аннотацией {@link task_3.annotations.IntensivePostConstruct}.
     *
     * @param type класс объекта, который необходимо создать
     * @param object объект, который необходимо создать
     */
    private <T> void invokeInitMethod(Class<T> type, T object) {
        for (Method method : type.getMethods()) {
            if (method.isAnnotationPresent(IntensivePostConstruct.class)) {
                try {
                    method.invoke(object);
                } catch (IllegalAccessException | InvocationTargetException e) {
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
     */
    private void configureAfterInit(Object object) {
        for (ObjectConfigurator configurator : configurators) {
            object = configurator.configureAfterInitialization(object, context);
        }
    }
}
