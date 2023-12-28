package task_3.component.subcomponent.impl;

import task_3.context.IntensiveComponent_SlavaSles;
import task_3.component.subcomponent.Vehicle;

/**
 * Тестовый класс для проверки работы приложения
 */
@IntensiveComponent_SlavaSles
public class Car implements Vehicle {

    @Override
    public void ride() {
        System.out.println("Машина быстро едет");
    }
}
