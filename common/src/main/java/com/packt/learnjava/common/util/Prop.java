package com.packt.learnjava.common.util;

import java.io.FileInputStream;
import java.util.Properties;

public class Prop {
    public static Properties getProperties(ClassLoader classLoader, String fileName){
        String file = classLoader.getResource(fileName).getFile();
        Properties properties = new Properties();
        try(FileInputStream fis = new FileInputStream(file)){
            properties.load(fis);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    public static int getInt(Properties properties, String name){
        return Integer.parseInt(properties.getProperty(name));
    }
}
