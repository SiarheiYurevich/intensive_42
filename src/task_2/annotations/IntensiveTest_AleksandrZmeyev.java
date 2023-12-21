package task_2.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация {@code @IntensiveTest_AleksandrZmeyev} отмечает, что аннотированный
 * метод является тестовым. Тестовые методы должны быть объявлены как {@code public},
 * не являться {@code static} и не иметь параметров.
 *
 * @author Александр Змеев
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntensiveTest_AleksandrZmeyev {
}
