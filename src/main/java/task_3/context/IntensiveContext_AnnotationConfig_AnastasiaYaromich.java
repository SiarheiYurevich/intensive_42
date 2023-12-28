package task_3.context;

import task_3.annotations.IntensiveSingletonScope_AnastasiaYaromich;
import task_3.objectfactory.IntensiveFactory_AnnotationConfig_AnastasiaYaromich;
import task_3.scanner.ClassPathObjectScanner;
import task_3.scanner.ClassPathObjectScannerImpl;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IntensiveContext_AnnotationConfig_AnastasiaYaromich implements IntensiveContext_AnastasiaYaromich {
    private final ClassPathObjectScanner classPathObjectScanner;
    private final IntensiveFactory_AnnotationConfig_AnastasiaYaromich factory;
    private final Map<Class<?>,Object> singletonObjects = new ConcurrentHashMap<>();


    public IntensiveContext_AnnotationConfig_AnastasiaYaromich(String... basePackages) {
        this.classPathObjectScanner = new ClassPathObjectScannerImpl(basePackages);
        factory = new IntensiveFactory_AnnotationConfig_AnastasiaYaromich(this);
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> type) {
        if(singletonObjects.containsKey(type)) {
            return (T) singletonObjects.get(type);
        }

        Class<? extends T> implClass = type;
        
        if(type.isInterface()) {
            List<Class<?>> typeImplementations =  classPathObjectScanner.determineImplementations(type);
            if(typeImplementations.size() != 1) {
                throw new RuntimeException(type + " has zero or more than one impl");
            } else {
                implClass = (Class<T>) classPathObjectScanner.determineImplementations(type).get(0);
            }
        }

        T t = (T) factory.createObject(implClass);
        if(implClass.isAnnotationPresent(IntensiveSingletonScope_AnastasiaYaromich.class)) {
            singletonObjects.put(type, t);
        }
        return t;
    }

    @Override
    public ClassPathObjectScanner getScanner() {
        return classPathObjectScanner;
    }
}
