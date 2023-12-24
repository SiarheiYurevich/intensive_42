package context;

import annotation.IntensiveComponent_NikitaNazarjev;
import service.InjectionService;
import service.InjectionServiceImpl;
import service.SearchService;
import service.SearchServiceImpl;

import java.util.Map;
import java.util.HashMap;

/**
 *The implementation of {@link IntensiveContext_NikitaNazarjev}.
 * When creating, it accepts a package in a string representation
 * in which it searches for all components marked with the {@link IntensiveComponent_NikitaNazarjev}
 * annotation.
 */
public class IntensiveContext_NikitaNazarjevImpl implements IntensiveContext_NikitaNazarjev{

    /**
     * searches for classes in a given package and subpackages
     */
    private final SearchService SEARCH_SERVICE = SearchServiceImpl.getInstance();
    /**
     * Implements the logic of implementing the found dependencies
     */
    private final InjectionService INJECTION_SERVICE = InjectionServiceImpl.getInstance();

    /**
     * HashMap it stores objects of found components
     */
    private final Map<String, Object> BEANS = new HashMap<>();

    /**
     * The constructor accepts the package name in a string representation,
     * and then searches for all annotated classes in the specified package.
     * Then creates objects but with an empty constructor based on these objects
     * @param packageName  package name in a string representation,
     */

    public IntensiveContext_NikitaNazarjevImpl(String packageName){
        try {
            Class[] classes = SEARCH_SERVICE.getClasses(packageName);

            for(Class c : classes){
                if(c.isAnnotationPresent(IntensiveComponent_NikitaNazarjev.class)){
                    String name = SEARCH_SERVICE.getClassName(c);
                    Object object = c.newInstance();
                    BEANS.put(name, object);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * the method returns an object based on the specified parameter
     * @param type
     * @return the object of the class passed to the parameter
     * @param <T>
     */
    public <T> T getObject(Class<T> type)  {
        String name  = SEARCH_SERVICE.getClassName(type);
        Object object = BEANS.get(name);

        try {
            INJECTION_SERVICE.injectDependency(object, BEANS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (T) object;
    }

}
