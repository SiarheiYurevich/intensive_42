package task_3.objectDefinitionScanners;

import org.reflections.Reflections;
import java.util.Set;

/**
 * Интерфейс, описывающий методы сканера, основная задача которого -
 * сканирование пакетов на наличие классов, помеченных аннотацией
 * {@link task_3.annotations.IntensiveComponent_AleksandrZmeyev}.
 */
public interface ObjectDefinitionScanner {

    /**
     * Метод, возвращающий сканер класса {@link Reflections}
     */
    Reflections getScanner();

    /**
     * Метод возвращает класс, который реализует
     * передающийся в параметре {@code infce} интерфейс.
     *
     * @param infce интерфейс, реализацию которого необходимо найти
     * @return {@code Class}, который является реализацией {@code infce}
     */
    <T> Class<? extends T> getImplementationClass(Class<T> infce);

    /**
     * Метод возвращает коллекцию классов, имеющих аннотацию
     * {@link task_3.annotations.IntensiveComponent_AleksandrZmeyev}.
     */
    Set<Class<?>> getObjectDefinitions();
}
