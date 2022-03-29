package com.packt.learnjava.ch11_network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlSiteReader {
    public static void main(String[] args) {
        /* Warning!
         * If it is invoked too many times,
         * Google may block your IP address.
         */
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