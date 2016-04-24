package com.whalenut.winject;

import com.whalenut.winject.torpedo.Torpedo;

import javax.inject.Inject;

public class Aclass {

    private final Bclass b;
    private final Torpedo torpedo;

    @Inject
    public Aclass(final Bclass b, final Torpedo torpedo) {
        System.out.println("A constructor.");
        this.b = b;
        this.torpedo = torpedo;
        torpedo.inspect();
        b.printName();
        System.out.println("Constructor for class A constructed with " + b.toString());
    }


    public void tell() {
        System.out.println("This is a dependency graph management.");
    }
}
