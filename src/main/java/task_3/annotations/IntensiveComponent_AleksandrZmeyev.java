package task_3.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для классов,
 * которые необходимо загрузить в контекст.
 *
 * @author Aleksandr Zmeyev
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntensiveComponent_AleksandrZmeyev {
}
