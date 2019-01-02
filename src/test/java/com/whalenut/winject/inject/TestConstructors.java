package com.whalenut.winject.inject;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class TestConstructors {



    @Test
    void testIt() {
        Constructor<?>[] constructors = Foo.class.getConstructors();
        Arrays.asList(constructors).stream().forEach(System.out::println);
    }


    public static class Foo {

        private boolean active;

        public boolean isActive() {
            return active;
        }
    }
}
