package com.packt.learnjava.ch18_bestpractices;

public class Person {
    private int age;
    private String firstName, lastName;

    public Person(int age, String firstName, String lastName) {
        this.age = age;
        this.lastName = lastName;
        this.firstName = firstName;
    }
    public int getAge() { return age; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

}
