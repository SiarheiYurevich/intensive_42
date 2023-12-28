package task3.main;

import task3.context.IntensiveContextImpl_MaximYasnogorodskiy;
import task3.context.IntensiveContext_MaximYasnogorodskiy;

public class Main {
    public static void main(String[] args) {
        IntensiveContext_MaximYasnogorodskiy context =
                new IntensiveContextImpl_MaximYasnogorodskiy("intensive_42/java");

        TestClassWithAnnotatedField testClass = context.getObject(TestClassWithAnnotatedField.class).orElse(null);

        assert testClass != null;

        testClass.test("Привет");
        testClass.getTestClass().test("Привет N2");
    }
}
