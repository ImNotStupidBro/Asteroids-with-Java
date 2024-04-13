import java.util.Random;
import javafx.geometry.Bounds;
import javafx.scene.shape.Polygon;
import java.util.Random;
public class Asteroid extends InteractableObject{
   
   public Asteroid(double x, double y, Polygon theShape, HitBox hitbox){
      super(x, y, theShape, hitbox);
   }
   
   private static Polygon generateConfiguration() {
      double verticeCoordinates[];
      Random rand = new Random();
      int randNum = rand.nextInt(2);
      
      if(randNum == 0){
         verticeCoordinates = new double[] {
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
         verticeCoordinates = new double[] {
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
         verticeCoordinates = new double[] {
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
      
      Polygon generatedPolygon = new Polygon(verticeCoordinates);
      return generatedPolygon;
   }
   
   public static Asteroid createRandomAsteroid(){
      Random rand = new Random();
      int asteroidXStartLocation = rand.nextInt(World.X_DIMENSION);
      int asteroidYStartLocation = rand.nextInt(World.Y_DIMENSION);
      Polygon asteroidPolygon = generateConfiguration();
      Bounds asteroidBox = asteroidPolygon.getBoundsInParent();
      HitBox asteroidHitbox = new HitBox(asteroidXStartLocation, asteroidYStartLocation, asteroidBox.getWidth(), asteroidBox.getHeight());
      Asteroid asteroid = new Asteroid(asteroidXStartLocation, asteroidYStartLocation, asteroidPolygon, asteroidHitbox);
      return asteroid;
   }
}