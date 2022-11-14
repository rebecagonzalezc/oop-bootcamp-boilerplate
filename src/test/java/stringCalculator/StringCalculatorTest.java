package stringCalculator;

import static org.testng.Assert.*;

import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

  @Test
  public void itShouldReturnZeroWhenEmptyStringGiven () {

    StringCalculator stringCalculator = new StringCalculator();

//    final int actualResult = stringCalculator.add("");
//
//    assertEquals(actualResult, 0);

    assertEquals(stringCalculator.add(""), 0);

  }

}