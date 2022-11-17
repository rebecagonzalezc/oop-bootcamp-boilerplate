package oop.parking;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ParkingLandlordTest {

    @Test
    public void itShouldNotifyWhenCapacityReaches75Percentage() {
        ParkingLandlord landlord = new ParkingLandlord();
        Parking parking = new Parking(10, landlord);
        Assistant assistant = new Assistant(parking);

        for(int i = 1; i <= 8; i++) {
            assistant.park("MAT-00" + i);
        }
        assertTrue(landlord.isPurchaseNeeded());
    }
}
