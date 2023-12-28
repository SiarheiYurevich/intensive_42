package task_3.context;

import task_3.scanner.ClassPathObjectScanner;

public interface IntensiveContext_AnastasiaYaromich {
    public <T> T getObject(Class<T> type);
    public ClassPathObjectScanner getScanner();
}
