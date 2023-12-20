package test_framework;

import java.lang.annotation.*;

/**
 *  Аннотация для пометки теста как интенсивного.
 *  Аннотация должна использоваться на уровне метода.
 *  Тесты, помеченные этой аннотацией,
 *  получат особое внимание при тестировании.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface IntensiveTest_GalinaSamokhina {
}