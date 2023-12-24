package org.example.services.search;

import java.util.List;

public interface SearchService {
    List<Class<?>> findClasses(String packageName);
}
