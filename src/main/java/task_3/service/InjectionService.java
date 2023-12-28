package task_3.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

public interface InjectionService {
    void injectDependencies() throws InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException;
}
