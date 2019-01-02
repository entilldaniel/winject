package com.whalenut.winject;

import com.whalenut.winject.inject.Injector;
import junit.framework.TestResult;
import org.atinject.tck.Tck;
import org.atinject.tck.auto.Car;
import org.junit.Ignore;
import org.junit.Test;


@Ignore
public class TckTest {

    @Test
    public void runTCK() {
        TestResult testResult = new TestResult();
        Injector winject = Winject.init();
        Car car = winject.create(Car.class);
        Tck.testsFor(car, false, true).run(testResult);
    }


}
