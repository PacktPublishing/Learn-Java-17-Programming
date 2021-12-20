package com.packt.learnjava.ch13_functional;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class StandardFunctionalInterfaces {
    public static void main(String... args) {
        consumer();
        predicate();
        supplier();
        function();
    }

    private static void consumer(){
        Consumer<String> printResult =
                s -> System.out.println("Result: " + s);
        printResult.accept("10.0");         //prints: Result: 10.0

        printWithPrefixAndPostfix("Result: ", " Great!")
                .accept("10.0");  //prints: Result: 10.0 Great!

        String externalData = "external data";
        Consumer<Person> setRecord =
                p -> p.setRecord(p.getFirstName() + " " +
                        p.getLastName() + ", " + p.getAge() + ", " + externalData);

        Consumer<Person> printRecord =
                p -> System.out.println(p.getRecord());

        Consumer<Person> setRecordThenPrint = setRecord.andThen(printRecord);

        setRecordThenPrint.accept(new Person(42, "Nick", "Samoylov"));
                                         //prints: Nick-Samoylov-42-externalData
    }

    private static Consumer<String> printWithPrefixAndPostfix(String prefix, String postfix){
        return s -> System.out.println(prefix + s + postfix);
    }

    private static void predicate(){
        Predicate<Integer> isLessThan10 = i -> i < 10;

        System.out.println(isLessThan10.test(7));  //prints: true
        System.out.println(isLessThan10.test(12)); //prints: false

        int val = 7;
        Consumer<String> printIsSmallerThan10 =
                printWithPrefixAndPostfix("Is " + val + " smaller than 10? ", " Great!");
        printIsSmallerThan10.accept(String.valueOf(isLessThan10.test(val)));         //prints: Is 7 smaller than 10? true Great!

        Predicate<Integer> isEqualOrGreaterThan10 = isLessThan10.negate();
        System.out.println(isEqualOrGreaterThan10.test(7));   //prints: false
        System.out.println(isEqualOrGreaterThan10.test(12));  //prints: true

        isEqualOrGreaterThan10 = Predicate.not(isLessThan10);
        System.out.println(isEqualOrGreaterThan10.test(7));   //prints: false
        System.out.println(isEqualOrGreaterThan10.test(12));  //prints: true

        Predicate<Integer> isGreaterThan10 = i -> i > 10;
        Predicate<Integer> is_lessThan10_OR_greaterThan10 = isLessThan10.or(isGreaterThan10);
        System.out.println(is_lessThan10_OR_greaterThan10.test(20));   //prints: true
        System.out.println(is_lessThan10_OR_greaterThan10.test(10));   //prints: false

        Predicate<Integer> isGreaterThan5 = i -> i > 5;
        Predicate<Integer> is_lessThan10_AND_greaterThan5 = isLessThan10.and(isGreaterThan5);
        System.out.println(is_lessThan10_AND_greaterThan5.test(3));  //prints: false
        System.out.println(is_lessThan10_AND_greaterThan5.test(7));  //prints: true

        Person nick = new Person(42, "Nick", "Samoylov");
        Predicate<Person> isItNick = Predicate.isEqual(nick);
        Person john = new Person(42, "John", "Smith");
        Person person = new Person(42, "Nick", "Samoylov");
        System.out.println(isItNick.test(john));      //prints: false
        System.out.println(isItNick.test(person));    //prints: true
    }

    private static void supplier() {
        Supplier<Integer> supply42 = () -> {
            return 42;
        };
        System.out.println(supply42.get());  //prints: 42


        int input = 7;
        int limit = 10;

        Supplier<Integer> supply7 = () -> input;
        Predicate<Integer> isLessThan10 = i -> i < limit;

        Consumer<String> printResult =
                printWithPrefixAndPostfix("Is " + input + " smaller than " + limit + "? ", " Great!");
        printResult.accept(String.valueOf(isLessThan10.test(supply7.get())));
                                            //prints: Is 7 smaller than 10? true Great!
    }

    private static void function(){
        Function<Integer, Double> multiplyByTen = i -> i * 10.0;
        System.out.println(multiplyByTen.apply(1)); //prints: 10.0

        Supplier<Integer> supply7 = () -> 7;
        Function<Integer, Double> multiplyByFive = i -> i * 5.0;
        Consumer<String> printResult = printWithPrefixAndPostfix("Result: ", " Great!");
        printResult.accept(multiplyByFive.apply(supply7.get()).toString()); //prints: Result: 35.0 Great!

        Function<Double, Long> divideByTwo = d -> Double.valueOf(d / 2.).longValue();
        Function<Long, String> incrementAndCreateString = l -> String.valueOf(l + 1);
        Function<Double, String> divideByTwoIncrementAndCreateString = divideByTwo.andThen(incrementAndCreateString);
        printResult.accept(divideByTwoIncrementAndCreateString.apply(4.));     //prints: Result: 3 Great!

        divideByTwoIncrementAndCreateString = incrementAndCreateString.compose(divideByTwo);
        printResult.accept(divideByTwoIncrementAndCreateString.apply(4.));  //prints: Result: 3 Great!

        Function<Double, Double> multiplyByTwo = d -> d * 2.0;
        System.out.println(multiplyByTwo.apply(2.));  //prints: 4.0

        Function<Double, Long> subtract7 = d -> Math.round(d - 7);
        System.out.println(subtract7.apply(11.0));   //prints: 4

        long r = multiplyByTwo.andThen(subtract7).apply(2.);
        System.out.println(r);                          //prints: -3

        multiplyByTwo = Function.identity();
        System.out.println(multiplyByTwo.apply(2.));  //prints: 2.0

        r = multiplyByTwo.andThen(subtract7).apply(2.);
        System.out.println(r);                          //prints: -5
    }

}
