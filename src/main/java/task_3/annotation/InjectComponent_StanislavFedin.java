package src.main.java.task_3.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Label for classes that need to be loaded into the context.
 * @author Stanislav Fedin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectComponent_StanislavFedin {
    String productName() default "";
}
