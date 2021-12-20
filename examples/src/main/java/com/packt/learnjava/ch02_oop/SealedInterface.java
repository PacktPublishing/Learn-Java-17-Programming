package com.packt.learnjava.ch02_oop;

public class SealedInterface {
    private sealed interface Engine permits EngineBrand {
        int getHorsePower();
    }

   private sealed interface EngineBrand extends Engine permits Vehicle {
        String getBrand();
    }

   private non-sealed class Vehicle implements EngineBrand {
        private final String make, model, brand;
        private final int horsePower;
        public Vehicle(String make, String model, String brand, int horsePower) {
            this.make = make;
            this.model = model;
            this.brand = brand;
            this.horsePower = horsePower;
        }
        public String getMake() { return make; }
        public String getModel() { return model; }
        public String getBrand() { return brand; }
        public int getHorsePower() { return horsePower; }
    }

}
