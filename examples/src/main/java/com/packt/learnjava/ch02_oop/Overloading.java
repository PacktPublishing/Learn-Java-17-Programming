package com.packt.learnjava.ch02_oop;

public class Overloading {
    public static void main(String... args) {
    }

    interface A {
        int m(String s);
        int m(String s, double d);

        private int m(double d) { return 42; }
        private int m(int i, String s) { return 1; }

        default int m(String s, int i) { return 1; }
        static int m(String s, int i, double d) { return 1; }
    }

    class C {
        int m(String s){ return 42; }
        int m(String s, double d){ return 42; }

        private int m(double d) { return 42; }
        private int m(int i, String s) { return 1; }

        public int m(String s, int i) { return 1; }
        public int m(String s, int i, double d) { return 1; }

        protected int m(double d, String s, int i) { return 1; }
        protected int m(double d, String s, int i, double b) { return 1; }
/*
        public static int m(String s, double d, int i) { return 1; }
        public static int m(String s, double d, int i, double b) { return 1; }
*/
    }

    public static int m(String s, double d, int i) { return 1; }
    public static int m(String s, double d, int i, double b) { return 1; }
}
