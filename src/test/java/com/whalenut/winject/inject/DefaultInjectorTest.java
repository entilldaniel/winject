package com.whalenut.winject.inject;

import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.*;

public class DefaultInjectorTest {

    private Injector injector;

    @Before
    public void setUp() {
        injector = new DefaultInjector(ClassLoader.getSystemClassLoader());
    }

    @Test
    public void testConstructorInjection() {
        Foo test = injector.create(Foo.class);

        assertNotNull(test.getX());
    }

    @Test
    public void testMemberInjection() {
        Foo2 test = injector.create(Foo2.class);

        assertNotNull(test.getX());
    }

    @Test
    public void testMemberAndConstructorInjection() {
        Foo3 test = injector.create(Foo3.class);

        assertNotNull(test.getX());
        assertNotNull(test.getY());
    }

    @Test
    public void testObjectConstruction() {
        Bar test = injector.create(Bar.class);

        assertNotNull(test);
    }

    private static class Foo {
        private final Bar x;

        @Inject
        public Foo(final Bar x) {
            this.x = x;
        }

        public Bar getX() {
            return x;
        }
    }

    private static class Foo2 {
        @Inject
        private Bar x;

        public Foo2() {
        }

        public Bar getX() {
            return x;
        }
    }

    private static class Foo3 {

        private final Bar x;

        @Inject
        private Bar y;

        @Inject
        public Foo3(final Bar x) {
            this.x = x;
        }

        public Bar getX() {
            return x;
        }

        public Bar getY() {
            return y;
        }
    }

    private static class Bar {
        public Bar() {
        }
    }


}
