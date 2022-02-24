package oop.parking;

import oop.parking.strategy.ParkStrategy;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Assistant implements PropertyChangeListener, ParkingAssistant {

    private Map<Parking, Double> parkingOccupancies = new LinkedHashMap<>();
    private ParkingAssistant parkingAssistant;
    private String name;

    public Assistant(final List<Parking> parkingList, final String name) {
        for (Parking parking : parkingList) {
            parking.addPropertyChangeListener(this);
            parkingOccupancies.put(parking, 0.0);
        }
        this.name = name;
    }

    public Assistant() {
        this.name = "Default Name";
    }

    @Override
    public boolean parkVehicle(final Vehicle vehicle) {
        boolean parked = false;
        if (this.parkingAssistant != null) {
            parked = this.parkingAssistant.parkVehicle(vehicle);
        }
        if (!parked) {
            final var parkingList = parkingOccupancies.keySet().stream().collect(Collectors.toList());
            ParkStrategy parkStrategy = vehicle.selectParkStrategy(parkingList);
            parked = parkStrategy.park(vehicle, parkingList);
        }
        return parked;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public boolean retrieveVehicle(final String licensePlate) {
        final var parkingList = parkingOccupancies.keySet().stream().collect(Collectors.toList());
        boolean success = false;
        for (Parking parking : parkingList) {
            if (parking.isPresent(licensePlate)) {
                success = parking.retrieveVehicle(licensePlate);
            }
        }
        return success;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final var parkingCapacityChangeEvent = (ParkingCapacityChangeEvent) evt.getNewValue();
        final var parking = (Parking) evt.getSource();
        this.parkingOccupancies.put(parking, parkingCapacityChangeEvent.getPercentageOfOccupancy());
    }

    public double getCapacity(final Parking parking) {
        return this.parkingOccupancies.get(parking);
    }

    public void setParkingAssistant(ParkingAssistant parkingAssistant) {
        this.parkingAssistant = parkingAssistant;
    }

}
