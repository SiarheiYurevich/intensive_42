package task_3.context;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, маркирующая классы, которые будут добавляться в контекст приложения {@link IntensiveContext_SlavaSlesImp} с
 * помощью сервиса {@link task_3.service.SearchService}
 * @author Slava Sles
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface IntensiveComponent_SlavaSles {
}
