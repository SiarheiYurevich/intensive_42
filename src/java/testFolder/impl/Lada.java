package testFolder.impl;

import annotation.IntensiveComponent_YaroslavRulev;

@IntensiveComponent_YaroslavRulev
public class Lada implements Car {
    @Override
    public void run() {
        System.out.println("run");

    }
}
