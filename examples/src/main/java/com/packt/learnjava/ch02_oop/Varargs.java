package com.packt.learnjava.ch02_oop;

public class Varargs {
    public static void main(String... args){
        someMethod("str", 42, 10, 17.23, 4);

    }

    private static String someMethod(String s, int i, double... arr){
        System.out.println(arr[0] + ", " + arr[1] + ", " + arr[2]); //prints: 10.0, 17.23, 4.0
        return s;
    }
}
