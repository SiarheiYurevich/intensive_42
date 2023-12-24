import context.IntensiveContext_NikitaNazarjev;
import context.IntensiveContext_NikitaNazarjevImpl;
import testPackage.FirstClass;
import testPackage.FirstInterface;
import testPackage.secondLayer.threePackage.ClassicalMusik;
import testPackage.secondLayer.threePackage.Musik;
import testPackage.secondLayer.threePackage.MusikPlayer;

/**
 * A test class.
 * It shows that dependencies are being successfully implemented.
 * You can also see that an exception is thrown in the case of multiple implementations of the same bean
 */
public class Main {

    public static void main(String[] args) {

        IntensiveContext_NikitaNazarjev contextNikitaNazarjev = new IntensiveContext_NikitaNazarjevImpl("testPackage");

        FirstInterface object = contextNikitaNazarjev.getObject(FirstClass.class);

        System.out.println(object);
        System.out.println(object.getSecondClass());
        System.out.println(object.getSecondClass().getThreeClass());



        MusikPlayer musikPlayer = contextNikitaNazarjev.getObject(MusikPlayer.class);
        System.out.println(musikPlayer);

    }
}
