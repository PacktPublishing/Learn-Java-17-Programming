package com.packt.learnjava.ch09_jvm;

import com.packt.learnjava.ch09_jvm.example.ExampleClass;

public class MyApplication {
    public static void main(String... args){
        System.out.println("Hello, world!"); //prints: Hello, world!
        for(String arg: args){
            System.out.print(arg + " ");     //prints all program arguments
        }
        String p = System.getProperty("someParameter");
        System.out.println("\n" + p);               //prints value of VM option someParameter

        int i = ExampleClass.multiplyByTwo(2);
        System.out.println(i);      //prints: 4
    }
}






