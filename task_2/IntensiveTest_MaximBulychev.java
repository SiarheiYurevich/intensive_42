package task_2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Аннотация {@code @IntensiveTest_MaximBulychev} отмечает, что аннотированный
 * метод является тестовым. Тестовые методы должны быть объявлены как {@code public}
 * и не {@code static}, а также они не должны иметь параметров.
 * Тестам также не следует зависеть от порядка выполнения тестовых методов, так как
 * не гарантируется, что порядок их выполнения будет соответствовать порядку объявления.
 * Класс, содержащий тестовые методы, обязательно должен иметь конструктор без аргументов.
 *
 * @author Максим Булычев
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntensiveTest_MaximBulychev {
}
