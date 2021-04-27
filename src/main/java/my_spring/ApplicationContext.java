package my_spring;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * @author Evgeny Borisov
 */
public class ApplicationContext {
    static final Map<Class,Object> singletonsCacheHandlerMap = new HashMap<>();
    private static final ApplicationContext applicationContext = new ApplicationContext();



    private ApplicationContext(){
        Reflections scanner = new Reflections("my_spring");
        Set<Class<?>> typesAnnotatedWith = scanner.getTypesAnnotatedWith(Singleton.class);
        for (Class<?> aClass : typesAnnotatedWith) {
            singletonsCacheHandlerMap.put(aClass,null);
        }
    }

    public ApplicationContext getInstance(){
        return applicationContext;
    }

    public static Object getObject(Class type) {
        type = ObjectFactory.getInstance().resolveRealImpl(type);
        Object aClass = singletonsCacheHandlerMap.get(type);

        if(aClass == null){
            aClass = ObjectFactory.getInstance().createObject(type);
            if(singletonsCacheHandlerMap.containsKey(type)){
                singletonsCacheHandlerMap.put(type,aClass);
            }
        }

        return aClass;
        //todo finish this
        // context should cache all objects, which class marked by @Singleton
        // in case object is not singleton, or still not present in cache
        // use ObjectFactory to create
    }

    /*private <T> Class<T> resolveRealImpl(Class<T> type) {
        if (type.isInterface()) {
            Class<T> implClass = config.getImplClass(type);
            if (implClass == null) {
                Set<Class<? extends T>> classes = scanner.getSubTypesOf(type);
                if (classes.size() != 1) {
                    throw new IllegalStateException("0 or more than one impl found for type " + type);
                }
                type = (Class<T>) classes.iterator().next();
            }else {
                type = implClass;
            }
        }
        return type;
    }*/

    /*private Class handleSingletonAnotation(Class type){

        aClass = singletonsCacheHandlerMap.get(type);
        if(aClass == null){
            if(type.isInterface()){
                Set<Class<?>> classes = scanner.getSubTypesOf(type);
                if (classes.size() != 1) {
                    throw new IllegalStateException("0 or more than one impl found for type " + type);
                }
                type = classes.iterator().next();
            }
            aClass = ObjectFactory.getInstance().createObject(type);
            singletonsCacheHandlerMap.put(type,aClass);
        }
        return aClass;
    }*/
}
