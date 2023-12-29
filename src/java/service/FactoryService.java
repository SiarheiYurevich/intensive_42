package service;

public interface FactoryService {
    <T> T createInstance(Class<T> clazz);
}
