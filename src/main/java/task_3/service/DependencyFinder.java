package task_3.service;

import task_3.context.IntensiveContext_SlavaSles;

import java.util.Map;
import java.util.Set;

/**
 * Интерфейс, определяющий способ получения зависимостей для запрашиваемого класса при помощи метода
 * {@link DependencyFinder#findDependencies(Class)}.
 * @author Slava Sles
 * @version 1.0
 */
public interface DependencyFinder {
    /**
     * Метод, возвращающий Map со всеми зависимостями для запрашиваемого класса.
     * @param type класс (тип) запрашиваемого объекта
     * @return Map с парами ключ - класс, значение - набор полей классов (зависимостей), аннотированных
     * {@link IntensiveContext_SlavaSles}
     */
    Map<Class<?>, Set<Class<?>>> findDependencies(Class<?> type);
}
