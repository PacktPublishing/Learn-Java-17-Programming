package com.packt.learnjava.ch11_network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class UrlPost {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:3333/something");
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Method", "POST");
            conn.setRequestProperty("User-Agent", "Java client");
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream();
                 OutputStreamWriter osw = new OutputStreamWriter(os)) {
                osw.write("parameter1=value1&parameter2=value2");
                osw.flush();
            }
            try (InputStream is = conn.getInputStream();
                 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}