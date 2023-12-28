package task_3.context;

import task_3.exception.FindComponentException;
import task_3.exception.NoDefaultConstructorException;
import task_3.exception.NoSuchComponentClassException;
import task_3.service.DependencyFinder;
import task_3.service.InjectionService;
import task_3.service.SearchService;
import task_3.service.impl.DependencyFinderImpl;
import task_3.service.impl.InjectionServiceImpl;
import task_3.service.impl.SearchServiceImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Класс, реализующий интерфейс {@link IntensiveContext_SlavaSles}.
 * @author Slava Sles
 * @version 1.0
 */
public class IntensiveContext_SlavaSlesImp implements IntensiveContext_SlavaSles{

    private final Set<Class<?>> annotatedClassesFromPackage;

    /**
     * Конструктор класса.
     * <p>
     *     При создании класса {@link IntensiveContext_SlavaSlesImp} сканируется пакет {@code packageName} на наличие
     *     в нем классов с аннотацией {@link IntensiveComponent_SlavaSles} при помощи сервиса {@link SearchService}.
     *     В случае, если при получении классов из пакета {@code packageName} возникает ошибка, то выбрасывается
     *     исключение {@code FindComponentException}.
     * </p>
     * @param packageName название пакета, в котором находятся классы, помеченные аннотацией
     * {@link IntensiveComponent_SlavaSles}
     */
    public IntensiveContext_SlavaSlesImp(String packageName) {

        SearchService searchService = new SearchServiceImpl();

        try {
            annotatedClassesFromPackage = searchService.getAnnotatedClassesFromPackage(packageName);
        } catch (IOException | ClassNotFoundException exception) {
            throw new FindComponentException(exception);
        }

    }

    /**
     * Метод, возвращающий из контекста приложения экземпляр запрашиваемого класса.
     *<p>
     *    В методе определяется перечень зависимостей {@code dependenciesForClass} запрашиваемого объекта с помощью
     *    класса {@link DependencyFinderImpl}, реализующего интерфейс {@link DependencyFinder}.
     *    Далее с помощью класса {@link InjectionServiceImpl}, реализующего интерфейс {@link InjectionService},
     *    последовательно создаются и внедряются объекты в зависимые от них классы в обратном порядке.
     *</p>
     * @param type класс (тип) запрашиваемого объекта
     * @return экземпляр объекта, запрашиваемого типа
     * @param <T> параметризованный дженерик тип класса
     */
    @Override
    public <T> T getObject(Class<T> type) {
        Map<Class<?>, Object> intensiveContext = new HashMap<>();

        T clazzInstance = null;

        if (!annotatedClassesFromPackage.contains(type)) {
            throw new NoSuchComponentClassException(type);

        } else {
            try {

                DependencyFinder dependencyFactory = new DependencyFinderImpl(annotatedClassesFromPackage);
                Map<Class<?>, Set<Class<?>>> dependenciesForClass = dependencyFactory.findDependencies(type);

                InjectionService injectionService = new InjectionServiceImpl(dependenciesForClass, intensiveContext);
                injectionService.injectDependencies();

                boolean firstDependencyClass = false;
                for (Class<?> clazz : dependenciesForClass.keySet()) {
                    if (firstDependencyClass) {
                        break;
                    }
                    firstDependencyClass = true;
                    clazzInstance = (T) intensiveContext.get(clazz);
                }

            } catch (InstantiationException | IllegalAccessException |
                     InvocationTargetException | NoSuchMethodException exception) {
                throw new NoDefaultConstructorException(exception);
            }
        }
        return clazzInstance;
    }
}
