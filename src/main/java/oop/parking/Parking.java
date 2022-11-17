package oop.parking;

import java.util.HashSet;
import java.util.Set;

public class Parking {

    private static final double MAX_OCCUPATION_FACTOR = 0.75;
    private Set<String> parkedCars;
    private int totalSlots;
    private ParkingLandlord landlord;

    public int getTotalSlots() {
        return totalSlots;
    }

    public Parking(int totalSlots){
        parkedCars = new HashSet<>();
        this.totalSlots = totalSlots;
        this.landlord = new ParkingLandlord();
    }

    public Parking(int totalSlots, ParkingLandlord landlord){
        parkedCars = new HashSet<>();
        this.totalSlots = totalSlots;
        this.landlord = landlord;
    }

    public boolean park(String licenseNumber) {
        if(availableSpace() > 0) {
            var parked = parkedCars.add(licenseNumber);
            notifyInCaseIsAlmostFull();
            return parked;
        }
        return false;
    }

    private void notifyInCaseIsAlmostFull() {
        int maxOccupation = (int)(totalSlots * MAX_OCCUPATION_FACTOR);
        if(parkedCars.size() > maxOccupation) {
            landlord.setPurchaseNeeded();
        }
    }

    public boolean isParked(String licenseNumber) {
        return parkedCars.contains(licenseNumber);
    }

    public boolean retrieve(String licenseNumber) {
        return parkedCars.remove(licenseNumber);
    }

    public int availableSpace() {
        return totalSlots - parkedCars.size();
    }

    public boolean containsCar(String licenseNumber) {
        return parkedCars.contains(licenseNumber);
    }
}
