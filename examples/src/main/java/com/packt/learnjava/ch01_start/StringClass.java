package com.packt.learnjava.ch01_start;

public class StringClass {
    public static void main(String... args) {
        compareReferences();
        operatorAdd();
    }

    private static void compareReferences() {
        System.out.println("\nCompare string references:");
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1 == s2);    //prints: true
        System.out.println("abc" == s1); //prints: true

        String o1 = new String("abc");
        String o2 = new String("abc");
        System.out.println(o1 == o2);    //prints: false
        System.out.println("abc" == o1); //prints: false

        System.out.println("\nCompare string references after intern():");
        System.out.println("abc" == o1.intern()); //prints: true

        System.out.println("\nCompare string spelling:");
        System.out.println(o1.equals(o2));     //prints: true
        System.out.println(o2.equals(o1));     //prints: true
        System.out.println(o1.equals("abc"));  //prints: true
        System.out.println("abc".equals(o1));  //prints: true
        System.out.println("abc".equals("abc"));  //prints: true
    }

    private static void operatorAdd() {
        System.out.println("\nAdd strings:");
        String s1 = "abc";
        String s2 = "abc";
        String o1 = new String("abc");
        String o2 = new String("abc");

        String s = s1 + s2;
        System.out.println(s);              //prints: abcabc
        System.out.println(s1 + "abc");     //prints: abcabc
        System.out.println("abc" + "abc");  //prints: abcabc
        String o = o1 + o2;
        System.out.println(o);              //prints: abcabc
        System.out.println(o1 + "abc");     //prints: abcabc

        String str = "abc";
        str = str + "def";
        System.out.println(str);   //prints: abcdef
        str = str + new String("123");
        System.out.println(str);  //prints: abcdef123

        String str1 = "abc";
        String r1 = str1;
        str1 = str1 + "def";
        String r2 = str1;
        System.out.println(r1 == r2);      //prints: false
        System.out.println(r1.equals(r2)); //prints: false

    }
}

