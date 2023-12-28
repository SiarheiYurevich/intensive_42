import task_3.components.Cat;
import task_3.context.ApplicationContext;
import task_3.context.IntensiveContext_AleksandrZmeyev;

/**
 * @author Aleksandr Zmeyev
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new IntensiveContext_AleksandrZmeyev("task_3");
        Cat cat = context.getObject(Cat.class);
        cat.makeSound();
    }
}
