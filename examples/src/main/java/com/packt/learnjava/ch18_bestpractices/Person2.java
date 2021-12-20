package com.packt.learnjava.ch18_bestpractices;

import java.util.Comparator;
import java.util.Objects;

public class Person2 implements Comparable<Person2> {
    private int age;
    private String firstName, lastName;

    public Person2(int age, String firstName, String lastName) {
        this.age = age;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public int getAge() { return age; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }
    public void setFirstName(String n){
        this.firstName = n;
    }

    @Override
    public int compareTo(Person2 p) {
        int result = Objects.compare(getFirstName(), p.getFirstName(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(getLastName(), p.getLastName(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(age, p.getAge(), Comparator.naturalOrder());
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", " + age;
    }
}
