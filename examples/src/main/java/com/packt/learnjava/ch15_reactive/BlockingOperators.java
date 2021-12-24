package com.packt.learnjava.ch15_reactive;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class BlockingOperators {
    public static void main(String... args){
        squareRootsSum();
        reuseObservable();
        cacheObservableData();
        observableBlocking1();
        observableBlocking2();
        flowableBlocking();
        singleBlocking1();
        singleBlocking2();
        maybeBlocking();
        completableBlocking();
    }

    private static void completableBlocking(){
        Completable obs = Completable.fromRunnable(() -> {
            System.out.println("Running...");
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }); //prints: Run

        Throwable ex = obs.blockingGet();
        System.out.println(ex);   //prints: null

        //ex = obs.blockingGet(15, TimeUnit.MILLISECONDS);
        //java.util.concurrent.TimeoutException: The source did not signal an event for 15 milliseconds and has been terminated.

        ex = obs.blockingGet(150, TimeUnit.MILLISECONDS);
        System.out.println(ex);   //prints: null

        obs.blockingAwait();

        obs.blockingAwait(15, TimeUnit.MILLISECONDS);
    }


    private static void completableBlocking1(){
        Completable obs = Completable.fromRunnable(() -> System.out.println("Run")); //prints: Run

        Throwable ex = obs.delay(100, TimeUnit.MILLISECONDS).blockingGet();
        System.out.println(ex);   //prints: null

        //ex = obs.delay(100, TimeUnit.MILLISECONDS).blockingGet(15, TimeUnit.MILLISECONDS);
        //java.util.concurrent.TimeoutException: The source did not signal an event for 15 milliseconds and has been terminated.

        ex = obs.delay(100, TimeUnit.MILLISECONDS).blockingGet(150, TimeUnit.MILLISECONDS);
        System.out.println(ex);   //prints: null

        obs.delay(100, TimeUnit.MILLISECONDS).blockingAwait();

        obs.delay(100, TimeUnit.MILLISECONDS).blockingAwait(15, TimeUnit.MILLISECONDS);
    }

    private static void maybeBlocking(){
        Maybe<Integer> obs = Maybe.just(42);

        int r = obs.delay(100, TimeUnit.MILLISECONDS).blockingGet();
        System.out.println(r);   //prints: 42

        List<Integer> list = new ArrayList<>();
        obs.delay(100, TimeUnit.MILLISECONDS)
                .subscribe(i -> {
                    if(list.size() == 1){
                        list.remove(0);
                    }
                    list.add(i);
                });

        System.out.println(list);  //prints: []
    }

    private static void singleBlocking2(){
        Single<Integer> obs = Single.just(42);

        List<Integer> list = new ArrayList<>();
        obs.delay(100, TimeUnit.MILLISECONDS)
                .subscribe(i -> {
                    if(list.size() == 1){
                        list.remove(0);
                    }
                    list.add(i);
                });

        System.out.println(list);  //prints: []

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(list);  //prints: [42]
    }

    private static void singleBlocking1(){
        Single<Integer> obs = Single.just(42);

        int r = obs.delay(100, TimeUnit.MILLISECONDS).blockingGet();
        System.out.println(r);   //prints: 42

        List<Integer> list = new ArrayList<>();
        obs.delay(100, TimeUnit.MILLISECONDS)
                .subscribe(i -> {
                    if(list.size() == 1){
                        list.remove(0);
                    }
                    list.add(i);
                });

        System.out.println(list);  //prints: []
    }

    private static void flowableBlocking(){
        Flowable<Integer> obs = Flowable.range(1,5);

        Double d2 = obs.filter(i -> i % 2 == 0)
                .doOnNext(System.out::println)  //prints 2 and 4
                .map(Math::sqrt)
                .delay(100, TimeUnit.MILLISECONDS)
                .blockingLast();
        System.out.println(d2);   //prints: 2.0
    }


    private static void observableBlocking2(){
        Observable<Integer> obs = Observable.range(1,5);

        List<Double> list = new ArrayList<>();
        obs.filter(i -> i % 2 == 0)
                .doOnNext(System.out::println)  //prints 2 and 4
                .map(Math::sqrt)
                .delay(100, TimeUnit.MILLISECONDS)
                .subscribe(d -> {
                    if(list.size() == 1){
                        list.remove(0);
                    }
                    list.add(d);
                });
        System.out.println(list);   //prints: []

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list);   //prints: [2.0]
    }

    private static void observableBlocking1(){
        Observable<Integer> obs = Observable.range(1,5);

        Double d2 = obs.filter(i -> i % 2 == 0)
                       .doOnNext(System.out::println)  //prints 2 and 4
                       .map(Math::sqrt)
                       .delay(100, TimeUnit.MILLISECONDS)
                       .blockingLast();
        System.out.println(d2);   //prints: 2.0

        List<Double> list = new ArrayList<>();
        obs.filter(i -> i % 2 == 0)
           .doOnNext(System.out::println)  //prints 2 and 4
           .map(Math::sqrt)
           .delay(100, TimeUnit.MILLISECONDS)
           .subscribe(d -> {
                if(list.size() == 1){
                    list.remove(0);
                }
                list.add(d);
           });
        System.out.println(list);   //prints: []
    }

    private static void cacheObservableData(){
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

    private static void reuseObservable(){
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

    private static void squareRootsSum(){
        double a = IntStream.rangeClosed(1, 5)
                            .filter(i -> i % 2 == 0)
                            .mapToDouble(Double::valueOf)
                            .map(Math::sqrt)
                            .sum();
        System.out.println(a); //prints: 3.414213562373095

        Observable.range(1, 5)
                  .filter(i -> i % 2 == 0)
                  .map(Math::sqrt)
                  .reduce((r, d) -> r + d)
                  .subscribe(System.out::println); //prints: 3.414213562373095
    }

}
