package com.packt.learnjava.ch05_stringsIoStreams;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

public class ConsoleDemo {
    public static void main(String... args) {
        console1();
        // Uncomment the rest as needed
        // They are waiting for the user input
        //console2();
        //console3();
        //console4();
    }

    private static void console1(){
        System.out.println("\nconsole1:");
        Console console = System.console();
        System.out.println(console);
    }

    private static void console2(){
        System.out.println("\nconsole2:");
        Console console = System.console();

        String line = console.readLine();
        System.out.println("Entered 1: " + line);
        line = console.readLine("Enter something 2: ");
        System.out.println("Entered 2: " + line);
        line = console.readLine("Enter some%s", "thing 3: ");
        System.out.println("Entered 3: " + line);

        char[] password = console.readPassword();
        System.out.println("Entered 4: " + new String(password));
        password = console.readPassword("Enter password 5: ");
        System.out.println("Entered 5: " + new String(password));
        password = console.readPassword("Enter pass%s", "word 6: ");
        System.out.println("Entered 6: " + new String(password));
    }

    private static void console3(){
        System.out.println("\nconsole3:");
        Console console = System.console();
        String line = console.format("Enter some%s", "thing:").readLine();
        System.out.println("Here what I got: " + line);
    }

    private static void console4(){
        System.out.println("\nconsole4:");
        Console console = System.console();

        try (Reader reader = console.reader()){
            char[] chars = new char[10];
            System.out.print("Enter something: ");
            reader.read(chars);
            System.out.print("Entered: " + new String(chars));
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter out = console.writer();
        out.println("Hello!");

        console.flush();

    }

}
