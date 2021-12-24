package com.packt.learnjava.ch15_reactive;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NonBlockingOperators {
    public static void main(String... args){
        transforming();
        filtering();
        combined();
        exceptions();
        events();
        utilities();
        conditional();
    }

    private static void conditional(){
        Observable<String> obs = Observable.just("one")
                .flatMap(s -> Observable.fromArray(s.split("")));
        Single<Boolean> cont = obs.contains("n");
        System.out.println(cont.blockingGet());       //prints: true

        obs.defaultIfEmpty("two")
                .subscribe(System.out::print);         //prints: one

        System.out.println();

        Observable.empty().defaultIfEmpty("two")
                .subscribe(System.out::print);         //prints: two

        System.out.println();

        Single<Boolean> equal = Observable.sequenceEqual(obs, Observable.just("one"));
        System.out.println(equal.blockingGet());       //prints: false

        equal = Observable.sequenceEqual(Observable.just("one"), Observable.just("one"));
        System.out.println(equal.blockingGet());       //prints: true

        equal = Observable.sequenceEqual(Observable.just("one"), Observable.just("two"));
        System.out.println(equal.blockingGet());       //prints: false
    }
    private static void utilities(){
        Observable<String> obs = Observable.just("one")
                .flatMap(s -> Observable.fromArray(s.split("")));

        obs.delay(5, TimeUnit.MILLISECONDS)
                .subscribe(System.out::print);         //prints: one
        pauseMs(10);

        System.out.println();

        Observable source = Observable.range(1,5);
        Disposable disposable = source.subscribe();
        Observable.using(
                () -> disposable,
                x -> source,
                y -> System.out.println("Disposed: " + y) //prints: Disposed: DISPOSED
        )
        .delay(10, TimeUnit.MILLISECONDS)
        .subscribe(System.out::print);                   //prints: 12345
        pauseMs(25);
    }
    private static void events(){
        Observable<String> obs = Observable.just("one")
                .flatMap(s -> Observable.fromArray(s.split("")));

        System.out.println();

        obs.doOnComplete(() -> System.out.println("Completed!")) //prints: Completed!
                .subscribe(v -> {
                    System.out.println("Subscribe onNext: " + v);
                });
        pauseMs(25);
    }
    private static void exceptions(){
        Observable<String> obs = Observable.just("one")
                .flatMap(s -> Observable.fromArray(s.split("")));

        Observable.error(new RuntimeException("MyException"))
                .flatMap(x -> Observable.fromArray("two".split("")))
                .subscribe(System.out::print,
                        e -> System.out.println(e.getMessage())  //prints: MyException
                );

        Observable.error(new RuntimeException("MyException"))
                .flatMap(y -> Observable.fromArray("two".split("")))
                .onErrorResumeNext(obs)
                .subscribe(System.out::print); //prints: one
        System.out.println();
        Observable.error(new RuntimeException("MyException"))
                .flatMap(z -> Observable.fromArray("two".split("")))
                .onErrorReturnItem("42")
                .subscribe(System.out::print); //prints: 42
        System.out.println();
        pauseMs(100);
    }
    private static void combined(){
        Observable<String> obs1 = Observable.just("one")
                .flatMap(s -> Observable.fromArray(s.split("")));
        Observable<String> obs2 = Observable.just("two")
                .flatMap(s -> Observable.fromArray(s.split("")));

        Observable.concat(obs2, obs1, obs2).subscribe(System.out::print); //prints: twoonetwo
        System.out.println();
        Observable.combineLatest(obs2, obs1, (x,y) -> "("+x+y+")")
                  .subscribe(System.out::print); //prints: (oo)(on)(oe)
        System.out.println();
        obs1.join(obs2, i -> Observable.timer(5, TimeUnit.MILLISECONDS),
                i -> Observable.timer(5, TimeUnit.MILLISECONDS),
                (x,y) -> "("+x+y+")").subscribe(System.out::print); //prints: (ot)(nt)(et)(ow)(nw)(ew)(oo)(no)(eo)
        System.out.println();
        Observable.merge(obs2, obs1, obs2).subscribe(System.out::print); //prints: twoonetwo

        System.out.println();
        obs1.startWith("42").subscribe(System.out::print); //prints: 42one

        System.out.println();
        Observable.zip(obs1, obs2, obs1,  (x,y,z) -> "("+x+y+z+")")
                .subscribe(System.out::print);
        pauseMs(100);
    }
    private static void filtering(){
        Observable<String> obs = Observable.just("onetwo")
                .flatMap(s -> Observable.fromArray(s.split("")));

        obs.map(s -> {
                    if("t".equals(s)){
                       NonBlockingOperators.pauseMs(15);
                    }
                    return s;
                })
                .debounce(10, TimeUnit.MILLISECONDS)
                .forEach(System.out::print);     //prints: eo

        System.out.println();

        obs.distinct()
                .forEach(System.out::print);     //prints: onetw

        System.out.println();

        obs.elementAt(3)
                .subscribe(System.out::println);     //prints: t

        obs.filter(s -> s.equals("o"))
                .forEach(System.out::print);     //prints: oo

        System.out.println();

        obs.firstElement()
                .subscribe(System.out::println);     //prints: o


        obs.ignoreElements()
                .subscribe(() -> System.out.println("Completed!"));     //prints: Completed!

        Observable.interval(5, TimeUnit.MILLISECONDS)
                  .sample(10, TimeUnit.MILLISECONDS)
                  .subscribe(v -> System.out.print(v + " "));     //prints: 1 3 4 6 8
        pauseMs(50);
    }
    private static void transforming(){
        Observable<String> obs = Observable.fromArray("one", "two");

        obs.map(s ->  s.contains("w") ? 1 : 0).forEach(System.out::print); //prints: 01

        List<String> os = new ArrayList<>();
        List<String> noto = new ArrayList<>();
        obs.flatMap(s -> Observable.fromArray(s.split("")))
                .groupBy(s -> "o".equals(s) ? "o" : "noto")
                .subscribe(g -> g.subscribe(s -> {
                    if (g.getKey().equals("o")) {
                        os.add(s);
                    } else {
                        noto.add(s);
                    }
                }));
        System.out.println(os);     //prints: [o, o]
        System.out.println(noto);   //prints: [n, e, t, w]

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
