package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 *The annotation is placed above the class field.
 * The program uses it to put an object in this field.
 * The class of the object should be marked with
 * the annotation {@link IntensiveComponent_NikitaNazarjev}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Autowired_NikitaNazarjev {
}
