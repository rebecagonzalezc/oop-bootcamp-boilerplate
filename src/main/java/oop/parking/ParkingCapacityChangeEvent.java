package oop.parking;

public class ParkingCapacityChangeEvent {

    private final int maxCapacity;
    private final int currentCapacity;

    public ParkingCapacityChangeEvent(int maxCapacity, int currentCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    public double getPercentageOfOccupancy() {
        return ((maxCapacity - currentCapacity) * 1.0 / maxCapacity) * 100;
    }
}
