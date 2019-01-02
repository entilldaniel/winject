package com.whalenut.winject.real;

import com.whalenut.winject.real.torpedo.Torpedo;

import javax.inject.Inject;

public class Bclass {

    @Inject
    private Torpedo torpedo;

    private final Cclass c;
    private final Cclass c2;

    @Inject
    public Bclass(Cclass c, Cclass c2) {
        System.out.println("B constructor");
        this.c = c;
        this.c2 = c2;
    }

    public void printName() {
        System.out.println("Bclass instance: " + this.toString());
        if(torpedo != null) {
            torpedo.inspect();
        } else {
            System.out.println("Torpedo is still null! :(");
        }
    }
}
