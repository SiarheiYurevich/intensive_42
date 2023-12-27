package src.main.java.task_3.service;

import java.util.List;

public interface ScanPackageService {
    List<Class> getAnnotatedClasses(String packageToScan);
}
