package com.whalenut.winject;

import com.whalenut.winject.torpedo.Torpedo;

import javax.inject.Inject;

public class Bclass {

    @Inject
    private Torpedo torpedo;

    private final Cclass c;

    @Inject
    public Bclass(Cclass c) {
        System.out.println("B constructor");
        this.c = c;
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
