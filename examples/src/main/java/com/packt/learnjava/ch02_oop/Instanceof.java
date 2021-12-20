package com.packt.learnjava.ch02_oop;

public class Instanceof {
    public static void main(String... args) {

        double result = calculate(new CalcInput1(1));
        System.out.println(result);        //prints: 42.0
    }

    public static double calculate(Object input) {
        return Calculator.calculate(input);
    }

    private static class Calculator{

        public static double calculate(Object input){
            if(input instanceof CalcInput1 calcInput1){
                return new CalcUsingAlg1().calculate(calcInput1);
            } else if (input instanceof CalcInput2 calcInput2){
                return new CalcUsingAlg2().calculate(calcInput2);
            } else {
                throw new RuntimeException("Unknown input type " + input.getClass().getCanonicalName());
            }

        }
    }

    private static class CalcUsingAlg1 {
        public double calculate(CalcInput1 input){
            return 42. * input.getProp1();
        }
    }

    private static class CalcUsingAlg2{
        public double calculate(CalcInput2 input){
            return input.getProp2() * input.getProp1();
        }
    }

    private static class CalcInput1{
        private int prop1;

        public CalcInput1(int prop1) {
            this.prop1 = prop1;
        }

        public int getProp1() {
            return prop1;
        }
    }

    private static class CalcInput2{
        private int prop1;
        private double prop2;

        public CalcInput2(int prop1, double prop2) {
            this.prop1 = prop1;
            this.prop2 = prop2;
        }

        public int getProp1() {
            return prop1;
        }

        public double getProp2() {
            return prop2;
        }
    }


}

