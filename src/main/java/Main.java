
import task_3.IntensiveContext_SlavaSles;
import task_3.component.InputMessage;
import task_3.component.subcomponent.IntensiveArrayList2_SlavaSles;
import task_3.component.subcomponent.Vehicle;

public class Main {
    public static void main(String[] args) {
        IntensiveContext_SlavaSles intensiveContext = new IntensiveContext_SlavaSles("task_3.component");
        IntensiveArrayList2_SlavaSles list = intensiveContext.getObject(IntensiveArrayList2_SlavaSles.class);
        System.out.println(list.toString() + " - " + list.size());

        Vehicle vehicle = intensiveContext.getObject(Vehicle.class);
        vehicle.ride();

        InputMessage inputMessage = intensiveContext.getObject(InputMessage.class);
        inputMessage.inputMessage();
        inputMessage.getPrintMessage().setMessage(inputMessage.getMessage());
        inputMessage.getPrintMessage().printMessage();
    }
}
