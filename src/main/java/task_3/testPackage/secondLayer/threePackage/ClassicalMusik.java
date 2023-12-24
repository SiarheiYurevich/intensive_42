package testPackage.secondLayer.threePackage;

import annotation.IntensiveComponent_NikitaNazarjev;

@IntensiveComponent_NikitaNazarjev
public class ClassicalMusik implements Musik{

    @Override
    public void playMusik() {
        System.out.println("Bohemian rhapsody");
    }
}
