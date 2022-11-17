package oop.parking;

public class ParkingLandlord {
    private boolean purchaseNeeded;

    public ParkingLandlord() {
        this.purchaseNeeded = false;
    }

    public boolean isPurchaseNeeded() {
        return purchaseNeeded;
    }

    public void setPurchaseNeeded() {
        purchaseNeeded = true;
    }
}
