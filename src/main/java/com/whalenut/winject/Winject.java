package com.whalenut.winject;


import com.whalenut.winject.inject.DefaultInjector;
import com.whalenut.winject.inject.Injector;

public class Winject {

    private Winject(ClassLoader loader) {
        //Noop
    }

    public static Injector init() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return new DefaultInjector(classLoader);
    }

    public static Injector init(ClassLoader classLoader) {
        return new DefaultInjector(classLoader);
    }

}
