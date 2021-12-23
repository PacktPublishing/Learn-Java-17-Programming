package com.packt.learnjava.ch04_exceptions;

public class CustomExceptions {

    class MyCheckedException extends Exception{
        public MyCheckedException(String message){
            super(message);
        }
        //whatever code you need to have here
    }

    class MyUncheckedException extends RuntimeException{
        public MyUncheckedException(String message){
            super(message);
        }
        //whatever code you need to have here
    }

}
