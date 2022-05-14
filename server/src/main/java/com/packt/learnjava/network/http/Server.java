package com.packt.learnjava.network.http;

import com.packt.learnjava.common.util.Prop;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Properties;

public class Server {
    public static void main(String[] args){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Properties properties = Prop.getProperties(classLoader, "app.properties");
        int port = Prop.getInt(properties, "port");

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/something", new PostHandler());
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class PostHandler implements HttpHandler {
        public void handle(HttpExchange exch) {
            System.out.println();   //to skip the row in the output
            System.out.println(exch.getRequestURI());            //prints: /something
            System.out.println(exch.getHttpContext().getPath()); //prints: /something
            try (InputStream is = exch.getRequestBody();
                 BufferedReader in = new BufferedReader(new InputStreamReader(is));
                 OutputStream os = exch.getResponseBody()){
                System.out.println("Received as body:");
                in.lines().forEach(l -> System.out.println("  " + l));

                String confirm = "Got it! Thanks.";
                exch.sendResponseHeaders(200, confirm.length());
                os.write(confirm.getBytes());
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
