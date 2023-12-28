package src.main.java.task_3.annotation;

import src.main.java.task_3.context.IntensiveContext_StanislavFedin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Label to inject the specified dependency into the annotated {@link IntensiveContext_StanislavFedin} object.
 * @author Stanislav Fedin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IntensiveComponent_StanislavFedin {
}
