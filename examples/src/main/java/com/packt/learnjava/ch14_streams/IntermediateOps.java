package com.packt.learnjava.ch14_streams;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class IntermediateOps {

    public static void main(String... args){
        filtering();
        mapping();
        sorting();
        peeking();
    }

    private static void filtering(){
        Stream.of("3", "2", "3", "4", "2").distinct()
                .forEach(System.out::print);                     //prints: 324

        System.out.println();

        List<String> list = List.of("1", "2", "3", "4", "5");
        list.stream().skip(3).forEach(System.out::print);         //prints: 45

        System.out.println();

        list.stream().limit(3).forEach(System.out::print);        //prints: 123

        System.out.println();

        list.stream().filter(s -> Objects.equals(s, "2"))
                .forEach(System.out::print);                      //prints: 2

        System.out.println();

        list.stream().dropWhile(s -> Integer.valueOf(s) < 3)
                .forEach(System.out::print);                      //prints: 345

        System.out.println();

        list.stream().takeWhile(s -> Integer.valueOf(s) < 3)
                .forEach(System.out::print);                      //prints: 12
    }

    private static void mapping(){
        List<String> list = List.of("1", "2", "3", "4", "5");
        list.stream().map(s -> s + s)
                     .forEach(System.out::print);    //prints: 1122334455

        System.out.println();

        list.stream().mapToInt(Integer::valueOf)
                     .forEach(System.out::print);    //prints: 12345

        System.out.println();

        list.stream().mapToLong(Long::valueOf)
                     .forEach(System.out::print);    //prints: 12345

        System.out.println();

        list.stream().mapToDouble(Double::valueOf)
                .mapToObj(Double::toString)
                .map(s -> s + " ")
                .forEach(System.out::print);    //prints: 1.0 2.0 3.0 4.0 5.0

        System.out.println();

        list.stream().mapToInt(Integer::valueOf)
                .flatMap(n -> IntStream.iterate(1, i -> i < n, i -> ++i))
                .forEach(System.out::print);        //prints: 1121231234

        System.out.println();

        list.stream().map(Integer::valueOf)
                .flatMapToInt(n ->
                        IntStream.iterate(1, i -> i < n, i -> ++i))
                .forEach(System.out::print);        //prints: 1121231234

        System.out.println();

        list.stream().map(Integer::valueOf)
                .flatMapToLong(n ->
                        LongStream.iterate(1, i -> i < n, i -> ++i))
                .forEach(System.out::print);        //prints: 1121231234;

        System.out.println();

        list.stream().map(Integer::valueOf)
                .flatMapToDouble(n ->
                        DoubleStream.iterate(1, i -> i < n, i -> ++i))
                .mapToObj(Double::toString)
                .map(s -> s + " ")
                .forEach(System.out::print);
                            //prints: 1.0 1.0 2.0 1.0 2.0 3.0 1.0 2.0 3.0 4.0
    }

    private static void sorting(){
        List<String> list = List.of("2", "1", "5", "4", "3");
        list.stream().sorted().forEach(System.out::print);  //prints: 12345

        System.out.println();

        list.stream().sorted(Comparator.reverseOrder())
                     .forEach(System.out::print);           //prints: 54321
    }

    private static void peeking(){
        List<String> list = List.of("1", "2", "3", "4", "5");
        list.stream()
            .peek(s-> System.out.print("3".equals(s) ? 3 : 0))
            .forEach(System.out::print);    //prints: 0102330405
    }
}
