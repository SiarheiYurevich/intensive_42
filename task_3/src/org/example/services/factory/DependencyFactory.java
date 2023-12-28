package org.example.services.factory;

public interface DependencyFactory {
    /**
     * Создает экзмепляр класса
     *
     * @param clazz - тип класса, экзмемляр которого нужно создать
     * @return созданный объект
     */
    Object createInstance(Class<?> clazz);
}
