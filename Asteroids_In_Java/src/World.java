/**
 * Describes the physics of the world.
 */
import java.util.Random;
import javafx.scene.shape.Polygon;
import javafx.geometry.Bounds;

public class World {
   private AsteroidSet asteroids;
   private AsteroidSetMedium mediumAsteroids;
   private AsteroidSetSmall smallAsteroids;
   private Ship ship;
   private currAsteroidCount;
   private additionAsteroidCount;
   private int numOfLives = 3;
   
   public static final int X_DIMENSION = 100; // meters
   public static final int Y_DIMENSION = 70; // meters
   private final int INITIAL_NUMBER_OF_ASTEROIDS = 4;
   
   public World() {
      //Create Asteroid sets.
      asteroids = new AsteroidSet(INITIAL_NUMBER_OF_ASTEROIDS);
      mediumAsteroids = new AsteroidSetMedium(0);
      smallAsteroids = new AsteroidSetSmall(0);

      //Generate the ship.
      double shipXStartLocation = X_DIMENSION/2;
      double shipYStartLocation = Y_DIMENSION/2;
      double shipXSpeed = 0.0;
      double shipYSpeed = 0.0;
      double shipDirection = 0.0;
      HitBox shipHitbox = new HitBox(0,0,0,0);
      shipHitbox.set(shipXStartLocation, shipYStartLocation, 40, 40);
      
      ship = new Ship(shipXStartLocation, shipYStartLocation, shipXSpeed, shipYSpeed, shipDirection, shipHitbox);
      currAsteroidCount = INITIAL_NUMBER_OF_ASTEROIDS;
      additionalAsteroidCount = 0;
   }
   
   /** Runs the physics of the world. */
   public void run(long elapsedTimeInNanoseconds) {
      while(numOfLives >= 0){
         asteroids.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension());
         ship.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension());
         
         respawnAsteroids();
         respawnShip();
      }
      System.out.println("Game Over.");
      System.exit();
   }
   
   private void respawnAsteroids(){
      if(currAsteroidCount == 0){
         additionalAsteroidCount++;
         currAsteroidCount = INITIAL_NUMBER_OF_ASTEROIDS + additionalAsteroidCount;
         for(int i = currAsteroidCount; i > 0; i--){
            asteroids.addAsteroid();
         }
      }
   }
   
   private void respawnShip(){
      
      decrementLives();
   }
   
   private void decrementLives() {
      numOfLives -= 1;
   }
   
   private void incrementLives() {
      if(numOfLives < 5){
         numOfLives += 1;
      }
   }

   //Accessor Methods
   public AsteroidSet getAsteroidSet() {
      return asteroids;
   }
   
   public AsteroidSetMedium getMediumAsteroidSet() {
      return mediumAsteroids;
   }
   
   public AsteroidSetSmall getSmallAsteroidSet() {
      return smallAsteroids;
   }
   
   public Asteroid[] getAsteroidsAsArray() {
      return asteroids.getAsteroidsAsArray();
   }
   
   public Asteroid[] getMediumAsteroidsAsArray() {
      return mediumAsteroids.getAsteroidsAsArray();
   }
   
   public Asteroid[] getSmallAsteroidsAsArray() {
      return smallAsteroids.getAsteroidsAsArray();
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
   
   public int getNumOfLives() {
      return numOfLives;
   }
   
}
