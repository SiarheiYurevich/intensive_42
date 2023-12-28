package task_3.component.subcomponent.impl;

import task_3.IntensiveComponent_SlavaSles;
import task_3.component.subcomponent.Vehicle;

@IntensiveComponent_SlavaSles
public class Car implements Vehicle {

    @Override
    public void ride() {
        System.out.println("Машина быстро едет");
    }
}
