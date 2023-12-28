package org.example.services.injection;

import org.example.annotation.IntensiveComponent_TimurAgeev;
import org.example.services.factory.DependencyFactory;
import org.example.services.factory.DependencyFactoryImpl;

import java.lang.reflect.Field;

public class InjectionServiceImpl implements InjectionService {
    private final DependencyFactory factory = new DependencyFactoryImpl();

    public void inject(Object target) {
        Field[] fields = target.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(IntensiveComponent_TimurAgeev.class)) {
                field.setAccessible(true);
                Object dependency = factory.createInstance(field.getType());

                try {
                    field.set(target, dependency);
                } catch (IllegalAccessException | IllegalArgumentException e) {
                    throw new RuntimeException("Невозможно установить значение в  " + field.getName());
                }
            }
        }
    }
}
