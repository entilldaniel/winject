package com.whalenut.winject.real.torpedo;

import javax.inject.Singleton;

@Singleton
public class FotonTorpedo implements Torpedo {


    public FotonTorpedo() {
        System.out.println("FotonTorpedo constructor.");
    }

    @Override
    public void inspect() {
        System.out.println("This is a foton torpedo!");
    }
}
