package com.packt.learnjava.ch01_start;

import java.util.List;

public class ControlFlow {
    public static void main(String... args){
        selection();
        iteration();
        exception();
        branching();
    }

    private static void selection() {
        System.out.println("\nSelection statements:");
        int x = 1, y = 2;

        if (x > y) {
            //do something
        }

        if (x > y) {
            //do something
        } else {
            //do something else
        }

        if (x > y) {
            //do something
        } else if (x == y) {
            //do something else
        } else {
            //do something different
        }

        switch (x) {
            case 5:
                //do something
                break;
            case 7:
                //do something else
                break;
            case 12:
                //do something different
                break;
            case 50:
                //do something yet more different
                break;
            default:
                //do something completely different
        }

        switchStatement(1);    //prints: 1 or 3: 1
        switchStatement(2);    //prints: Not 1,3,4,5,6: 2
        switchStatement(5);    //prints: 5 or 6: 5

        switch (x) {
            case 1, 3 -> {
                            //do something
                         }
            case 4    -> {
                            //do something else
                         }
            case 5, 6 -> System.out.println("5 or 6");
            default   -> System.out.println("Not 1,3,4,5,6");
        }

        System.out.println("\nswitchExpression1: ");
        switchExpression1(0);    //prints: false
        switchExpression1(1);    //prints: false
        switchExpression1(2);    //prints: true

        System.out.println("\nswitchExpression2: ");
        switchExpression2(TWO);            //prints: 1
        switchExpression2(FOUR);           //prints: 2
        switchExpression2("blah"); //prints: 3

        System.out.println("\nswitchExpression3: ");
        switchExpression3(Num.TWO);        //prints: 1
        switchExpression3(Num.FOUR);       //prints: 2
        //switchExpression3("blah"); //does not compile

        System.out.println("\nswitchExpression4: ");
        switchExpression4(Num.TWO);        //prints: 1
        switchExpression4(Num.THREE);      //prints: 5
    }

    private static void iteration(){
        System.out.println("\nIteration statements:");

        int n = 0;
        while(n < 5){
            System.out.print(n + " ");   //prints: 0 1 2 3 4
            n++;
        }
        System.out.println();

        n = 0;
        do {
            System.out.print(n + " ");   //prints: 0 1 2 3 4
            n++;
        } while(n < 5);
        System.out.println();

        for (int i=0; i < 3; i++){
            System.out.print(i + " ");  //prints: 0 1 2
        }
        System.out.println();

        for (int i=0; i > -1; i++){
            System.out.print(i + " ");  //prints: 0 1 2
            if(i > 1) break;
        }

        System.out.println("\n\nLoop 1:");
        for (int i=0, j=0; i < 3 && j < 3; ++i, ++j){
            System.out.println(i + " " + j);  //prints: 0 0
                                              //        1 1
                                              //        2 2
        }

        System.out.println("\nLoop 2:");
        for (int x = getInitialValue(), i=x == -2 ? x + 2 : 0, j=0;
             i < 3 || j < 3 ; ++i, j = i) {
            System.out.println(i + " " + j);  //prints: 0 0
                                              //        1 1
                                              //        2 2
        }

        System.out.println("\nIterate of array:");
        int[] arr = {24, 42, 0};
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");  //prints: 24 42 0
        }
        System.out.println();

        //int[] arr = {24, 42, 0};
        for (int a: arr){
            System.out.print(a + " ");  //prints: 24 42 0
        }

