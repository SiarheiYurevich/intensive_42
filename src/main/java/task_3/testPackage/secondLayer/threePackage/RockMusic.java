package testPackage.secondLayer.threePackage;

import annotation.IntensiveComponent_NikitaNazarjev;

@IntensiveComponent_NikitaNazarjev
public class RockMusic implements Musik{

    @Override
    public void playMusik() {
        System.out.println("Crazy Train");
    }
}
