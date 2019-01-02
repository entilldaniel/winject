package com.whalenut.winject;


import com.whalenut.winject.inject.DefaultInjector;
import com.whalenut.winject.inject.Injector;

public abstract class Winject {

    /**
     * Initializes the injection system using the current classloader.
     *
     * @return {@link Injector}
     */
    public static Injector init() {
        return new DefaultInjector();
    }

}
