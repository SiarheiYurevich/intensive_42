import task_2.TestRunner_StanislavFedin;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        Set<Package> set = ReflectionUtil.findPackage();

        List<Package> pak = set.stream().toList();
        
        TestRunner_StanislavFedin.testRunner(pak);
    }
}
