package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Обозначает, что класс является bean компонентом.
 * Интенсивные компоненты обычно используются для инъекции зависимостей в приложении.
 * Аннотация класса `@IntensiveComponent_YaroslavRulev` позволяет легко определить класс как интенсивный компонент.
 *
 * <p>Аннотация {@code IntensiveComponent_YaroslavRulev} является пользовательской аннотацией,
 * которая не предоставляет дополнительной функциональности по умолчанию. Она служит маркерной аннотацией,
 * позволяющей обозначить класс как bean компонент.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface IntensiveComponent_YaroslavRulev {
}
