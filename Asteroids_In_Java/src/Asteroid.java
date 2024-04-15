import java.util.Random;
import javafx.geometry.Bounds;
import javafx.scene.shape.Polygon;
import java.util.Random;
public class Asteroid extends InteractableObject{

   private double[] XVertices;
   private double[] YVertices;
   
   public Asteroid(double x, double y, Polygon theShape, HitBox hitbox){
      super(x, y, theShape, hitbox);
   }
   
   private void generateConfiguration(double X, double Y, int SCALE) {
      Random rand = new Random();
      int randNum = rand.nextInt(2);

      if(randNum == 0){
      //New Strategy: Create templates for each configuration by referencing the position of the asteroid
      // and then drawing the vertices from it according to each vertice's disposition.
         XVertices = new double[]{X + (4.0 * SCALE), X + (4.0 * SCALE), X + (4.0 * SCALE), X + (4.0 * SCALE), 
         X + (4.0 * SCALE), X + (4.0 * SCALE), X + (4.0 * SCALE), X + (4.0 * SCALE), 
         X + (4.0 * SCALE), X + (4.0 * SCALE), X + (4.0 * SCALE), X + (4.0 * SCALE)};
         YVertices = new double[]{Y + (4.0 * SCALE), 40.0, 40.0, 46.0, 52.0, 56.0, 56.0, 50.0, 56.0, 50.0, 48.0, 46.0};
      }else if(randNum == 1){
         XVertices = new double[]{60, 64, 68, 72, 76, 72, 76, 72, 66, 64, 60, 62, 60};
         YVertices = new double[]{46.0, 40.0, 40.0, 46.0, 52.0, 56.0, 56.0, 50.0, 56.0, 50.0, 48.0, 46.0};
      }else{
         XVertices = new double[]{40.0, 46.0, 52.0, 56.0, 56.0, 52.0, 48.0, 48.0, 44.0, 40.0, 44.0, 40.0};
         YVertices = new double[]{46.0, 40.0, 40.0, 46.0, 52.0, 56.0, 56.0, 50.0, 56.0, 50.0, 48.0, 46.0};
      } 
   }
   
   private static Polygon generatePolygon(double[] VCoords){
      return new Polygon(VCoords);
   }
   
   public static Asteroid createRandomAsteroid(){
      Random rand = new Random();
      int asteroidXStartLocation = rand.nextInt(World.X_DIMENSION);
      int asteroidYStartLocation = rand.nextInt(World.Y_DIMENSION);
      Polygon asteroidPolygon = generatePolygon(asteroidVCoords);
      Bounds asteroidBox = asteroidPolygon.getBoundsInParent();
      HitBox asteroidHitbox = new HitBox(asteroidXStartLocation, asteroidYStartLocation, asteroidBox.getWidth(), asteroidBox.getHeight());
      Asteroid asteroid = new Asteroid(asteroidXStartLocation, asteroidYStartLocation, asteroidPolygon, asteroidHitbox);
      asteroid.generateConfiguration();
      return asteroid;
   }
   
   public double[] getVerticies(){ return verticeCoordinates; }  
}