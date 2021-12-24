package com.packt.learnjava.ch15_reactive;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MeasuringSystem {
    public static void main(String... args){
        compareSequentialAndParallelProcessing();
        completabelFuture();
        threadPool();
    }

    private static void threadPool(){
        ExecutorService pool = Executors.newFixedThreadPool(3);
        List<Integer> ids = IntStream.range(1, 11)
                .mapToObj(i -> i).collect(Collectors.toList());
        List<CompletableFuture<Double>> list = ids.stream()
                .map(id -> CompletableFuture.supplyAsync(() -> new MeasuringSystem().get(id), pool))
                .collect(Collectors.toList());
        pool.shutdown();
    }

    private static void completabelFuture(){
        List<Integer> ids = IntStream.range(1, 11)
                .mapToObj(i -> i).collect(Collectors.toList());
        List<CompletableFuture<Double>> list = ids.stream()
                .map(id -> CompletableFuture.supplyAsync(() -> new MeasuringSystem().get(id)))
                .collect(Collectors.toList());
        try{
            TimeUnit.MILLISECONDS.sleep(300);
        } catch(InterruptedException ex){
            ex.printStackTrace();
        }

        LocalTime start = LocalTime.now();
        double a = list.stream().mapToDouble(cf -> cf.join().doubleValue()).average().orElse(0);
        System.out.println((Math.round(a * 100.) / 100.) + " in " +
                Duration.between(start, LocalTime.now()).toMillis() + " ms"); //prints: 2.92 in 6 ms
    }

    private static void compareSequentialAndParallelProcessing() {
        List<Integer> ids = IntStream.range(1, 11)
                .mapToObj(i -> i).collect(Collectors.toList());

        getAverage(ids.stream());          //prints: 2.99 in 1030 ms
        getAverage(ids.parallelStream());  //prints: 2.34 in  214 ms
    }

    private static void getAverage(Stream<Integer> ids) {
        LocalTime start = LocalTime.now();
        double a = ids.mapToDouble(id -> new MeasuringSystem().get(id))
                      .average()
                      .orElse(0);
        System.out.println((Math.round(a * 100.) / 100.) + " in " +
                Duration.between(start, LocalTime.now()).toMillis() + " ms");
    }

    private double get(Integer id){
        try{
            TimeUnit.MILLISECONDS.sleep(100);
        } catch(InterruptedException ex){
            ex.printStackTrace();
        }
        return id * Math.random();
    }
}
