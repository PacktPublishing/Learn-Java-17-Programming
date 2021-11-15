package com.packt.learnjava.ch01_start;

public class Operators {

    public static void main(String... args){
        integerDivision();
        incrementDecrement();
        equality();
        relational();
        logical();
        conditional();
        assignment();
    }

    private static void integerDivision(){
        System.out.println("\nDivision of integral values:");
        int x = 5;
        System.out.println(x % 2);   //prints: 1

        System.out.println(x / 2);   //prints: 2

        System.out.println(x / 2.);       //prints: 2.5
        System.out.println((1. * x) / 2); //prints: 2.5
        System.out.println(((float)x) / 2);   //prints: 2.5
        System.out.println(((double) x) / 2); //prints: 2.5

    }

    private static void incrementDecrement(){
        System.out.println("\nIncrement/decrement operators:");
        int i = 2;
        System.out.println(++i);   //prints: 3
        System.out.println(i);     //prints: 3
        System.out.println(--i);   //prints: 2
        System.out.println(i);     //prints: 2
        System.out.println(i++);   //prints: 2
        System.out.println(i);     //prints: 3
        System.out.println(i--);   //prints: 3
        System.out.println(i);     //prints: 2

    }

    private static void equality(){
        System.out.println("\nEquality operators:");
        int i1 = 1;
        int i2 = 2;
        System.out.println(i1 == i2);  //prints: false
        System.out.println(i1 != i2);  //prints: true
        System.out.println(i1 == (i2 -1));  //prints: true
        System.out.println(i1 != (i2 - 1));  //prints: false
    }

    private static void relational(){
        System.out.println("\nRelational operators:");
        int i1 = 1;
        int i2 = 2;
        System.out.println(i1 > i2);  //prints: false
        System.out.println(i1 >= i2);  //prints: false
        System.out.println(i1 >= (i2 - 1));  //prints: true
        System.out.println(i1 < i2);  //prints: true
        System.out.println(i1 <= i2);  //prints: true
        System.out.println(i1 <= (i2 - 1));  //prints: true
        float f = 1.2f;
        System.out.println(i1 < f); //prints: true

    }

    private static void logical(){
        System.out.println("\nLogical operators:");
        boolean b = true;
        System.out.println(!b);    //prints: false
        System.out.println(!!b);   //prints: true
        boolean c = true;
        System.out.println(c & b); //prints: true
        System.out.println(c | b); //prints: true
        boolean d = false;
        System.out.println(c & d); //prints: false
        System.out.println(c | d); //prints: true

    }

    private static void conditional(){
        System.out.println("\nConditional operators:");
        boolean b = true;
        boolean c = true;
        boolean d = false;
        System.out.println(c && b); //prints: true
        System.out.println(c || b); //prints: true
        //boolean d = false;
        System.out.println(c && d); //prints: false
        System.out.println(c || d); //prints: true

        int h = 1;
        System.out.println(h > 3 & h++ < 3);  //prints: false
        System.out.println(h);                //prints: 2
        System.out.println(h > 3 && h++ < 3); //prints: false
        System.out.println(h);                //prints: 2

        int n = 1, m = 2;
        float k = n > m ? (n * m + 3) : ((float)n / m);
        System.out.println(k);           //prints: 0.5

    }

    private static void assignment(){
        System.out.println("\nAssignment operators:");
        float a = 1f;
        a += 2;
        System.out.println(a); //prints: 3.0
        a -= 1;
        System.out.println(a); //prints: 2.0
        a *= 2;
        System.out.println(a); //prints: 4.0
        a /= 2;
        System.out.println(a); //prints: 2.0
        a %= 2;
        System.out.println(a); //prints: 0.0
    }

}
