package com.packt.learnjava.ch02_oop;

import com.packt.learnjava.ch02_oop.hiding.C;
import com.packt.learnjava.ch02_oop.hiding.D;
import com.packt.learnjava.ch02_oop.hiding.HidingProperty;

public class Hiding {
    public static void main(String... args) {
        System.out.println(B.NAME); //prints: interface B
        B.method();                 //prints: interface B

        System.out.println(D.NAME);  //prints: class D
        D.method();                  //prints: class D

        C obj = new D();

        System.out.println(obj.NAME);       //prints: class C
        System.out.println(((D) obj).NAME); //prints: class D

        obj.method();                       //prints: class C
        ((D)obj).method();                  //prints: class D

        System.out.println(obj.name1);       //prints: class C
        System.out.println(((D) obj).name1); //prints: class D

        X x = new Y();
        System.out.println(x.getName());      //prints: class Y
        System.out.println(((Y)x).getName()); //prints: class Y

        HidingProperty hiding = new HidingProperty();
        hiding.method();
    }

    interface A {
        String NAME = "interface A";
        static void method() {
            System.out.println("interface A");;
        }
    }
    interface B extends A {
        String NAME = "interface B";
        static void method() {
            System.out.println("interface B");;
        }
    }

    private static class X {
        private String name = "class X";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    private static class Y extends X {
        private String name = "class Y";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
