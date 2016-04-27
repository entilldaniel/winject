package com.whalenut.winject;

import javax.inject.Inject;

public class Aclass {

    private final Bclass b;

    @Inject
    public Aclass(final Bclass b) {
        System.out.println("A constructor.");
        this.b = b;
        b.printName();
        System.out.println("Constructor for class A constructed with " + b.toString());
    }


    public void tell() {
        System.out.println("This is a dependency graph management.");
    }
}
