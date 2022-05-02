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
        observableBlocking1();
        observableBlocking2();
        flowableBlocking();
        singleBlocking();
        maybeBlocking();
        completableBlocking();
    }

    private static void completableBlocking(){
        System.out.println("\ncompletableBlocking():");
        Completable obs = Completable.fromRunnable(() -> {
            System.out.println("Run");
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

    private static void maybeBlocking(){
        System.out.println("\nmaybeBlocking():");
        Maybe<Integer> obs = Maybe.just(42);

        int r = obs.delay(100, TimeUnit.MILLISECONDS).blockingGet();
        System.out.println(r);   //prints: 42
    }

    private static void singleBlocking(){
        System.out.println("\nsingleBlocking():");
        Single<Integer> obs = Single.just(42);

        int r = obs.delay(100, TimeUnit.MILLISECONDS).blockingGet();
        System.out.println(r);   //prints: 42
    }

    private static void flowableBlocking(){
        System.out.println("\nflowableBlocking():");
        Flowable<Integer> obs = Flowable.range(1,5);

        Double d2 = obs.filter(i -> i % 2 == 0)
                .doOnNext(System.out::println)  //prints 2 and 4
                .map(Math::sqrt)
                .delay(100, TimeUnit.MILLISECONDS)
                .blockingLast();
        System.out.println(d2);   //prints: 2.0
    }

    private static void observableBlocking2(){
        System.out.println("\nobservableBlocking2():");
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
            TimeUnit.MILLISECONDS.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list);   //prints: [2.0]
    }

    private static void observableBlocking1(){
        System.out.println("\nobservableBlocking1():");
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

}
