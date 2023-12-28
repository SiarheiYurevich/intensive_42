package task_3.scanner;

import task_3.annotations.IntensiveComponent_AnastasiaYaromich;
import java.io.File;
import java.net.URL;
import java.util.*;

public class ClassPathObjectScannerImpl implements ClassPathObjectScanner {
    private final String[] basePackages;
    private List<Class<?>> componentAnnotatedClasses;

    public ClassPathObjectScannerImpl(String... basePackages) {
        this.basePackages = basePackages;
        this.componentAnnotatedClasses = doScan(basePackages);
    }

    @Override
    public List<Class<?>> doScan(String... basePackages) {
        List<Class<?>> componentAnnotatedClasses = new ArrayList<>();

        for (String currentPackage : basePackages) {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Enumeration<URL> resources = null;
            try {
                resources = classLoader.getResources(currentPackage.replace(".", "/"));
                Iterable<URL> urls = resources::asIterator;
                List<File> dir = new ArrayList<>();
                for (URL url : urls) {
                    dir.add(new File(url.toURI().getPath()));
                }
                componentAnnotatedClasses = dir.stream()
                        .flatMap((File d) -> findComponentAnnotatedClasses(d, currentPackage).stream())
                        .toList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return componentAnnotatedClasses;
    }

    @Override
    public List<Class<?>> determineImplementations(Class<?> type) {
        List<Class<?>> implementations = new ArrayList<>();
        for (Class<?> c : componentAnnotatedClasses) {
            if(type.isAssignableFrom(c)) {
                implementations.add(c);
            }
        }
        return implementations;
    }

    private List<Class<?>> findComponentAnnotatedClasses(File directory, String basePackage) {

        ClassLoader classLoader = this.getClass().getClassLoader();
        List<Class<?>> classes = new ArrayList<>();

        File[] files = directory.listFiles();
        assert files != null;

        for (File file : files) {
            String fileName = file.getName();
            if(file.isDirectory()) {
                classes.addAll(findComponentAnnotatedClasses(file, basePackage + "." + fileName));
            } else if(fileName.endsWith(".class")) {
                String currentClassName = basePackage + "." + fileName.substring(0, fileName.length() - 6);
                Class<?> currentClass = null;
                try {
                    currentClass = classLoader.loadClass(currentClassName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                assert currentClass != null;
                if(currentClass.isAnnotationPresent(IntensiveComponent_AnastasiaYaromich.class)) {
                    classes.add(currentClass);
                }
            }
        }
        return classes;
    }
}
