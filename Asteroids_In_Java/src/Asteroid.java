import java.util.Random;
import javafx.geometry.Bounds;
import javafx.scene.shape.Polygon;
import java.util.Random;
public class Asteroid extends InteractableObject{

   private static double verticeCoordinates[];
   
   public Asteroid(double x, double y, Polygon theShape, HitBox hitbox, double[] VCoords){
      super(x, y, theShape, hitbox);
      verticeCoordinates = VCoords;
   }
   
   private static double[] generateConfiguration() {
      Random rand = new Random();
      int randNum = rand.nextInt(2);

      if(randNum == 0){
         return new double[]{
         40.0, 46.0, 
         46.0, 40.0,
         52.0, 40.0,
         56.0, 46.0,
         56.0, 52.0,
         52.0, 56.0,
         48.0, 56.0,
         48.0, 50.0,
         44.0, 56.0,
         40.0, 50.0,
         44.0, 48.0,
         40.0, 46.0};
      }else if(randNum == 1){
         return new double[]{
         60.0, 46.0, 
         64.0, 40.0,
         68.0, 42.0,
         72.0, 40.0,
         76.0, 44.0,
         72.0, 46.0,
         76.0, 50.0,
         72.0, 56.0,
         66.0, 54.0,
         64.0, 56.0,
         60.0, 52.0,
         62.0, 48.0,
         60.0, 44.0};
      }else{
         return new double[] {
         80.0, 44.0, 
         86.0, 44.0,
         84.0, 40.0,
         90.0, 40.0,
         96.0, 44.0,
         96.0, 46.0,
         90.0, 48.0,
         96.0, 50.0,
         92.0, 56.0,
         90.0, 54.0,
         82.0, 56.0,
         80.0, 50.0,
         80.0, 44.0};
      } 
   }
   
   private static Polygon generatePolygon(double[] VCoords){
      return new Polygon(VCoords);
   }
   
   public static Asteroid createRandomAsteroid(){
      Random rand = new Random();
      int asteroidXStartLocation = rand.nextInt(World.X_DIMENSION);
      int asteroidYStartLocation = rand.nextInt(World.Y_DIMENSION);
      double[] asteroidVCoords = generateConfiguration();
      Polygon asteroidPolygon = generatePolygon(asteroidVCoords);
      Bounds asteroidBox = asteroidPolygon.getBoundsInParent();
      HitBox asteroidHitbox = new HitBox(asteroidXStartLocation, asteroidYStartLocation, asteroidBox.getWidth(), asteroidBox.getHeight());
      Asteroid asteroid = new Asteroid(asteroidXStartLocation, asteroidYStartLocation, asteroidPolygon, asteroidHitbox, asteroidVCoords);
      return asteroid;
   }
   
   public double[] getVerticies(){ return verticeCoordinates; }  
}