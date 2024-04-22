import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class HitBoxTest {


   /** Fixture initialization (common initialization for all tests). **/
   @Before public void setUp() {
   }

   @Test
    public void testIntersect1() {
        // Create HitBox objects for testing
        HitBox hitBox1 = new HitBox(0, 0, 10, 10, new Point(-5, 5), new Point(5, 5), new Point(5, -5), new Point(-5, -5));
        HitBox hitBox2 = new HitBox(5, 5, 10, 10, new Point(0, 10), new Point(10, 10), new Point(10, 0), new Point(0, 0));
        // Test intersect when hitboxes are overlapping
        assertTrue(hitBox1.intersect(hitBox2));
    }
    @Test
    public void testIntersect2() {
        // Test intersect when hitboxes are not overlapping
        HitBox hitBox4 = new HitBox(0, 0, 10, 10, new Point(-5, 5), new Point(5, 5), new Point(5, -5), new Point(-5, -5));
        HitBox hitBox3 = new HitBox(20, 20, 10, 10, new Point(15, 25), new Point(25, 25), new Point(25, 15), new Point(15, 15));
        assertFalse(hitBox4.intersect(hitBox3));
    }
}
