package task_3.objectfactory;

import task_3.context.IntensiveContext_AnastasiaYaromich;
import task_3.objectPostProcessors.ObjectPostProcessor;
import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class IntensiveFactory_AnnotationConfig_AnastasiaYaromich implements IntensiveFactory_AnastasiaYaromich {
    private final IntensiveContext_AnastasiaYaromich context;
    private final List<ObjectPostProcessor> objectPostProcessors = new ArrayList<>();


    public IntensiveFactory_AnnotationConfig_AnastasiaYaromich(IntensiveContext_AnastasiaYaromich intensiveContext_anastasiaYaromich){
        this.context = intensiveContext_anastasiaYaromich;

        for (Class<?> objectPostProcessor : context.getScanner().determineImplementations(ObjectPostProcessor.class)) {
            try {
                objectPostProcessors.add((ObjectPostProcessor) objectPostProcessor.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public <T> T createObject(Class<T> implClass) {
        T t = create(implClass);

        applyBeforeInitializationObjectPostProcessors(t);

        invokeInit(implClass, t);

        t = applyAfterInitializationObjectPostProcessors(implClass, t);

        return t;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T applyAfterInitializationObjectPostProcessors(Class<T> implClass, T t) {
        for (ObjectPostProcessor objectPostProcessor: objectPostProcessors) {
            t = (T) objectPostProcessor.postProcessAfterInitialization(t, implClass);
        }
        return t;
    }

    @Override
    public <T> void invokeInit(Class<T> implClass, T t) {
        for (Method method: implClass.getMethods()) {
            if(method.isAnnotationPresent(PostConstruct.class)) {
                try {
                    method.invoke(t);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public <T> void applyBeforeInitializationObjectPostProcessors(T t) {
        objectPostProcessors.forEach(objectPostProcessor -> {
            objectPostProcessor.postProcessBeforeInitialization(t, context);
        });
    }

    @Override
    public <T> T create(Class<T> implClass) {
        T t = null;
        try {
            t = implClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return t;
    }
}

