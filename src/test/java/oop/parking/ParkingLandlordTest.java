package oop.parking;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class ParkingLandlordTest {

    @Test
    public void itShouldReceiveNotificationWhenParkingUsedMoreThan80Percent() {
        Parking parking = new Parking(10, false, "Parking Callao");
        ParkingLandlord parkingLandlord = new ParkingLandlord();

        parking.addPropertyChangeListener(parkingLandlord);
        parking.add("M1");
        parking.add("M2");
        parking.add("M3");
        parking.add("M4");
        parking.add("M5");
        parking.add("M6");
        parking.add("M7");
        parking.add("M8");

        assertTrue(parkingLandlord.isPurchaseNeeded());
    }

    @Test
    public void itShouldNotReceiveNotificationWhenParkingUsedMoreThan80Percent() {
        Parking parking = new Parking(10, false, "Parking Callao");
        ParkingLandlord parkingLandlord = new ParkingLandlord();

        parking.addPropertyChangeListener(parkingLandlord);
        parking.add("M1");
        assertFalse(parkingLandlord.isPurchaseNeeded());
    }

    @Test
    public void itShouldCloseParkingIfUsageLessThan20Percent() {
        final var parking = new Parking(6, false, "Parking Callao");
        final var parkingLandlord = new ParkingLandlord();
        parking.addPropertyChangeListener(parkingLandlord);

        parking.add("M1");
        assertTrue(parkingLandlord.isToBeClosed());
    }

    @Test
    public void itShouldNotCloseParkingIfUsageGreaterThan20Percent() {
        final var parking = new Parking(3, false, "Parking Callao");
        final var parkingLandlord = new ParkingLandlord();
        parking.addPropertyChangeListener(parkingLandlord);

        parking.add("M1");
        assertFalse(parkingLandlord.isToBeClosed());
    }

    @Test
    public void itShouldReopenIfUsageIncreases20Percent() {
        final var parking = new Parking(6, false, "Parking Callao");
        final var parkingLandlord = new ParkingLandlord();
        parking.addPropertyChangeListener(parkingLandlord);

        parking.add("M1");
        assertTrue(parkingLandlord.isToBeClosed());
        parking.add("M2");
        assertFalse(parkingLandlord.isToBeClosed());
    }
}