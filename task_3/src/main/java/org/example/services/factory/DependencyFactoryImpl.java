package org.example.services.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DependencyFactoryImpl implements DependencyFactory {
    public Object createInstance(Class<?> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Нельзя создать экзмепляр абстрактного класса или интерфейса");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Нельзя создать экзмепляр с приватным конструктором");
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Конструктор или метод создаваемого класса выбросил исключение: " + e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Не найден метод или конструктор вызываемого класса");
        }
    }
}
