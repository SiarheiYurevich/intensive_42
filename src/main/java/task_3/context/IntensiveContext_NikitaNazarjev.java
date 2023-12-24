package context;


/**
 * This interface defines a method for obtaining an
 * object of a specified type within the intensive context.
 */
public interface IntensiveContext_NikitaNazarjev {

    /**
     * This method is used to obtain an object of the specified type within the intensive context.
     * @param - the class or interface that you want to create an object from
     * @return an object based on a parameter specified in the method
     * @param <T>
     */
    <T> T getObject(Class<T> type);
}
