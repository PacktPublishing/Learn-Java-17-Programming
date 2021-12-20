package com.packt.learnjava.ch03_fundamentals;

public class ThisAndSuper {
    public static void main(String... args){
        A a = new A();
        a.setCount(2);
        System.out.println(a.getCount()); //prints: 0

        new C().anotherMethod();
    }

    private static class A {
        private int count;
        public void setCount(int count) {
            count = count;
        }
        public int getCount(){
            return this.count;
        }
    }

    private static class B  {
        public void someMethod() {
            System.out.println("Method of B class");
        }
    }

    private static class C extends B {
        public void someMethod() {
            System.out.println("Method of C class");
        }

        public void anotherMethod() {
            this.someMethod();     //prints: Method of C class
            super.someMethod();    //prints: Method of B class
        }
    }
}
