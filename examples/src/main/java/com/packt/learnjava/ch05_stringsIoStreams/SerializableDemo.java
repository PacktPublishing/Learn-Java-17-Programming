package com.packt.learnjava.ch05_stringsIoStreams;

import java.io.ObjectStreamClass;
import java.io.ObjectStreamField;

public class SerializableDemo {
    public static void main(String... args) {
        ObjectStreamClass osc1 = ObjectStreamClass.lookup(Person1.class);
        printInfo(osc1);

        ObjectStreamClass osc2 = ObjectStreamClass.lookup(Person2.class);
        System.out.println("osc2: " + osc2);

        ObjectStreamClass osc3 = ObjectStreamClass.lookupAny(Person2.class);
        printInfo(osc3);
    }

    private static void printInfo(ObjectStreamClass osc) {
        System.out.println(osc.forClass());
        System.out.println("Class name: " + osc.getName());
        System.out.println("SerialVersionUID: " + osc.getSerialVersionUID());
        ObjectStreamField[] fields = osc.getFields();
        System.out.println("Serialized fields:");
        for (ObjectStreamField osf : fields) {
            System.out.println(osf.getName() + ": ");
            System.out.println("\t" + osf.getType());
            System.out.println("\t" + osf.getTypeCode());
            System.out.println("\t" + osf.getTypeString());
            System.out.println("\t" + osf.getOffset());
        }
    }
}
