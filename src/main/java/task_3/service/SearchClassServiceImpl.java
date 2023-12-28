package src.main.java.task_3.service;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Implementation of a search service for possible interface implementations.
 * @author Stanislav Fedin
 */
public class SearchClassServiceImpl  implements SearchClassService{

    /**
     * Searches for implementations of the passed interface.
     * If they are not present, an exception will be thrown.
     * If there is more than one implementation, an exception will be thrown
     * @param interfaceClass Interface whose implementations you need to look for
     * @param annotatedClasses List of annotated classes
     * @return Implementation of the specified interface
     * @throws RuntimeException Interface has 0 or more than one implementation
     */
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
