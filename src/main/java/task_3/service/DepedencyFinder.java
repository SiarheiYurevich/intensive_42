package task_3.service;

import java.util.Map;
import java.util.Set;

public interface DepedencyFinder {
    public Map<Class<?>, Set<Class<?>>> findDependencies(Class<?> type);
}
