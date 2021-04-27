package my_spring;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Evgeny Borisov
 */
public class JavaConfig implements Config {

    private Map<Class,Class> ifc2ImplClass = new HashMap<Class,Class>();

    public JavaConfig() {
        ifc2ImplClass.put(Speaker.class, ConsoleSpeaker.class);
    }

    @Override
    public <T> Class<T> getImplClass(Class<T> type) {
        return ifc2ImplClass.get(type);
    }
}








