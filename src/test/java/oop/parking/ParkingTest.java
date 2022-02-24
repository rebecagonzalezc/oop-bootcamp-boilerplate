package oop.parking;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ParkingTest {

    private static final String LICENSE_PLATE = "MAT-001";

    @Test
    public void itShouldNotAllowVehiclesIfNoAvailableSpace() {
        assertFalse(new Parking(0, false, "Parking Callao").add(LICENSE_PLATE));
    }

    @Test
    public void itShouldAllowVehiclesIfAvailableSpace() {
        final var parking = new Parking(5, false, "Parking Callao");
        assertEquals(5, parking.getAvailableSpace());
        assertTrue(parking.add(LICENSE_PLATE));
        assertTrue(parking.isPresent(LICENSE_PLATE));
        assertEquals(4, parking.getAvailableSpace());
    }

    @Test
    public void itShouldNotAllowDuplicateVehicles() {
        final var parking = new Parking(5, false, "Parking Callao");
        assertTrue(parking.add(LICENSE_PLATE));
        assertFalse(parking.add(LICENSE_PLATE));
    }

    @Test
    public void itShouldAllowCarRetrieval() {
        final var parking = new Parking(5, false, "Parking Callao");
        assertFalse(parking.retrieveVehicle(LICENSE_PLATE));
        assertTrue(parking.add(LICENSE_PLATE));
        assertTrue(parking.isPresent(LICENSE_PLATE));
        assertTrue(parking.retrieveVehicle(LICENSE_PLATE));
    }

    @Test
    public void itShouldHaveName() {
        final var parking = new Parking(5, false, "Parking Callao");
        assertEquals("Parking Callao", parking.getName());
    }
}