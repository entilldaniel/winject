package com.whalenut.winject;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Winject {

    Map<String, Object> graph;
    Map<String, MappingInstance> mappings;

    private Winject() {
        graph = new HashMap<>();
        mappings = new HashMap<>();
    }

    public static Winject init() {
        return new Winject();
    }

    public <T> MappingInstance map(Class<T> from) {
        MappingInstance mappingInstance = new MappingInstance(from);
        mappings.put(from.getName(), mappingInstance);
        return mappingInstance;
    }

    public <T> T create(Class<T> clazz) {
        if(clazz.getClass().equals(Provider.class)) {
            createProvider(clazz);
        }

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

    private <T> Provider<T> createProvider(final Class<T> clazz) {
        return null;
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
                .map(p -> create(p.getType()))
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
                    try {
                        field.setAccessible(true);
                        field.set(t, create(field.getType()));
                        field.setAccessible(false);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        throw new RuntimeException("Cannot access field: " + field.getName() + " in class: " + t.getClass().getCanonicalName());
                    }
                });
    }
}
