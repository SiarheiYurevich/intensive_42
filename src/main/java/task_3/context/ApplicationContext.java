package task_3.context;

import task_3.objectDefinitionScanners.ObjectDefinitionScanner;

/**
 * Интерфейс контекста. Определяет метод получения объекта из контекста.
 *
 * @author Aleksandr Zmeyev
 */
public interface ApplicationContext {

    /**
     * Этот метод используется для получения объекта указанного типа из реализации {@code ApplicationContext}.
     *
     * @param type класс или интерфейс, из которого необходимо создать объект
     * @return объект на основе параметра, указанного в параметре
     */
    <T> T getObject(Class<T> type);

    /**
     * Метод позволяет получить объект {@link ObjectDefinitionScanner},
     * основная задача которого - сканирование пакетов
     * на наличие классов, помеченных аннотацией
     * {@link task_3.annotations.IntensiveComponent_AleksandrZmeyev}.
     *
     * @return объект ObjectDefinitionScanner
     */
    ObjectDefinitionScanner getObjectDefinitionScanner();
}