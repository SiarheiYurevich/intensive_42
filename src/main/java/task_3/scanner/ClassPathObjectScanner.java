package task_3.scanner;

import java.util.List;

public interface ClassPathObjectScanner {
    List<Class<?>> doScan(String... basePackages);
    List<Class<?>> determineImplementations(Class<?> type);
}
