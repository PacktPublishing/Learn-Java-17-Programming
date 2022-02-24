package com.packt.learnjava.network.http;

import com.packt.learnjava.common.util.Prop;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class Client {
    private static Properties properties;
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        properties = Prop.getProperties(classLoader, "app.properties");
        try {
            InetAddress address = InetAddress.getByName(properties.getProperty("server.host"));
            System.out.println("server.host=" + address);
            int port = Prop.getInt(properties, "server.port");
            System.out.println("server.port=" + port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
