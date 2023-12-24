package org.example;

public class Main {
    public static void main(String[] args) {
        org.example.IntensiveContext_TimurAgeev context = new org.example.IntensiveContext_TimurAgeev("org.example");
        org.example.MyComponent myComponent = context.getObject(org.example.MyComponent.class);

        myComponent.printMessage();
    }
}