package oop.parking.strategy;

import oop.parking.Parking;
import oop.parking.Vehicle;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RegularVehicleParkStrategy implements ParkStrategy {

    private Vehicle vehicle;
    private List<Parking> parkingList;
    private Map<Parking, Double> parkingOccupancies = new LinkedHashMap<>();

    public RegularVehicleParkStrategy(Vehicle vehicle, List<Parking> parkingList) {
        this.vehicle = vehicle;
        this.parkingList = parkingList;
    }

    @Override
    public boolean park(Vehicle vehicle, List<Parking> parkingList) {
        boolean success = false;
        int i = 0;
        while (!success && i < parkingList.size() && isOccupiedAt80Percent(i)) {
            success = parkingList.get(i).add(vehicle.licensePlate);
            i++;
        }
        return success;
    }

    private boolean isOccupiedAt80Percent(int i) {
        for (Parking parking : parkingList) {
            parkingOccupancies.put(parking, 0.0);
        }

        final var parkingList = parkingOccupancies.keySet().stream().collect(Collectors.toList());
        final var occupancy = this.parkingOccupancies.get(parkingList.get(i));
        return occupancy < 80;
    }
}
