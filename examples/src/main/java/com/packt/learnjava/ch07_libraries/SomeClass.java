package com.packt.learnjava.ch07_libraries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SomeClass {
    static final Logger logger = LogManager.getLogger(SomeClass.class.getName());

    public int multiplyByTwo(int i){
        return i * 2;
    }

    public int multiplyByTwoTheValueFromSomeOtherClass(SomeOtherClass someOtherClass){
        if(someOtherClass == null){
            logger.error("The parameter should not be null");
            System.exit(1);
        }
        return someOtherClass.getValue() * 2;
    }

    public static void main(String... args){

        new SomeClass().multiplyByTwoTheValueFromSomeOtherClass(null);
    }
}
