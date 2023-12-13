package framework_for_tests;

/**
 *An error is thrown if the test is not passed.
 */
public class TestFailedException extends Error {

    public TestFailedException(String message) {
        super(message);
    }

    public TestFailedException() {
        super();
    }
}
