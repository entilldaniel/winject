package com.whalenut.winject;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.*;

public class Winject {

    Map<String, Object> graph;

    private Winject() {
        graph = new HashMap<>();
    }

    public static Winject init() {
        return new Winject();
    }

    public <T> T create(final Class<T> clazz) {
        Optional<? extends Constructor<?>> constructor = Arrays.asList(clazz.getConstructors()).stream().filter(ctor -> {
            Optional<Inject> injectableConstructor = Optional.ofNullable(ctor.getAnnotation(Inject.class));
            return injectableConstructor.isPresent();
        }).findFirst();

        if(!constructor.isPresent()) {
            return checkNoArgs(clazz);
        } else {
            Constructor<?> injectableConstructor = constructor.get();
            List<Parameter> parameters = Arrays.asList(injectableConstructor.getParameters());
            List<Object> params = new ArrayList<>(parameters.size());
            for(Parameter param : parameters) {
                params.add(create(param.getType()));
            }
            Object[] objects = params.toArray();
            try {
                T instance = (T) injectableConstructor.newInstance(objects);
                graph.put(instance.getClass().getCanonicalName(), instance);
                return instance;
            } catch (InstantiationException | IllegalAccessException |InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        throw new IllegalArgumentException("Could not create instace!");
    }

    private <T> T checkNoArgs(final Class<T> clazz) {
        Optional<? extends Constructor<?>> noArgsConstructor = Arrays.asList(clazz.getConstructors())
                .stream()
                .filter(constructor -> constructor.getParameterCount() == 0)
                .findFirst();

        if(noArgsConstructor.isPresent()) {
            try {
                T t = clazz.newInstance();
                System.out.println(t.getClass().getCanonicalName());
                graph.put(t.getClass().getCanonicalName(), t);
                return t;
            } catch (IllegalAccessException | InstantiationException e) {
                //Let it continue to Illegalargumentexception.
            }
        }
        throw new IllegalArgumentException("No injectable or no arguments constructor found for class " + clazz.getCanonicalName());
    }
}
