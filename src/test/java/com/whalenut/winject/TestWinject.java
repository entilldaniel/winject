package com.whalenut.winject;

import com.whalenut.winject.torpedo.FotonTorpedo;
import com.whalenut.winject.torpedo.Torpedo;

public class TestWinject {

    public TestWinject() {
        Winject winject = Winject.init();
        winject.map(Torpedo.class).to(FotonTorpedo.class);
        Aclass a = winject.create(Aclass.class);
        a.tell();
    }

    public static void main(String[] args) {
        new TestWinject();
    }



}
