package org.example.services.injection;

import org.example.annotation.IntensiveComponent_TimurAgeev;
import org.example.services.factory.DependencyFactory;
import org.example.services.factory.DependencyFactoryImpl;

import java.lang.reflect.Field;

public class InjectionServiceImpl implements InjectionService {
    private final DependencyFactory factory = new DependencyFactoryImpl();

    public InjectionServiceImpl() {
    }

    public void inject(Object target) {
        Field[] fields = target.getClass().getFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(IntensiveComponent_TimurAgeev.class)) {
                field.setAccessible(true);

                Object dependency = factory.createInstance(field.getDeclaringClass());
                try {
                    field.set(target, dependency);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
