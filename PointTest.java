import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PointTest {


   /** Fixture initialization (common initialization for all tests). **/
   @Before public void setUp() {
   }
   
   @Test
    public void testMovePoint() {
        Point point = new Point(2.5, 3.5);
        point.movePoint(5.0, 6.0);
        assertEquals(5.0, point.getX(), 0.01);
        assertEquals(6.0, point.getY(), 0.01);
    }

    @Test
    public void testIsBelowAndToLeft() {
        Point point1 = new Point(2.5, 3.5);
        Point point2 = new Point(1.5, 2.5);
        assertTrue(point1.isBelowAndToLeft(point2));
    }

    @Test
    public void testIsBelowAndToRight() {
        Point point1 = new Point(2.5, 3.5);
        Point point2 = new Point(3.5, 4.5);
        assertFalse(point1.isBelowAndToRight(point2));
    }

    @Test
    public void testIsAboveAndToLeft() {
        Point point1 = new Point(2.5, 3.5);
        Point point3 = new Point(1.5, 2.5);
        assertFalse(point1.isAboveAndToLeft(point3));
    }

    @Test
    public void testIsAboveAndToRight() {
        Point point1 = new Point(2.5, 3.5);
        Point point3 = new Point(3.5, 2.5);
        assertFalse(point1.isAboveAndToRight(point3));
    }
     
}
