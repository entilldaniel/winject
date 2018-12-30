package com.whalenut.winject.inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class DefaultInjectorTest {

    private Injector injector;

    @BeforeEach
    public void setUp() {
        injector = new DefaultInjector(ClassLoader.getSystemClassLoader());
    }

    @Test
    public void testConstructorInjection() {
        ConstructorInjected test = injector.create(ConstructorInjected.class);
        assertNotNull(test.getBar());
    }

    @Test
    public void testMemberInjection() {
        FieldInjected test = injector.create(FieldInjected.class);
        assertNotNull(test.getBar());
    }

    @Test
    public void testMemberAndConstructorInjection() {
        FinalFieldInjection test = injector.create(FinalFieldInjection.class);

        assertNotNull(test.getFinalBar());
        assertNotNull(test.getFieldBar());
    }

    @Test
    public void testObjectConstruction() {
        Bar test = injector.create(Bar.class);

        assertNotNull(test);
    }

    @Test
    public void testInterfaceToConcreteMapping() {
        injector.map(Buzz.class).to(Fizz.class);
        Buzz test = injector.create(Buzz.class);

        assertNotNull(test);
    }

    private static class ConstructorInjected {
        private final Bar bar;

        @Inject
        public ConstructorInjected(final Bar bar) {
            this.bar = bar;
        }

        public Bar getBar() {
            return bar;
        }
    }

    private static class FieldInjected {
        @Inject
        private Bar bar;

        public FieldInjected() {
        }

        public Bar getBar() {
            return bar;
        }
    }

    private static class FinalFieldInjection {

        private final Bar bar;

        @Inject
        private Bar fieldBar;

        @Inject
        public FinalFieldInjection(final Bar bar) {
            this.bar = bar;
        }

        public Bar getFinalBar() {
            return bar;
        }

        public Bar getFieldBar() {
            return fieldBar;
        }
    }

    private static class Bar {
        public Bar() {
        }
    }

    private static class Fizz implements Buzz {
        public Fizz() {
        }
    }

    private interface Buzz {}

}
