package com.packt.learnjava.ch14_streams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Initialize {
    public static void main(String... args){
        emptyOfAndOfNullable();
        iterate();
        concat();
        generate();
        builder();
    }

    private static void builder() {
        Stream.<String>builder().add("cat").add(" dog").add(" bear")
                .build().forEach(System.out::print);  //prints: cat dog bear

        System.out.println();

        Stream.Builder<String> builder = Stream.builder();
        List.of("1", "2", "3").stream().forEach(builder);
        builder.build().forEach(System.out::print);        //prints: 123

        System.out.println();

        List<String> values = List.of("cat", " dog", " bear");
        //Stream.Builder<String>
                builder = Stream.builder();
        for(String s: values){
            if(s.contains("a")){
                builder.accept(s);
            }
        }
        builder.build().forEach(System.out::print);        //prints: cat bear
    }

    private static void generate() {
        Stream.generate(() -> 1).limit(5)
                .forEach(System.out::print);       //prints: 11111

        System.out.println();

        Stream.generate(() -> new Random().nextDouble()).limit(5)
                .forEach(System.out::println);      //prints: 0.38575117472619247
                                                    //        0.5055765386778835
                                                    //        0.6528038976983277
                                                    //        0.4422354489467244
                                                    //        0.06770955839148762
    }

    private static void concat(){
        Stream<Integer> stream1 = List.of(1, 2).stream();
        Stream<Integer> stream2 = List.of(2, 3).stream();
        Stream.concat(stream1, stream2)
                .forEach(System.out::print);        //prints: 1223
    }

    private static void iterate(){
        Stream.iterate(1, i -> ++i).limit(9)
                .forEach(System.out::print);        //prints: 123456789

        System.out.println();

        Stream.iterate(1, i -> i < 10, i -> ++i)
                .forEach(System.out::print);        //prints: 123456789
    }

    private static void emptyOfAndOfNullable() {
        Stream.empty().forEach(System.out::println);  //prints nothing
        new ArrayList().stream().forEach(System.out::println);  //prints nothing
        new ArrayList().forEach(System.out::println);  //prints nothing

        Stream.of().forEach(System.out::print);       //prints nothing
        Stream.of(1).forEach(System.out::print);       //prints: 1

        System.out.println();

        Stream.of(1,2).forEach(System.out::print);    //prints: 12

        System.out.println();

        Stream.of("1 ","2").forEach(System.out::print);    //prints: 1 2

        System.out.println();

        String[] strings = {"1 ", "2"};
        Stream.of(strings).forEach(System.out::print);      //prints: 1 2

        System.out.println();

        Stream<Integer> stream1 = Stream.of(1, 2);
        Stream<Integer> stream2 = Stream.of(2, 3);
        Stream<Integer> stream3 = Stream.of(3, 4);
        Stream<Integer> stream4 = Stream.of(4, 5);

        Stream.of(stream1, stream2, stream3, stream4)
                .forEach(System.out::print);
                         //prints: java.util.stream.ReferencePipeline$Head@58ceff1j
        System.out.println();

        Stream.of(stream1, stream2, stream3, stream4)
                .flatMap(e -> e).forEach(System.out::print);   //prints: 12233445

        //printList1(null);                          //NullPointerException
        List<String> list = List.of("1 ", "2");
        printList1(list);                            //prints: 1 2
        printList2(null);            //prints nothing

        System.out.println();

        printList2(list);            //prints: [1 , 2]

        System.out.println();

        printList3(list);            //prints: 1 2

        System.out.println();

        printList4(list);            //prints: 1 2

        System.out.println();

        String s = Stream.of("1","2").collect(Collectors.joining(","));

        System.out.println(s);     //prints: 1,2
    }

    private static void printList1(List<String> list){
        list.stream().forEach(System.out::print);
    }

    private static void printList2(List<String> list){
        Stream.ofNullable(list).forEach(System.out::print);
    }

    private static void printList3(List<String> list){
        Stream.ofNullable(list).flatMap(e -> e.stream())
                .forEach(System.out::print);
    }

    private static void printList4(List<String> list){
        Stream.ofNullable(list).flatMap(Collection::stream)
                .forEach(System.out::print);
    }

}
