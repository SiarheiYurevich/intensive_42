package src.main.test;

import java.util.Objects;

public class TestClass {

    private final String str;

    public TestClass(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestClass testClass = (TestClass) o;
        return Objects.equals(str, testClass.str);
    }

    @Override
    public int hashCode() {
        return Objects.hash(str);
    }

    @Override
    public String toString() {
        return str;
    }
}
