package task_3.component.subcomponent;

import task_1.impl.ArrayList_SlavaSles;
import task_3.IntensiveComponent_SlavaSles;

@IntensiveComponent_SlavaSles
public class IntensiveArrayList2_SlavaSles<T> extends ArrayList_SlavaSles<T> {
    public void printElements() {
        for (int i = 0; i < super.size() - 1; i++) {
            System.out.println(super.get(i));
        }
    }
}
