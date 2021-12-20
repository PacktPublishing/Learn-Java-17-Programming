package com.packt.learnjava.ch03_fundamentals;

public class BoxingUnboxing {
    public static void main(String... args){
        boxing();
        unboxing();
    }

    private static void boxing(){
        int i1 = 42;
        Integer i2 = i1;              //autoboxing
        //Long l2 = i1;               //error
        System.out.println(i2);       //prints: 42

        i2 = Integer.valueOf(i1);
        System.out.println(i2);       //prints: 42

        Byte b = Byte.valueOf((byte)i1);
        System.out.println(b);       //prints: 42

        Short s = Short.valueOf((short)i1);
        System.out.println(s);       //prints: 42

        Long l = Long.valueOf(i1);
        System.out.println(l);       //prints: 42

        Float f = Float.valueOf(i1);
        System.out.println(f);       //prints: 42.0

        Double d = Double.valueOf(i1);
        System.out.println(d);       //prints: 42.0
    }

    private static void unboxing(){
        Integer i1 = Integer.valueOf(42);
        int i2 = i1.intValue();
        System.out.println(i2);      //prints: 42

        byte b = i1.byteValue();
        System.out.println(b);       //prints: 42

        short s = i1.shortValue();
        System.out.println(s);       //prints: 42

        long l = i1.longValue();
        System.out.println(l);       //prints: 42

        float f = i1.floatValue();
        System.out.println(f);       //prints: 42.0

        double d = i1.doubleValue();
        System.out.println(d);       //prints: 42.0

        Long l1 = Long.valueOf(42L);
        long l2 = l1;                //implicit unboxing
        System.out.println(l2);      //prints: 42

        double d2 = l1;              //implicit unboxing
        System.out.println(d2);      //prints: 42

        long l3 = i1;                //implicit unboxing
        System.out.println(l3);      //prints: 42

        double d3 = i1;                //implicit unboxing
        System.out.println(d3);      //prints: 42
    }
}
