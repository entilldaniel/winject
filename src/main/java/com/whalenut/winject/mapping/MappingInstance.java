package com.whalenut.winject.mapping;


/**
 *
 *
 * @param <T> The base type
 * @param <T2> The target type
 */
public interface MappingInstance<T, T2> {

    Class<T2> get();

    BasicMappingInstance to(Class<T2> toClass);

}
