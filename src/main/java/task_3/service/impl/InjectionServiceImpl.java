package task_3.service.impl;

import task_3.context.IntensiveComponent_SlavaSles;
import task_3.service.InjectionService;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

/**
 * Класс, реализующий интерфейс {@link InjectionService}.
 * @author Slava Sles
 * @version 1.0
 */
public class InjectionServiceImpl implements InjectionService {

    private final Map<Class<?>, Set<Class<?>>> dependenciesForClass;
    private final Map<Class<?>, Object> intensiveContext;

    /**
     * Конструктор класса с параметрами.
     * @param dependenciesForClass перечень зависимостей запрашиваемого объекта
     * @param intensiveContext контекст приложения, в котором содержатся экземпляры объектов классов, аннотированных
     * {@link IntensiveComponent_SlavaSles}
     */
    public InjectionServiceImpl(Map<Class<?>, Set<Class<?>>> dependenciesForClass, Map<Class<?>,
            Object> intensiveContext) {
        this.dependenciesForClass = dependenciesForClass;
        this.intensiveContext = intensiveContext;
    }

    /**
     * Метод, создающий экземпляры классов и внедряющий в них зависимости.
     * <p>
     *     Метод выполняется до тех, пока количество экземпляров объектов в контексте приложения
     *     {@code intensiveContext} не станет равным количеству классов из перечня зависимостей
     *     {@code dependenciesForClass}.
     *     В методе последовательно создаются и внедряются объекты в зависимые от них классы в обратном порядке.
     * </p>
     * @throws InvocationTargetException выбрасывается при возникновении ошибки вызова метода/конструктора у класса
     * @throws NoSuchMethodException выбрасывается при отсутствии запрашиваемого метода у класса
     * @throws InstantiationException выбрасывается при возникновении ошибки при создании экземпляра класса
     * @throws IllegalAccessException выбрасывается при возникновении ошибки получения доступа к классу
     */
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
