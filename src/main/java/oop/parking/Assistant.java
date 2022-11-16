package oop.parking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Assistant  {

  Set<Parking> parkingLots;

  public Assistant(Parking... parkingLots) {
    this.parkingLots = new HashSet<>(Arrays.asList(parkingLots));
  }

  public boolean park(String licenseNumber) {
    for ( Parking parking : parkingLots) {
      int minFreeSlots = (int) (parking.getTotalSlots() * 0.2);
      if(parking.availableSpace() > minFreeSlots) {
        return parking.park(licenseNumber);
      }
    }
    return false;
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
