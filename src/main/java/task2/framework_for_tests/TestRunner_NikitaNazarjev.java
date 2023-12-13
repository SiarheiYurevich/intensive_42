package framework_for_tests;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.List;


/**
 * The class is designed for conducting tests.
 * It works in conjunction with the @framework_for_tests.IntensiveTest_NikitaNazarjev
 * annotation and the framework_for_tests.Assertions_NikitaNazarjev auxiliary class.
 * To perform tests, you need to mark the static method with the
 * annotation @framework_for_tests.IntensiveTest_NikitaNazarjev. If the method is not static,
 * the test throws a NullPointerException. Next, you need to run the run
 * method by passing a package in string format or a list of packages to it.
 * The run method will recursively go through the packages, find all the
 * annotations and run the tests.
 */
public class TestRunner_NikitaNazarjev {

    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_WHITE = "\u001B[0m";

    private static final String SUCCESSFUL_RESULT_TEST = "Class: %s has been tested. Successful tests: %d, Failed tests: %d"+ "\n";

    private static final String ERROR_MESSAGE = ANSI_RED + "Test: %s failed. Exception: %s" + ANSI_WHITE + "\n";


    /**
     *The starter method goes through the list of packages and runs,
     *  searches for classes in them and runs test methods marked with
     *  the annotation @framework_for_tests.IntensiveTest_NikitaNazarjev.
     * @param packageName a list of lines with the name of the packages to be tested
     * @throws IOException you can catch an exception if the packages are set incorrectly
     * @throws ClassNotFoundException you can catch an exception if no classes are found
     */
    public static void run(List<String> packageName) throws IOException, ClassNotFoundException {
        for(String p : packageName){
            Class[] classes = PackageParser.getClasses(p);
            for (Class c : classes){
                testOfClassMethods(c);
            }
        }
    }

    /**
     * the overload of the method, which allows searching for
     * a single package, includes a string with the package name
     * @param packageName the string with the package name
     * @throws IOException you can catch an exception if the packages are set incorrectly
     * @throws ClassNotFoundException  you can catch an exception if no classes are found
     */
    public static void run(String packageName) throws IOException, ClassNotFoundException {
       Class[] classes = PackageParser.getClasses(packageName);
        for (Class c : classes){
            testOfClassMethods(c);
        }

    }

    /**
     * The method passes through the class using reflection and
     * launches static methods marked with the annotation
     * @IntensiveTest_NikitaNazarjev. If successful, it increases
     * the counter of successfully completed tests and moves on.
     * If an error is excluded, it displays the class and method in
     * which the error occurred and stops working. After all the tests,
     * it outputs the result for this class to the console. Please note
     * that this method only works with static methods, otherwise it
     * throws a NullPointerException.
     * @param testClass the class under test.
     */
    private static void testOfClassMethods(Class testClass) {
        int test = 0;
        int successful = 0;
        int failed = 0;
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(IntensiveTest_NikitaNazarjev.class)) {
                test++;
            } else {
                continue;
            }
            try {
                method.invoke(null);
                successful++;
            } catch (InvocationTargetException wrappedExc) {
                failed++;
                Throwable exc = wrappedExc.getCause();
                System.out.printf(ERROR_MESSAGE,method.getName(),exc);
            } catch (IllegalAccessException exc) {
                failed++;
                System.out.println("Invalid @Test: " + method);
            }
        }
        if(failed == 0){
            System.out.printf(ANSI_GREEN + SUCCESSFUL_RESULT_TEST, testClass.getName(), successful, failed);
        }else {
            System.out.printf(ANSI_RED + SUCCESSFUL_RESULT_TEST, testClass.getName(), successful, failed);
        }

    }
}
