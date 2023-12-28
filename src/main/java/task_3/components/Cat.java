package task_3.components;

import task_3.annotations.IntensiveComponent_AleksandrZmeyev;
import task_3.annotations.IntensiveInject;

@IntensiveComponent_AleksandrZmeyev
public class Cat implements task_3.components.Animal {

    String name = "Chipi";
    @IntensiveInject
    Dog friend;
    @Override
    public void makeSound() {
        System.out.println("A cat named " + name + " meows to its friend " + friend);
    }

    public void purr() {
        System.out.println("The cat is purring...");
    }

    @Override
    public String toString() {
        return name;
    }
}
