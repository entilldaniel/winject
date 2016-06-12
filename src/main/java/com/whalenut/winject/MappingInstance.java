package com.whalenut.winject;

import java.lang.reflect.Modifier;

public class MappingInstance<T, T2> {

    private final Class<T> fromClass;
    private Class<T2> toClass;

    public MappingInstance(Class<T> fromClass) {
        if (isConcrete(fromClass)) {
            throw new IllegalArgumentException("Class must be interface or abstract!");
        }
        this.fromClass = fromClass;
    }

    private boolean isConcrete(final Class<T> fromClass) {
        return !(Modifier.isInterface(fromClass.getModifiers()) || Modifier.isAbstract(fromClass.getModifiers()));
    }

    public MappingInstance to(Class<T2> toClass) {
        this.toClass = toClass;
        return this;
    }

    public Class<T2> get() {
        return toClass;
    }
}
