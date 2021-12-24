package com.packt.learnjava.ch05_stringsIoStreams;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReaderWriter {
    public static void main(String... args) throws Exception {
        bufferedReader();
    }

    private static void bufferedReader() {
        System.out.println("\nBufferedReader():");
        InputStream inputStream = InputOutputStream.class.getResourceAsStream("/hello.txt");
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
