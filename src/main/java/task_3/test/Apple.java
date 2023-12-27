package src.main.java.task_3.test;

import src.main.java.task_3.annotation.IntensiveComponent_StanislavFedin;

@IntensiveComponent_StanislavFedin
public class Apple implements Product{
    private final String variety;

    public Apple() {
        this.variety = "Gold";
    }

    @Override
    public String getProductName() {
        return variety;
    }
}
