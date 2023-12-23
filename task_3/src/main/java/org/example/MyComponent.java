package org.example;

import org.example.annotation.IntensiveComponent_TimurAgeev;

@IntensiveComponent_TimurAgeev
public class MyComponent {
    private String message = "Hello world";

    public MyComponent() {

    }

    public void printMessage() {
        System.out.println(message);
    }
}
