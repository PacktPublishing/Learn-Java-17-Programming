package com.packt.learnjava.ch14_streams;

public class Person {
    private int age;
    private String name;
    public Person(){}
    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
    public int getAge() {return this.age; }
    public String getName() { return this.name; }
    public void setAge(int age) { this.age = age;}
    public void setName(String name) { this.name = name; }
    @Override
    public String toString() {
        return "Person{" + "name='" + this.name + "'" +
                ", age=" + age + "}";
    }

}
