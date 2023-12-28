package task_3.context;

/**
 * Интерфейс, определяющий способ получения экземпляра класса из контекста приложения {@code intensiveContext} при
 * помощи метода {@link IntensiveContext_SlavaSles#getObject(Class)}.
 * @author Slava Sles
 * @version 1.0
 */
public interface IntensiveContext_SlavaSles {
    /**
     * Метод, возвращающий из контекста приложения экземпляр запрашиваемого класса.
     * @param type класс (тип) запрашиваемого объекта
     * @return экземпляр объекта, запрашиваемого типа
     * @param <T> параметризованный дженерик тип класса
     */
    <T> T getObject(Class<T> type);
}
