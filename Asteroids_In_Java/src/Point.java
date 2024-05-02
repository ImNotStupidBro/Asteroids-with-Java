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
   /*
   public boolean isToLeft(Point point){
      return this.x <= point.x;
   }
   
   public boolean isToLeft(Point point1, Point point2){
      return this.x <= point1.x && this.x <= point2.x;
   }
   
   public boolean isToRight(Point point){
      return this.x >= point.x;
   }
   
   public boolean isToRight(Point point1, Point point2){
      return this.x >= point1.x && this.x >= point2.x;
   }
   
   public boolean isAbove(Point point){
      return this.y <= point.y;
   }
   
   public boolean isAbove(Point point1, Point point2){
      return this.y <= point1.y && this.y <= point2.y;
   }
   
   public boolean isBelow(Point point){
      return this.y >= point.y;
   }
   
   public boolean isBelow(Point point1, Point point2){
      return this.y >= point1.y && this.y >= point2.y;
   }
   */
   
   //The "point" parameter being referenced is the point that has the boolean applied to it.
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
