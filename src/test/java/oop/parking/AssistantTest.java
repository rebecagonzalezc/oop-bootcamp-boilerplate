package oop.parking;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AssistantTest {

    private static final String LICENSE_PLATE = "MAT-001";
    public static final String LICENSE_PLATE_TEST = "M-1";
    public static final double DELTA_ERROR_MARGIN = 0.01;
    public static final String NAME_TEST = "Pepe Perez";
    private Assistant assistant;
    private Parking parking;
    private Parking parkingOther;
    private List<Parking> parkingList;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        this.parking = new Parking(1, false, "Parking Callao");
        this.parkingOther = new Parking(10, false, "Parking Callao");
        this.parkingList = new ArrayList<>();
        this.parkingList.add(parking);
        this.parkingList.add(parkingOther);
        this.assistant = new Assistant(parkingList, NAME_TEST);
    }

    @Test
    public void itShouldParkVehicleInFirstParkingWithSpace() {
        final var parkingFull = new Parking(0, false, "Parking Callao");
        final var parkingAvailable = new Parking(2, false, "Parking Callao");
        final var parkingList = List.of(parkingFull, parkingAvailable);
        final var assistant = new Assistant(parkingList, NAME_TEST);
        assertTrue(assistant.parkVehicle(new Vehicle(LICENSE_PLATE, false, false)));
    }

    @Test
    public void itShouldRetrieveVehicle() {
        assistant.parkVehicle(new Vehicle(LICENSE_PLATE, false, false));
        assertTrue(assistant.retrieveVehicle(LICENSE_PLATE));
        assertFalse(assistant.retrieveVehicle(LICENSE_PLATE));
    }

    @Test
    public void itShouldParkLargeCarsInParkingWithLeastPercentageOfUsage() {
        final var assistant = assembleAssistant(parking, parkingOther);

        parking.add("M1");

        assistant.parkVehicle(new Vehicle(LICENSE_PLATE_TEST, true, false));
        assertFalse(parking.retrieveVehicle(LICENSE_PLATE_TEST));
        assertTrue(parkingOther.retrieveVehicle(LICENSE_PLATE_TEST));
    }

    private Assistant assembleAssistant(Parking ... parkings) {
        final var parkingList = new ArrayList<Parking>();
        for (Parking parking: parkings) {
            parkingList.add(parking);
        }
        return new Assistant(parkingList, NAME_TEST);
    }

    @Test
    public void itShouldParkHandicappedCarsInParkingForHandicappedCars() {
        final var parking = new Parking(5, false, "Parking Callao");
        final var parkingOther = new Parking(5, true, "Parking Callao");
        final var assistant = new Assistant(List.of(parking, parkingOther), NAME_TEST);

        assistant.parkVehicle(new Vehicle(LICENSE_PLATE_TEST, false, true));
        assertFalse(parking.retrieveVehicle(LICENSE_PLATE_TEST));
        assertTrue(parkingOther.retrieveVehicle(LICENSE_PLATE_TEST));
    }

    @Test
    public void itShouldDelegateOnHiredAssistantToParkVehicle() {
        final var parking = new Parking(5, false, "Parking Callao");
        final var parkingOther = new Parking(5, true, "Parking Callao");
        final var assistant = new Assistant(List.of(parking, parkingOther), NAME_TEST);
        final var assistantHired = new Assistant();

        final var spyAssistant = spy(assistant);
        final var spyAssistantHired = spy(assistantHired);
        spyAssistant.setParkingAssistant(spyAssistantHired);

        spyAssistant.parkVehicle(new Vehicle(LICENSE_PLATE_TEST, false, true));

        verify(spyAssistantHired, times(1)).parkVehicle(any());
        assertTrue(parkingOther.retrieveVehicle(LICENSE_PLATE_TEST));
    }

    @Test
    public void itShouldUpdateCapacityForOneParking() {
        final var parking = new Parking(5, false, "Parking Callao");
        final var assistant = new Assistant(List.of(parking), NAME_TEST);
        assistant.parkVehicle(new Vehicle(LICENSE_PLATE_TEST, false, false));

        assertEquals(20, assistant.getCapacity(parking), DELTA_ERROR_MARGIN);
    }

    @Test
    public void itShouldParkVehicleIfHiredAssistantCouldNot() {
        final var parkingFirstAssistant = new Parking(1, true, "Parking Callao");
        final var assistant = new Assistant(List.of(parkingFirstAssistant), NAME_TEST);
        final var assistantHired = new Assistant(List.of(new Parking(0, false, "Parking Callao")), NAME_TEST);

        assistant.setParkingAssistant(assistantHired);
        assistant.parkVehicle(new Vehicle("M1", false, true));
        assertTrue(parkingFirstAssistant.retrieveVehicle("M1"));
    }

    @Test
    public void itShouldUpdateCapacityForMoreThanOneParking() {
        final var parking = new Parking(0, false, "Parking Callao");
        final var parkingOther = new Parking(4, false, "Parking Callao");
        final var assistant = new Assistant(List.of(parking, parkingOther), NAME_TEST);

        assistant.parkVehicle(new Vehicle(LICENSE_PLATE_TEST, false, false));

        assertFalse(parking.retrieveVehicle(LICENSE_PLATE_TEST));
        assertEquals(0, assistant.getCapacity(parking), DELTA_ERROR_MARGIN);

        assertEquals(25, assistant.getCapacity(parkingOther), DELTA_ERROR_MARGIN);
        assertTrue(parkingOther.retrieveVehicle(LICENSE_PLATE_TEST));
    }

    @Test
    public void itShouldParkInTheStreet() {
        final var assistant = new Assistant(List.of(parking, parkingOther), NAME_TEST);
        final var streetAssistant = new StreetParkingAssistance();
        final var secondaryAssistant = new Assistant();
        secondaryAssistant.setParkingAssistant(streetAssistant);
        assistant.setParkingAssistant(secondaryAssistant);

        assertTrue(assistant.parkVehicle(new Vehicle("M1", false, false)));
        assertFalse(parking.retrieveVehicle("M1"));
        assertFalse(parkingOther.retrieveVehicle("M1"));
    }

    @Test
    public void itShouldHaveName() {
        final ParkingAssistant assistant = new Assistant(List.of(parking, parkingOther), NAME_TEST);
        assertEquals(NAME_TEST, assistant.getName());
    }
}