package com.whalenut.winject.mapping;

import java.lang.reflect.Modifier;

public class BasicMappingInstance<FROM, TO> implements MappingInstance<TO> {

    private Class<TO> to;

    public BasicMappingInstance(Class<FROM> from) {
        if (isConcrete(from)) {
            throw new IllegalArgumentException("Class must be either a interface or abstract!");
        }
    }

    private boolean isConcrete(final Class<FROM> from) {
        return !(Modifier.isInterface(from.getModifiers()) || Modifier.isAbstract(from.getModifiers()));
    }

    @Override
    public BasicMappingInstance to(Class<TO> clazz) {
        this.to = clazz;
        return this;
    }

    @Override
    public Class<TO> get() {
        return to;
    }
}
