package com.packt.learnjava.ch15_reactive;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class Scheduler {
    public static void main(String... args){
        parallel1();
        parallel2();
        parallel3();
        subscribeOn1();
        subscribeOn2();
        observeOnsubscribeOn();
    }

    private static void observeOnsubscribeOn(){
        Observable.just("a", "b", "c")
                .subscribeOn(Schedulers.computation())
                .doAfterNext(s -> System.out.println("1: " + Thread.currentThread().getName() + " => " + s))
                .observeOn(Schedulers.computation())
                .subscribe(s -> System.out.println("2: " + Thread.currentThread().getName() + " => " + s));

        pauseMs(100);
    }

    private static void subscribeOn2(){
        Observable.just("a", "b", "c")
                .subscribeOn(Schedulers.computation())
                .doAfterNext(s -> System.out.println("1: " + Thread.currentThread().getName() + " => " + s))
                .subscribe(s -> System.out.println("2: " + Thread.currentThread().getName() + " => " + s));

        pauseMs(100);
    }

    private static void subscribeOn1(){
        Observable.just("a", "b", "c")
                .doAfterNext(s -> System.out.println("1: " + Thread.currentThread().getName() + " => " + s))
                .subscribeOn(Schedulers.computation())
                .subscribe(s -> System.out.println("2: " + Thread.currentThread().getName() + " => " + s));

        pauseMs(100);
    }

    private static void parallel3(){

        ParallelFlowable src = Flowable.fromArray("one","two","three").parallel();
        src.runOn(Schedulers.computation())
           .doAfterNext(s -> System.out.println("1: " + Thread.currentThread().getName() + " => " + s))
           .flatMap(w -> Flowable.fromArray(((String)w).split("")))
           .runOn(Schedulers.computation())
           .doAfterNext(s -> System.out.println("2: " + Thread.currentThread().getName() + " => " + s))
           .sequential()
           .subscribe(s -> System.out.println("3: " + s));

        pauseMs(100);

    }

    private static void parallel2(){
        Observable.fromArray("one","two","three")
                .observeOn(Schedulers.computation())
                .doAfterNext(s -> System.out.println("1: " + Thread.currentThread().getName() + " => " + s))
                .flatMap(w -> Observable.fromArray(w.split(""))
                        .observeOn(Schedulers.computation())
                        .doAfterNext(s -> System.out.println("2: " + Thread.currentThread().getName() + " => " + s))
                )
                .subscribe(s -> System.out.println("3: " + s));
        pauseMs(100);

    }

    private static void parallel1(){
        Observable.fromArray("one","two","three")
                .doAfterNext(s -> System.out.println("1: " + Thread.currentThread().getName() + " => " + s))
                .flatMap(w -> Observable.fromArray(w.split(""))
                        .observeOn(Schedulers.computation())
                        .doAfterNext(s -> System.out.println("2: " + Thread.currentThread().getName() + " => " + s))
                )
                .subscribe(s -> System.out.println("3: " + s));
        pauseMs(100);

    }

    private static void pauseMs(long ms){
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
