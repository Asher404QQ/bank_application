package ru.kors.config.impl;

import org.reflections.Reflections;
import ru.kors.config.Config;

import java.util.Map;
import java.util.Set;

public class JavaConfig implements Config {
    private final System.Logger logger = System.getLogger(JavaConfig.class.getName());
    private Reflections scanner;
    private Map<Class, Class> interfaces4impl;

    public JavaConfig(String packageToScan, Map<Class, Class> interfaces4impl) {
        this.interfaces4impl = interfaces4impl;
        scanner = new Reflections(packageToScan);
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        return interfaces4impl.computeIfAbsent(ifc, aClass -> {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
            if (classes.size() != 1) {
                logger.log(System.Logger.Level.ERROR, "There can only be one impl");
                throw new RuntimeException();
            }
            return classes.iterator().next();
        });

    }
}
