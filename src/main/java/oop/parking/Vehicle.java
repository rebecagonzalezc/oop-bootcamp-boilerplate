package oop.parking;

import oop.parking.strategy.HandicappedVehicleParkStrategy;
import oop.parking.strategy.LargeVehicleParkStrategy;
import oop.parking.strategy.ParkStrategy;
import oop.parking.strategy.RegularVehicleParkStrategy;

import java.util.List;

public class Vehicle {

    public String licensePlate;
    public boolean large;
    public boolean handicapped;
    private ParkStrategy parkStrategy; //todo with polymorphism

    public Vehicle(final String licensePlate, final boolean large, final boolean handicapped) {
        this.licensePlate = licensePlate;
        this.large = large;
        this.handicapped = handicapped;
    }

    public ParkStrategy selectParkStrategy(final List<Parking> parkingList) {
        parkStrategy = new RegularVehicleParkStrategy(this, parkingList);
        if (isLarge()) {
            parkStrategy = new LargeVehicleParkStrategy(this, parkingList);
        } else if(isHandicapped()) {
            parkStrategy = new HandicappedVehicleParkStrategy(this, parkingList);
        }
        return parkStrategy;
    }

    public boolean isLarge() {
        return large;
    }

    public boolean isHandicapped() {
        return handicapped;
    }

    public ParkStrategy getParkStrategy() {
        return parkStrategy;
    }
}
