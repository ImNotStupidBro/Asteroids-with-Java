import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PresentationTest {

   /** Fixture initialization (common initialization for all tests). **/
   @Before public void setUp() {
   }

   /** Polygon Rotation Y Calculation Test **/
   @Test public void YCalculationTest() {
      double testX = 0.0;
      double testY = 15.0;
      double expected = 0.0;
      double actual = (testY * Math.cos(Math.toRadians(90.0))) + (testX * Math.sin(Math.toRadians(90)));
      assertEquals(expected, actual, 0.1);
   }
   
   /** Polygon Rotation X Calculation Test **/
   @Test public void XCalculationTest() {
      double testX = 0.0;
      double testY = 15.0;
      double expected = 15.0;
      double actual = (testX * Math.cos(Math.toRadians(90.0))) + (testY * Math.sin(Math.toRadians(90)));
      assertEquals(expected, actual, 0.1);
   }
}
