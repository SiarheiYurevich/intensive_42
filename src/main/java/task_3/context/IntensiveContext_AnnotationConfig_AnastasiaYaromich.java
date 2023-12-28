package task_3.context;


//
//        Реализовать класс IntensiveContext_NameSurname - "аналог" Spring контекста. +
//
//        - коструктур данного класса должен принимать пакет для поиска классов,
//        аннотированных @IntensiveComponent_NameSurname (предварительно создать
//        аннотацию @IntensiveComponent_NameSurname).
//
//        - класс должен иметь единственный публичный метод:
//public <T> T getObject(Class<T> type);
//
//        - после создания класса IntensiveContext_NameSurname мы должны иметь возможность
//        с помощью метода getObject получить любой класс, имеющий аннотацию
//@IntensiveComponent_NameSurname, и находящийся в переданном пакете.
//        - если запрашиваем интерфейс - получаем реализацию, если нет реализации или
//        интерфейс имеет больше одной реализации - эксепшен.
//
//        - классы, аннотированные @IntensiveComponent_NameSurname, не должны создаваться
//        и добавляться в IntensiveContext_NameSurname вместе с его созданием, а только
//        при вызове метода getObject.
//        - если класс аннотированнй @IntensiveComponent_NameSurname имеет поля, которые
//        также являются классами, аннотированными данной аннотацией, они должны быть
//        также проинициализированны.
//
//        - логику поиска и создания классов выносить по специализированным классам,
//        например SearchService, InjectionService, DependencyFactory и т.п.,
//        классы должны иметь интерфейсы и реализацию (все по SOLID).
//
//        - в методе main класса продемострировать работу класса IntensiveContext_NameSurname,
//        например:
//        IntensiveContext_NameSurname context = new IntensiveContext_NameSurname("пакет");
//        SomeClass1 class1 = context.getObject(SomeClass1.class);
//        class1.run(); // должен, например, вывести сообщение в консоль

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
