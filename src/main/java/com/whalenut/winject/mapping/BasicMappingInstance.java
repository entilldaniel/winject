package com.whalenut.winject.mapping;

import java.lang.reflect.Modifier;

public class BasicMappingInstance<T, T2> implements MappingInstance<T2> {

    private Class<T2> toClass;

    public BasicMappingInstance(Class<T> fromClass) {
        if (isConcrete(fromClass)) {
            throw new IllegalArgumentException("Class must be either a interface or abstract!");
        }
    }

    private boolean isConcrete(final Class<T> fromClass) {
        return !(Modifier.isInterface(fromClass.getModifiers()) || Modifier.isAbstract(fromClass.getModifiers()));
    }

    @Override
    public BasicMappingInstance to(Class<T2> toClass) {
        this.toClass = toClass;
        return this;
    }

    @Override
    public Class<T2> get() {
        return toClass;
    }
}
