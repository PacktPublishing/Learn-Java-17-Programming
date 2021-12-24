package com.packt.learnjava.ch08_threads;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentCollections {

    public static void main(String... args) {
        //modifyList();
        modifyCopyOnWriteArrayList();
    }

    private static void modifyList(){
        System.out.println("\nmodifyList():\n");
        List<String> list = Arrays.asList("One", "Two");
        System.out.println(list);
        try {
            for (String e : list) {
                System.out.println(e); //prints: One
                list.add("Three");     //UnsupportedOperationException
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(list);       //prints: [One, Two]
    }

    private static void modifyCopyOnWriteArrayList(){
        System.out.println("\nmodifyCopyOnWriteArrayList():\n");
        List<String> list = new CopyOnWriteArrayList<>(Arrays.asList("One", "Two"));
        System.out.println(list);
        try {
            for (String e : list) {
                System.out.print(e + " "); //prints: One Two
                list.add("Three");         //adds element Three
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("\n" + list);   //prints: [One, Two, Three, Three]
    }

}
