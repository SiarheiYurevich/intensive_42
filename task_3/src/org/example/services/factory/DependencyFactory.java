package org.example.services.factory;

public interface DependencyFactory {
    Object createInstance(Class<?> clazz);
}
