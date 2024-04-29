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
      return this.y <= point.y && this.x <= point.x;
   }

   public boolean isBelowAndToRight(Point point) {
      return this.y <= point.y && this.x >= point.x;
   }
   
   public boolean isAboveAndToLeft(Point point) {
      return this.y >= point.y && this.x <= point.x;
   }

   public boolean isAboveAndToRight(Point point) {
      return this.y >= point.y && this.x >= point.x;
   }
   
   public double getX() {
      return x;
   }
   
   public double getY() {
      return y;
   }
}
