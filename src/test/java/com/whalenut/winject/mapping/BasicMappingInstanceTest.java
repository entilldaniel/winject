package com.whalenut.winject.mapping;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BasicMappingInstanceTest {


    @Test
    void shouldThrowOnConcreteClass() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BasicMappingInstance<Foo, Bar>(Foo.class);
        });
    }

    @Test
    void shouldAcceptInterface() {
        new BasicMappingInstance<Baz, Foo>(Baz.class);
    }

    @Test
    void shouldReturnClass() {
        var bmi = new BasicMappingInstance<Baz, Foo>(Baz.class);
        Class foo = bmi.to(Foo.class).get();

        assertTrue(foo.equals(Foo.class));
    }


    public interface Baz {
    }

    public static class Foo implements Baz {
    }

    public static class Bar implements Baz {
    }


}
