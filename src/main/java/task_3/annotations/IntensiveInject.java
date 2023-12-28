package task_3.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для поля класса, помеченного аннотацией {@code IntensiveComponent_AleksandrZmeyev},
 * в которое необходимо внедрить объект из контекста.
 *
 * @author Aleksandr Zmeyev
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntensiveInject {
}
