package task3.service;

import java.util.List;

/**
 * Интерфейс, задающий контракт для сервиса поиска аннотированных классов
 */
public interface SearchService {

    /**
     * Метод в заданном имени пакета ищет аннотированные классы
     * @param packageName имя пакета
     * @return список аннотированных классов
     */
    List<Class<?>> searchAnnotatedClasses(String packageName);
}
