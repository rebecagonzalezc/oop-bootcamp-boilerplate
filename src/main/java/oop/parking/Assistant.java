package oop.parking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Assistant  {

  private static final double MIN_FREE_PERCENTAGE_TRESHOLD = 0.2;
  private Set<Parking> parkingLots;

  public Assistant(Parking... parkingLots) {
    this.parkingLots = new HashSet<>(Arrays.asList(parkingLots));
  }

  public boolean park(String licenseNumber) {
    for ( Parking parking : parkingLots) {
      if(parking.availableSpace() > isCapacityGreaterThanThreshold(parking)) {
        return parking.park(licenseNumber);
      }
    }
    return false;
  }

  private int isCapacityGreaterThanThreshold(Parking parking) {
    return (int) (parking.getTotalSlots() * MIN_FREE_PERCENTAGE_TRESHOLD);
  }

  private int isCapacityCloseToLimit(Parking parking) {
    return (int) (parking.getTotalSlots() * MIN_FREE_PERCENTAGE_TRESHOLD);
  }

  public boolean retrieve(String licenseNumber) {
    for ( Parking parking : parkingLots) {
      if(parking.containsCar(licenseNumber))
      {
        return parking.retrieve(licenseNumber);
      }
    }
    return false;
  }
}
