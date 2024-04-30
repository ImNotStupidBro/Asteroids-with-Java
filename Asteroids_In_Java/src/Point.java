public class Point {
   private double x;
   private double y;

   
   public Point(double x, double y) {
      this.x = x;
      this.y = y;
   }
   
   public void movePoint(double dx, double dy, long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension){
      this.x += dx * elapsedTimeInNanoseconds / 1_000_000_000.0;
      this.y += dy * elapsedTimeInNanoseconds / 1_000_000_000.0;
      
      //Keep object on the torus
      if (x < 0) { // moving in the negative x direction
         x = worldXDimension + x % worldXDimension;
      } else if (x >= worldXDimension) {
         x = x % worldXDimension;
      }
      if (y < 0) { // moving in the negative y direction
         y = worldYDimension + y % worldYDimension;
      } else if (y >= worldYDimension) {
         y = y % worldYDimension;
      }
   }
   
   public boolean isBelowAndToLeft(Point point) {
      return point.y <= this.y && point.x <= this.x;
   }

   public boolean isBelowAndToRight(Point point) {
      return point.y <= this.y && point.x >= this.x;
   }
   
   public boolean isAboveAndToLeft(Point point) {
      return point.y >= this.y && point.x <= this.x;
   }

   public boolean isAboveAndToRight(Point point) {
      return point.y >= this.y && point.x >= this.x;
   }
   
   public double getX() {
      return x;
   }
   
   public double getY() {
      return y;
   }
}
