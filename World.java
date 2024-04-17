/**
 * Describes the physics of the world.
 */
import java.util.Random;
import javafx.scene.shape.Polygon;
import javafx.geometry.Bounds;

public class World {
   private AsteroidSet asteroids;
   private Ship ship;
   private Lazers lazers;
   public static final int X_DIMENSION = 100; // meters
   public static final int Y_DIMENSION = 70; // meters
   private final double MAX_SHIP_SPEED = 30.0;
   private final int NUMBER_OF_ASTEROIDS = 4;
   private final int NUMBER_OF_LASERS = 1; //this is just a test
   
   public World() {
      //Create Asteroid set.
      asteroids = new AsteroidSet(NUMBER_OF_ASTEROIDS);
      
      //Create Lazer set.
      lazers = new Lazers();

      //Generate the ship.
      double shipXStartLocation = X_DIMENSION/2;
      double shipYStartLocation = Y_DIMENSION/2;
      double shipXSpeed = 0.0;
      double shipYSpeed = 0.0;
      double shipDirection = 0.0;
      HitBox shipHitbox = new HitBox(0,0,0,0);
      shipHitbox.set(shipXStartLocation, shipYStartLocation, 20, 40);
      
      ship = new Ship(shipXStartLocation, shipYStartLocation, shipXSpeed, shipYSpeed, shipDirection, shipHitbox);
      
      
   }
   
   /** Runs the physics of the world. */
   public void run(long elapsedTimeInNanoseconds) {
      asteroids.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension());
      ship.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension());
      lazers.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension());
   }

   public AsteroidSet getAsteroidSet() {
      return asteroids;
   }
   
   public Asteroid[] getAsteroidsAsArray() {
      return asteroids.getAsteroidsAsArray();
   }
   
   public Lazers getLazers() {
      return lazers;
   }
   
   public Lazer[] getLazersAsArray() {
      return lazers.getLazersAsArray();
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