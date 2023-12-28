package task_3.service;

import task_3.context.IntensiveComponent_SlavaSles;

import java.io.IOException;
import java.util.Set;

/**
 * Интерфейс, сканирующий пакет {@code packageName} на наличие в нем классов с аннотацией.
 * {@link IntensiveComponent_SlavaSles} при помощи метода {@link SearchService#getAnnotatedClassesFromPackage(String)}
 * @author Slava Sles
 * @version 1.0
 */
public interface SearchService {
    /**
     * Метод, возвращающий набор классов, аннотированных {@link IntensiveComponent_SlavaSles}.
     * @param packageName название пакета, в котором находятся классы, помеченные аннотацией
     * @return набор классов из пакета {@code packageName}, аннотированных {@link IntensiveComponent_SlavaSles}
     * @throws IOException если не удается получить доступ к пакету {@code packageName}
     * @throws ClassNotFoundException если не удается найти нужный файл в пакете {@code packageName}
     */
    Set<Class<?>> getAnnotatedClassesFromPackage(String packageName) throws IOException, ClassNotFoundException;
}
