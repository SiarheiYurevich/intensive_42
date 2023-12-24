package service;

import java.io.IOException;


/**
 * An interface for searching for classes in a given package and under packages. And for convenient work with class names
 */
public interface SearchService {


    /**
     * The method pulls out the short name of the file (class )
     * @param fullName
     * @return short class name
     */
     String getClassName(Class fullName);


    /**
     * The method finds all classes in the specified package and subpackages
     * @param packageName
     * @return all classes in package
     * @throws ClassNotFoundException
     * @throws IOException
     */
    Class[] getClasses(String packageName) throws ClassNotFoundException, IOException;
}