        System.out.println("\n\nIterate of list:");
        List<String> list = List.of("24", "42", "0");
        for (String s: list){
            System.out.print(s + " ");  //prints: 24 42 0
        }
    }

    private static int getInitialValue(){ return -2; }

    private static void exception(){
        System.out.println("\n\nException statements:");
        int x = 12;
        try {
            //x = someMethod();
            if(x > 10){
                throw new RuntimeException("The x value is out of range: " + x);
            }
            //normal processing flow of x here
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            //do what has to be done to address the problem
        }
    }

    private static void branching(){
        System.out.println("\nBranching statements:");

        System.out.println("\nBreak statements:");

        String found = null;
        List<String> list = List.of("24", "42", "31", "2", "1");
        for (String s: list){
            System.out.print(s + " ");
            if(s.contains("3")){
                found = s;
                break;
            }
        }
        System.out.println("Found " + found);  //prints: Found 31

        //String found = null;
        List<List<String>> listOfLists = List.of(
                List.of("24", "16", "1", "2", "1"),
                List.of("43", "42", "31", "3", "3"),
                List.of("24", "22", "31", "2", "1")
        );
        exit: for(List<String> l: listOfLists){
            for (String s: l){
                System.out.print(s + " ");
                if(s.contains("3")){
                    found = s;
                    break exit;
                }
            }
        }
        System.out.println("Found " + found);  //prints: Found 43

        System.out.println("\nContinue statements:");
/*
        String found = null;
        List<List<String>> listOfLists = List.of(
                List.of("24", "16", "1", "2", "1"),
                List.of("43", "42", "31", "3", "3"),
                List.of("24", "22", "31", "2", "1")
        );
*/
        String checked = "";
        cont: for(List<String> l: listOfLists){
            for (String s: l){
                System.out.print(s + " ");       //prints: 24 16 1 2 1 43 24 22 31
                if(s.contains("3")){
                    continue cont;
                }
                checked += s + " ";
            }
        }
        System.out.println("Found " + found);      //prints: Found 43
        System.out.println("Checked " + checked);  //prints: Checked 24 16 1 2 1 24 22

        int result = 0;
        List<List<Integer>> source = List.of(
                List.of(1, 2, 3, 4, 6),
                List.of(22, 23, 24, 25),
                List.of(32, 33)
        );

        cont: for(List<Integer> l: source){
            for (int i: l){
                if(i > 7){
                    result = i;
                    continue cont;
                }
            }
        }
        System.out.println("result=" + result); //prints: result=32

        System.out.println("\nReturn statement:");
        String r = returnDemo(3);
        System.out.println(r);                 //prints: Not enough

        r = returnDemo(10);
        System.out.println(r);                 //prints: Exactly right

        r = returnDemo(12);
        System.out.println(r);                 //prints: More than enough
    }

    private static String returnDemo(int i){
        if(i < 10){
            return "Not enough";
        } else if (i == 10){
            return "Exactly right";
        } else {
            return "More than enough";
        }
    }

    private static void switchStatement(int x){
        switch (x) {
            case 1, 3 -> System.out.print("1 or 3");
            case 4    -> System.out.print("4");
            case 5, 6 -> System.out.print("5 or 6");
            default   -> System.out.print("Not 1,3,4,5,6");
        }
        System.out.println(": " + x);
    }

    private static void switchExpression1(int i){
        boolean b = switch(i) {
            case 0, 1 -> false;
            case 2 -> true;
            default -> false;
        };
        System.out.println(b);
    }

    private static final String ONE = "one", TWO = "two", THREE = "three", FOUR = "four", FIVE = "five";
    private static void switchExpression2(String number){
        var res = switch(number) {
            case ONE, TWO -> 1;
            case THREE, FOUR, FIVE -> 2;
            default -> 3;
        };
        System.out.println(res);
    }

    private enum Num { ONE, TWO, THREE, FOUR, FIVE }
    private static void switchExpression3(Num number){
        var res = switch(number) {
            case ONE, TWO -> 1;
            case THREE, FOUR, FIVE -> 2;
        };
        System.out.println(res);
    }

    private static void switchExpression4(Num number){
        var res = switch(number) {
            case ONE, TWO -> 1;
            case THREE, FOUR, FIVE -> {
                String s = number.name();
                yield s.length();
            }
        };
        System.out.println(res);
    }

}
