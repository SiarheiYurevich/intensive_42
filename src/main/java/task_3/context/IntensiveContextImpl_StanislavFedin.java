package src.main.java.task_3.context;

import src.main.java.task_3.service.*;

import java.util.HashMap;
import java.util.Map;

public class IntensiveContextImpl_StanislavFedin implements IntensiveContext_StanislavFedin{
    private final SearchClassService searchClassService = new SearchClassServiceImpl();
    private final InjectionService injectionService = new InjectionServiceImpl();

    private Map<String, Class> annotatedClasses = new HashMap<>();

    // todo нужна мапа с инстансами, чтобы их выдавать в getObject()

    public IntensiveContextImpl_StanislavFedin(String packageName) {
        ScanPackageService scanner = new ScanPackageServiceImpl();

        scanner.getAnnotatedClasses(packageName)
                .forEach(definedClass -> annotatedClasses.put(definedClass.getSimpleName(), definedClass));
    }

    @Override
    public <T> T getObject(Class<T> type) throws Exception {

        return createObject(type);
    }

    private <T> T createObject(Class<T> type) throws Exception {
        Class<? extends T> implClass = type;

        if (type.isInterface()) {
            implClass = searchClassService.getImplClass(type, annotatedClasses);
        }

        T t = implClass.getConstructor().newInstance();

        injectionService.inject(t, annotatedClasses);

        return t;
    }
}
