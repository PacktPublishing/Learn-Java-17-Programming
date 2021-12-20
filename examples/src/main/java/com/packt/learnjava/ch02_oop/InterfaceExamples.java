package com.packt.learnjava.ch02_oop;

import java.util.HashSet;
import java.util.Set;

public class InterfaceExamples {
    public static void main(String... args){

        staticProperty();
        SomeInterface si = new SomeClass();

        SomeClass sc = new SomeClass();
        sc.method1();
        sc.method2(22);  //returns: "abc"
        sc.method3();       //returns: 42

        System.out.println(SomeInterface1.someMethod()); //prints: abc
    }

    private static class SomeClass implements SomeInterface{
        public void method1(){
            //method body
        }
        public String method2(int i) {
            //method body
            return "abc";
        }
    }

    interface SomeInterface {
        void method1();
        String method2(int i);
        default int method3(){
            return getNumber();
        }
        default int method4(){
            return getNumber() + 22;
        }
        private int getNumber(){
            return 42;
        }
    }

    private static void staticProperty(){
        A.set.add("a");
        A.set.add("b");
        System.out.println(A.set); //prints: [a, b]
    }

    interface A {
        Set<String> set = new HashSet<>();
    }

    interface SomeInterface1{
        static String someMethod() {
            return "abc";
        }
    }

}
