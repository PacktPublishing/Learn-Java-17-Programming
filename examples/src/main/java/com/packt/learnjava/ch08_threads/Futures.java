package com.packt.learnjava.ch08_threads;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Futures {
    public static void main(String... args) {
        futureSubmitRunnable1();
        futureSubmitRunnable2();
        futureSubmitRunnable3();
        futureSubmitCallable1();
    }

    private static void futureSubmitCallable1(){
        System.out.println("\nfutureSubmitCallable1:\n");

        ExecutorService pool = Executors.newSingleThreadExecutor();

        Future<Result> future = pool.submit(new MyCallable("Three"));
        System.out.println(future.isDone());         //prints: false
        System.out.println(future.isCancelled());    //prints: false
        try{
            System.out.println(future.get());         //prints: null
            System.out.println(future.isDone());      //prints: true
            System.out.println(future.isCancelled()); //prints: false
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            shutdownAndTerminate(pool);
        }
    }

    private static class MyCallable implements Callable {
        private String name;

        public MyCallable(String name) {
            this.name = name;
        }

        public Result call() {
            try {
                System.out.println(this.name + " is working...");
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(this.name + " is done");
                return new Result(name, 42.42);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(this.name + " was interrupted\n" +
                        this.name + " Thread.currentThread().isInterrupted()="
                        + Thread.currentThread().isInterrupted());
            }
            return null;
        }
    }

    private static void futureSubmitRunnable3(){
        System.out.println("\nfutureSubmitRunnable3:\n");

        ExecutorService pool = Executors.newSingleThreadExecutor();

        Future<Result> future = pool.submit(new MyRunnable("Two"), new Result("Two", 42.));
        System.out.println(future.isDone());         //prints: false
        System.out.println(future.isCancelled());    //prints: false
        try{
            System.out.println(future.get());         //prints: null
            System.out.println(future.isDone());      //prints: true
            System.out.println(future.isCancelled()); //prints: false
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            shutdownAndTerminate(pool);
        }
    }

    private static class Result {
        private String name;
        private double result;
        public Result(String name, double result) {
            this.name = name;
            this.result = result;
        }

        @Override
        public String toString() {
            return "Result{name=" + name +
                    ", result=" + result + "}";
        }
    }

    private static void futureSubmitRunnable2(){
        System.out.println("\nfutureSubmitRunnable2:\n");

        ExecutorService pool = Executors.newSingleThreadExecutor();

        Future future = pool.submit(new MyRunnable("One"));
        System.out.println(future.isDone());         //prints: false
        System.out.println(future.isCancelled());    //prints: false
        try{
            System.out.println(future.get(1, TimeUnit.SECONDS));
            System.out.println(future.isDone());
            System.out.println(future.isCancelled());
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            shutdownAndTerminate(pool);
        }
    }

    private static void futureSubmitRunnable1(){
        System.out.println("\nfutureSubmitRunnable1:\n");

        ExecutorService pool = Executors.newSingleThreadExecutor();

        Future future = pool.submit(new MyRunnable("One"));
        System.out.println(future.isDone());         //prints: false
        System.out.println(future.isCancelled());    //prints: false
        try{
            System.out.println(future.get());        //prints: null
            System.out.println(future.isDone());     //prints: true
            System.out.println(future.isCancelled());//prints: false
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            shutdownAndTerminate(pool);
        }
    }

    private static void shutdownAndTerminate(ExecutorService pool){
        try {
            long timeout = 100;
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            System.out.println("Waiting all threads completion for "
                    + timeout + " " + timeUnit + "...");
            // Blocks until timeout or all threads complete execution,
            // or the current thread is interrupted, whichever happens first.
            boolean isTerminated =
                    pool.awaitTermination(timeout, timeUnit);
            System.out.println("isTerminated()=" + isTerminated);
            if (!isTerminated) {
                System.out.println("Calling shutdownNow()...");
                List<Runnable> list = pool.shutdownNow();
                System.out.println(list.size() + " threads running");
                isTerminated =
                        pool.awaitTermination(timeout, timeUnit);
                if (!isTerminated) {
                    System.out.println("Some threads are still running");
                }
                System.out.println("Exiting");
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }

    private static class MyRunnable implements Runnable {
        private String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        public void run() {
            try {
                System.out.println(this.name + " is working...");
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(this.name + " is done");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(this.name + " was interrupted\n" +
                        this.name + " Thread.currentThread().isInterrupted()="
                        + Thread.currentThread().isInterrupted());
            }
        }
    }

}
