package com.packt.learnjava.ch07_libraries;

import org.apache.commons.codec.binary.Base64;

public class Codec {

    public static void main(String... args){
        String encodedStr = new String(Base64.encodeBase64("Hello, World!".getBytes()));
        System.out.println(encodedStr);  //prints: SGVsbG8sIFdvcmxkIQ==

        System.out.println(Base64.isBase64(encodedStr));  //prints: true

        String decodedStr = new String(Base64.decodeBase64(encodedStr.getBytes()));
        System.out.println(decodedStr);  //prints: Hello, World!
    }

}
