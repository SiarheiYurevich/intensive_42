import task_1.impl.ArrayList_SlavaSles;
import task_1.IntensiveList;
import task_2.Assertions_SlavaSles;
import task_2.TestRunner_SlavaSles;
import task_3.IntensiveContext_SlavaSles;
import task_3.component.subcomponent.IntensiveArrayList2_SlavaSles;
import task_3.service.SearchService;
import task_3.service.impl.SearchServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        IntensiveContext_SlavaSles intensiveContextSlavaSles =
                new IntensiveContext_SlavaSles("task_3.component");
        IntensiveArrayList2_SlavaSles list = intensiveContextSlavaSles.getObject(IntensiveArrayList2_SlavaSles.class);
        System.out.println(list.toString() + " - " + list.size());
    }
}
