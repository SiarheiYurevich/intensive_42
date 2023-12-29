package service.impl;

import annotation.IntensiveInject_YaroslavRulev;


import exception.NoSuchBeanException;
import service.InjectionService;

import java.lang.reflect.Field;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Класс, реализующий интерфейс InjectionService, отвечающий за внедрение зависимостей в объекты.
 *
 * <p>Метод injectDependency этого класса получает объект и карту бинов. Он ищет все поля класса объекта,
 * помеченные аннотацией IntensiveInject_YaroslavRulev. Для каждого помеченного поля пытается найти соответствующий
 * бин в карте, сравнивая тип поля с типами бинов. Если найден соответствующий бин, метод рекурсивно вызывает
 * сам себя, чтобы внедрить зависимости в бин, а затем с использованием рефлексии устанавливает бин в поле объекта
 * с помощью соответствующего метода-сеттера.
 */

public class InjectServiceImpl implements InjectionService {
    /**
     * <p>Метод injectDependency этого класса получает объект и карту бинов. Он ищет все поля класса объекта,
     * помеченные аннотацией IntensiveInject_YaroslavRulev. Для каждого помеченного поля пытается найти соответствующий
     * бин в карте, сравнивая тип поля с типами бинов. Если найден соответствующий бин, метод рекурсивно вызывает
     * сам себя, чтобы внедрить зависимости в бин, а затем с использованием рефлексии устанавливает бин в поле объекта
     * с помощью соответствующего метода-сеттера.
     */
    public void injectDependency(Object object, Map<String, Object> beans) {
        List<Field> annotatedFields = getAnnotatedFields(object.getClass());
        for (Field field : annotatedFields) {
            boolean beanInstalled = false;
            for (Object bean : beans.values()) {
                if (field.getType().isInstance(bean)) {
                    if (beanInstalled) {
                        throw new NoSuchBeanException("Компонент уже существует");
                    }
                    beanInstall(object, beans, field, bean);
                    beanInstalled = true;
                }
            }
            if (!beanInstalled) {
                throw new NoSuchBeanException("The bean was not found.");
            }
        }
    }

    /**
     * <p>Метод beanInstall - вспомогательный метод, используемый для рекурсивного внедрения зависимостей в бины и установки
     * их в поля.
     */

    private void beanInstall(Object object, Map<String, Object> beans, Field field, Object bean) {
        try {
            injectDependency(bean, beans);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String methodName = createSetterMethodName(field);
        setBeansInField(object, bean, methodName);
    }
    /**
     ** <p>Метод getAnnotatedFields - вспомогательный метод, используемый для извлечения всех полей класса, помеченных аннотацией
     *  * IntensiveInject_YaroslavRulev.
     */

    private List<Field> getAnnotatedFields(Class<?> clazz) {
        List<Field> annotatedFields = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(IntensiveInject_YaroslavRulev.class)) {
                annotatedFields.add(field);
            }
        }
        return annotatedFields;
    }
    /**
     * <p>Метод createSetterMethodName - вспомогательный метод, используемый для создания имени метода-сеттера для поля
     * в соответствии со стандартной соглашением именования в Java.
     */

    private String createSetterMethodName(Field field) {
        String fieldName = field.getName();
        return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
    /**
     <p>Метод setBeansInField - вспомогательный метод, используемый для установки заданного бина в поле объекта с помощью
     * рефлексии вызова соответствующего метода-сеттера.
     */

    private void setBeansInField(Object object, Object bean, String methodName) {
        try {
            Method method = object.getClass().getMethod(methodName, bean.getClass());
            method.invoke(object, bean);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}


