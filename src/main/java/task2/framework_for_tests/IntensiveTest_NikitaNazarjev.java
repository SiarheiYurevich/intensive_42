package framework_for_tests;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *With this annotation, you can mark static methods
 * that need to be tested using the framework_for_tests.
 * TestRunner_NikitaNazarjev class
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntensiveTest_NikitaNazarjev {
}
