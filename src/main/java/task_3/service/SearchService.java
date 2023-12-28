package task_3.service;

import java.io.IOException;
import java.util.Set;

public interface SearchService {
    Set<Class<?>> getAnnotatedClassesFromPackage(String packageName) throws IOException, ClassNotFoundException;
}
