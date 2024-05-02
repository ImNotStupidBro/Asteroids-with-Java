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
   private int levelNum;
   private int additionalAsteroidCount;
   private int numOfLives = 3;
   private Score scoreboard;
   private long activatedAt = Long.MAX_VALUE;
   
   private Random rand;
  
   public static final int X_DIMENSION = 100; // meters
   public static final int Y_DIMENSION = 70; // meters
   private final int NUMBER_OF_LASERS = 1;
   private final int INITIAL_NUMBER_OF_ASTEROIDS = 5;
   private long INVULNERABILITYTIMER = 5000;
   
   public World() {
      //Create Asteroid sets.
      asteroids = new AsteroidSet(1);
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
      Point shipUpperLeft = new Point(shipXStartLocation-2,shipYStartLocation+2); 
      Point shipUpperRight = new Point(shipXStartLocation+2,shipYStartLocation+2);
      Point shipLowerLeft = new Point(shipXStartLocation-2,shipYStartLocation-2);
      Point shipLowerRight = new Point(shipXStartLocation+2,shipYStartLocation-2);
      shipHitbox.set(shipXStartLocation, shipYStartLocation, 4, 4, shipUpperLeft, shipUpperRight, shipLowerLeft, shipLowerRight);
      
      ship = new Ship(shipXStartLocation, shipYStartLocation, shipXSpeed, shipYSpeed, shipDirection, shipHitbox);
      currAsteroidCount = INITIAL_NUMBER_OF_ASTEROIDS;
      levelNum = 1;
      additionalAsteroidCount = 0;
      
      //Create the score board
      scoreboard = new Score();
   }
   
   /** Runs the physics of the world. */
   public void run(long elapsedTimeInNanoseconds) {
      if(currAsteroidCount == 0){
         respawnAsteroids();
      } 
      asteroids.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension(), 8, 8);
      mediumAsteroids.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension(), 4, 4);
      smallAsteroids.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension(), 2, 2);
      ship.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension(), 3, 3);
      lazers.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension());
      alienShips.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension(), 8, 4);
      
      asteroidCollisionDetect();
      mediumAsteroidCollisionDetect();
      smallAsteroidCollisionDetect();
      
      shipCollisionDetect();
   
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
   
   public void shipCollisionDetect(){
      //part 1 (asteroid and ship collision)
      for(Asteroid asteroid: asteroids.getAsteroidsAsArray()){                   
         if(asteroid.getHitBox().intersect(ship.getHitBox()) && !isInvulnerable()){    
            System.out.println("Collision detected");
            respawnShip();
            toggleInvulnerability();
         }
      }
      for(Asteroid asteroid: mediumAsteroids.getAsteroidsAsArray()){                   
         if(asteroid.getHitBox().intersect(ship.getHitBox()) && !isInvulnerable()){    
            System.out.println("Collision detected");
            respawnShip();
            toggleInvulnerability();
         }
      }
      for(Asteroid asteroid: smallAsteroids.getAsteroidsAsArray()){                   
         if(asteroid.getHitBox().intersect(ship.getHitBox()) && !isInvulnerable()){    
            System.out.println("Collision detected");
            respawnShip();
            toggleInvulnerability();
         }
      }
      for(AlienShip alienShip: alienShips.getAlienShipsAsArray()){                   
         if(alienShip.getHitBox().intersect(ship.getHitBox()) && !isInvulnerable()){    
            System.out.println("Collision detected");
            respawnShip();
            toggleInvulnerability();
         }
      }
   }
   
   public void asteroidCollisionDetect(){
      //part 2 (lazer and ship collision)
      for(Lazer lazer: lazers.getLazersAsArray()){                                        
         for(Asteroid asteroid: asteroids.getAsteroidsAsArray()){                             
            if(asteroid.getHitBox().intersect(lazer.getHitbox())){
               System.out.println("Boom!");
               for(int i = 1; i <= 2; i++){
                  mediumAsteroids.addAsteroidAt(asteroid.getX(), asteroid.getY());
               }
               asteroids.deleteSpecifiedAsteroid(asteroid);
               currAsteroidCount++;
               lazers.deleteLazer(lazer);
               scoreboard.addScore(0, 0, 1, 0);
            }
         }
      }
   }
   
   public void mediumAsteroidCollisionDetect(){
      for(Lazer lazer: lazers.getLazersAsArray()){                                        
         for(Asteroid asteroid: mediumAsteroids.getAsteroidsAsArray()){                             
            if(asteroid.getHitBox().intersect(lazer.getHitbox())){
               System.out.println("Collision detected");
               for(int i = 1; i <= 2; i++){
                  smallAsteroids.addAsteroidAt(asteroid.getX(), asteroid.getY());
               }
               mediumAsteroids.deleteSpecifiedAsteroid(asteroid);
               currAsteroidCount++;
               lazers.deleteLazer(lazer);
               scoreboard.addScore(0, 1, 0, 0);
            }
         }
      }
   }
   
   public void smallAsteroidCollisionDetect(){
      for(Lazer lazer: lazers.getLazersAsArray()){                                       
         for(Asteroid asteroid: smallAsteroids.getAsteroidsAsArray()){                             
            if(asteroid.getHitBox().intersect(lazer.getHitbox())){
               System.out.println("Collision detected");
               smallAsteroids.deleteSpecifiedAsteroid(asteroid);
               currAsteroidCount--;
               lazers.deleteLazer(lazer);
               scoreboard.addScore(1, 0, 0, 0);
            }
         }
      }
   }
   
   public void alienShipCollisionDetect(){
      for(Lazer lazer: lazers.getLazersAsArray()){                                        
         for(AlienShip alienShip: alienShips.getAlienShipsAsArray()){                             
            if(alienShip.getHitBox().intersect(lazer.getHitbox())){
               System.out.println("Collision detected");
               alienShips.deleteSpecifiedAlienShip(alienShip);
               lazers.deleteLazer(lazer);
               scoreboard.addScore(0, 0, 0, 1);
            }
         }
      }
   }
   
   private void respawnAsteroids(){
      rand = new Random();
      int randNumOfAsteroids = rand.nextInt(4);
      int randNumSmallAsteroids = rand.nextInt(3);
      int randNumOfMediumAsteroids = rand.nextInt(2);
      int randNumOfAlienShips = rand.nextInt(1);
      additionalAsteroidCount++;
      for(int i = randNumOfAsteroids + additionalAsteroidCount; i > 0; i--){
         asteroids.addAsteroid();
      }
      for(int i = randNumOfMediumAsteroids; i > 0; i--){
         mediumAsteroids.addAsteroid();
      }
      for(int i = randNumSmallAsteroids; i > 0; i--){
         smallAsteroids.addAsteroid();
      }
      for(int i = randNumOfAlienShips; i > 0; i--){
         alienShips.addAlienShip();
      }
      currAsteroidCount += (randNumOfAsteroids + additionalAsteroidCount) + randNumOfMediumAsteroids + randNumSmallAsteroids;
      levelNum++;
   }
   
   private void toggleInvulnerability(){
      activatedAt = System.currentTimeMillis();
   }
   
   private boolean isInvulnerable(){
      long activeFor = System.currentTimeMillis() - activatedAt;
      return activeFor >= 0 && activeFor <= INVULNERABILITYTIMER;
   }
   
   private void respawnShip(){
      ship.set(X_DIMENSION/2, Y_DIMENSION/2, 0.0, 0.0, 270.0);
      ship.hitbox.set(ship.xPosition, ship.yPosition, 3, 3, ship.hitbox.upperLeft, ship.hitbox.upperRight, ship.hitbox.lowerRight, ship.hitbox.lowerLeft);
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
   
   public int getCurrAsteroidCount() {
      return currAsteroidCount;
   }
   
   public int getLevelNumber() {
      return levelNum;
   }
   
   public int getAdditionalAsteroidCount() {
      return additionalAsteroidCount;
   }
   
   public int getScoreInt() {
      return scoreboard.getScoreInt();
   }
   
   public String getScoreString() {
      return scoreboard.getScoreString();
   }
}
