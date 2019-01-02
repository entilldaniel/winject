package com.whalenut.winject.inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class DefaultInjectorTest {

    private Injector injector;

    @BeforeEach
    void setUp() {
        injector = new DefaultInjector(ClassLoader.getSystemClassLoader());
    }

    @Test
    void testConstructorInjection() {
        ConstructorInjected test = injector.create(ConstructorInjected.class);
        assertNotNull(test.getBar());
    }

    @Test
    void testFieldInjection() {
        FieldInjected test = injector.create(FieldInjected.class);
        assertNotNull(test.getBar());
    }

    @Test
    void testFieldAndConstructorInjection() {
        FinalFieldInjection test = injector.create(FinalFieldInjection.class);

        assertNotNull(test.getFinalBar());
        assertNotNull(test.getFieldBar());
    }

    @Test
    void testObjectConstruction() {
        Bar test = injector.create(Bar.class);

        assertNotNull(test);
    }

    @Test
    void testInterfaceToConcreteMapping() {
        injector.map(Buzz.class).to(Fizz.class);
        Buzz test = injector.create(Buzz.class);

        assertNotNull(test);
    }

    @Test
    void testSetterInjection() {
        SetterInjection test = injector.create(SetterInjection.class);
        assertNotNull(test.getBar());
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

    private static class SetterInjection {

        private Bar bar;

        public SetterInjection() {}

        public Bar getBar() {
            return bar;
        }

        @Inject
        public void setBar(Bar bar) {
            this.bar = bar;
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
