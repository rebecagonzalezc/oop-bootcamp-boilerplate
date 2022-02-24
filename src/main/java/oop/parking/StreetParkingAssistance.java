package oop.parking;

public class StreetParkingAssistance implements ParkingAssistant {

    @Override
    public boolean parkVehicle(Vehicle vehicle) {
        return true;
    }

    @Override
    public String getName() {
        return "Street Parking";
    }
}
