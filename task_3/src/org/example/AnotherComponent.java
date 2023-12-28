package org.example;

import org.example.annotation.IntensiveComponent_TimurAgeev;

public class AnotherComponent {
    @IntensiveComponent_TimurAgeev
    private MyComponent myComponent;

    public void doMyComponentAction(String message) {
        myComponent.printMessage(message);
    }

    public void setMyComponent(MyComponent myComponent) {
        this.myComponent = myComponent;
    }

    public MyComponent getMyComponent() {
        return myComponent;
    }
}
