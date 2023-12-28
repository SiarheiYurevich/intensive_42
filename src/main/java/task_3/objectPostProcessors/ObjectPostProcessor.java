package task_3.objectPostProcessors;

import task_3.context.IntensiveContext_AnastasiaYaromich;

public interface ObjectPostProcessor {
    void postProcessBeforeInitialization(Object t, IntensiveContext_AnastasiaYaromich context);
    Object postProcessAfterInitialization(Object t, Class<?> implClass);
}
