package src.main.java.task_3.service;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class SearchClassServiceImpl  implements SearchClassService{
    @Override
    public <T> Class<T> getImplClass(Class<T> interfaceClass, Map<String, Class> annotatedClasses) {
        AtomicReference<Class<? extends T>> implClass = new AtomicReference<>();
        AtomicInteger counter = new AtomicInteger(0);

        annotatedClasses.entrySet()
                .forEach(key -> {
                    if (interfaceClass.isAssignableFrom(key.getValue())) {
                        counter.getAndIncrement();
                        implClass.set(key.getValue());
                    }
                });

        if (counter.get() != 1)
            throw new RuntimeException(interfaceClass.getName() + " has 0 or more than one implementation");

        return (Class<T>) implClass.getPlain();
    }
}
