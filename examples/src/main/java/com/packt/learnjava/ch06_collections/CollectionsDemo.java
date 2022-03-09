package com.packt.learnjava.ch06_collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class CollectionsDemo {
    public static void main(String... args) {
        initialize1();
        initialize2();
        initialize3();
        initialize4();
        initialize5();
        iterable();
        equals();
        copyOf();
        listIterator();
        sort1();
        sort2();
        map();
        unmodifiable();
    }

    private static void unmodifiable(){
        System.out.println("\nmodifiable():");

        Person1 p1 = new Person1(45, "Bill");
        List<Person1> list = List.of(p1);
        //list.add(new Person1(22, "Bob")); //UnsupportedOperationException
        System.out.println(list);  //[Person{age=45, name=Bill}]
        p1.setName("Kelly");       //[Person{age=45, name=Kelly}]
        System.out.println(list);
    }

    private static void initialize1(){
        System.out.println("\ninitialize1():");

        //Collection<String> coll = List.of("s1", null); //does not allow null
        Collection<String> coll = List.of("s1", "s1", "s2");
        //coll.add("s3");     //does not allow add element
        //((List<String>) coll).set(1, "s3"); //does not allow modify element
        //coll.remove("s1");  //does not allow remove element
        System.out.println(coll); //prints: [s1, s1, s2]

        //coll = Set.of("s3", "s3", "s4"); //does not allow duplicate
        //coll = Set.of("s2", "s3", null); //does not allow null
        coll = Set.of("s3", "s4");
        System.out.println(coll); //prints: [s3, s4] or [s4, s3]

        //coll.add("s5");   //does not allow add element
        //coll.remove("s2"); //does not allow remove
    }

    private static void initialize2(){
        System.out.println("\ninitialize2():");

        List<String> list1 = new ArrayList<>();
        list1.add("s1");
        list1.add("s1");
        System.out.println(list1);     //prints: [s1, s1]

        Set<String> set1 = new HashSet<>();
        set1.add("s1");
        set1.add("s1");
        System.out.println(set1);      //prints: [s1]
    }

    private static void initialize3(){
        System.out.println("\ninitialize3():");

        List<String> list1 = new ArrayList<>();
        list1.add("s1");
        list1.add("s1");
        System.out.println(list1);     //prints: [s1, s1]

        List<String> list2 = new ArrayList<>();
        list2.addAll(list1);
        System.out.println(list2);      //prints: [s1, s1]

        Set<String> set = new HashSet<>();
        set.addAll(list1);
        System.out.println(set);      //prints: [s1]

        Set<String> set1 = new HashSet<>();
        set1.add("s1");

        Set<String> set2 = new HashSet<>();
        set2.add("s1");
        set2.add("s2");

        System.out.println(set1.addAll(set2)); //prints: true
        System.out.println(set1);              //prints: [s1, s2]
    }

    private static void initialize4(){
        System.out.println("\ninitialize4():");

        Set<String> set = new HashSet<>();
        System.out.println(set.add("s1"));   //prints: true
        System.out.println(set.add("s1"));   //prints: false
        System.out.println(set);             //prints: [s1]

        Set<String> set1 = new HashSet<>();
        set1.add("s1");
        set1.add("s2");

        Set<String> set2 = new HashSet<>();
        set2.add("s1");
        set2.add("s2");

        System.out.println(set1.addAll(set2)); //prints: false
        System.out.println(set1);              //prints: [s1, s2]

    }

    private static void initialize5(){
        System.out.println("\ninitialize5():");

        Collection<String> list1 = List.of("s1", "s1", "s2");
        System.out.println(list1);     //prints: [s1, s1, s2]

        List<String> list2 = new ArrayList<>(list1);
        System.out.println(list2);      //prints: [s1, s1, s2]

        Set<String> set = new HashSet<>(list1);
        System.out.println(set);        //prints: [s1, s2]

        List<String> list3 = new ArrayList<>(set);
        System.out.println(list3);      //prints: [s1, s2]

    }

    private static void iterable(){
        System.out.println("\niterable():");

        Iterable<String> list = List.of("s1", "s2", "s3");
        System.out.println(list);       //prints: [s1, s2, s3]

        for(String e: list){
            System.out.print(e + " ");  //prints: s1 s2 s3
        }
        System.out.println();
        list.forEach(e -> System.out.print(e + " ")); //prints: s1 s2 s3
        System.out.println();
    }

    private static void equals(){
        System.out.println("\nequals():");

        Collection<String> list1 = List.of("s1", "s2", "s3");
        System.out.println(list1);       //prints: [s1, s2, s3]

        Collection<String> list2 = List.of("s1", "s2", "s3");
        System.out.println(list2);       //prints: [s1, s2, s3]

        System.out.println(list1.equals(list2));  //prints: true

        Collection<String> list3 = List.of("s2", "s1", "s3");
        System.out.println(list3);       //prints: [s2, s1, s3]

        System.out.println(list1.equals(list3));  //prints: false

        Collection<String> set1 = Set.of("s1", "s2", "s3");
        System.out.println(set1);       //prints: [s2, s3, s1] or different order

        Collection<String> set2 = Set.of("s2", "s1", "s3");
        System.out.println(set2);       //prints: [s2, s1, s3] or different order

        System.out.println(set1.equals(set2));  //prints: true

        Collection<String> set3 = Set.of("s4", "s1", "s3");
        System.out.println(set3);              //prints: [s4, s1, s3] or different order

        System.out.println(set1.equals(set3));  //prints: false
    }

    private static void copyOf(){
        System.out.println("\ncopyOf():");

        Collection<String> list = List.of("s1", "s2", "s3");
        System.out.println(list);       //prints: [s1, s2, s3]

        List<String> list1 = List.copyOf(list);
        //list1.add("s4");                //run-time error
        //list1.set(1, "s5");             //run-time error
        //list1.remove("s1");             //run-time error

        Set<String> set = new HashSet<>();
        System.out.println(set.add("s1"));
        System.out.println(set);          //prints: [s1]

        Set<String> set1 = Set.copyOf(set);
        //set1.add("s2");                   //run-time error
        //set1.remove("s1");                //run-time error

        Set<String> set2 = Set.copyOf(list);
        System.out.println(set2);       //prints: [s1, s2, s3]
    }

    private static void listIterator(){
        System.out.println("\nlistIterator():");

        List<String> list = List.of("s1", "s2", "s3");
        ListIterator<String> li = list.listIterator();

        while(li.hasNext()){
            System.out.print(li.next() + " ");  //prints: s1 s2 s3
        }

        System.out.println();

        while(li.hasPrevious()){
            System.out.print(li.previous() + " ");  //prints: s3 s2 s1
        }

        System.out.println();

        ListIterator<String> li1 = list.listIterator(1);
        while(li1.hasNext()){
            System.out.print(li1.next() + " ");  //prints: s2 s3
        }
        System.out.println();

        ListIterator<String> li2 = list.listIterator(1);
        while(li2.hasPrevious()){
            System.out.print(li2.previous() + " "); //prints: s1
        }
        System.out.println();
    }

    private static void sort1(){
        System.out.println("\nsort1():");

        List<String> list = new ArrayList<>();
        list.add("S2");
        list.add("s3");
        list.add("s1");
        System.out.println(list);                //prints: [S2, s3, s1]

        list.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println(list);                //prints: [s1, S2, s3]

        list.sort(Comparator.naturalOrder());
        System.out.println(list);               //prints: [S2, s1, s3]

        list.sort(Comparator.reverseOrder());
        System.out.println(list);               //prints: [s3, s1, S2]

        list.add(null);
        list.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
        System.out.println(list);              //prints: [null, S2, s1, s3]

        list.sort(Comparator.nullsLast(Comparator.naturalOrder()));
        System.out.println(list);              //prints: [S2, s1, s3, null]

        Comparator<String> comparator = (s1, s2) ->
            s1 == null ? -1 : s1.compareTo(s2);
        list.sort(comparator);
        System.out.println(list);              //prints: [S2, null, s1, s3]
    }

    private static void sort2(){
        System.out.println("\nsort2():");

        List<Person> list = new ArrayList<>();
        list.add(new Person(45, "Bill"));
        list.add(new Person(42, "Kelly"));
        list.add(new Person(34, "Kelly"));
        list.add(new Person(25, "Courtney"));

        System.out.println();
        list.forEach(System.out::println);

        list.sort(Comparator.naturalOrder());
        System.out.println();
        list.forEach(System.out::println);

        list.sort(Comparator.reverseOrder());
        System.out.println();
        list.forEach(System.out::println);

        list.sort(Comparator.comparing(Person::getName));
        System.out.println();
        list.forEach(System.out::println);

        list.sort(Comparator.comparing(Person::getAge));
        System.out.println();
        list.forEach(System.out::println);

        list.sort(Comparator.comparing(Person::getName).thenComparing(Person::getAge));
        System.out.println();
        list.forEach(System.out::println);

    }

    private static class Person implements Comparable<Person> {
        private int age;
        private String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name == null ? "" : name;
        }

        public int getAge(){ return this.age; }

        public String getName(){ return this.name; }

        @Override
        public int compareTo(Person p){
            //return age - p.getAge();
            //return name.compareTo(p.getName());
            int result = this.name.compareTo(p.getName());
            if (result != 0) {
                return result;
            }
            return this.age - p.getAge();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if(!(o instanceof Person person)) return false;
            return age == person.getAge() &&
                    Objects.equals(name, person.getName());
        }

        @Override
        public int hashCode(){
            return Objects.hash(age, name);
        }

        @Override
        public String toString() {
            return "Person{age=" + age +
                    ", name=" + name + "}";
        }
    }

    private static class Person1 {
        private int age;
        private String name;

        public Person1(int age, String name) {
            this.age = age;
            this.name = name == null ? "" : name;
        }
        public void setName(String name){ this.name = name; }
        @Override
        public String toString() {
            return "Person{age=" + age +
                    ", name=" + name + "}";
        }
    }

    private static void map(){
        System.out.println("\nmap():");

        Map<Integer, String> map = Map.of(1, "s1", 2, "s2", 3, "s3");

        for(Integer key: map.keySet()){
            System.out.print(key + ", " + map.get(key) + ", ");  //prints: 3, s3, 2, s2, 1, s1,
        }

        System.out.println();

        for(Map.Entry e: map.entrySet()){
            System.out.print(e.getKey() + ", " + e.getValue() + ", "); //prints: 2, s2, 3, s3, 1, s1,
        }

    }




}
