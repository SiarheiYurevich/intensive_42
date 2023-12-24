package testPackage.secondLayer;

import annotation.Autowired_NikitaNazarjev;
import annotation.IntensiveComponent_NikitaNazarjev;
import testPackage.secondLayer.threePackage.ThreeClass;

@IntensiveComponent_NikitaNazarjev
public class SecondClass {

    @Autowired_NikitaNazarjev
    private ThreeClass threeClass;
    @Override
    public String toString() {
        return "Im SecondClass";
    }

    public ThreeClass getThreeClass(){
        return threeClass;
    }

    public void setThreeClass(ThreeClass threeClass) {
        this.threeClass = threeClass;
    }
}
