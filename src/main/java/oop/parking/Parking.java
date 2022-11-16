package oop.parking;

import java.util.HashSet;
import java.util.Set;

public class Parking {

    Set<String> parkedCars;
    private int totalSlots;

    public Parking(int totalSlots){
        parkedCars = new HashSet<>();
        this.totalSlots = totalSlots;
    }

    public boolean park(String licenseNumber) {
        if(haveSpace()) {
            return parkedCars.add(licenseNumber);
        }
        return false;
    }

    public boolean isParked(String licenseNumber) {
        return parkedCars.contains(licenseNumber);
    }

    public boolean retrieve(String licenseNumber) {
        return parkedCars.remove(licenseNumber);
    }

    public boolean haveSpace() {
        return parkedCars.size() < totalSlots;
    }

    public boolean containsCar(String licenseNumber) {
        return parkedCars.contains(licenseNumber);
    }
}
