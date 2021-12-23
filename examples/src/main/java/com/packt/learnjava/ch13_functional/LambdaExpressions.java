package com.packt.learnjava.ch13_functional;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class LambdaExpressions {
    public static void main(String... args) {
        introduction();
        thisDemo();
        localVariableSyntax();
    }

    private static void introduction(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello!");
            }
        };
        runnable.run();   //prints: Hello!

        Runnable runnable2 = () -> System.out.println("Hello!");
        runnable2.run();
    }


    private static void thisDemo(){
        ThisDemo d = new ThisDemo();
        d.useAnonymousClass();   //prints: Consumer.field
        d.useLambdaExpression(); //prints: ThisDemo.field
    }

    private static class ThisDemo {
        private String field = "ThisDemo.field";
        public void useAnonymousClass() {
            Consumer<String> consumer = new Consumer<>() {
                private String field = "Consumer.field";
                public void accept(String s) {
                    System.out.println(this.field);
                }
            };
            consumer.accept(this.field);
        }
        public void useLambdaExpression() {
            Consumer<String> consumer = consumer = s -> {
                System.out.println(this.field);
            };
            consumer.accept(this.field);
        }
    }

    private static void localVariableSyntax(){
        BiFunction<Double, Integer, Double> f4 =
                (var x, var y) -> x / y;
        System.out.println(f4.apply(null, 2));
    }
}
