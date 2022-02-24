package oop.parking.strategy;

import oop.parking.Parking;
import oop.parking.Vehicle;

import java.util.List;

public interface ParkStrategy {

    boolean park(final Vehicle vehicle, final List<Parking> parkingList);
}
