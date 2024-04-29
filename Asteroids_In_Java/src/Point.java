public class Point {
   private double x;
   private double y;

   
   public Point(double x, double y) {
      this.x = x;
      this.y = y;
   }
   
   public void movePoint(double x, double y){
      this.x = x;
      this.y = y;
   }
   
   public boolean isBelowAndToLeft(Point point) {
      return point.y <= y && point.x <= x;
   }

   public boolean isBelowAndToRight(Point point) {
      return point.y <= y && point.x >= x;
   }
   
   public boolean isAboveAndToLeft(Point point) {
      return point.y >= y && point.x <= x;
   }

   public boolean isAboveAndToRight(Point point) {
      return point.y >= y && point.x >= x;
   }
   
   public double getX() {
      return x;
   }
   
   public double getY() {
      return y;
   }
}
