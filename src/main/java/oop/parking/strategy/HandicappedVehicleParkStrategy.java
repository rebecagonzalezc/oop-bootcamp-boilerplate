package oop.parking.strategy;

import oop.parking.Parking;
import oop.parking.Vehicle;

import java.util.List;

public class HandicappedVehicleParkStrategy implements ParkStrategy {

    private Vehicle vehicle;
    private List<Parking> parkingList;

    public HandicappedVehicleParkStrategy(Vehicle vehicle, List<Parking> parkingList) {
        this.vehicle = vehicle;
        this.parkingList = parkingList;
    }

    @Override
    public boolean park(Vehicle vehicle, List<Parking> parkingList) {
        boolean success = false;
        for(final Parking parking: parkingList) {
            if (parking.isHandicappedFriendly() && !success) {
                success = parking.add(vehicle.licensePlate);
            }
        }
        return success;
    }
}
