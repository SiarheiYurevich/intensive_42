package task_3.objectPostProcessors;

import net.sf.cglib.Enhancer;
import task_3.annotations.IntensiveComponent_AnastasiaYaromich;
import task_3.annotations.IntensiveInject_AnastasiaYaromich;
import task_3.annotations.IntensiveSingletonScope_AnastasiaYaromich;
import task_3.context.IntensiveContext_AnastasiaYaromich;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@IntensiveSingletonScope_AnastasiaYaromich
@IntensiveComponent_AnastasiaYaromich
public class InjectAnnotationObjectPostProcessor implements ObjectPostProcessor {

    @Override
    public void postProcessBeforeInitialization(Object t, IntensiveContext_AnastasiaYaromich context) {
        for(Field field: t.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(IntensiveInject_AnastasiaYaromich.class)) {
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                try {
                    field.set(t, object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Object postProcessAfterInitialization(Object t, Class<?> implClass) {
        if(implClass.isAnnotationPresent(Deprecated.class)) {
            System.out.println("Class is deprecated. Check the documentation to find any implementations.");
            if(implClass.getInterfaces().length == 0) {
                return Enhancer.enhance(implClass, (o, method, args, methodProxy) -> method.invoke(method, args, t));
            }
            return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(), (proxy, method, args) -> method.invoke(method, args, t));
        } else {
            return t;
        }
    }
}
