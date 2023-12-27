package com.aston.task3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation indicates that the marked class is a candidate
 * for auto-detection creating an instance of this class and saving it in the context
 */
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface IntensiveComponent_VitaliBoshko {

}
