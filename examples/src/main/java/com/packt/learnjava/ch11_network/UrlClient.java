package com.packt.learnjava.ch11_network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class UrlClient {
    public static void main(String[] args) {
        getFromFile();

        /* The following method is commented out
         * because if it is invoked too many times,
         * Google may block your IP
         */
        //getFromUrl();

        postToUrl();
    }

    private static void postToUrl() {
        try {
            URL url = new URL("http://localhost:3333/something");
            URLConnection conn = url.openConnection();
            //conn.setRequestProperty("Method", "POST");
            //conn.setRequestProperty("User-Agent", "Java client");
            conn.setDoOutput(true);
            try (OutputStreamWriter osw =
                         new OutputStreamWriter(conn.getOutputStream())) {
                osw.write("parameter1=value1&parameter2=value2");
                osw.flush();
            }
            try (BufferedReader br =
                         new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void getFromFile(){
        try {
            URL url = new URL("file:src/main/resources/hello.txt");
            System.out.println(url.getPath());  //src/main/resources/hello.txt
            System.out.println(url.getFile());  //src/main/resources/hello.txt
            try(InputStream is = url.openStream()){
                int data = is.read();
                while(data != -1){
                    System.out.print((char) data); //prints: Hello!
                    data = is.read();
                }            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getFromUrl(){
        try {
            URL url = new URL("https://www.google.com/search?q=Java&num=10");
            System.out.println(url.getPath());  //prints: /search
            System.out.println(url.getFile());  //prints: /search?q=Java&num=10
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("Connection", "close");
            conn.setRequestProperty("Accept-Language", "en-US");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            try(InputStream is = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is))){
                String line;
                while ((line = br.readLine()) != null){
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }    }

}