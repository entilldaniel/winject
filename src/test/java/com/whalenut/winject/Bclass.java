package com.whalenut.winject;

import javax.inject.Inject;

public class Bclass {

    private final Cclass c;

    @Inject
    public Bclass(Cclass c) {
        this.c = c;
    }

    public void printName() {
        System.out.println("Bclass instance: " + this.toString());
    }
}
