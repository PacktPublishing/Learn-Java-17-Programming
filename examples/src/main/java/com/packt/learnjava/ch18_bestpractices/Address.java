package com.packt.learnjava.ch18_bestpractices;

public class Address implements Cloneable{
    private String street, city;

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public void setStreet(String street) { this.street = street; }

    public String getStreet() { return street; }

    public String getCity() { return city; }

    @Override
    public Address clone() throws CloneNotSupportedException {
        return (Address)super.clone();
    }
}
