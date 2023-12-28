package src.main.java.task_3.context;

import src.main.java.task_3.annotation.IntensiveComponent_StanislavFedin;
import src.main.java.task_3.service.*;

import java.util.HashMap;
import java.util.Map;

/**
 * A class is an analogue of a spring context, which contains annotated {@link IntensiveComponent_StanislavFedin}
 * but not yet initialized classes.
 * It also contains already initialized classes.
 * The class constructor scans the passed package for the presence of annotated classes.
 * @author Stanislav Fedin
 */
public class IntensiveContextImpl_StanislavFedin implements IntensiveContext_StanislavFedin{
    private final SearchClassService searchClassService = new SearchClassServiceImpl();
    private final InjectionService injectionService = new InjectionServiceImpl();

    private final Map<String, Class> annotatedClasses = new HashMap<>();
    
    private final Map<String, Object> instanceOfObject = new HashMap<>();

    public IntensiveContextImpl_StanislavFedin(String packageName) {
        ScanPackageService scanner = new ScanPackageServiceImpl();

        scanner.getAnnotatedClasses(packageName)
                .forEach(definedClass -> annotatedClasses.put(definedClass.getSimpleName(), definedClass));
    }

    /**
     * Returns an instance of the passed class.
     * If the instance does not yet exist, then the method creates it, saves it and returns it.
     * If an interface was passed, it returns its implementation.
     * @param type Type of object to get
     * @return Object cast to the required type
     * @throws Exception If the passed interface has no implementation or has more than one implementation,
     * then an error will occur
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> type) throws Exception {
        
        if (type.isInterface()) {
            for (Object value : instanceOfObject.values()) {
                if (value.getClass().isAssignableFrom(type))
                    return (T) value;
            }
        }
        
        if (instanceOfObject.containsKey(type.getSimpleName()))
            return (T) instanceOfObject.get(type.getSimpleName());

        return createObject(type);
    }

    private <T> T createObject(Class<T> type) throws Exception {
        Class<? extends T> implClass = type;

        if (type.isInterface()) {
            implClass = searchClassService.getImplClass(type, annotatedClasses);
        }

        T t = implClass.getConstructor().newInstance();

        injectionService.inject(t, annotatedClasses);
        
        instanceOfObject.put(t.getClass().getSimpleName(), t);

        return t;
    }
}
