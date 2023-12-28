package task3.factory;

import task3.annotation.IntensiveComponent_MaximYasnogorodskiy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


/**
 * Реализация интерфейса {@link DependencyFactory}
 */
public class DependencyFactoryImpl implements DependencyFactory {
    private final Class<? extends Annotation> annotation
            = IntensiveComponent_MaximYasnogorodskiy.class;

    /**
     * Реализация метода интерфейса DependencyFactory. Кроме того, проверяется
     * наличие полей класса, также представляющих собой аннотированные классы, и их
     * инициализация.
     * @param classType тип класса, из которого нужно создать объект
     * @return объект класса заданного типа.
     */
    @Override
    public Object getInstanceFromClassType(Class<?> classType) {
        try {
            Object objectFromClassType = classType.getDeclaredConstructor().newInstance();
            initializeAnnotatedFields(objectFromClassType);

            return objectFromClassType;
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException("Ошибка при создании экземпляра из класса " + classType.getName());
        }
    }

    private void initializeAnnotatedFields(Object outerClass) {
        Field[] declaredFields = outerClass.getClass().getDeclaredFields();

        for (Field field : declaredFields) {
            if(field.getType().getAnnotation(annotation) != null) {
                field.setAccessible(true);
                try {
                    Object valueForField = field.getType().getDeclaredConstructor().newInstance();
                    field.set(outerClass, valueForField);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(
                            "Не удалось инициализировать аннотированное поле " +
                            field.getName() + " класса " + outerClass.getClass().getName());
                }
            }
        }
    }
}
