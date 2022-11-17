package oop.parking;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AssistantTest {

  Parking parking;
  Assistant assistant;

  @BeforeMethod
  public void setUp() {
    parking = new Parking(2);
    assistant = new Assistant(parking);
  }

  @Test
  public void itShouldParkCar() {
    assertTrue(assistant.park("MAT-001"));
    assertFalse(assistant.park("MAT-001"));
  }

  @Test
  public void itShouldRetrieveCar() {
    assistant.park("MAT-001");
    assertTrue(assistant.retrieve("MAT-001"));
    assertFalse(assistant.retrieve("MAT-001"));
  }

  @Test
  public void itShouldParkCarInMultipleParkingLots() {
    Parking parking2 = new Parking(2);
    assistant = new Assistant(parking, parking2);
    assertTrue(assistant.park("MAT-001"));
    assertTrue(assistant.park("MAT-002"));
    assertTrue(assistant.park("MAT-003"));
    assertTrue(assistant.park("MAT-004"));
    assertFalse(assistant.park("MAT-005"));
  }

  @Test
  public void itShouldParkCarLessThanEightyPercentFull() {
    Parking parking = new Parking(10);
    assistant = new Assistant(parking);

    for(int i = 1; i <= 8; i++) {
      assertTrue(assistant.park("MAT-00" + i));
    }
    assertFalse(assistant.park("MAT-009"));
  }
}
