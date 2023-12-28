package com.aston.task2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation uses to specify testing methods
 * to be automatically run using a special test runner
 * @author Vitali Boshko
 * @see TestRunner_VitaliBoshko
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntensiveTest_VitaliBoshko {

}
