package com.packt.learnjava.ch04_exceptions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TryCatchFinally {
    public static void main(String... args){

    }

    private static void method(String s){
        if(s.equals("abc")){
            System.out.println("Equal");
        } else {
            System.out.println("Not equal");
        }
    }

    private static void noCatchBlock(){
        try {
            method(null);
        } finally {
            //do something
        }
    }

    private static void tryWithRecources() {
        try (Connection conn = DriverManager.getConnection("dburl", "username", "password");
             ResultSet rs = conn.createStatement().executeQuery("select * from some_table")) {
            while (rs.next()) {
                //process the retrieved data
            }
        } catch (SQLException ex) {
            //Do something
            //The exception was most probably caused by incorrect SQL statement
        }
    }

    private static void tryWithRecources(Connection conn, ResultSet rs) {
        try (conn; rs) {
            while (rs.next()) {
                //process the retrieved data
            }
        } catch (SQLException ex) {
            //Do something
            //The exception was most probably caused by incorrect SQL statement
        }
    }

    private static void example1() {
        try {
            throw null;
        } catch (RuntimeException ex) {
            System.out.print("RuntimeException ");
        } catch (Exception ex) {
            System.out.print("Exception ");
        } catch (Error ex) {
            System.out.print("Error ");
        } catch (Throwable ex) {
            System.out.print("Throwable ");
        } finally {
            System.out.println("Finally ");
        }
    }

    private static void method1() throws Exception {
            throw null;
    }

    private static void method2() throws RuntimeException {
        throw null;
    }

    private static void method3() throws Throwable {
        throw null;
    }

    private static void method4() throws Error {
        throw null;
    }

    private static void example2() {
        //throw new NullPointerException("Hi there!"); //1
        //throws new Exception("Hi there!");          //2
        //throw RuntimeException("Hi there!");       //3
        //throws RuntimeException("Hi there!");     //4
    }

    private static void example3() {
        int x = 4;
        assert (x > 3);    //1
        //assert (x = 3);  //2
        assert (x < 4);    //3
        //assert (x = 4);   //4
    }

}
