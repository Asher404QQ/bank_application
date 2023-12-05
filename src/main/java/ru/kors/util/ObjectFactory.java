package ru.kors.util;

import ru.kors.config.Config;
import ru.kors.config.impl.JavaConfig;

import java.lang.reflect.InvocationTargetException;

public class ObjectFactory {
    private static ObjectFactory instance = new ObjectFactory();
    private Config config = new JavaConfig("ru.kors");

    private ObjectFactory(){}

    public static ObjectFactory getInstance() {
        return instance;
    }

    public <T> T createObject(Class<T> type) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
        return implClass.getDeclaredConstructor().newInstance();
    }
}
