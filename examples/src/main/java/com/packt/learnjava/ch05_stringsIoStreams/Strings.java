package com.packt.learnjava.ch05_stringsIoStreams;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Strings {
    public static void main(String... args){

        //class String
        equals();
        contains();
        startsEndsWith();
        length();
        indexOf();
        substring();
        concat();
        join();
        compareTo();
        matches();
        format();
        replace();
        toLowerUpperCase();
        split();
        isEmpty();
        valueOf();
        repeat();
        isBlank();
        strip();
        lines();

        //StringUtils
        trim();
    }


    private static void equals() {
        System.out.println("\nString method equals():");
        String s1 = "abc";
        String s2 = "abc";
        String s3 = "acb";
        System.out.println(s1.equals(s2));     //prints: true
        System.out.println(s1.equals(s3));     //prints: false
        System.out.println("abc".equals(s2));  //prints: true
        System.out.println("abc".equals(s3));  //prints: false

        System.out.println("\nString method equalsIgnoreCase():");
        String s4 = "aBc";
        String s5 = "Abc";
        System.out.println(s4.equals(s5));           //prints: false
        System.out.println(s4.equalsIgnoreCase(s5)); //prints: true

        System.out.println("\nString method contentEquals():");
        System.out.println(s1.contentEquals(s2));    //prints: true
        System.out.println("abc".contentEquals(s2)); //prints: true

    }
    private static void contains(){
        System.out.println("\nString method contains():");
        String s6 = "abc42t%";
        String s7 = "42";
        String s8 = "xyz";
        System.out.println(s6.contains(s7));    //prints: true
        System.out.println(s6.contains(s8));    //prints: false
    }

    private static void startsEndsWith(){
        System.out.println("\nString methods startsWith(), endWsith():");
        String s6 = "abc42t%";
        String s7 = "42";
        System.out.println(s6.startsWith(s7));  //prints: false
        System.out.println(s6.startsWith("ab"));//prints: true
        System.out.println(s6.startsWith("42", 3));//prints: true
        System.out.println(s6.endsWith(s7));    //prints: false
        System.out.println(s6.endsWith("t%"));  //prints: true
    }

    private static void length(){
        System.out.println("\nString method length():");
        String s7 = "42";
        System.out.println(s7.length());        //prints: 2
        System.out.println("0 0".length());     //prints: 3

    }

    private static void indexOf(){
        System.out.println("\nString methods indexOf(), lastIndexOf():");
        String s6 = "abc42t%";
        String s7 = "42";
        System.out.println(s6.indexOf(s7));     //prints: 3
        System.out.println(s6.indexOf("a"));    //prints: 0
        System.out.println(s6.indexOf("xyz"));  //prints: -1
        System.out.println("ababa".lastIndexOf("ba")); //prints: 3
    }

    private static void substring(){
        System.out.println("\nString method substring():");
        String s6 = "abc42t%";
        System.out.println("42".substring(0));  //prints: 42
        System.out.println("42".substring(1));  //prints: 2
        System.out.println("42".substring(2));  //prints:
        //System.out.println("42".substring(3));  //error: String index out of range: -1
        System.out.println(s6.substring(3));    //prints: 42t%
        System.out.println(s6.substring(3, 5)); //prints: 42
    }

    private static void concat(){
        System.out.println("\nString method concat():");
        String s7 = "42";
        String s8 = "xyz";
        String newStr1 = s7.concat(s8);
        System.out.println(newStr1);        //prints: 42xyz

        String newStr2 = s7 + s8;
        System.out.println(newStr2);        //prints: 42xyz
    }

    private static void join(){
        System.out.println("\nString method join():");
        String newStr1 = String.join(",", "abc", "xyz");
        System.out.println(newStr1);        //prints: abc,xyz

        List<String> list = List.of("abc","xyz");
        String newStr2 = String.join(",", list);
        System.out.println(newStr2);        //prints: abc,xyz
    }

    private static void compareTo(){
        System.out.println("\nString methods compareTo() and compareToIgnoreCase():");
        String s4 = "aBc";
        String s5 = "Abc";
        System.out.println(s4.compareTo(s5));             //prints: 32
        System.out.println(s4.compareToIgnoreCase(s5));   //prints: 0
        System.out.println(s4.codePointAt(0));      //prints: 97
        System.out.println(s5.codePointAt(0));      //prints: 65
    }

    private static void matches(){
        System.out.println("\nString method matches():");
        System.out.println("abc".matches("[a-z]+"));   //prints: true
        System.out.println("abc1".matches("[a-z]+"));  //prints: false
    }

    private static void format(){
        System.out.println("\nString method format():");
        String t = "Hey, %s! Give me %d apples, please!";
        System.out.println(String.format(t, "Nick", 2));
        String t1 = String.format(t, "Nick", 2);
        System.out.println(t1);
        System.out.println(String.format("Hey, %s! Give me %d apples, please!", "Nick", 2));
    }

    private static void replace(){
        System.out.println("\nString methods replace(), replaceFirst(), and replaceAll():");
        System.out.println("abcbc".replace("bc", "42"));  //prints: a4242
        System.out.println("abcbc".replaceFirst("bc", "42"));  //prints: a42bc
        System.out.println("ab11bcd".replaceAll("[a-z]+", "42"));  //prints: 421142
    }

    private static void toLowerUpperCase(){
        System.out.println("\nString methods toLowerCase() and toUpperCase():");
        System.out.println("aBc".toLowerCase());   //prints: abc
        System.out.println("aBc".toUpperCase());   //prints: ABC
    }

    private static void split(){
        System.out.println("\nString method split():");
        String[] arr = "abcbc".split("b");
        System.out.println(arr[0]);   //prints: a
        System.out.println(arr[1]);   //prints: c
        System.out.println(arr[2]);   //prints: c
    }

    private static void isEmpty(){
        System.out.println("\nString method isEmpty():");
        System.out.println("".isEmpty());   //prints: true
        System.out.println(" ".isEmpty());  //prints: false
    }

    private static void valueOf(){
        System.out.println("\nString method valueOf():");
        float f = 23.42f;
        String sf = String.valueOf(f);
        System.out.println(sf);   //prints: 23.42
    }

    private static void repeat(){
        System.out.println("\nString method repeat():");
        System.out.println("ab".repeat(3)); //prints: ababab
        System.out.println("ab".repeat(1)); //prints: ab
        System.out.println("ab".repeat(0)); //prints:
    }

    private static void isBlank(){
        System.out.println("\nString method isBlank():");
        System.out.println("".isBlank());     //prints: true
        System.out.println("   ".isBlank());  //prints: true
        System.out.println(" a ".isBlank());  //prints: false
    }

    private static void strip(){
        System.out.println("\nString method strip():");
        String sp = "   abc   ";
        System.out.println("'" + sp + "'");                 //prints: '   abc   '
        System.out.println("'" + sp.stripLeading() + "'");  //prints: 'abc   '
        System.out.println("'" + sp.stripTrailing() + "'"); //prints: '  abc'
        System.out.println("'" + sp.strip() + "'");         //prints: 'abc'
    }

    private static void lines(){
        System.out.println("\nString method lines():\n");
        String line = "Line 1\nLine 2\rLine 3\r\nLine 4";
        line.lines().forEach(System.out::println);
    }

    private static void trim(){
        System.out.println("\nStringUtils methods trim():\n");

        System.out.println("'" + StringUtils.trim(" x ") + "'");      //prints: 'x'
        System.out.println(StringUtils.trim(null));    //prints: null
        System.out.println("'" + StringUtils.trim("") + "'");      //prints: ''
        System.out.println("'" + StringUtils.trim("   ") + "'");      //prints: ''

        System.out.println("'" + StringUtils.trimToNull(" x ") + "'");  //prints: 'x'
        System.out.println(StringUtils.trimToNull(null));    //prints: null
        System.out.println(StringUtils.trimToNull(""));      //prints: null
        System.out.println(StringUtils.trimToNull("   "));   //prints: null

        System.out.println("'" + StringUtils.trimToEmpty(" x ") + "'");  //prints: 'x'
        System.out.println("'" + StringUtils.trimToEmpty(null) + "'");    //prints: ''
        System.out.println("'" + StringUtils.trimToEmpty("") + "'");      //prints: ''
        System.out.println("'" + StringUtils.trimToEmpty("   ") + "'");   //prints: ''

        Character c = '\u2000';
        System.out.println("'"+c+"'");

    }

}
