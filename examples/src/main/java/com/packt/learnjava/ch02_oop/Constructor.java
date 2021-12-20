package com.packt.learnjava.ch02_oop;

public class Constructor {
    public static void main(String... args){
        TheChildClass ref1 = new TheChildClass("something");
        TheParentClass ref2 = new TheChildClass("something");
        ref1.someChildMethod();
        ref1.someParentMethod();
        ((TheChildClass) ref2).someChildMethod();
        ref2.someParentMethod();
        System.out.println(ref1.toString());
    }

    private static class SomeClass {
        private int prop1;
        private String prop2;

        public SomeClass(int prop1){
            this.prop1 = prop1;
        }

        public SomeClass(String prop2){
            this.prop2 = prop2;
        }

        public SomeClass(int prop1, String prop2){
            this.prop1 = prop1;
            this.prop2 = prop2;
        }

        // other methods follow
   }

    private static class TheParentClass {
        private int prop;

        public TheParentClass(){
        }
        public TheParentClass(int prop){
            this.prop = prop;
        }
        public void someParentMethod(){}
    }

    private static class TheChildClass extends TheParentClass{
        private String prop;

        public TheChildClass(String prop){
            //super(42);
            this.prop = prop;
        }
        public TheChildClass(int arg1, String arg2){
            super(arg1);
            this.prop = arg2;
        }
        public void someChildMethod(){}

        @Override
        public String toString() {
            return "TheChildClass{" +
                    "prop='" + prop + '\'' +
                    '}';
        }


    }
}
