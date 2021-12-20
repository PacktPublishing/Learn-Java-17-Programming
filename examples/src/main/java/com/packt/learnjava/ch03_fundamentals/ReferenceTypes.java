package com.packt.learnjava.ch03_fundamentals;

import java.util.Objects;

public class ReferenceTypes {
    public static void main(String... args){
        array1();
        array2();
        array3();
        enumDemo();
        parameter();
        parameter1();
        parameter2();
        parameter3();
    }

    interface C {}
    interface B extends C {}
    class A implements B { }
    B b = new A();
    C c = new A();
    A a1 = (A)b;
    A a2 = (A)c;

    private static void array1(){
        int[] intArray = new int[10];
        float[][] floatArray = new float[3][4];
        String[] stringArray = new String[2];
        //SomeClass[][][] arr = new SomeClass[3][5][2];

        System.out.println(intArray[3]);      //prints: 0
        System.out.println(floatArray[2][2]); //prints: 0.0
        System.out.println(stringArray[1]);   //prints: null
    }

    private static void array2(){
        int[] intArray = {1,2,3,4,5,6,7,8,9,10};
        float[][] floatArray ={{1.1f,2.2f,3,2},{10,20.f,30.f,5},{1,2,3,4}};
        String[] stringArray = {"abc", "a23"};

        System.out.println(intArray[3]);      //prints: 4
        System.out.println(floatArray[2][2]); //prints: 3.0
        System.out.println(stringArray[1]);   //prints: a23
     }

    private static void array3(){
        float[][] floatArray = new float[3][];

        System.out.println(floatArray.length);  //prints: 3
        System.out.println(floatArray[0]);      //prints: null
        System.out.println(floatArray[1]);      //prints: null
        System.out.println(floatArray[2]);      //prints: null
        //System.out.println(floatArray[3]);    //error
        //System.out.println(floatArray[2][2]); //error

        floatArray[0] = new float[4];
        floatArray[1] = new float[3];
        floatArray[2] = new float[7];
        System.out.println(floatArray[2][5]);   //prints: 0.0
    }

    enum Season { SPRING, SUMMER, AUTUMN, WINTER }

    enum Season1 {
        SPRING, SUMMER, AUTUMN, WINTER;
        public String toString() {
            return this.name().charAt(0) + this.name().substring(1).toLowerCase();
        }
    }

    enum Season2 {
        SPRING(42), SUMMER(67), AUTUMN(32), WINTER(20);
        private int temperature;
        Season2(String temperature){
            this.temperature = Integer.valueOf(temperature);
        }
        Season2(int temperature){
            this.temperature = temperature;
        }
        public int getTemperature(){
            return this.temperature;
        }
        public String toString() {
            return this.name().charAt(0) +
                    this.name().substring(1).toLowerCase() +
                    "(" + this.temperature + ")";
        }
    }

    private static void enumDemo(){
        System.out.println(Season.SPRING.name());       //prints: SPRING
        System.out.println(Season.WINTER.toString());   //prints: WINTER
        System.out.println(Season.SUMMER.ordinal());    //prints: 1
        Season season = Enum.valueOf(Season.class, "AUTUMN");
        System.out.println(season == Season.AUTUMN);    //prints: true

        for(Season s: Season.values()){
            System.out.print(s.name() + " "); //prints: SPRING SUMMER AUTUMN WINTER
        }
        System.out.println();
        for(Season1 s: Season1.values()){
            System.out.print(s.toString() + " "); //prints: Spring Summer Autumn Winter
        }
        System.out.println();
        for(Season2 s: Season2.values()){
            System.out.print(s.toString() + " "); //prints: Spring(42) Summer(67) Autumn(32) Winter(20)
        }
    }

    private static void modifyParameter(int x){
        x = 2;
    }
    private static class DemoClass{
        private String prop;
        public DemoClass(String prop) { this.prop = prop; }
        public String getProp() { return prop; }
        public void setProp(String prop) { this.prop = prop; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof DemoClass demoClass)) return false;
            return Objects.equals(getProp(), demoClass.getProp());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getProp());
        }
    }
    private static void modifyParameter(DemoClass obj){
        obj.setProp("Changed inside the method");
    }
    private static void parameter(){
        int x = 1;
        modifyParameter(x);
        System.out.println(x);  //prints: 1

        DemoClass obj = new DemoClass("Is not changed");
        modifyParameter(obj);
        System.out.println(obj.getProp()); //prints: Changed inside the method
    }

    private static class DemoClass1{
        private Integer prop;
        public DemoClass1(Integer prop) { this.prop = prop; }
        public Integer getProp() { return prop; }
        public void setProp(Integer prop) { this.prop = prop; }
    }
    private static void modifyParameter(DemoClass1 obj){
        obj.setProp(Integer.valueOf(2));
    }
    private static void parameter1(){
        DemoClass1 obj = new DemoClass1(Integer.valueOf(1));
        modifyParameter(obj);
        System.out.println(obj.getProp());  //prints: 2
    }

    private static void modifyParameter(String obj){
        obj = "Changed inside the method";
    }
    private static void parameter2(){
        String obj = "Is not changed";
        modifyParameter(obj);
        System.out.println(obj); //prints: Is not changed

        obj = new String("Is not changed");
        modifyParameter(obj);
        System.out.println(obj); //prints: Is not changed
    }

    private static void modifyParameter(int[] arr){ arr[0] = 42; }
    private static void parameter3(){
        int[] arr = {1,2,3};
        modifyParameter(arr);
        System.out.println(arr[0]); //prints: 42
    }
}
