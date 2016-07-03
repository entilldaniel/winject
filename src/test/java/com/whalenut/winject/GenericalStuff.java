package com.whalenut.winject;

import com.whalenut.winject.inject.Injector;
import org.atinject.tck.Tck;
import org.atinject.tck.auto.Car;
import org.junit.Test;

public class GenericalStuff {

    @Test
    public void foo() {
        Injector winject = Winject.init();
        Car car = winject.create(Car.class);
        Tck.testsFor(car, true, true);
    }

}
