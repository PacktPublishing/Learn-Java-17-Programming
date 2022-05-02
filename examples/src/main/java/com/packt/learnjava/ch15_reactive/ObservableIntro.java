package com.packt.learnjava.ch15_reactive;

import io.reactivex.Observable;

import java.util.stream.IntStream;

public class ObservableIntro {
    public static void main(String... args){
        squareRootsSum();
        reuseObservable();
        cacheObservableData();
    }

    private static void squareRootsSum(){
        System.out.println("\nsquareRootsSum():");
        double a = IntStream.rangeClosed(1, 5)
                .filter(i -> i % 2 == 0)
                .mapToDouble(Double::valueOf)
                .map(Math::sqrt)
                .sum();
        System.out.println(a);                  //prints: 3.414213562373095

        Observable.range(1, 5)
                .filter(i -> i % 2 == 0)
                .map(Math::sqrt)
                .reduce((r, d) -> r + d)
                .subscribe(System.out::println); //prints: 3.414213562373095
    }
    private static void reuseObservable(){
        System.out.println("\nreuseObservable():");
        Observable<Double> observable = Observable.range(1, 5)
                .filter(i -> i % 2 == 0)
                .doOnNext(System.out::println)    //prints 2 and 4 twice
                .map(Math::sqrt);
        observable
                .reduce((r, d) -> r + d)
                .subscribe(System.out::println);  //prints: 3.414213562373095
        observable
                .reduce((r, d) -> r + d)
                .map(r -> r / 2)
                .subscribe(System.out::println);  //prints: 1.7071067811865475
    }

    private static void cacheObservableData(){
        System.out.println("\ncacheObservableData():");
        Observable<Double> observable = Observable.range(1,5)
                .filter(i -> i % 2 == 0)
                .doOnNext(System.out::println)  //prints 2 and 4 only once
                .map(Math::sqrt)
                .cache();
        observable
                .reduce((r, d) -> r + d)
                .subscribe(System.out::println); //prints: 3.414213562373095
        observable
                .reduce((r, d) -> r + d)
                .map(r -> r / 2)
                .subscribe(System.out::println);  //prints: 1.7071067811865475
    }

}
