package com.packt.learnjava.ch02_oop;

public class Polymorphism1 {
    public static void main(String... args) {

        CalcSomething calc = CalcFactory.getCalculator();
        System.out.println(calc.calculate());
    }

    private static class CalcFactory{
        public static CalcSomething getCalculator(){
            String alg = getAlgValueFromPropertyFile();
            switch(alg){
                case "1":
                    return new CalcUsingAlg1();
                case "2":
                    int p1 = getAlg2Prop1FromPropertyFile();
                    double p2 = getAlg2Prop2FromPropertyFile();
                    return new CalcUsingAlg2(p1, p2);
                default:
                    System.out.println("Unknown value " + alg);
                    return new CalcUsingAlg1();
            }

        }

        private static String getAlgValueFromPropertyFile(){
            return "1"; // or "2"
        }
        private static int getAlg2Prop1FromPropertyFile(){
            return 1;
        }
        private static double getAlg2Prop2FromPropertyFile(){
            return 42.2;
        }
    }

    interface CalcSomething{
        double calculate();
    }

    private static class CalcUsingAlg1 implements CalcSomething{
        public double calculate(){
            return 42.1;
        }
    }

    private static class CalcUsingAlg2 implements CalcSomething{
        private int prop1;
        private double prop2;

        public CalcUsingAlg2(int prop1, double prop2) {
            this.prop1 = prop1;
            this.prop2 = prop2;
        }

        public double calculate(){
            return prop1 * prop2;
        }
    }

}
