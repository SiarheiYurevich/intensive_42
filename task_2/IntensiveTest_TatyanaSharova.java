package task_2;

import java.lang.annotation.*;

/**
 *  Аннотация {@code @IntensiveTest_TatyanaSharova} отмечает, что аннотированный
 *  метод является тестовым
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface IntensiveTest_TatyanaSharova {

}
