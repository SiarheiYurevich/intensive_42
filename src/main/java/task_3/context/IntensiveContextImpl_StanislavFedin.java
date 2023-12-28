package src.main.java.task_3.context;

import src.main.java.task_3.service.*;

import java.util.HashMap;
import java.util.Map;

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
