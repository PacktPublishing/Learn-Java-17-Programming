package com.packt.learnjava.common.util;

import java.io.InputStream;
import java.util.Properties;

public class Prop {
    public static Properties getProperties(ClassLoader classLoader, String fileName){
        Properties properties = new Properties();
        try(InputStream is = classLoader.getResourceAsStream(fileName)){
            properties.load(is);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    public static int getInt(Properties properties, String name){
        return Integer.parseInt(properties.getProperty(name));
    }
}
