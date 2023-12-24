package service;

import annotation.Autowired_NikitaNazarjev;
import exception.BeanNotFoundException;
import exception.MultipleImplementationsException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * The service singleton class. It is used to embed dependencies
 * into the object passed to it. It is able to search for fields
 * marked with the {@link Autowired_NikitaNazarjev} annotation and embed
 * the necessary objects in them. It also recursively implements
 * the dependencies needed to create other dependencies.
 * IT is IMPORTANT to introduce dependency into the field. The class must have a setter for this field.
 */
public class InjectionServiceImpl implements InjectionService{



    private InjectionServiceImpl(){}

    /**
     * singleton object
     */
    private static InjectionServiceImpl instance;

    /**
     * @return a singleton object
     */
    public static InjectionServiceImpl getInstance(){
        if(instance == null){
            instance = new InjectionServiceImpl();
        }
        return instance;
    }


    /**
     * The method uses recursion to traverse the annotated fields of
     * the class and implement dependencies. If the dependency has its
     * own dependencies, the method falls into it and so on until it reaches the end
     * @param object the object to embed dependencies in
     * @param beans which stores (The class of the object in the string representation and its object)
     */
    public  void injectDependency(Object object, Map<String, Object> beans) {
        List<Field> annotetedFields = getAnnotetedFields(object.getClass());

        for (Field field : annotetedFields) {
            boolean beanInstalled = false;

            for (Object bean : beans.values()) {

                if(field.getType().isInstance(bean)){
                    if(beanInstalled){
                        throwAnExceptionThatTheBeanAlreadyExists(field, bean);
                    }
                    beanInstall(object, beans, field, bean);
                    beanInstalled = true;
                }
            }

            if(!beanInstalled){
                throwAnExceptionThatTheBeanWasNotFound(field);
            }
        }

    }

    private void beanInstall(Object object, Map<String, Object> beans, Field field, Object bean)  {
        try {
            injectDependency(bean, beans);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String methodName = createSetterMethodName(field);
        setBeansInField(object, bean,methodName);
    }


    /**
     * An auxiliary method that sets its dependency to the passed object using setter
     * @param originalObject the object to be embedded in
     * @param bean the dependency that we will implement
     * @param methodName setter name for Method
     */
    private void setBeansInField(Object originalObject, Object bean, String methodName){
        Method[] methods = originalObject.getClass().getMethods();
        for(Method method : methods){
            if(method.getName().equals(methodName)){
                try {
                    method.invoke(originalObject, bean);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     *Searches for all fields in the class marked with the annotation {@link Autowired_NikitaNazarjev}
     * @param clazz
     * @return list fields marked annotation
     */
    private List<Field> getAnnotetedFields(Class clazz){
        Field[] declaredFields = clazz.getDeclaredFields();
            List<Field> annotatedField = Arrays.stream(declaredFields).filter(a->a.isAnnotationPresent(Autowired_NikitaNazarjev.class)).toList();
        return annotatedField;
    }


    /**
     * an auxiliary method. Creates a setter based on the field name
     * @param field
     * @return setter method name
     */
    private String createSetterMethodName(Field field){
        StringBuilder builder = new StringBuilder();
        builder.append("set");
        builder.append(field.getName().substring(0,1).toUpperCase());
        builder.append(field.getName().substring(1));
        return builder.toString();
    }

    /**
     * the method throws an exception if there are several suitable bins for this field
     * @param field
     * @param bean
     */
    private void throwAnExceptionThatTheBeanAlreadyExists(Field field, Object bean) {
        try {
            throw new MultipleImplementationsException(field.getName() + " already initialized. " + bean + " conflicts with another implementation");
        } catch (MultipleImplementationsException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * the method throws an exception if there is no suitable bin for this field
     * @param field
     */
    private void throwAnExceptionThatTheBeanWasNotFound(Field field) {
        try {
            throw new BeanNotFoundException();
        } catch (BeanNotFoundException e) {
            throw new RuntimeException("For the field: "+ field +" no bin found.");
        }
    }

}
