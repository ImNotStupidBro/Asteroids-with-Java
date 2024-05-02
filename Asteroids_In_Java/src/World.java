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
   private Score scoreboard;
   private long activatedAt = Long.MAX_VALUE;
  
   public static final int X_DIMENSION = 100; // meters
   public static final int Y_DIMENSION = 70; // meters
   private final int NUMBER_OF_LASERS = 1;
   private final int INITIAL_NUMBER_OF_ASTEROIDS = 1;
   private long INVULNERABILITYTIMER = 5000;
   
   public World() {
      //Create Asteroid sets.
      asteroids = new AsteroidSet(0);
      mediumAsteroids = new AsteroidSetMedium(0);
      smallAsteroids = new AsteroidSetSmall(1);
      
      //Create Lazer set.
      lazers = new Lazers();
      
      //Create Alien Ship set.
      alienShips = new AlienShipSet(0);

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
      shipHitbox.set(shipXStartLocation, shipYStartLocation, 2, 4, shipUpperLeft, shipUpperRight, shipLowerLeft, shipLowerRight);
      
      ship = new Ship(shipXStartLocation, shipYStartLocation, shipXSpeed, shipYSpeed, shipDirection, shipHitbox);
      currAsteroidCount = INITIAL_NUMBER_OF_ASTEROIDS;
      additionalAsteroidCount = 0;
      
      //Create the score board
      scoreboard = new Score();
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
      
      asteroidCollisionDetect();
      mediumAsteroidCollisionDetect();
      smallAsteroidCollisionDetect();
      
      if(shipCollisionDetect()){
         respawnShip();
         toggleInvulnerability();
      }
      
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
   
   public boolean shipCollisionDetect(){
      //part 1 (asteroid and ship collision)
      boolean isIntersecting = false;
      for(Asteroid asteroid: asteroids.getAsteroidsAsArray()){
         boolean asteroidCollision = ship.getHitBox().intersect(asteroid.getHitBox());
         if(asteroidCollision && !isInvulnerable()){    
            //System.out.println("Collision detected");
            isIntersecting = true;
         }else{
            isIntersecting = false;
         }
         System.out.println(asteroid.getHitBox().intersect(ship.getHitBox()));
      }
      for(Asteroid asteroid: mediumAsteroids.getAsteroidsAsArray()){                   
         if(asteroid.getHitBox().intersect(ship.getHitBox()) && !isInvulnerable()){    
            //System.out.println("Collision detected");
            isIntersecting = true;
         }else{
            isIntersecting = false;
         }
      }
      for(Asteroid asteroid: smallAsteroids.getAsteroidsAsArray()){                   
         if(asteroid.getHitBox().intersect(ship.getHitBox()) && !isInvulnerable()){    
            //System.out.println("Collision detected");
            isIntersecting = true;
         }else{
            isIntersecting = false;
         }
      }
      for(AlienShip alienShip: alienShips.getAlienShipsAsArray()){                   
         if(alienShip.getHitBox().intersect(ship.getHitBox()) && !isInvulnerable()){    
            //System.out.println("Collision detected");
            isIntersecting = true;
         }else{
            isIntersecting = false;
         }
      }
      return isIntersecting;
   }
   
   public void asteroidCollisionDetect(){
      //part 2 (lazer and ship collision)
      for(Lazer lazer: lazers.getLazersAsArray()){                                        
         for(Asteroid asteroid: asteroids.getAsteroidsAsArray()){                             
            if(asteroid.getHitBox().intersect(lazer.getHitbox())){
               //System.out.println("Collision detected");
               for(int i = 1; i <= 2; i++){
                  mediumAsteroids.addAsteroidAt(asteroid.getX(), asteroid.getY());
               }
               asteroids.deleteSpecifiedAsteroid(asteroid.getID());
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
               //System.out.println("Collision detected");
               for(int i = 1; i <= 2; i++){
                  smallAsteroids.addAsteroidAt(asteroid.getX(), asteroid.getY());
               }
               mediumAsteroids.deleteSpecifiedAsteroid(asteroid.getID());
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
               //System.out.println("Collision detected");
               smallAsteroids.deleteSpecifiedAsteroid(asteroid.getID());
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
               //System.out.println("Collision detected");
               alienShips.deleteSpecifiedAlienShip(alienShip.getID());
               lazers.deleteLazer(lazer);
               scoreboard.addScore(0, 0, 0, 1);
            }
         }
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
   
   private void toggleInvulnerability(){
      activatedAt = System.currentTimeMillis();
   }
   
   private boolean isInvulnerable(){
      long activeFor = System.currentTimeMillis() - activatedAt;
      return activeFor >= 0 && activeFor <= INVULNERABILITYTIMER;
   }
   
   private void respawnShip(){
      ship.set(X_DIMENSION/2, Y_DIMENSION/2, 0.0, 0.0, 270.0);
      ship.hitbox.set(ship.xPosition, ship.yPosition, 40, 40, ship.hitbox.upperLeft, ship.hitbox.upperRight, ship.hitbox.lowerRight, ship.hitbox.lowerLeft);
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
   
   public int getScoreInt() {
      return scoreboard.getScoreInt();
   }
   
   public String getScoreString() {
      return scoreboard.getScoreString();
   }
}
