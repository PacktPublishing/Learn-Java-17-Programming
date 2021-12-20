package com.packt.learnjava.ch13_functional;

public class FunctionalInterface {
    @java.lang.FunctionalInterface
    interface A {
        void method1();
        default void method2(){}
        static void method3(){}
    }

    @java.lang.FunctionalInterface
    interface B extends A {
        default void method4(){}
    }

    @java.lang.FunctionalInterface
    interface C extends B {
        void method1();
    }

    //@FunctionalInterface  //compilation error
    interface D extends C {
        void method5();
    }
}
