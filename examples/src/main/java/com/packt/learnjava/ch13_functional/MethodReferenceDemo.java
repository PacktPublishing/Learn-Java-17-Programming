package com.packt.learnjava.ch13_functional;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MethodReferenceDemo {
    public static void main(String... args) {
        MethodReferenceDemo mrd = new MethodReferenceDemo();
        mrd.methodReference0();
        mrd.methodReference1();
        mrd.methodReference2();
    }

    public void methodReference0() {
        Supplier<Integer> input = () -> 3;
        Predicate<Integer> checkValue = d -> d < 5;
        Function<Integer, Double> calculate = i -> i * 5.0;
        Consumer<Double> printResult = d -> System.out.println("Result: " + d);

        if(checkValue.test(input.get())){
            printResult.accept(calculate.apply(input.get()));
        } else {
            System.out.println("Input " + input.get() + " is too small.");
        }
    }

    public void methodReference1() {
        Supplier<Integer> input = () -> generateInput();
        Predicate<Integer> checkValue = d -> checkValue(d);
        Function<Integer, Double> calculate = i -> new Helper().calculate(i);
        Consumer<Double> printResult = d -> Helper.printResult(d);

        if(checkValue.test(input.get())){
            printResult.accept(calculate.apply(input.get()));
        } else {
            System.out.println("Input " + input.get() + " is too small.");
        }

    }

    private void methodReference2() {
        Supplier<Integer> input = this::generateInput;
        Predicate<Integer> checkValue = MethodReferenceDemo::checkValue;
        Function<Integer, Double> calculate = new Helper()::calculate;;
        Consumer<Double> printResult = Helper::printResult;

        if(checkValue.test(input.get())){
            printResult.accept(calculate.apply(input.get()));
        } else {
            System.out.println("Input " + input.get() + " is too small.");
        }
    }

    private int generateInput(){
        // Maybe many lines of code here
        return 3; //7
    }
    private static boolean checkValue(double d){
        // Maybe many lines of code here
        return d < 5;
    }

    private static class Helper {

        public double calculate(int i){
            // Maybe many lines of code here
            return i * 5;
        }
        public static void printResult(double d){
            // Maybe many lines of code here
            System.out.println("Result: " + d);
        }
    }


}
