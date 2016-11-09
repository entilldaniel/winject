package com.whalenut.winject.real;

import com.whalenut.winject.Winject;
import com.whalenut.winject.inject.Injector;
import org.atinject.tck.Tck;
import org.atinject.tck.auto.Car;
import org.atinject.tck.auto.Convertible;
import org.junit.Test;

public class GenericalStuff {

    @Test
    public void foo() {
        Injector winject = Winject.init();
        winject.map(Car.class).to(Convertible.class);
        Car car = winject.create(Car.class);
        Tck.testsFor(car, false, false);
    }

}
