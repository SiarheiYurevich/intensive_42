package org.example;

import org.example.context.IntensiveContext_TimurAgeev;
import org.example.context.IntensiveContext_TimurAgeevImpl;

public class Main {
    public static void main(String[] args) {
        IntensiveContext_TimurAgeev context = new IntensiveContext_TimurAgeevImpl("org.example");
        MyComponent myComponent = context.getObject(MyComponent.class);
        MyComponent myComponent2 = context.getObject(MyComponent.class);

        myComponent.printMessage("Hello world!");
        myComponent2.printMessage("Fuck yeah!");
    }
}