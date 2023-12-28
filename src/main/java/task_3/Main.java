package src.main.java.task_3;

import src.main.java.task_3.context.IntensiveContextImpl_StanislavFedin;
import src.main.java.task_3.context.IntensiveContext_StanislavFedin;
import src.main.java.task_3.test.ListOfPurchases;

public class Main {
    public static void main(String[] args) throws Exception {
        IntensiveContext_StanislavFedin context = new IntensiveContextImpl_StanislavFedin("test");
        ListOfPurchases list = context.getObject(ListOfPurchases.class);
        list.listOfPurchases();
    }
}
