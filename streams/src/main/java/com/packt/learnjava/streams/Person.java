package com.packt.learnjava.streams;

public class Person {
    private int age, zip;
    private String fName, lName, street, city, state;
    public Person(int age, String fName, String lName) {
        this.age = age;
        this.fName = fName;
        this.lName = lName;
    }
    public int getAge() {return this.age; }
    public int getZip() { return zip; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getName() { return this.fName + " " + this.lName; }
    public void setAddress(String street, String city, String state, int zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
}