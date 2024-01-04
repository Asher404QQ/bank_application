package ru.kors.util;

import ru.kors.config.Config;
import ru.kors.config.impl.JavaConfig;
import ru.kors.repository.AccountRepository;
import ru.kors.repository.impl.DBAccountRepositoryImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ObjectFactory {
    private static ObjectFactory instance = new ObjectFactory();
    private static Config config;

    private ObjectFactory(){}

    static {
        config = new JavaConfig(PropertiesUtil.get("package.to.scan"),
                new HashMap<>(Map.of(AccountRepository.class, DBAccountRepositoryImpl.class)));
    }

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
