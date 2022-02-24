package oop.parking;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ParkingLandlord implements PropertyChangeListener {

    private boolean isPurchaseNeeded;
    private boolean toBeClosed;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("There are more vehicles in the parking!!!");
        final var parkingCapacityChangeEvent = (ParkingCapacityChangeEvent) evt.getNewValue();
        evaluateStatus(parkingCapacityChangeEvent);
    }

    private void evaluateStatus(ParkingCapacityChangeEvent parkingCapacityChangeEvent) {
        if(parkingCapacityChangeEvent.getPercentageOfOccupancy() >= 80) {
            System.out.println("This parking is nearly full, I need to buy a new one!");
            isPurchaseNeeded=true;
        }
        if (parkingCapacityChangeEvent.getPercentageOfOccupancy() < 20) {
            this.toBeClosed = true;
        } else {
            this.toBeClosed = false;
        }
    }

    public boolean isPurchaseNeeded() {
        return isPurchaseNeeded;
    }

    public boolean isToBeClosed() {
        return toBeClosed;
    }
}
