import java.util.Random;
import javafx.geometry.Bounds;
import javafx.scene.shape.Polygon;
import java.util.Random;
public class Asteroid extends InteractableObject{

   private static final double MAX_ASTEROID_SPEED = 30.0;
   private int configNumber;
   private int id;
   
   public Asteroid(double x, double y, double dx, double dy, double degrees, HitBox hitbox, int newID){
      super(x, y, dx, dy, degrees, hitbox);
      id = newID;
   }
   
   public void move(long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension) {
      xPosition += xSpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      yPosition += ySpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      
      //Keep object on the torus
      if (xPosition < 0) { // moving in the negative x direction
         xPosition = worldXDimension + xPosition % worldXDimension;
      } else if (xPosition >= worldXDimension) {
         xPosition = xPosition % worldXDimension;
      }
      if (yPosition < 0) { // moving in the negative y direction
         yPosition = worldYDimension + yPosition % worldYDimension;
      } else if (yPosition >= worldYDimension) {
         yPosition = yPosition % worldYDimension;
      }
   }
   
   private void generateConfiguration() {
      Random rand = new Random();
      configNumber = rand.nextInt(3);
   }
   
   public static Asteroid createRandomAsteroid(){
      Random rand = new Random();
      double asteroidXStartLocation = rand.nextInt(World.X_DIMENSION);
      double asteroidYStartLocation = rand.nextInt(World.Y_DIMENSION);
      double asteroidXSpeed = (rand.nextDouble() - 0.5) * MAX_ASTEROID_SPEED;
      double asteroidYSpeed = (rand.nextDouble() - 0.5) * MAX_ASTEROID_SPEED;
      double asteroidDirection = 360 * rand.nextDouble();
      HitBox asteroidHitbox = new HitBox(asteroidXStartLocation, asteroidYStartLocation, 80, 80);
      int asteroidID = rand.nextInt(1000);
      Asteroid asteroid = new Asteroid(asteroidXStartLocation, asteroidYStartLocation, asteroidXSpeed, asteroidYSpeed, asteroidDirection, asteroidHitbox, asteroidID);
      asteroid.generateConfiguration();
      return asteroid;
   }
   
   public static Asteroid createAsteroid(double initialX, double initialY, int hitboxWidth, int hitboxHeight){
      Random rand = new Random();
      double asteroidXStartLocation = initialX;
      double asteroidYStartLocation = initialY;
      double asteroidXSpeed = (rand.nextDouble() - 0.5) * MAX_ASTEROID_SPEED;
      double asteroidYSpeed = (rand.nextDouble() - 0.5) * MAX_ASTEROID_SPEED;
      double asteroidDirection = 360 * rand.nextDouble();
      HitBox asteroidHitbox = new HitBox(asteroidXStartLocation, asteroidYStartLocation, hitboxWidth, hitboxHeight);
      int asteroidID = rand.nextInt(1000);
      Asteroid asteroid = new Asteroid(asteroidXStartLocation, asteroidYStartLocation, asteroidXSpeed, asteroidYSpeed, asteroidDirection, asteroidHitbox, asteroidID);
      asteroid.generateConfiguration();
      return asteroid;
   }
   
   public int getConfigNumber(){ return configNumber; }
   public int getID(){ return id; }
}