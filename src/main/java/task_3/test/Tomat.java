package src.main.java.task_3.test;

import src.main.java.task_3.annotation.IntensiveComponent_StanislavFedin;

@IntensiveComponent_StanislavFedin
public class Tomat implements Product{

    private final String variety;

    public Tomat() {
        this.variety = "Cherry";
    }

    @Override
    public String getProductName() {
        return variety;
    }
}
