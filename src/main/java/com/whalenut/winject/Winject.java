package com.whalenut.winject;


import com.whalenut.winject.inject.DefaultInjector;
import com.whalenut.winject.inject.Injector;

public class Winject {

    private Winject(ClassLoader loader) {
        //Noop
    }

    /**
     * Initializes the injection system using the current classloader.
     *
     * @return {@link Injector}
     */
    public static Injector init() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return new DefaultInjector(classLoader);
    }

    /**
     * Initializes the injection system using a specified classloader.
     *
     * @return {@link Injector}
     */
    public static Injector init(ClassLoader classLoader) {
        return new DefaultInjector(classLoader);
    }

}
