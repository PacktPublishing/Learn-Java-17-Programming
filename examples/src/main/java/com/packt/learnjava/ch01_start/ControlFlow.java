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

/*
        //Uncomment after adding --enable-preview option to the compiler and to the runtime

        switch (x) {
            case 1, 3 -> System.out.println("1 or 3");
            case 4    -> System.out.println("4");
            case 5, 6 -> System.out.println("5 or 6");
            default   -> System.out.println("Not 1,3,4,5,6");
        }

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

        int z = switch (x) {
            case 1, 3 -> x + 2;
            case 4    -> x - 4;
            case 5, 6 -> x + 3;
            default   -> 42;
        };
*/
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

/*
        for (int i=0; i > -1; i++){
            System.out.print(i + " ");  //prints: 0 1 2 ...
        }
*/
        System.out.println("Loop 1:");

        for (int i=0, j=0; i < 3 && j < 3; ++i, ++j){
            System.out.println(i + " " + j);
        }

        System.out.println("Loop 2:");

        for (int x = getInitialValue(), i=x == -2 ? x + 2 : 0, j=0;
             i < 3 || j < 3 ; ++i, j = i) {
            System.out.println(i + " " + j);
        }

        System.out.println("Iterate of array:");

        int[] arr = {24, 42, 0};
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");  //prints: 24 42 0
        }

        System.out.println();

        //int[] arr = {24, 42, 0};
        for (int a: arr){
            System.out.print(a + " ");  //prints: 24 42 0
        }

        System.out.println("\nIterate of list:");

        List<String> list = List.of("24", "42", "0");
        for (String s: list){
            System.out.print(s + " ");  //prints: 24 42 0
        }
    }

    private static int getInitialValue(){ return -2; }

    private static void exception(){
        System.out.println("\nException statements:");
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
                System.out.print(s + " "); //prints: 24 16 1 2 1 43 24 22 31
                if(s.contains("3")){
                    continue cont;
                }
                checked += s + " ";
            }
        }
        System.out.println("Found " + found);  //prints: Found 43
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
        System.out.println("result=" + result);

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
}
