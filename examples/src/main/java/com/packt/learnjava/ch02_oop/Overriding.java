package com.packt.learnjava.ch02_oop;

public class Overriding {
    public static void main(String... args) {

        C c = new C();
        c.method();      //prints: interface D

        C2 c2 = new C2();
        c2.method();      //prints: class C2
    }

    interface A {
        default void method(){
            System.out.println("interface C");
        }
    }

    interface B extends A{
        @Override
        default void method(){
            System.out.println("interface D");
        }
    }

    private static class C implements B{

    }

    private static class C1{
        public void method(){
            System.out.println("class C1");
        }
    }

    private static class C2 extends C1{
        @Override
        public void method(){
            System.out.println("class C2");
        }
    }

}
