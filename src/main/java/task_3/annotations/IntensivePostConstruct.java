package task_3.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для метода класса, помеченного аннотацией {@code IntensiveComponent_AleksandrZmeyev}.
 * При наличии данной аннотации метод будет вызываться
 * как "вторая фаза" конструктора на этапе создания объекта.
 *
 * @author Aleksandr Zmeyev
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntensivePostConstruct {
}
