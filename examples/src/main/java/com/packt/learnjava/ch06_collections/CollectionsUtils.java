package com.packt.learnjava.ch06_collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsUtils {
    public static void main(String... args) {
        javaUtilCollections_copy();
        javaUtilCollections_sort();
    }

    private static void javaUtilCollections_copy(){
        System.out.println("\njavaUtilCollections_copy():");

        List<String> list1 = Arrays.asList("s1","s2");
        List<String> list2 = Arrays.asList("s3", "s4", "s5");
        Collections.copy(list2, list1);
        System.out.println(list2);    //prints: [s1, s2, s5]
    }

    private static void javaUtilCollections_sort(){
        System.out.println("\njavaUtilCollections_sort():");

        //List<String> list = List.of("a", "X", "10", "20", "1", "2");
        List<String> list = Arrays.asList("a", "X", "10", "20", "1", "2");
        Collections.sort(list);
        System.out.println(list);     //prints: [1, 10, 2, 20, X, a]
        list.forEach(s -> {
            for(int i = 0; i < s.length(); i++){
                System.out.print(" " + Character.codePointAt(s, i));
            }
            if(!s.equals("a")) {
                System.out.print(",");   //prints: 49, 49 48, 50, 50 48, 88, 97,
            }
        });
        System.out.println();
        List<Person> persons = Arrays.asList(new Person(23, "Jack"),
                new Person(30, "Bob"), new Person(15, "Bob"));
        Collections.sort(persons, new ComparePersons());
        System.out.println(persons);     //prints: [Person{name=Bob, age=15}, Person{name=Bob, age=30}, Person{name=Jack, age=23}]

    }

    private static class ComparePersons implements Comparator<Person> {
        public int compare(Person p1, Person p2){
            int result = p1.getName().compareTo(p2.getName());
            if (result != 0) {
                return result;
            }
            return p1.age - p2.getAge();
        }

    }

    private static class Person  {
        private int age;
        private String name;
        public Person(int age, String name) {
            this.age = age;
            this.name = name == null ? "" : name;
        }
        public int getAge() { return this.age; }
        public String getName() { return this.name; }

        @Override
        public String toString() {
            return "Person{" +
                    "name=" + name +
                    ", age=" + age + "}";
        }
    }
}
