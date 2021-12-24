package com.packt.learnjava.ch15_reactive;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

import java.util.concurrent.TimeUnit;

public class CreateObservable {
    public static void main(String... args){
        create();
    }

    private static void create(){
        System.out.println();
        ObservableOnSubscribe<String> source = emitter -> {
            emitter.onNext("One");
            emitter.onNext("Two");
            emitter.onComplete();
        };

        Observable.create(source)
                  .filter(s -> s.contains("w"))
                  .subscribe(v -> System.out.println(v),
                             e -> e.printStackTrace(),
                             () -> System.out.println("Completed"));

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
