package task3.factory;


/**
 * Интерфейс, задающий контракт на создание фабрики объектов
 */
public interface DependencyFactory {

    /**
     * Метод создает объект из переданного в него типа класса
     * @param classType тип класса, из которого нужно создать объект
     * @return объект класса заданного типа
     */
    Object getInstanceFromClassType(Class<?> classType);
}
