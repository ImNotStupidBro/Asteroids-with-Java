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
      
      this.hitbox.moveHitbox(xSpeed, ySpeed, elapsedTimeInNanoseconds, worldXDimension, worldYDimension);
      
      //Keep object on the torus
      if (xPosition < 0 - (this.hitbox.getWidth() / 4)) { // moving in the negative x direction
         xPosition = worldXDimension + xPosition % worldXDimension + (this.hitbox.getWidth() / 4);
      } else if (xPosition >= worldXDimension + (this.hitbox.getWidth() / 4)) {
         xPosition = xPosition % worldXDimension - (this.hitbox.getWidth() / 4);
      }
      if (yPosition < 0 - (this.hitbox.getHeight() / 4)) { // moving in the negative y direction
         yPosition = worldYDimension + yPosition % worldYDimension + (this.hitbox.getHeight() / 4);
      } else if (yPosition >= worldYDimension + (this.hitbox.getHeight() / 4)) {
         yPosition = yPosition % worldYDimension - (this.hitbox.getHeight() / 4);
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
      Point upperLeft = new Point((asteroidXStartLocation-40),(asteroidYStartLocation+40)); 
      Point upperRight = new Point((asteroidXStartLocation+40),(asteroidYStartLocation+40));
      Point lowerLeft = new Point((asteroidXStartLocation-40),(asteroidYStartLocation-40));
      Point lowerRight = new Point((asteroidXStartLocation+40),(asteroidYStartLocation-40));
      HitBox asteroidHitbox = new HitBox(asteroidXStartLocation, asteroidYStartLocation, 80, 80, upperLeft, upperRight, lowerLeft, lowerRight);
      /*
      System.out.println(asteroidXStartLocation);
      System.out.println(asteroidYStartLocation);
      System.out.println(upperLeft.getX() + " " + upperLeft.getY());
      System.out.println(upperRight.getX() + " " + upperRight.getY());
      System.out.println(lowerLeft.getX() + " " + lowerLeft.getY());
      System.out.println(lowerRight.getX() + " " + lowerRight.getY());
      */
      int asteroidID = rand.nextInt(1000);
      Asteroid asteroid = new Asteroid(asteroidXStartLocation, asteroidYStartLocation, asteroidXSpeed, asteroidYSpeed, asteroidDirection, asteroidHitbox, asteroidID);
      asteroid.generateConfiguration();
      return asteroid;
   }
   
   public static Asteroid createRandomMediumAsteroid(){
      Random rand = new Random();
      double asteroidXStartLocation = rand.nextInt(World.X_DIMENSION);
      double asteroidYStartLocation = rand.nextInt(World.Y_DIMENSION);
      double asteroidXSpeed = (rand.nextDouble() - 0.5) * MAX_ASTEROID_SPEED;
      double asteroidYSpeed = (rand.nextDouble() - 0.5) * MAX_ASTEROID_SPEED;
      double asteroidDirection = 360 * rand.nextDouble();
      Point upperLeft = new Point((asteroidXStartLocation-20),(asteroidYStartLocation+20)); 
      Point upperRight = new Point((asteroidXStartLocation+20),(asteroidYStartLocation+20));
      Point lowerLeft = new Point((asteroidXStartLocation-20),(asteroidYStartLocation-20));
      Point lowerRight = new Point((asteroidXStartLocation+20),(asteroidYStartLocation-20));
      HitBox asteroidHitbox = new HitBox(asteroidXStartLocation, asteroidYStartLocation, 40, 40, upperLeft, upperRight, lowerLeft, lowerRight);
      int asteroidID = rand.nextInt(1000);
      Asteroid asteroid = new Asteroid(asteroidXStartLocation, asteroidYStartLocation, asteroidXSpeed, asteroidYSpeed, asteroidDirection, asteroidHitbox, asteroidID);
      asteroid.generateConfiguration();
      return asteroid;
   }
   
   public static Asteroid createRandomSmallAsteroid(){
      Random rand = new Random();
      double asteroidXStartLocation = rand.nextInt(World.X_DIMENSION);
      double asteroidYStartLocation = rand.nextInt(World.Y_DIMENSION);
      double asteroidXSpeed = (rand.nextDouble() - 0.5) * MAX_ASTEROID_SPEED;
      double asteroidYSpeed = (rand.nextDouble() - 0.5) * MAX_ASTEROID_SPEED;
      double asteroidDirection = 360 * rand.nextDouble();
      Point upperLeft = new Point((asteroidXStartLocation-10),(asteroidYStartLocation+10)); 
      Point upperRight = new Point((asteroidXStartLocation+10),(asteroidYStartLocation+10));
      Point lowerLeft = new Point((asteroidXStartLocation-10),(asteroidYStartLocation-10));
      Point lowerRight = new Point((asteroidXStartLocation+10),(asteroidYStartLocation-10));
      HitBox asteroidHitbox = new HitBox(asteroidXStartLocation, asteroidYStartLocation, 20, 20, upperLeft, upperRight, lowerLeft, lowerRight);
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
      Point upperLeft = new Point((asteroidXStartLocation-(hitboxWidth/2)),(asteroidYStartLocation+(hitboxHeight/2))); 
      Point upperRight = new Point((asteroidXStartLocation+(hitboxWidth/2)),(asteroidYStartLocation+(hitboxHeight/2)));
      Point lowerLeft = new Point((asteroidXStartLocation-(hitboxWidth/2)),(asteroidYStartLocation-(hitboxHeight/2)));
      Point lowerRight = new Point((asteroidXStartLocation+(hitboxWidth/2)),(asteroidYStartLocation-(hitboxHeight/2)));
      HitBox asteroidHitbox = new HitBox(asteroidXStartLocation, asteroidYStartLocation, hitboxWidth, hitboxHeight, upperLeft, upperRight, lowerLeft, lowerRight);
      int asteroidID = rand.nextInt(1000);
      Asteroid asteroid = new Asteroid(asteroidXStartLocation, asteroidYStartLocation, asteroidXSpeed, asteroidYSpeed, asteroidDirection, asteroidHitbox, asteroidID);
      asteroid.generateConfiguration();
      return asteroid;
   }
   
   public int getConfigNumber(){ return configNumber; }
   public int getID(){ return id; }
}