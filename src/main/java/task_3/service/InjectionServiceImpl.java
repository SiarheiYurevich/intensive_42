package src.main.java.task_3.service;

import src.main.java.task_3.annotation.InjectComponent_StanislavFedin;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InjectionServiceImpl implements InjectionService {
    @Override
    public void inject(Object object, Map<String, Class> annotatedClasses) {
        Class<?> currentClass = object.getClass();
        Field[] fields = currentClass.getDeclaredFields();
        List<Object> dependencies = new ArrayList<>();

        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectComponent_StanislavFedin.class)) {
                String className = field.getAnnotation(InjectComponent_StanislavFedin.class).productName();
                Class clazz = annotatedClasses.get(className);

                try {
                    Object dependency = clazz.getConstructor().newInstance();
                    dependencies.add(dependency);
                } catch (Exception e) {
                    throw new RuntimeException("Cannot get constructor of the " + clazz.getName());
                }
            }
        }

        Method[] methods = object.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (method.getName().startsWith("set")) {
                dependencies.stream()
                        .forEach(dependency -> {
                            try {
                                method.invoke(object, dependency);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                throw new RuntimeException("Cannot invoke method " + method.getName() +
                                        " with the " + dependency.getClass().getName());
                            }
                        });
            }
        }
    }
}
