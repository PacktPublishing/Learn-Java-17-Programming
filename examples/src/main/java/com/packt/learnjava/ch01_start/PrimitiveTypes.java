package com.packt.learnjava.ch01_start;

import java.text.NumberFormat;
import java.util.Locale;

public class PrimitiveTypes {
    public static void main(String... args){
        System.out.println("Hello, world!");
        charType();
        minMax();
        casting();
        literals();
        newNumberFormat();
    }

    private static void charType(){
        System.out.println("\nType char:");

        char x1 = '\u0032';
        System.out.println(x1);  //prints: 2

        char x2 = '2';
        System.out.println(x2);  //prints: 2
        x2 = 65;
        System.out.println(x2);  //prints: A

        char y1 = '\u0041';
        System.out.println(y1);  //prints: A

        char y2 = 'A';
        System.out.println(y2);  //prints: A
        y2 = 50;
        System.out.println(y2);  //prints: 2

        System.out.println(x1 + x2);  //prints: 115
        System.out.println(x1 + y1);  //prints: 115
    }

    private static void minMax() {
        System.out.println("\nMin and max:");

        System.out.println(Byte.MIN_VALUE);           //prints: -128
        System.out.println(Byte.MAX_VALUE);           //prints: 127
        System.out.println(Short.MIN_VALUE);          //prints: -32768
        System.out.println(Short.MAX_VALUE);          //prints: 32767
        System.out.println(Integer.MIN_VALUE);        //prints: -2147483648
        System.out.println(Integer.MAX_VALUE);        //prints: 2147483647
        System.out.println(Long.MIN_VALUE);           //prints: -9223372036854775808
        System.out.println(Long.MAX_VALUE);           //prints: 9223372036854775807
        System.out.println((int) Character.MIN_VALUE); //prints: 0
        System.out.println((int) Character.MAX_VALUE); //prints: 65535

        System.out.println(Float.MIN_VALUE);  // 1.4E-45
        System.out.println(Float.MAX_VALUE);  // 3.4028235E38
        System.out.println(Double.MIN_VALUE); // 4.9E-324
        System.out.println(Double.MAX_VALUE); // 1.7976931348623157E308
    }

    private static void casting(){
        float f = 42.3f;
        float d = 42.3F;
        double a = 42.3f;
        double b = 42.3F;
        float x = (float)42.3d;
        float y = (float)42.3D;
    }

    private static void literals(){
        byte b = 42;
        short s = 42;
        int i = 42;
        long l = 42, l1 = 42l, l2 = 42L;

        System.out.println("\nLiterals:");
        System.out.println(Integer.toString(i, 2)); // 101010
        System.out.println(Integer.toBinaryString(i));    // 101010
        System.out.println(0b101010);                     // 42

        System.out.println(Integer.toString(i, 8)); // 52
        System.out.println(Integer.toOctalString(i));     // 52
        System.out.println(052);                          // 42

        System.out.println(Integer.toString(i, 10)); // 42
        System.out.println(Integer.toString(i));           // 42
        System.out.println(42);                            // 42

        System.out.println(Integer.toString(i, 16)); // 2a
        System.out.println(Integer.toHexString(i));        // 2a
        System.out.println(0x2a);                          // 42

        i = 354_263_654;
        System.out.println(i);  //prints: 354263654

        float f = 54_436.98f;
        System.out.println(f);  //prints: 54436.98

        l = 55_763_948L;
        System.out.println(l);  //prints: 55763948

        System.out.println("\nEscqpe sequences:");
        System.out.println("\"");   //prints: "
        System.out.println('\'');   //prints: '
        System.out.println('\\');   //prints: \

        System.out.println("The back\bspace");     //The backspace
        System.out.println("The horizontal\ttab"); //The horizontal	tab
        System.out.println("The line\nfeed");      //The line
                                                   //feed
        System.out.println("The form\ffeed");      //The formfeed
        System.out.println("The carriage\rreturn");//return
    }

    private static void var(){
        var x = 1;
    }

    private static void newNumberFormat(){
        System.out.println("\nNew compact number format:");
        NumberFormat fmt = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        System.out.println(fmt.format(42_000));      //prints: 42K
        System.out.println(fmt.format(42_000_000));  //prints: 42M

        NumberFormat fmtP = NumberFormat.getPercentInstance();
        System.out.println(fmtP.format(0.42));       //prints: 42%
    }
}

