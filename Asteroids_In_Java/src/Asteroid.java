import java.util.Random;
import javafx.geometry.Bounds;
import javafx.scene.shape.Polygon;
import java.util.Random;
public class Asteroid extends InteractableObject{

   private static final double MAX_ASTEROID_SPEED = 30.0;
   private int configNumber;
   private int id;
   
   public Asteroid(double x, double y, double dx, double dy, double degrees, HitBox hitbox, int ID){
      super(x, y, dx, dy, degrees, hitbox);
      id = ID;
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
      Point upperLeft = new Point(asteroidXStartLocation-4,(asteroidYStartLocation+4)); 
      Point upperRight = new Point((asteroidXStartLocation+4),(asteroidYStartLocation+4));
      Point lowerLeft = new Point(asteroidXStartLocation-4,asteroidYStartLocation-4);
      Point lowerRight = new Point(asteroidXStartLocation+4,asteroidYStartLocation-4);
      HitBox asteroidHitbox = new HitBox(asteroidXStartLocation, asteroidYStartLocation, 8, 8, upperLeft, upperRight, lowerRight, lowerLeft);
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
      Point upperLeft = new Point((asteroidXStartLocation-2),(asteroidYStartLocation+2)); 
      Point upperRight = new Point((asteroidXStartLocation+2),(asteroidYStartLocation+2));
      Point lowerLeft = new Point((asteroidXStartLocation-2),(asteroidYStartLocation-2));
      Point lowerRight = new Point((asteroidXStartLocation+2),(asteroidYStartLocation-2));
      HitBox asteroidHitbox = new HitBox(asteroidXStartLocation, asteroidYStartLocation, 4, 4, upperLeft, upperRight, lowerLeft, lowerRight);
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
      Point upperLeft = new Point((asteroidXStartLocation-1),(asteroidYStartLocation+1)); 
      Point upperRight = new Point((asteroidXStartLocation+1),(asteroidYStartLocation+1));
      Point lowerLeft = new Point((asteroidXStartLocation-1),(asteroidYStartLocation-1));
      Point lowerRight = new Point((asteroidXStartLocation+1),(asteroidYStartLocation-1));
      HitBox asteroidHitbox = new HitBox(asteroidXStartLocation, asteroidYStartLocation, 2, 2, upperLeft, upperRight, lowerLeft, lowerRight);
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
      Point upperLeft = new Point((asteroidXStartLocation-(hitboxWidth/(2*10))),(asteroidYStartLocation+(hitboxHeight/(2*10)))); 
      Point upperRight = new Point((asteroidXStartLocation+(hitboxWidth/(2*10))),(asteroidYStartLocation+(hitboxHeight/(2*10))));
      Point lowerLeft = new Point((asteroidXStartLocation-(hitboxWidth/(2*10))),(asteroidYStartLocation-(hitboxHeight/(2*10))));
      Point lowerRight = new Point((asteroidXStartLocation+(hitboxWidth/(2*10))),(asteroidYStartLocation-(hitboxHeight/(2*10))));
      HitBox asteroidHitbox = new HitBox(asteroidXStartLocation, asteroidYStartLocation, hitboxWidth/10, hitboxHeight/10, upperLeft, upperRight, lowerLeft, lowerRight);
      int asteroidID = rand.nextInt(1000);
      Asteroid asteroid = new Asteroid(asteroidXStartLocation, asteroidYStartLocation, asteroidXSpeed, asteroidYSpeed, asteroidDirection, asteroidHitbox, asteroidID);
      asteroid.generateConfiguration();
      return asteroid;
   }
   
   public int getConfigNumber(){ return configNumber; }
   public int getID(){ return id; }
}