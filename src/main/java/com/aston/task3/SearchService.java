package com.aston.task3;

import java.util.List;

/**
 * Interface provides functionality for scan specified path.
 */
public interface SearchService {
    /**
     * This method scans the specified path for the presence of classes marked
     * with an annotation {@code IntensiveComponent_VitaliBoshko}.
     *
     * @param path the string value of the path of the scanned package
     * @return list objects {@code Class}
     */
    List<Class<?>> search(String path);
}
