package task_3.component.subcomponent.impl;

import task_3.component.subcomponent.Vehicle;

/**
 * Тестовый класс для проверки работы приложения
 */
//@IntensiveComponent_SlavaSles
public class Truck implements Vehicle {
    @Override
    public void ride() {
        System.out.println("Грузовик едет медленно");
    }
}
