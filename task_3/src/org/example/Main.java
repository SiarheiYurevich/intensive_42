package org.example;

import org.example.context.IntensiveContext_TimurAgeev;
import org.example.context.IntensiveContext_TimurAgeevImpl;
import org.example.services.component.AnotherComponent;
import org.example.services.component.MyComponent;

public class Main {
    public static void main(String[] args) {
        IntensiveContext_TimurAgeev context = new IntensiveContext_TimurAgeevImpl("org.example");
        MyComponent myComponent = context.getObject(MyComponent.class);
        AnotherComponent anotherComponent = context.getObject(AnotherComponent.class);

        myComponent.printMessage("Hello world!");
        anotherComponent.doMyComponentAction("Fuck yeah");
    }
}