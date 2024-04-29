/**
 * Describes the physics of the world.
 */
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class World {
   private AsteroidSet asteroids;
   private AsteroidSetMedium mediumAsteroids;
   private AsteroidSetSmall smallAsteroids;
   private AlienShipSet alienShips;
   private Ship ship;
   private Lazers lazers;
   private int currAsteroidCount;
   private int additionalAsteroidCount;
   private int numOfLives = 3;
  
   public static final int X_DIMENSION = 100; // meters
   public static final int Y_DIMENSION = 70; // meters
   private final int NUMBER_OF_LASERS = 1;
   private final int INITIAL_NUMBER_OF_ASTEROIDS = 1;
   
   public World() {
      //Create Asteroid sets.
      asteroids = new AsteroidSet(INITIAL_NUMBER_OF_ASTEROIDS);
      mediumAsteroids = new AsteroidSetMedium(2);
      smallAsteroids = new AsteroidSetSmall(2);
      
      //Create Lazer set.
      lazers = new Lazers();
      
      //Create Alien Ship set.
      alienShips = new AlienShipSet(1);

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
      alienShips.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension());
      
      respawnAsteroids();
      
      /*
      if(Collision Detection Method Here){
         respawnShip();
      }
      if(Collision Detection Method Here){
         asteroidCollision();
      }
      
      if(Collision Detection Method Here){
         mediumAsteroidCollision();
      }
      
      if(Collision Detection Method Here){
         smallAsteroidCollision();
      }
      */
      
      if(numOfLives < 0){
         System.out.println("Game Over.");
         try{
            Thread.sleep(5000);
         }catch (InterruptedException ie){
            Thread.currentThread().interrupt();
         }
         System.exit(0);
      }
   }
   
   private void respawnAsteroids(){
      if(currAsteroidCount == 0){
         additionalAsteroidCount++;
         currAsteroidCount += INITIAL_NUMBER_OF_ASTEROIDS + additionalAsteroidCount;
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
      If a regular asteroid detects a collision with a bullet,
      destroy the regular asteroid and spawn two medium asteroids.
   }
   
   private mediumAsteroidCollision() {
      If a medium asteroid detects a collision with a bullet,
      destroy the medium asteroid and spawn two small asteroids.
   }
   
   private asteroidCollision() {
      If a small asteroid detects a collision with a bullet,
      destroy the small asteroid.
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
   
   public AlienShipSet getAlienShipSet(){
      return alienShips;
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
   
   public AlienShip[] getAlienShipsAsArray() {
      return alienShips.getAlienShipsAsArray();
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
   
   public int getAdditionalAsteroidCount() {
      return additionalAsteroidCount;
   }
}
