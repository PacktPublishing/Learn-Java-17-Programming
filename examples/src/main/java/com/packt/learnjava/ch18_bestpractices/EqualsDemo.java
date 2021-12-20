package com.packt.learnjava.ch18_bestpractices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EqualsDemo {
    public static void main(String... args){
        defaultEqualsAndHashCode();
        customEqualsAndHashCode();
        order();
        cloneDemo1();
        cloneDemo2();
        cloneDemo3();
    }

    private static void cloneDemo3() {
        Person4 p1 = new Person4(42, "Nick", "Samoylov",
                new Address("25 Main Street", "Denver"));
        Address address = new Address(p1.getAddress().getStreet(), p1.getAddress().getCity());
        Person4 p2 = new Person4(p1.getAge(), p1.getFirstName(), p1.getLastName(), address);
        System.out.println(p1.getAge() == p2.getAge());                //prints: true
        System.out.println(p1.getLastName() == p2.getLastName());      //prints: true
        System.out.println(p1.getLastName().equals(p2.getLastName())); //prints: true
        System.out.println(p1.getAddress() == p2.getAddress());        //prints: false
        System.out.println(p2.getAddress().getStreet());               //prints: 25 Main Street
        p1.getAddress().setStreet("42 Dead End");
        System.out.println(p2.getAddress().getStreet());               //prints: 25 Main Street
    }

    private static void cloneDemo2() {
        try {
            Person4 p1 = new Person4(42, "Nick", "Samoylov",
                    new Address("25 Main Street", "Denver"));
            Person4 p2 = p1.clone();
            System.out.println(p1.getAge() == p2.getAge());                //prints: true
            System.out.println(p1.getLastName() == p2.getLastName());      //prints: true
            System.out.println(p1.getLastName().equals(p2.getLastName())); //prints: true
            System.out.println(p1.getAddress() == p2.getAddress());        //prints: false
            System.out.println(p2.getAddress().getStreet());               //prints: 25 Main Street
            p1.getAddress().setStreet("42 Dead End");
            System.out.println(p2.getAddress().getStreet());               //prints: 25 Main Street
        } catch (CloneNotSupportedException ex){
            ex.printStackTrace();
        }
    }

    private static void cloneDemo1() {
        try {
            Person3 p1 = new Person3(42, "Nick", "Samoylov",
                    new Address("25 Main Street", "Denver"));
            Person3 p2 = p1.clone();
            System.out.println(p1.getAge() == p2.getAge());                //prints: true
            System.out.println(p1.getLastName() == p2.getLastName());      //prints: true
            System.out.println(p1.getLastName().equals(p2.getLastName())); //prints: true
            System.out.println(p1.getAddress() == p2.getAddress());        //prints: true
            System.out.println(p2.getAddress().getStreet());               //prints: 25 Main Street
            p1.getAddress().setStreet("42 Dead End");
            System.out.println(p2.getAddress().getStreet());               //prints: 42 Dead End
        } catch (CloneNotSupportedException ex){
            ex.printStackTrace();
        }
    }

    private static void order(){
        Person2 p1 = new Person2(15, "Zoe", "Adams");
        Person2 p2 = new Person2(25, "Nick", "Brook");
        Person2 p3 = new Person2(42, "Nick", "Samoylov");
        Person2 p4 = new Person2(50, "Ada", "Valentino");
        Person2 p6 = new Person2(50, "Bob", "Avalon");
        Person2 p5 = new Person2(10, "Zoe", "Adams");
        List<Person2> list = new ArrayList<>(List.of(p5, p2, p6, p1, p4, p3));
        Collections.sort(list);
        list.stream().forEach(System.out::println);
    }

    private static void customEqualsAndHashCode(){
        Person1 person1 = new Person1(42, "Nick", "Samoylov");
        Person1 person2 = person1;
        Person1 person3 = new Person1(42, "Nick", "Samoylov");
        System.out.println(person1.equals(person2)); //prints: true
        System.out.println(person1.equals(person3)); //prints: true
        System.out.println(person1.hashCode());      //prints: 2115012528
        System.out.println(person2.hashCode());      //prints: 2115012528
        System.out.println(person3.hashCode());      //prints: 2115012528
    }

    public static void defaultEqualsAndHashCode(){
        Person person1 = new Person(42, "Nick", "Samoylov");
        Person person2 = person1;
        Person person3 = new Person(42, "Nick", "Samoylov");
        System.out.println(person1.equals(person2)); //prints: true
        System.out.println(person1.equals(person3)); //prints: false
        System.out.println(person1.hashCode());      //prints: 777874839
        System.out.println(person2.hashCode());      //prints: 777874839
        System.out.println(person3.hashCode());      //prints: 596512129
    }

}
