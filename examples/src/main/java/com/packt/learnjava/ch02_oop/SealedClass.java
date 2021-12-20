package com.packt.learnjava.ch02_oop;

import java.util.Arrays;
import java.util.Objects;

public class SealedClass {
    public static void main(String... args) {
        Vehicle vehicle = new Vehicle("Ford", "Taurus", 300);
        System.out.println(vehicle.getClass().isSealed());   //prints: true
        System.out.println(Arrays.stream(vehicle.getClass().getPermittedSubclasses()).map(Objects::toString).toList());
                                                             //prints list of permitted classes

        Car car = new Car("Ford", "Taurus", 300, 4);
        System.out.println(car.getClass().isSealed());                  //prints: false
        System.out.println(car.getClass().getPermittedSubclasses());    //prints: null
    }

    private static sealed class Vehicle permits Car, Truck {
        private final String make, model;
        private final int horsePower;
        public Vehicle(String make, String model, int horsePower) {
            this.make = make;
            this.model = model;
            this.horsePower = horsePower;
        }
        public String getMake() { return make; }
        public String getModel() { return model; }
        public int getHorsePower() { return horsePower; }
    }

    private static final class Car extends Vehicle {
        private final int passengerCount;
        public Car(String make, String model, int horsePower, int passengerCount) {
            super(make, model, horsePower);
            this.passengerCount = passengerCount;
        }
        public int getPassengerCount() { return passengerCount; }
    }

    private static non-sealed class Truck extends Vehicle {
        private final int payloadPounds;
        public Truck(String make, String model, int horsePower, int payloadPounds) {
            super(make, model, horsePower);
            this.payloadPounds = payloadPounds;
        }
        public int getPayloadPounds() { return payloadPounds; }
    }

}
