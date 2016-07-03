package com.whalenut.winject.inject;


import com.whalenut.winject.mapping.BasicMappingInstance;

/**
 * This is the main instance that will handle
 * the injection.
 */
public interface Injector {


    /**
     * Initiates a mapping from a supertype to a concrete type.
     *
     * @param from the base type to which you'll map
     * @param <T> The base type
     * @return A {@link BasicMappingInstance}
     */
    <T> BasicMappingInstance map(Class<T> from);

    <T> T create(Class<T> clazz);
}
