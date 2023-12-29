package service.impl;

import service.FactoryService;
/**
 * Класс FactoryServiceImpl является реализацией интерфейса FactoryService.
 * Он предоставляет методы для создания экземпляров классов.
 */
public class FactoryServiceImpl implements FactoryService {

    /**
     * Создает экземпляр указанного класса.
     *
     * @param type класс, для которого нужно создать экземпляр
     * @param <T> тип класса
     * @return экземпляр класса
     * @throws RuntimeException если не удалось создать экземпляр класса
     */
    @Override
    public <T> T createInstance(Class<T> type) {
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать экземпляр " + type.getName(), e);
        }
    }
}

