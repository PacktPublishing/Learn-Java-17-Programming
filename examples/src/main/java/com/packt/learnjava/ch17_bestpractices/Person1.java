package com.packt.learnjava.ch17_bestpractices;

import java.util.Objects;

public class Person1 {
    private int age;
    private String firstName, lastName;

    public Person1(int age, String firstName, String lastName) {
        this.age = age;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public int getAge() { return age; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if(!(o instanceof Person1 person)) return false;
        return getAge() == person.getAge() &&
                Objects.equals(getFirstName(), person.getFirstName()) &&
                Objects.equals(getLastName(), person.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAge(), getFirstName(), getLastName());
    }
}
