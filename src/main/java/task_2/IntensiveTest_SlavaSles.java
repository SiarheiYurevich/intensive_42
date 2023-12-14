package task_2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, маркирующая тестовые методы, для последующего запуска с помощью {@link TestRunner_SlavaSles}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IntensiveTest_SlavaSles {
}
