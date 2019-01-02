package com.whalenut.winject;

import com.whalenut.winject.inject.Injector;
import com.whalenut.winject.mapping.BasicMappingInstance;
import junit.framework.TestResult;
import org.atinject.tck.Tck;
import org.atinject.tck.auto.Car;
import org.atinject.tck.auto.Convertible;
import org.atinject.tck.auto.DriversSeat;
import org.atinject.tck.auto.Engine;
import org.atinject.tck.auto.Seat;
import org.atinject.tck.auto.V8Engine;
import org.junit.Ignore;
import org.junit.Test;


@Ignore
public class TckTest {

    @Test
    public void runTCK() {
        TestResult testResult = new TestResult();
        Injector winject = Winject.init();
        winject.map(Car.class).to(Convertible.class);
        winject.map(Engine.class).to(V8Engine.class);
//        winject.map(Seat.class).to(DriversSeat.class);
        Car car = winject.create(Car.class);
        Tck.testsFor(car, false, true).run(testResult);
    }


}
