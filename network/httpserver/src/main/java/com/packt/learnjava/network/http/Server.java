package com.packt.learnjava.network.http;

import com.packt.learnjava.common.util.Prop;
import java.util.Properties;

public class Server {
    private static Properties properties;
    public static void main(String[] args){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        properties = Prop.getProperties(classLoader, "app.properties");
        int port = Prop.getInt(properties, "port");
        System.out.println("port=" + port);
    }
}
