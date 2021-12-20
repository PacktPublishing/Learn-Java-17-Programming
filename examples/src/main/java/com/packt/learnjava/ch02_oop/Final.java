package com.packt.learnjava.ch02_oop;

public class Final {
    public static void main(String... args){
    }

    private static void finalVariable(){
        final String s;
        s = "abc";
    }

    private static class A {
        private final String s1 = "abc";
        private final String s2;
        //private final String s3;   //error
        //private final int x;       //error

        public A() {
            //this.s1 = "xyz";     //error
            this.s2 = "xyz";

        }
    }

    private static class B {
        private final String s1 = "abc";
        private final String s2;
        {
            //s1 = "xyz"; //error
            s2 = "abc";
        }
    }

    private static class C {
        private final static String s1 = "abc";
        private final static String s2;
        static {
            //s1 = "xyz"; //error
            s2 = "abc";
        }
    }

    interface I {
        //String s1;  //error
        String s2 = "abc";
    }

}
