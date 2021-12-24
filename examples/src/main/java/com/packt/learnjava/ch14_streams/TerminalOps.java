package com.packt.learnjava.ch14_streams;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOps {
    public static void main(String... args){
        collect1();
        foreach();
        createPersons();
        count();
        match();
        find();
        optional();
        minMax();
        toArray();
        reduce();
        collect2();
    }

    private static void collect1(){
        List<String> asList =
                Stream.of("1", "2", "3", "4", "5")
                      .collect(ArrayList::new,
                               ArrayList::add,
                               ArrayList::addAll);
        System.out.println(asList);   //prints: [1, 2, 3, 4, 5]
    }

    private static void foreach(){
        Path path = Paths.get("src/main/resources/persons.csv");
        try (Stream<String> lines = Files.newBufferedReader(path).lines()) {
            lines.filter(s -> s.contains("J"))
                 .forEach(System.out::println);  //prints: 23 , Ji m
                                                 //          15 , Jill
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void createPersons(){
        List<Person> persons = new ArrayList<>();
        Path path = Paths.get("src/main/resources/persons.csv");
        try (Stream<String> lines = Files.newBufferedReader(path).lines()) {
            //persons = createPersonsUsingForEach(lines);
            //persons = createPersonsUsingCollect1(lines);
            persons = new TerminalOps().createPersonsUsingCollect2(lines);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        persons.stream().forEach(System.out::println);
                                        //prints: Person{name='Jim', age=23}
                                        //        Person{name='Jill', age=15}
    }

    private static List<Person> createPersonsUsingForEach(Stream<String> lines){
        List<Person> persons = new ArrayList<>();
        lines.filter(s -> s.contains("J")).forEach(s -> {
            String[] arr = s.split(",");
            int age = Integer.valueOf(StringUtils.remove(arr[0], ' '));
            persons.add(new Person(age, StringUtils.remove(arr[1], ' ')));
        });
        return persons;
    }

    private static List<Person> createPersonsUsingCollect1(Stream<String> lines){
        return lines.filter(s -> s.contains("J")).map(s -> s.split(","))
                .map(arr -> {
                    int age = Integer.valueOf(StringUtils.remove(arr[0], ' '));
                    return new Person(age, StringUtils.remove(arr[1], ' '));
                }).collect(Collectors.toList());
    }

    private List<Person> createPersonsUsingCollect2(Stream<String> lines){
        return lines.filter(s -> s.contains("J")).map(s -> s.split(","))
                .map(this::createPerson)
                .collect(Collectors.toList());
    }

    private Person createPerson(String[] arr){
        int age = Integer.valueOf(StringUtils.remove(arr[0], ' '));
        return new Person(age, StringUtils.remove(arr[1], ' '));
    }

    private static void count(){
        long count = Stream.of("1", "2", "3", "4", "5")
                .peek(System.out::print)
                .count();
        System.out.print(count);                 //prints: 5

        System.out.println();

        //long
                count = Stream.of("1", "2", "3", "4", "5")
                .peek(System.out::print)         //prints: 12345
                .collect(Collectors.counting());
        System.out.println();
        System.out.println(count);                //prints: 5

    }

    private static void match(){
        List<String> list = List.of("1", "2", "3", "4", "5");
        boolean found = list.stream()
                .peek(System.out::print)       //prints: 123
                .anyMatch(e -> "3".equals(e));
        System.out.println();
        System.out.println(found);             //prints: true
        boolean noneMatches = list.stream()
                .peek(System.out::print)       //prints: 123
                .noneMatch(e -> "3".equals(e));
        System.out.println();
        System.out.println(noneMatches);       //prints: true
        boolean allMatch = list.stream()
                .peek(System.out::print)       //prints: 1
                .allMatch(e -> "3".equals(e));
        System.out.println();
        System.out.println(allMatch);          //prints: false
    }

    private static void find(){
        List<String> list = List.of("1", "2", "3", "4", "5");
        Optional<String> result = list.stream().findAny();
        System.out.println(result.isPresent());    //prints: true
        System.out.println(result.get());          //prints: 1
        result = list.stream()
                     .filter(e -> "42".equals(e))
                     .findAny();
        System.out.println(result.isPresent());    //prints: false
        //System.out.println(result.get());        //NoSuchElementException
        result = list.stream().findFirst();
        System.out.println(result.isPresent());    //prints: true
        System.out.println(result.get());          //prints: 1
    }

    private static void optional(){
        List<String> list = List.of("1", "2", "3", "4", "5");
        String result = list.stream().filter(e -> "42".equals(e))
                .findAny().or(() -> Optional.of("Not found")).get();
        System.out.println(result);                       //prints: Not found

        result = list.stream().filter(e -> "42".equals(e))
                .findAny().orElse("Not found");
        System.out.println(result);                      //prints: Not found

        Supplier<String> trySomethingElse = () -> {
            //Code that tries something else
            return "43";
        };
        result = list.stream().filter(e -> "42".equals(e))
                .findAny().orElseGet(trySomethingElse);
        System.out.println(result);                          //prints: 43

        list.stream().filter(e -> "42".equals(e))
                .findAny().ifPresentOrElse(System.out::println,
                () -> System.out.println("Not found")); //prints: Not found
    }

    private static void minMax(){
        List<String> list = List.of("a", "b", "c", "c", "a");
        String min = list.stream().min(Comparator.naturalOrder()).orElse("0");
        System.out.println(min);     //prints: a
        String max = list.stream().max(Comparator.naturalOrder()).orElse("0");
        System.out.println(max);     //prints: c

        int mn = Stream.of(42, 77, 33).min(Comparator.naturalOrder()).orElse(0);
        System.out.println(mn);    //prints: 33
        int mx = Stream.of(42, 77, 33).max(Comparator.naturalOrder()).orElse(0);
        System.out.println(mx);    //prints: 77

        List<Person> persons = List.of(new Person(23, "Bob"),
                                        new Person(33, "Jim"),
                                        new Person(28, "Jill"),
                                        new Person(27, "Bill"));
        Comparator<Person> perComp = (p1, p2) -> p1.getAge() - p2.getAge();
        Person theOldest = persons.stream().max(perComp).orElse(null);
        System.out.println(theOldest);  //prints: Person{name='Jim', age=33}
    }

    private static void toArray(){
        List<String> list = List.of("a", "b", "c");
        Object[] obj = list.stream().toArray();
        Arrays.stream(obj).forEach(System.out::print);    //prints: abc

        System.out.println();

        //String[] str = list.stream().toArray(String[]::new);
        //String[] str = list.stream().toArray(i -> new String[i]);
        String[] str = list.stream().toArray(i -> {
            System.out.println(i);    //prints: 3
           return  new String[i];
        });
        //String[] str = list.toArray(new String[list.size()]);
        Arrays.stream(str).forEach(System.out::print);    //prints: abc
    }

    private static void reduce(){
        List<Person> list = List.of(new Person(23, "Bob"),
                                    new Person(33, "Jim"),
                                    new Person(28, "Jill"),
                                    new Person(27, "Bill"));
        Person theOldest = list.stream()
                .reduce((p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2).orElse(null);
        System.out.println(theOldest);         //prints: Person{name='Jim', age=33}

        String allNames = list.stream().map(p -> p.getName())
                .reduce((n1, n2) -> n1 + ", " + n2).orElse(null);
        System.out.println(allNames);            //prints: Bob, Jim, Jill, Bill

        String all = list.stream().map(p -> p.getName())
                .reduce("All names: ", (n1, n2) -> n1 + ", " + n2);
        System.out.println(all);       //All names: , Bob, Jim, Jill, Bill

        //String
                all = "All names: " + list.stream().map(p -> p.getName())
                .reduce((n1, n2) -> n1 + ", " + n2).orElse(null);
        System.out.println(all);         //All names: Bob, Jim, Jill, Bill

        //String
                all = list.stream().map(p -> p.getName())
                .reduce("All names:", (n1, n2) -> n1 + " " + n2);
        System.out.println(all);        //prints: All names: Bob Jim Jill Bill


        //String
                all = list.stream().map(p -> p.getName())
                .reduce("All names:", (n1, n2) -> n1 + " " + n2,
                        (n1, n2) -> n1 + " " + n2 );
        System.out.println(all);          //prints: All names: Bob Jim Jill Bill

        //String
        all = list.parallelStream().map(p -> p.getName())
                .reduce("All names:", (n1, n2) -> n1 + " " + n2,
                        (n1, n2) -> n1 + " " + n2 );
        System.out.println(all);          //All names: Bob All names: Jim All names: Jill All names: Bill

        //String
        all = list.parallelStream().map(p -> p.getName())
                .reduce("All names:", (n1, n2) -> n1 + " " + n2);
        System.out.println(all);          //All names: Bob All names: Jim All names: Jill All names: Bill

        //String
        all = list.parallelStream().map(p->p.getName())
                .reduce("All names:", (n1, n2) -> n1 + " " + n2,
                        (n1, n2) -> n1 + " " + StringUtils.remove(n2, "All names: "));
        System.out.println(all);       //prints: All names: Bob Jim Jill Bill

        List<Integer> ints = List.of(1, 2, 3);
        int sum = ints.stream().reduce((i1, i2) -> i1 + i2).orElse(0);
        System.out.println(sum);                          //prints: 6
        sum = ints.stream().reduce(Integer::sum).orElse(0);
        System.out.println(sum);                          //prints: 6
        sum = ints.stream().reduce(10, Integer::sum);
        System.out.println(sum);                         //prints: 16
        sum = ints.stream().reduce(10, Integer::sum, Integer::sum);
        System.out.println(sum);                         //prints: 16

        //List<Integer>
        ints = List.of(1, 2, 3);
        //int
        sum = ints.parallelStream().reduce(10, Integer::sum, Integer::sum);
        System.out.println(sum);                                   //prints: 36

        //List<Integer>
        ints = List.of(1, 2, 3);
        //int
        sum = ints.parallelStream().reduce(0, Integer::sum, Integer::sum);
        System.out.println(sum);                                   //prints: 6
        sum = 10 + ints.parallelStream().reduce(0, Integer::sum, Integer::sum);
        System.out.println(sum);                                  //prints: 16
    }

    private static void collect2(){
        List<Person> list = List.of(new Person(23, "Bob"),
                                    new Person(33, "Jim"),
                                    new Person(28, "Jill"),
                                    new Person(27, "Bill"));
        BiConsumer<Person, Person> accumulator = (p1, p2) -> {
            if(p1.getAge() < p2.getAge()){
                p1.setAge(p2.getAge());
                p1.setName(p2.getName());
            }
        };
        BiConsumer<Person, Person> combiner = (p1, p2) -> {
            System.out.println("Combiner is called!");
            if(p1.getAge() < p2.getAge()){
                p1.setAge(p2.getAge());
                p1.setName(p2.getName());
            }
        };
        //Person theOldest = list.stream().collect(Person::new, accumulator, combiner);
        Person theOldest = list.parallelStream().collect(Person::new, accumulator, combiner);
        System.out.println(theOldest);        //prints: Person{name='Jim', age=33}

        List<String> ls = Stream.of("a", "b", "c").collect(Collectors.toList());
        System.out.println(ls);                //prints: [a, b, c]
        Set<String> set = Stream.of("a", "a", "c").collect(Collectors.toSet());
        System.out.println(set);                //prints: [a, c]
        Map<String, Person> map = list.stream()
                .collect(Collectors.toMap(p->p.getName() + "-" + p.getAge(), p->p));
        System.out.println(map); //prints: {Bob-23=Person{name:Bob,age:23},
                                 //         Bill-27=Person{name:Bill,age:27},
                                 //         Jill-28=Person{name:Jill,age:28},
                                 //         Jim-33=Person{name:Jim,age:33}}
        Set<Person> personSet = list.stream()
            .collect(Collectors.toCollection(HashSet::new));
        System.out.println(personSet);  //prints: [Person{name:Bill,age:27},
                                        //         Person{name:Jim,age:33},
                                        //         Person{name:Bob,age:23},
                                        //         Person{name:Jill,age:28}]


        List<String> list1 = List.of("a", "b", "c", "d");
        String result = list1.stream().collect(Collectors.joining());
        System.out.println(result);           //prints: abcd
        result = list1.stream().collect(Collectors.joining(", "));
        System.out.println(result);           //prints: a, b, c, d
        result = list1.stream()
                .collect(Collectors.joining(", ", "The result: ", ""));
        System.out.println(result);          //prints: The result: a, b, c, d
        result = list1.stream()
                .collect(Collectors.joining(", ", "The result: ", ". The End."));
        System.out.println(result); //prints: The result: a, b, c, d. The End.

        List<Person> list2 = List.of(new Person(23, "Bob"),
                                     new Person(33, "Jim"),
                                     new Person(28, "Jill"),
                                     new Person(27, "Bill"));
        int sum = list2.stream().collect(Collectors.summingInt(Person::getAge));
        System.out.println(sum);  //prints: 111
        IntSummaryStatistics stats =
                list2.stream().collect(Collectors.summarizingInt(Person::getAge));
        System.out.println(stats);     //IntSummaryStatistics{count=4, sum=111,
                                       //    min=23, average=27.750000, max=33}
        System.out.println(stats.getCount());    //4
        System.out.println(stats.getSum());      //111
        System.out.println(stats.getMin());      //23
        System.out.println(stats.getAverage());  //27.750000
        System.out.println(stats.getMax());      //33

        Map<Boolean, List<Person>> map2 =
                list2.stream().collect(Collectors.partitioningBy(p->p.getAge() > 27));
        System.out.println(map2);
                        //{false=[Person{name='Bob', age=23}, Person{name='Bill', age=27},
                       //  true=[Person{name='Jim', age=33}, Person{name='Jill', age=28}]}

        List<Person> list3 = List.of(new Person(23, "Bob"),
                                     new Person(33, "Jim"),
                                     new Person(23, "Jill"),
                                     new Person(33, "Bill"));
        Map<Integer, List<Person>> map3 =
                list3.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println(map3);
                        //{33=[Person{name='Jim', age=33}, Person{name='Bill', age=33}],
                        // 23=[Person{name='Bob', age=23}, Person{name='Jill', age=23}]}
    }

}
