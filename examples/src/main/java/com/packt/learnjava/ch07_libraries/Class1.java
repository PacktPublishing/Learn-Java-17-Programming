package com.packt.learnjava.ch07_libraries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Class1 {
    static final Logger logger = LogManager.getLogger(Class1.class.getName());

    public static void main(String... args){
        new Class1().multiplyByTwo2(null);
    }

    public int multiplyByTwo(int i){
        return i * 2;
    }

    public int multiplyByTwo2(Class2 class2){
        if(class2 == null){
            logger.error("The parameter should not be null");
            System.exit(1);
        }
        return class2.getValue() * 2;
    }
}
