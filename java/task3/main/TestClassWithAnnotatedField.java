package task3.main;

import task3.annotation.IntensiveComponent_MaximYasnogorodskiy;

@IntensiveComponent_MaximYasnogorodskiy
public class TestClassWithAnnotatedField {

    private TestClass testClass;

    public void test(String message) {
        System.out.println(message);
    }

    public TestClass getTestClass() {
        return testClass;
    }
}
