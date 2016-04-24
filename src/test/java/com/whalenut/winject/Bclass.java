package com.whalenut.winject;

import com.whalenut.winject.torpedo.Torpedo;

import javax.inject.Inject;

public class Bclass {

    private final Cclass c;
    private final Torpedo torpedo;

    @Inject
    public Bclass(Cclass c, Torpedo torpedo) {
        System.out.println("B constructor");
        this.c = c;
        this.torpedo = torpedo;
        torpedo.inspect();
    }

    public void printName() {
        System.out.println("Bclass instance: " + this.toString());
        System.out.println("B CLASS::" + torpedo.getClass().getCanonicalName());
    }
}
