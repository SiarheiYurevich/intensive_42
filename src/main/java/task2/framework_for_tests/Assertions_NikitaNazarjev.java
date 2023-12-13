package framework_for_tests;

/**
 An auxiliary class for conducting tests.
 Contains methods that check the data and throw
 the framework_for_tests.TestFailedException error in case of
 incorrectly passed parameters.
 */
public class Assertions_NikitaNazarjev {


    /**
     * Check that the expected parameter is indeed true
     * @param expected
     * @throws TestFailedException error in case of non-compliance
     */
    public static void assertTrue(boolean expected) throws TestFailedException {
        if (!expected) {
            testFail();
        }
    }

    /**
     * checks that the expected parameter is false
     * @param expected
     * @throws TestFailedException error in case of non-compliance
     */
    public static void assertFalse(boolean expected) throws TestFailedException {
        if (expected) {
            testFail();
        }
    }

    /**
     * accepts two parameters expected and actual.
     * Checks them using the equals method and
     * throws an error in case of a discrepancy
     * @param expected
     * @param actual
     * @throws TestFailedException error in case of non-compliance
     */
    public static void assertEquals(Object expected, Object actual) throws TestFailedException {
        if (!expected.equals(actual)) {
            testFail();
        }
    }

    /**
     *Accepts two parameters expected and actual.
     * Checks by equals that the parameters are not the same.
     * If it matches, it throws an error
     * @param expected
     * @param actual
     */
    public static void assertNotEquals(Object expected, Object actual){
        if(expected.equals(actual)){
            testFail();
        }
    }

    private static void testFail(){
        throw new TestFailedException();
    }
}
