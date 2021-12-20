package com.packt.learnjava.ch03_fundamentals;

public class WideningNarrowing {
    public static void main(String... args){
        widening();
        narrowing();
        usingWrapperClasses();
        strings();
    }

    private static void widening(){
        int i = 123456789;
        double d = (double)i;
        System.out.println(i - (int)d);       //prints: 0

        long l1 = 12345678L;
        float f1 = (float)l1;
        System.out.println(l1 - (long)f1);    //prints: 0

        long l2 = 123456789L;
        float f2 = (float)l2;
        System.out.println(l2 - (long)f2);    //prints: -3

        long l3 = 1234567891111111L;
        double d3 = (double)l3;
        System.out.println(l3 - (long)d3);    //prints: 0

        long l4 = 12345678999999999L;
        double d4 = (double)l4;
        System.out.println(l4 - (long)d4);    //prints: -1
    }

    private static void narrowing(){
        System.out.println(Integer.MAX_VALUE); //prints: 2147483647
        double d1 = 1234567890.0;
        System.out.println((int)d1);           //prints: 1234567890

        double d2 = 12345678909999999999999.0;
        System.out.println((int)d2);           //prints: 2147483647
    }

    private static void usingWrapperClasses(){
        int i = 123456789;
        double d = Integer.valueOf(i).doubleValue();
        System.out.println(i - (int)d);       //prints: 0

        long l1 = 12345678L;
        float f1 = Long.valueOf(l1).floatValue();
        System.out.println(l1 - (long)f1);    //prints: 0

        long l2 = 123456789L;
        float f2 = Long.valueOf(l2).floatValue();
        System.out.println(l2 - (long)f2);    //prints: -3

        long l3 = 1234567891111111L;
        double d3 = Long.valueOf(l3).doubleValue();
        System.out.println(l3 - (long)d3);    //prints: 0

        long l4 = 12345678999999999L;
        double d4 = Long.valueOf(l4).doubleValue();
        System.out.println(l4 - (long)d4);    //prints: -1

        double d1 = 1234567890.0;
        System.out.println(Double.valueOf(d1).intValue());   //prints: 1234567890

        double d2 = 12345678909999999999999.0;
        System.out.println(Double.valueOf(d2).intValue());           //prints: 2147483647
    }

    private static void strings(){
        byte b1 = Byte.parseByte("42");
        System.out.println(b1);             //prints: 42
        Byte b2 = Byte.decode("42");
        System.out.println(b2);             //prints: 42

        boolean b3 = Boolean.getBoolean("property");
        System.out.println(b3);            //prints: false
        Boolean b4 = Boolean.valueOf("false");
        System.out.println(b4);            //prints: false

        int i1 = Integer.parseInt("42");
        System.out.println(i1);            //prints: 42
        Integer i2 = Integer.getInteger("property");
        System.out.println(i2);            //prints: null

        double d1 = Double.parseDouble("3.14");
        System.out.println(d1);            //prints: 3.14
        Double d2 = Double.valueOf("3.14");
        System.out.println(d2);            //prints: 3.14

        String s1 = Integer.toString(42);
        System.out.println(s1);            //prints: 42
        String s2 = Double.toString(3.14);
        System.out.println(s2);            //prints: 3.14

    }


}
