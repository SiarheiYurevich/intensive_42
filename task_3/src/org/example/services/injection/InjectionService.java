package org.example.services.injection;

public interface InjectionService {
    /**
     * Осуществляет иньекцию зависимостей в переданный объект
     *
     * @param target - объект, в котором нужно выполнить инъекцию
     */
    void inject(Object target);
}
