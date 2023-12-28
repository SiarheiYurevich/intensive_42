package task3.context;

import java.util.Optional;

/**
 * Интерфейс, задающий контракт для создаваемого контекста.
 */
public interface IntensiveContext_MaximYasnogorodskiy {

    /**
     * Обязательный метод для реализации в контексте, необходимый
     * для возврата объекта из содержимого контекста.
     *
     * @param type тип класса, уже содержащегося в контексте.
     * @return Optional с завернутым в него объектом класса. Если объект не
     *         содержится в контексте, возвращает Optional.empty()
     */
    <T> Optional<T> getObject(Class<T> type);
}
