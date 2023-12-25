package org.example.services.search;

import java.util.List;

public interface SearchService {
    /**
     * Осщуествляет поиск классов в пакете по переданному имени.
     *
     * @param packageName - имя пакета, в котором осуществляется поиск
     * @return список найденных классов
     */
    List<Class<?>> findClasses(String packageName);
}
