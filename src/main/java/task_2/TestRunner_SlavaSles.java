package task_2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

public class TestRunner_SlavaSles {

    public void run(String packageName) {
        Set<Class> classes = findAllClassesUsingClassLoader(packageName);
        for (Class clazz : classes) {
            try {
                Class checkedClazz = Class.forName(clazz.getName());
                runAnnotatedMethod(checkedClazz);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void runAnnotatedMethod(Class checkedClazz) throws InvocationTargetException, IllegalAccessException,
            NoSuchMethodException, InstantiationException {
        Method[] methods = checkedClazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(IntensiveTest_SlavaSles.class)) {
                Object instance = checkedClazz.getDeclaredConstructor().newInstance();
                method.invoke(instance);
            }
        }
    }

    private Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
