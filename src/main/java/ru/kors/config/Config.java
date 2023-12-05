package ru.kors.config;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> ifc);
}
