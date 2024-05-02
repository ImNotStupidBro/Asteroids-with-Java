public class Point {
   public double x;
   public double y;

   public Point(double x, double y) {
      this.x = x;
      this.y = y;
   }
   
   public void movePoint(double x, double y){
      this.x = x;
      this.y = y;
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
