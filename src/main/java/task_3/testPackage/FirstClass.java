package testPackage;

import annotation.Autowired_NikitaNazarjev;
import annotation.IntensiveComponent_NikitaNazarjev;
import testPackage.secondLayer.SecondClass;

@IntensiveComponent_NikitaNazarjev
public class FirstClass implements FirstInterface{

    @Autowired_NikitaNazarjev
    private SecondClass secondClass;

    @Override
    public String toString() {
        return "Im FirstClass";
    }


    public SecondClass getSecondClass(){
        return secondClass;
    }

    public void setSecondClass(SecondClass secondClass) {
        this.secondClass = secondClass;
    }
}
