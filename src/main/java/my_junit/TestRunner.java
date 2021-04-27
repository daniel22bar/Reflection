package my_junit;

import lombok.SneakyThrows;

import java.lang.reflect.Method;


/**
 * @author Evgeny Borisov
 */
public class TestRunner {

    @SneakyThrows
    public void runAllTestsOfClass(String className) {
        Class<?> aClass = Class.forName(className);
        Method[] methods = aClass.getDeclaredMethods();
        boolean thereIsNoTestInClass = true;
        for (Method method : methods) {
            if(method.getName().startsWith("before")){
                method.invoke(aClass.getDeclaredConstructor().newInstance());
                break;
            }
        }
        for (Method method : methods) {
            if(method.getName().startsWith("test")){
                thereIsNoTestInClass = false;
                method.invoke(aClass.getDeclaredConstructor().newInstance());
            }
        }
        if(thereIsNoTestInClass){
            throw new Exception("There is no test in the given class");
        }
        //todo finish this by JUnit convension
    }
}
