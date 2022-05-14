package com.packt.learnjava.ch11_network;

import java.io.InputStream;
import java.net.URL;

public class UrlFileReader {
    public static void main(String[] args) throws Exception {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String file = classLoader.getResource("hello.txt").getFile();
            URL url = new URL("file:" + file);
            try(InputStream is = url.openStream()){
                int data = is.read();
                while(data != -1){
                    System.out.print((char) data); //prints: Hello!
                    data = is.read();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
