import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class HitBoxTest {


   /** Fixture initialization (common initialization for all tests). **/
   @Before public void setUp() {
   }

    @Test
    public void testIntersectLowerRight() {
        // Create HitBox objects for testing
        HitBox hitBox5 = new HitBox(0, 0, 10, 10, new Point(-5, 5), new Point(5, 5), new Point(5, -5), new Point(-5, -5));
        HitBox hitBox6 = new HitBox(5, -5, 10, 10, new Point(0, 0), new Point(10, 0), new Point(10, -10), new Point(0, -10));
        // Test intersect when hitboxes are overlapping
        assertTrue(hitBox5.intersect(hitBox6));
    }
    @Test
    public void testIntersectLowerLeft() {
        // Create HitBox objects for testing
        HitBox hitBox7 = new HitBox(0, 0, 10, 10, new Point(-5, 5), new Point(5, 5), new Point(5, -5), new Point(-5, -5));
        HitBox hitBox8 = new HitBox(-5, -5, 10, 10, new Point(-10, 0), new Point(0, 0), new Point(0, -10), new Point(-10, -10));
        // Test intersect when hitboxes are overlapping
        assertTrue(hitBox7.intersect(hitBox8));
    }
    @Test
    public void testIntersectUpperLeft() {
        // Create HitBox objects for testing
        HitBox hitBox9 = new HitBox(0, 0, 10, 10, new Point(-5, 5), new Point(5, 5), new Point(5, -5), new Point(-5, -5));
        HitBox hitBox10 = new HitBox(-5, 5, 10, 10, new Point(-10, 10), new Point(0, 10), new Point(0, 0), new Point(-10, 0));
        // Test intersect when hitboxes are overlapping
        assertTrue(hitBox9.intersect(hitBox10));
    }
    @Test
    public void testIntersect2() {
        // Test intersect when hitboxes are not overlapping
        HitBox hitBox4 = new HitBox(0, 0, 10, 10, new Point(-5, 5), new Point(5, 5), new Point(5, -5), new Point(-5, -5));
        HitBox hitBox3 = new HitBox(20, 20, 10, 10, new Point(15, 25), new Point(25, 25), new Point(25, 15), new Point(15, 15));
        assertFalse(hitBox4.intersect(hitBox3));
    }
}
