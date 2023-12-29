package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * Обозначает, что поле или метод является местом внедрения bean зависимости.
 * Аннотация `@IntensiveInject_YaroslavRulev` позволяет легко определить поля и методы,
 * в которых требуется выполнить инъекцию зависимости.
 *
 * <p>Интенсивная инъекция зависимости используется для автоматического внедрения зависимостей
 * в поля или методы, что позволяет избежать ручного создания и установки зависимостей.
 * Аннотация `@IntensiveInject_YaroslavRulev` служит маркерной аннотацией.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface IntensiveInject_YaroslavRulev {
}
