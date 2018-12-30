package com.whalenut.winject.real;

import com.whalenut.winject.Winject;
import com.whalenut.winject.inject.Injector;
import com.whalenut.winject.real.torpedo.FotonTorpedo;
import com.whalenut.winject.real.torpedo.Torpedo;
import org.junit.jupiter.api.Test;

public class WinjectTest {

    public static void main(String[] args) {
        new WinjectTest().runInjections();
    }

    @Test
    void runInjections() {
        Injector winject = Winject.init();
        winject.map(Torpedo.class).to(FotonTorpedo.class);

        Aclass a = winject.create(Aclass.class);
        a.tell();
    }




}
