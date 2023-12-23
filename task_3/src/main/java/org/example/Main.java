package org.example;

public class Main {
    public static void main(String[] args) {
        IntensiveContext_TimurAgeev context = new IntensiveContext_TimurAgeev("org.example");
        MyComponent myComponent = context.getObject(MyComponent.class);

        myComponent.printMessage();
    }
}