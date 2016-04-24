package com.whalenut.winject;

public class TestWinject {

    public TestWinject() {
        Winject winject = Winject.init();
        Aclass a = winject.create(Aclass.class);
        a.tell();
    }

    public static void main(String[] args) {
        new TestWinject();
    }



}
