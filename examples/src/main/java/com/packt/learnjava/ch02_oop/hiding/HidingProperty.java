package com.packt.learnjava.ch02_oop.hiding;

public class HidingProperty {
    private static String name1 = "static property";
    private String name2 = "instance property";

    public void method() {
        var name1 = "local variable";
        System.out.println(name1);     //prints: local variable

        var name2 = "local variable";  //prints: local variable
        System.out.println(name2);

        System.out.println(HidingProperty.name1);  //prints: static property
        System.out.println(this.name2);            //prints: instance property
    }
}
