package com.packt.learnjava.ch02_oop;

import java.util.Objects;

public class Record {
    public static void main(String... args) {
        Person person = new Person(25, "Bill");
        System.out.println(person);  //prints: Person{age=25, name='Bill'}
        System.out.println(person.name());  //prints: Bill

        Person person1 = new Person(25, "Bill");
        System.out.println(person.equals(person1)); //prints: true

        PersonR personR = new PersonR(25, "Bill");
        System.out.println(personR);   //prints: PersonR{age=25, name='Bill'}
        System.out.println(personR.name());      //prints: Bill

        PersonR personR1 = new PersonR(25, "Bill");
        System.out.println(personR.equals(personR1)); //prints: true

        System.out.println(personR.equals(person));   //prints: false
    }

    private record PersonR(int age, String name){}

    /**
     * Please, notice that the getters in the class above do not have prefix “get”.
     * It is done deliberately, because in the case of immutable class,
     * there is no need to distinguish between getters and setters,
     * as the setters do not and should not exist, if we would like to have the class truly immutable.
     * And that is the principal difference between such class and Java bean,
     * which is mutable and has both – setters and getters.
     *
     * The record allows to replace the following implementation with one line only:
     * record Person(int age, String name){}
     */
    private static final class Person{
        private int age;
        private String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int age() { return age; }
        public String name() { return name; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, name);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    private interface Student{
        String getSchoolName();
    }

    private record StudentImpl(int age, String name, String schoolName) implements Student{
        public StudentImpl(int age, String name) {
            this(age, name, "Unknown");
        }
        public static String getSchoolName(Student student) {
            return student.getSchoolName();
        }
        @Override
        public String getSchoolName() { return schoolName(); }
    }

}