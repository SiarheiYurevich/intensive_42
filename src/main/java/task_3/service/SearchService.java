package task_3.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SearchService {
    Map<Class<?>, String> getAnnotatedClassesFromPackage(String packageName) throws IOException, ClassNotFoundException;
}
