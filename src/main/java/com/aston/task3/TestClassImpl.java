package com.aston.task3;

@IntensiveComponent_VitaliBoshko
public class TestClassImpl implements TestClass {

    private TestForInjectClass testForInjectClass;

    public void setTestForInjectClass(TestForInjectClass testForInjectClass) {
        this.testForInjectClass = testForInjectClass;
    }

    @Override
    public void run() {
        testForInjectClass.run();
        System.out.println("Executed method of TestClass " + this);
    }
}
