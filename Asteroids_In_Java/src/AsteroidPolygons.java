import java.awt.Polygon;
import java.util.Random;
public class AsteroidPolygons{
   
   public static Polygon generateConfiguration() {
      int[] x_points;
      int[] y_points;
      Random rand = new Random();
      int randNum = rand.nextInt(2);
      
      if(randNum == 0){
         x_points = {40, 46, 52, 56, 56, 52, 48, 48, 44, 40, 44, 40};
         y_points = {46, 40, 40, 46, 52, 56, 56, 50, 56, 50, 48, 46};
      }else if(randNum == 1){
         x_points = {60, 64, 68, 72, 76, 72, 76, 72, 66, 64, 60, 62, 60};
         y_points = {44, 40, 42, 40, 44, 46, 50, 56, 54, 56, 52, 48, 44};
      }else{
         x_points = {80, 86, 84, 90, 96, 96, 90, 96, 92, 90, 82, 80, 80};
         y_points = {44, 44, 40, 40, 44, 46, 48, 50, 56, 54, 56, 50, 44};
      }
      
      Polygon generatedPolygon = new Polygon(x_points, y_points, x_points.length);
      return generatedPolygon;
   }
}