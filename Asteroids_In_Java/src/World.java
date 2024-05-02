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
   private Score scoreboard;
   public static final int X_DIMENSION = 100; // meters
   public static final int Y_DIMENSION = 70; // meters
   private final double MAX_SHIP_SPEED = 30.0;
   private final int NUMBER_OF_ASTEROIDS = 5;
   
   public World() {
      //Create the score board
      scoreboard = new Score();
      
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
      double shipWidth = 2;
      double shipHeight = 4;
      Point upperLeft = new Point(0,0); 
      Point upperRight = new Point(0,0);
      Point lowerLeft = new Point(0,0);
      Point lowerRight = new Point(0,0);
      HitBox shipHitbox = new HitBox(0,0,0,0, upperLeft, upperRight, lowerLeft, lowerRight);
      Point shipUpperLeft = new Point(shipXStartLocation-(0.5*shipWidth),shipYStartLocation+(0.5*shipHeight)); 
      Point shipUpperRight = new Point(shipXStartLocation+(0.5*shipWidth),shipYStartLocation+(0.5*shipHeight));
      Point shipLowerLeft = new Point(shipXStartLocation-(0.5*shipWidth),shipYStartLocation-(0.5*shipHeight));
      Point shipLowerRight = new Point(shipXStartLocation+(0.5*shipWidth),shipYStartLocation-(0.5*shipHeight));
      shipHitbox.set(shipXStartLocation, shipYStartLocation, 4, 4, shipUpperLeft, shipUpperRight, shipLowerRight, shipLowerLeft);
      
      ship = new Ship(shipXStartLocation, shipYStartLocation, shipXSpeed, shipYSpeed, shipDirection, shipHitbox);
      
      
   }
   
   /** Runs the physics of the world. */
   public void run(long elapsedTimeInNanoseconds) {
      asteroids.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension(),8,8);
      ship.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension(),2,4);
      lazers.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension(), 0.5, 0.5);
      collisionDetect();
   }

   public void collisionDetect(){
      //part 1 (asteroid and ship collision)
      for(Asteroid asteroid: asteroids.getAsteroidsAsArray()){                   
         if(asteroid.getHitBox().intersect(ship.getHitBox())){    
            System.out.println("Collision detected");
         }
      }
      
      //part 2 (lazer and ship collision)
      for(Lazer lazer: lazers.getLazersAsArray()){  ;                                      
         for(Asteroid asteroid: asteroids.getAsteroidsAsArray()){                             
            if(asteroid.getHitBox().intersect(lazer.getHitbox())){
               System.out.println("Boom!");
               asteroids.deleteSpecifiedAsteroid(asteroid);
               scoreboard.addScore(1, 0, 0, 0);
               lazers.deleteLazer(lazer);
            }
         }
      }
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
   
   public int getScoreInt() {
      return scoreboard.getScoreInt();
   }
   
   public String getScoreString() {
      return scoreboard.getScoreString();
   }
}
