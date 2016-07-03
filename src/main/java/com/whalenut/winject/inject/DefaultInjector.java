package com.whalenut.winject.inject;


import com.whalenut.winject.mapping.BasicMappingInstance;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class DefaultInjector implements Injector {

    private final Map<String, Object> graph;
    private final Map<String, BasicMappingInstance> mappings;

    private final ClassLoader loader;


    public DefaultInjector(final ClassLoader loader) {
        graph = new HashMap<>();
        mappings = new HashMap<>();
        this.loader = loader;
    }

    @Override
    public <T> BasicMappingInstance map(Class<T> from) {
        BasicMappingInstance basicMappingInstance = new BasicMappingInstance(from);
        mappings.put(from.getName(), basicMappingInstance);
        return basicMappingInstance;
    }

    @Override
    public <T> T create(Class<T> clazz) {

        if(mappings.containsKey(clazz.getName())) {
            clazz = mappings.get(clazz.getName()).get();
        }

        if(clazz.isAnnotationPresent(Singleton.class) && graph.containsKey(clazz.getName())) {
            return (T) graph.get(clazz.getName());
        }

        T instance;
        Optional<? extends Constructor<?>> constructor = getInjectableConstructor(clazz);
        if(!constructor.isPresent()) {
            instance = checkNoArgs(clazz);
        } else {
            instance = buildTree(constructor);
        }

        return instance;
    }

    private Provider<?> createProvider(Type type) {
        return () -> {
            try {
                return create(Class.forName(type.getTypeName()));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Could not create provider", e);
            }
        };
    }

    private <T> Optional<? extends Constructor<?>> getInjectableConstructor(final Class<T> clazz) {
        return Arrays.asList(clazz.getConstructors()).stream().filter(ctor -> {
            Optional<Inject> injectableConstructor = Optional.ofNullable(ctor.getAnnotation(Inject.class));
            return injectableConstructor.isPresent();
        }).findFirst();
    }

    private <T> T buildTree(final Optional<? extends Constructor<?>> constructor) {
        Constructor<?> injectableConstructor = constructor.get();
        Object[] objects = Stream.of(injectableConstructor.getParameters())
                .map(p -> {
                    if(p.getType().equals(Provider.class)) {
                        return createProvider(((ParameterizedType)p.getParameterizedType()).getActualTypeArguments()[0]);
                    }
                    return create(p.getType());
                })
                .collect(Collectors.toList()).toArray();

        try {
            T instance = (T) injectableConstructor.newInstance(objects);
            populateFields(instance);
            graph.put(instance.getClass().getName(), instance);
            return instance;
        } catch (InstantiationException | IllegalAccessException |InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Could not create instance!");
    }

    private <T> T checkNoArgs(final Class<T> clazz) {
        Optional<? extends Constructor<?>> noArgsConstructor = Arrays.asList(clazz.getConstructors())
                .stream()
                .filter(constructor -> constructor.getParameterCount() == 0)
                .findFirst();

        if(noArgsConstructor.isPresent()) {
            try {
                T instance = clazz.newInstance();
                populateFields(instance);
                graph.put(instance.getClass().getName(), instance);
                return instance;
            } catch (RuntimeException | IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
        throw new IllegalArgumentException("No injectable or no arguments constructor found for class " + clazz.getName());
    }

    private <T> void populateFields(T t) {
        Arrays.stream(t.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Inject.class))
                .forEach(field -> {
                    try { //TODO: Fix bad settings of fields.
                        boolean accessible = field.isAccessible();
                        field.setAccessible(true);
                        field.set(t, create(field.getType()));
                        field.setAccessible(accessible);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        throw new RuntimeException("Cannot access field: " + field.getName() + " in class: " + t.getClass().getCanonicalName());
                    }
                });
    }

}
