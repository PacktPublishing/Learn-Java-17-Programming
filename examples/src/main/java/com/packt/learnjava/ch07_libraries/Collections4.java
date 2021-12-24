package com.packt.learnjava.ch07_libraries;

import org.apache.commons.collections4.*;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.collections4.map.LinkedMap;

import java.util.Map;

public class Collections4 {
    public static void main(String... args){

        bag();
        bidiMap();
        iterableMap();
        orderedMap();
    }

    private static void bag(){
        System.out.println("\nbag():");

        Bag<String> bag = new HashBag<>();
        bag.add("one", 4);
        System.out.println(bag);                     //prints: [4:one]
        bag.remove("one", 1);
        System.out.println(bag);                     //prints: [3:one]
        System.out.println(bag.getCount("one"));  //prints: 3
    }

    private static void bidiMap(){
        System.out.println("\nbidiMap():");

        BidiMap<Integer, String> bidi = new TreeBidiMap<>();
        bidi.put(2, "two");
        bidi.put(3, "three");
        System.out.println(bidi);                    //prints: {2=two, 3=three}
        System.out.println(bidi.inverseBidiMap());   //prints: {three=3, two=2}
        System.out.println(bidi.get(3));             //prints: three
        System.out.println(bidi.getKey("three")); //prints: 3
        bidi.removeValue("three");
        System.out.println(bidi);                    //prints: {2=two}
    }

    private static void iterableMap(){
        System.out.println("\niterableMap():");

        IterableMap<Integer, String> map =
                new HashedMap<>(Map.of(1, "one", 2, "two"));
        MapIterator it = map.mapIterator();
        while (it.hasNext()) {
            Object key = it.next();
            Object value = it.getValue();
            System.out.print(key + ", " + value + ", "); //prints: 2, two, 1, one,
            if(((Integer)key) == 2){
                it.setValue("three");
            }
        }
        System.out.println("\n" + map); //prints: {2=three, 1=one}
    }

    private static void orderedMap(){
        System.out.println("\norderedMap():");

        OrderedMap<Integer, String> map = new LinkedMap<>();
        map.put(4, "four");
        map.put(7, "seven");
        map.put(12, "twelve");
        System.out.println(map.firstKey());     //prints: 4
        System.out.println(map.nextKey(2));  //prints: null
        System.out.println(map.nextKey(7));  //prints: 12
        System.out.println(map.nextKey(4));  //prints: 7
    }


}
