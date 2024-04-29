/**
 * Describes the physics of the world.
 */
import java.util.Random;
import javafx.scene.shape.Polygon;
import javafx.geometry.Bounds;
import java.util.concurrent.TimeUnit;

public class World {
   private AsteroidSet asteroids;
   private AsteroidSetMedium mediumAsteroids;
   private AsteroidSetSmall smallAsteroids;
   private Ship ship;
   private Lazers lazers;
   private int currAsteroidCount;
   private int additionalAsteroidCount;
   private int numOfLives = 3;
   
   public static final int X_DIMENSION = 100; // meters
   public static final int Y_DIMENSION = 70; // meters
   private final int INITIAL_NUMBER_OF_ASTEROIDS = 4;
   private final int NUMBER_OF_LASERS = 1;
   
   public World() {
      //Create Asteroid sets.
      asteroids = new AsteroidSet(INITIAL_NUMBER_OF_ASTEROIDS);
      mediumAsteroids = new AsteroidSetMedium(2);
      smallAsteroids = new AsteroidSetSmall(2);
      
      //Create Lazer set.
      lazers = new Lazers();

      //Generate the ship.
      double shipXStartLocation = X_DIMENSION/2;
      double shipYStartLocation = Y_DIMENSION/2;
      double shipXSpeed = 0.0;
      double shipYSpeed = 0.0;
      double shipDirection = 270.0;
      Point upperLeft = new Point(0,0); 
      Point upperRight = new Point(0,0);
      Point lowerLeft = new Point(0,0);
      Point lowerRight = new Point(0,0);
      HitBox shipHitbox = new HitBox(0,0,0,0, upperLeft, upperRight, lowerLeft, lowerRight);
      Point shipUpperLeft = new Point(shipXStartLocation-10,shipYStartLocation+20); 
      Point shipUpperRight = new Point(shipXStartLocation+10,shipYStartLocation+20);
      Point shipLowerLeft = new Point(shipXStartLocation-10,shipYStartLocation-20);
      Point shipLowerRight = new Point(shipXStartLocation+10,shipYStartLocation-20);
      shipHitbox.set(shipXStartLocation, shipYStartLocation, 20, 40, shipUpperLeft, shipUpperRight, shipLowerLeft, shipLowerRight);
      
      ship = new Ship(shipXStartLocation, shipYStartLocation, shipXSpeed, shipYSpeed, shipDirection, shipHitbox);
      currAsteroidCount = INITIAL_NUMBER_OF_ASTEROIDS;
      additionalAsteroidCount = 0;
   }
   
   /** Runs the physics of the world. */
   public void run(long elapsedTimeInNanoseconds) {
      asteroids.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension());
      mediumAsteroids.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension());
      smallAsteroids.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension());
      ship.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension());
      lazers.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension());
      
      respawnAsteroids();
      //respawnShip();
      
      if(numOfLives < 0){
         System.out.println("Game Over.");
         try{
            Thread.sleep(3000);
         }catch (InterruptedException ie){
            Thread.currentThread().interrupt();
         }
         System.exit(0);
      }
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
      ship.set(X_DIMENSION/2, Y_DIMENSION/2, 0.0, 0.0, 270.0);
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
   /*
   private asteroidCollision() {
      
   }
   */
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
   
   public Lazers getLazers() {
      return lazers;
   }
   
   public Lazer[] getLazersAsArray() {
      return lazers.getLazersAsArray();
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
