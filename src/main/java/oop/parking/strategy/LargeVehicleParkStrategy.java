package oop.parking.strategy;

import oop.parking.Parking;
import oop.parking.Vehicle;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LargeVehicleParkStrategy implements ParkStrategy {

    private Vehicle vehicle;
    private List<Parking> parkingList;
    private Map<Parking, Double> parkingOccupancies = new LinkedHashMap<>();


    public LargeVehicleParkStrategy(Vehicle vehicle, List<Parking> parkingList) {
        this.vehicle = vehicle;
        this.parkingList = parkingList;
    }

    @Override
    public boolean park(Vehicle vehicle, List<Parking> parkingList) {
        Map.Entry<Parking, Double> min = assembleParkingOccupancies(parkingList);
        if (min != null) {
            min.getKey().add(vehicle.licensePlate);
            return true;
        }
        return false;
    }

    private Map.Entry<Parking, Double> assembleParkingOccupancies(List<Parking> parkingList) {
        for (Parking parking : parkingList) {
            parkingOccupancies.put(parking,((parking.getMaxCapacity() - parking.getAvailableSpace()) * 1.0 / parking.getMaxCapacity()) * 100);
        }

        Map.Entry<Parking, Double> min = null;
        for (Map.Entry<Parking, Double> entry : this.parkingOccupancies.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }
        return min;
    }
}
