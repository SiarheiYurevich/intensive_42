package task3.context;

import task3.factory.DependencyFactory;
import task3.factory.DependencyFactoryImpl;
import task3.service.SearchService;
import task3.service.SearchServiceImpl;

import java.util.*;

/**
 * Класс-реализация интерфейса {@link IntensiveContext_MaximYasnogorodskiy}
 */
public class IntensiveContextImpl_MaximYasnogorodskiy implements IntensiveContext_MaximYasnogorodskiy {

    private final String packageName;
    private final SearchService searchService;

    private final DependencyFactory dependencyFactory;

    private final Map<String, Object> contextObjects = new HashMap<>();

    public IntensiveContextImpl_MaximYasnogorodskiy(String packageName) {
        this.packageName = packageName;
        this.searchService = new SearchServiceImpl();
        this.dependencyFactory = new DependencyFactoryImpl();
    }

    /**
     * Реализация метода интерфейса IntensiveContext
     *
     * @param type тип класса, уже содержащегося в контексте.
     * @return Optional с завернутым в него объектом класса. Если объект не
     *         содержится в контексте, возвращает {@code Optional.empty()}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> Optional<T> getObject(Class<T> type) {
        List<Class<?>> annotatedClassesFromPackage = searchService.searchAnnotatedClasses(packageName);

        for (Class<?> classFromPackage : annotatedClassesFromPackage) {
            if (!classFromPackage.equals(type)) {
                continue;
            }

            String className = classFromPackage.getName();

            if (contextObjects.containsKey(className)) {
                T objectFromContext = (T) contextObjects.get(className);
                return Optional.of(objectFromContext);
            }

            Object objectFromClassType = dependencyFactory.getInstanceFromClassType(classFromPackage);
            contextObjects.put(className, objectFromClassType);

            return Optional.of((T) objectFromClassType);
        }
        return Optional.empty();
    }
}
