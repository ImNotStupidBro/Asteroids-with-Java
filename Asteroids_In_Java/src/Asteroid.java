import java.util.Random;
import javafx.geometry.Bounds;
import javafx.scene.shape.Polygon;
import java.util.Random;
public class Asteroid extends InteractableObject{

   private static final double MAX_ASTEROID_SPEED = 30.0;
   private int configNumber;
   /*
   private double[] XVertices;
   private double[] YVertices;
   private int numOfVertices;
   */
   
   public Asteroid(double x, double y, double dx, double dy, double degrees, HitBox hitbox){
      super(x, y, dx, dy, degrees, hitbox);
   }
   
   private void generateConfiguration() {
      Random rand = new Random();
      configNumber = rand.nextInt(3);
      /*
      if(randNum == 0){
         XVertices = new double[]{
         this.getX() - (4.0 * SCALE), this.getX() - (1.0 * SCALE), this.getX() + (2.0 * SCALE), this.getX() + (4.0 * SCALE), 
         this.getX() + (4.0 * SCALE), this.getX() + (2.0 * SCALE), this.getX() + (0.0 * SCALE), this.getX() + (0.0 * SCALE), 
         this.getX() - (2.0 * SCALE), this.getX() - (4.0 * SCALE), this.getX() + (2.0 * SCALE), this.getX() - (4.0 * SCALE)};
         
         YVertices = new double[]{
         this.getY() + (1.0 * SCALE), this.getY() + (4.0 * SCALE), this.getY() + (4.0 * SCALE), this.getY() + (1.0 * SCALE),
         this.getY() - (2.0 * SCALE), this.getY() - (4.0 * SCALE), this.getY() - (4.0 * SCALE), this.getY() - (1.0 * SCALE),
         this.getY() + (4.0 * SCALE), this.getY() - (1.0 * SCALE), this.getY() + (0.0 * SCALE), this.getY() + (1.0 * SCALE)};
         numOfVertices = 12;
      }else if(randNum == 1){
         XVertices = new double[]{
         this.getX() - (3.0 * SCALE), this.getX() - (4.0 * SCALE), this.getX() - (2.0 * SCALE), this.getX() + (0.0 * SCALE), 
         this.getX() + (2.0 * SCALE), this.getX() + (4.0 * SCALE), this.getX() + (2.0 * SCALE), this.getX() + (4.0 * SCALE), 
         this.getX() + (2.0 * SCALE), this.getX() - (1.0 * SCALE), this.getX() - (2.0 * SCALE), this.getX() - (4.0 * SCALE),
         this.getX() - (3.0 * SCALE)};
         
         YVertices = new double[]{
         this.getY() + (0.0 * SCALE), this.getY() + (2.0 * SCALE), this.getY() + (4.0 * SCALE), this.getY() + (3.0 * SCALE),
         this.getY() + (4.0 * SCALE), this.getY() + (2.0 * SCALE), this.getY() + (1.0 * SCALE), this.getY() - (1.0 * SCALE),
         this.getY() - (4.0 * SCALE), this.getY() - (3.0 * SCALE), this.getY() - (4.0 * SCALE), this.getY() + (2.0 * SCALE),
         this.getY() + (0.0 * SCALE)};
         numOfVertices = 13;
      }else{
         XVertices = new double[]{
         this.getX() - (4.0 * SCALE), this.getX() - (4.0 * SCALE), this.getX() - (1.0 * SCALE), this.getX() - (2.0 * SCALE), 
         this.getX() + (1.0 * SCALE), this.getX() + (4.0 * SCALE), this.getX() + (4.0 * SCALE), this.getX() + (1.0 * SCALE), 
         this.getX() + (4.0 * SCALE), this.getX() + (2.0 * SCALE), this.getX() + (1.0 * SCALE), this.getX() - (3.0 * SCALE),
         this.getX() - (4.0 * SCALE)};
         
         YVertices = new double[]{
         this.getY() - (1.0 * SCALE), this.getY() + (2.0 * SCALE), this.getY() + (2.0 * SCALE), this.getY() + (4.0 * SCALE),
         this.getY() + (4.0 * SCALE), this.getY() + (2.0 * SCALE), this.getY() + (1.0 * SCALE), this.getY() - (0.0 * SCALE),
         this.getY() - (1.0 * SCALE), this.getY() - (4.0 * SCALE), this.getY() - (3.0 * SCALE), this.getY() - (4.0 * SCALE),
         this.getY() - (1.0 * SCALE)};
         numOfVertices = 13;
      }
      */
      
   }
   
   public static Asteroid createRandomAsteroid(){
      Random rand = new Random();
      double asteroidXStartLocation = rand.nextInt(World.X_DIMENSION);
      double asteroidYStartLocation = rand.nextInt(World.Y_DIMENSION);
      double asteroidXSpeed = (rand.nextDouble() - 0.5) * MAX_ASTEROID_SPEED;
      double asteroidYSpeed = (rand.nextDouble() - 0.5) * MAX_ASTEROID_SPEED;
      double asteroidDirection = 360 * rand.nextDouble();
      HitBox asteroidHitbox = new HitBox(asteroidXStartLocation, asteroidYStartLocation, 80, 80);
      Asteroid asteroid = new Asteroid(asteroidXStartLocation, asteroidYStartLocation, asteroidXSpeed, asteroidYSpeed, asteroidDirection, asteroidHitbox);
      asteroid.generateConfiguration();
      return asteroid;
   }
   /*
   public double[] getXVertices(){ return XVertices; }  
   public double[] getYVertices(){ return YVertices; }
   public int getNumOfVertices(){ return numOfVertices; } 
   */
   public int getConfigNumber(){ return configNumber; }
}