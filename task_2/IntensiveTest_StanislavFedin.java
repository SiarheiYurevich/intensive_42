package task_2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the method is running at the time of testing.
 * If a static method is marked with this annotation, then it will be invoked.
 * @author Stanislav Fedin
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface IntensiveTest_StanislavFedin {
}
