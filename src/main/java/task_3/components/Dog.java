package task_3.components;

import task_3.annotations.IntensiveComponent_AleksandrZmeyev;
import task_3.annotations.IntensivePostConstruct;

@IntensiveComponent_AleksandrZmeyev
public class Dog implements Animal {

    String name = "Bobik";

    @Override
    public void makeSound() {
        System.out.println("Woof! Woof!");
    }

    @IntensivePostConstruct
    public void wagTail() {
        System.out.println("The dog is wagging its tail...");
    }

    @Override
    public String toString() {
        return name;
    }
}
