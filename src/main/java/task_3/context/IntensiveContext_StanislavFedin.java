package src.main.java.task_3.context;

/**
 * An interface through which you can select the required context
 * implementation for reflective loading of annotated classes.
 * @author Stanislav Fedin
 */
public interface IntensiveContext_StanislavFedin {

    /**
     * Returns an instance of the passed class.
     * If the instance does not yet exist, then the method creates it, saves it and returns it.
     * If an interface was passed, it returns its implementation.
     * @param type Type of object to get
     * @return Object cast to the required type
     * @throws Exception If the passed interface has no implementation or has more than one implementation, then an error will occur
     */
    <T> T getObject(Class<T> type) throws Exception;
}
