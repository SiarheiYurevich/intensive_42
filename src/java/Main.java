import context.IntensiveContext_YaroslavRulev;
import context.impl.IntensiveContextImpl_YaroslavRulev;
import testFolder.errorImpl.Boeing;
import testFolder.impl.Lada;

public class Main {
    public static void main(String[] args)  {
        IntensiveContext_YaroslavRulev context = new IntensiveContextImpl_YaroslavRulev("testFolder");
        Lada object = context.getObject(Lada.class);
        object.run();





    }

}




