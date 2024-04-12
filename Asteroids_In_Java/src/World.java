/**
 * Describes the physics of the world.
 */
import java.util.Random;

public class World {
   private AsteroidSet asteroids;
   private Ship ship;
   public static final int X_DIMENSION = 100; // meters
   public static final int Y_DIMENSION = 70; // meters
   private final int[] SHIPXPOINTS = {116, 100, 104, 128, 132, 116};
   private final int[] SHIPYPOINTS = {80, 128, 116, 116, 128, 80};
   private final double MAX_SHIP_SPEED = 30.0;
   private final int NUMBER_OF_ASTEROIDS = 4;
   
   public World() {
      //Create Asteroid set.
      asteroids = new AsteroidSet(NUMBER_OF_ASTEROIDS);

      //Generate the ship.
      int shipXStartLocation = X_DIMENSION/2;
      int shipYStartLocation = Y_DIMENSION/2;
      double shipXSpeed = 0.0;
      double shipYSpeed = 0.0;
      HitBox ballHitbox = new HitBox(0,0,0,0);
      Polygon shipPolygon = new Polygon(SHIPXPOINTS, SHIPYPOINTS, SHIPXPOINTS.length);
      Rectangle shipBox = shipPolygon.getBounds();
      shipHitbox.set(shipXStartLocation, shipYStartLocation, shipBox.getWidth(), shipBox.getHeight());
      
      ship = new Ship(shipXStartLocation, shipYStartLocation, shipRadius, shipXSpeed, shipYSpeed, shipHitbox, shipPolygon);
   }
   
   /** Runs the physics of the world. */
   public void run(long elapsedTimeInNanoseconds) {
      asteroids.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension());
      ship.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension());
   }

   public AsteroidSet getAsteroidSet() {
      return asteroids;
   }
   
   public Asteroid[] getAsteroidsAsArray() {
      return asteroids.getAsteroidsAsArray();
   }

   public Ship getShip() {
      return ship;
   }

   public double getXDimension() {
      return X_DIMENSION;
   }

   public double getYDimension() {
      return Y_DIMENSION;
   }
}
