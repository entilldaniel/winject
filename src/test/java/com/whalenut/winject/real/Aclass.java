package com.whalenut.winject.real;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.stream.Stream;

public class Aclass {

    private final Bclass b;
    private final Provider<Teller> tellerProvider;

    @Inject
    public Aclass(final Bclass b, final Provider<Teller> tellerProvider) {
        System.out.println("A constructor.");
        this.b = b;
        this.tellerProvider = tellerProvider;
        b.printName();
        System.out.println("Constructor for class A constructed with " + b.toString());
    }


    public void tell() {
        System.out.println("This is a dependency graph management.");

        System.out.println("Creating a bunch of tellers");
        Stream.generate(() -> new Integer(1)).limit(10).forEach(a -> {
            tellerProvider.get().call();
        });


    }
}
