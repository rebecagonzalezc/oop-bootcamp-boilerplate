package oop.parking;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Set;

public class Parking {

    private int availableSpace;
    private final int maxCapacity;
    private final boolean handicappedFriendly;
    private final String name;
    private Set<String> slots;
    private PropertyChangeSupport support;

    public Parking(final int maxCapacity, boolean handicappedFriendly, String name) {
        support = new PropertyChangeSupport(this);
        this.maxCapacity = maxCapacity;
        this.availableSpace = maxCapacity;
        this.handicappedFriendly = handicappedFriendly;
        this.slots = new HashSet<>();
        this.name = name;
    }

    public boolean add(final String licensePlate) {
        if (!isParkable(licensePlate)) {
            return false;
        } else return addVehicle(licensePlate);
    }

    private boolean isParkable(final String licensePlate) {
        return !isFullParking() && !isPresent(licensePlate);
    }

    private boolean addVehicle(final String licensePlate) {
        final var eventPrevious = new ParkingCapacityChangeEvent(maxCapacity, availableSpace);
        slots.add(licensePlate);
        availableSpace--;
        final var eventAfter = new ParkingCapacityChangeEvent(maxCapacity, availableSpace);
        support.firePropertyChange("parkingCapacityChangeEvent", eventPrevious, eventAfter);
        return true;
    }

    private boolean isFullParking() {
        return availableSpace <= 0;
    }

    public int getAvailableSpace() {
        return this.availableSpace;
    }

    public boolean retrieveVehicle(String licensePlate) {
        if (!isPresent(licensePlate)) {
            return false;
        } else {
            slots.remove(licensePlate);
            availableSpace++;
            return true;
        }
    }

    public boolean isPresent(String licensePlate) {
        return slots.contains(licensePlate);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public boolean isHandicappedFriendly() {
        return handicappedFriendly;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public String getName() {
        return name;
    }
}
