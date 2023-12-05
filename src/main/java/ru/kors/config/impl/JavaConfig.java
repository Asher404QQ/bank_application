package ru.kors.config.impl;

import org.reflections.Reflections;
import ru.kors.config.Config;

import java.util.Set;

public class JavaConfig implements Config {
    private Reflections scanner;

    public JavaConfig(String packageToScan) {
        scanner = new Reflections(packageToScan);
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
        return classes.iterator().next();
    }
}
