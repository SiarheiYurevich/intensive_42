package service;

import java.io.IOException;
import java.util.List;

public interface SearchService {
     List<Class<?>> getClasses() throws ClassNotFoundException, IOException;
}
