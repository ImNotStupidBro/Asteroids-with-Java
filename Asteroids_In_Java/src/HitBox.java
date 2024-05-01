/*
This class was made with help from this website:
https://www.stefanonsoftware.com/post/gamedev-hit-detection

@author Christian Blair and Jason Zigmant
*/
public class HitBox{
   private double x, y, width, height; //x and y are at the center of each hotbox
   private Point upperLeft, upperRight, lowerRight, lowerLeft; //keeps track of each corner of the hitbox
   public HitBox(double x, double y, double width, double height, Point upperLeft, Point upperRight, Point lowerRight, Point lowerLeft){
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.upperLeft = upperLeft; 
      this.upperRight = upperRight;
      this.lowerLeft = lowerLeft;
      this.lowerRight = lowerRight;
   }
   
   //Set the hitboxes' parameters once declared.
   public void set(double x, double y, double width, double height, Point upperLeft, Point upperRight, Point lowerRight, Point lowerLeft){
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.upperLeft = upperLeft; 
      this.upperRight = upperRight;
      this.lowerLeft = lowerLeft;
      this.lowerRight = lowerRight;
   }
   
   public void moveHitbox(double dx, double dy, long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension){
      this.offset(dx,dy,elapsedTimeInNanoseconds,worldXDimension,worldYDimension);
      this.movePoint(this.upperLeft,dx,dy,elapsedTimeInNanoseconds,worldXDimension,worldYDimension);
      this.movePoint(this.upperRight,dx,dy,elapsedTimeInNanoseconds,worldXDimension,worldYDimension);
      this.movePoint(this.lowerLeft,dx,dy,elapsedTimeInNanoseconds,worldXDimension,worldYDimension);
      this.movePoint(this.lowerRight,dx,dy,elapsedTimeInNanoseconds,worldXDimension,worldYDimension);
   };
   
   private void offset(double dx, double dy, long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension){
      x += dx * elapsedTimeInNanoseconds / 1_000_000_000.0;
      y += dy * elapsedTimeInNanoseconds / 1_000_000_000.0;
      
      //Keep object on the torus
      if (x < 0 - (this.getWidth() / 8)) { // moving in the negative x direction
         x = worldXDimension + x % worldXDimension + (this.getWidth() / 8);
      } else if (x >= worldXDimension + (this.getWidth() / 8)) {
         x = x % worldXDimension - (this.getWidth() / 8);
      }
      if (y < 0 - (this.getHeight() / 8)) { // moving in the negative y direction
         y = worldYDimension + y % worldYDimension + (this.getHeight() / 8);
      } else if (y >= worldYDimension + (this.getHeight() / 8)) {
         y = y % worldYDimension - (this.getHeight() / 8);
      }
   }
   
   private void movePoint(Point point, double dx, double dy, long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension){
      point.x += dx * elapsedTimeInNanoseconds / 1_000_000_000.0;
      point.y += dy * elapsedTimeInNanoseconds / 1_000_000_000.0;
      
      //Keep object on the torus
      if (point.x < 0) { // moving in the negative x direction
         point.x = worldXDimension + point.x % worldXDimension;
      } else if (point.x >= worldXDimension) {
         point.x = point.x % worldXDimension;
      }
      if (point.y < 0) { // moving in the negative y direction
         point.y = worldYDimension + point.y % worldYDimension;
      } else if (point.y >= worldYDimension) {
         point.y = point.y % worldYDimension;
      }
   }
   
   //Check whether or not the hitboxes of one object collides with another.
   public boolean intersect(HitBox hitbox) {
      if (upperLeft.isBelowAndToRight(hitbox.upperLeft) && upperLeft.isAboveAndToLeft(hitbox.lowerRight)){
         System.out.println("collision occurred!");
         return true;
         }
      else if (upperRight.isBelowAndToLeft(hitbox.upperRight) && upperRight.isAboveAndToRight(hitbox.lowerLeft)){
         System.out.println("collision occurred!");
         return true;
         }
      else if (lowerLeft.isBelowAndToLeft(hitbox.upperRight) && lowerLeft.isAboveAndToRight(hitbox.lowerLeft)){
         System.out.println("collision occurred!");
         return true;
         }
      else if (lowerRight.isAboveAndToLeft(hitbox.lowerRight) && lowerRight.isBelowAndToRight(hitbox.upperLeft)){
         System.out.println("collision occurred!");
         return true;
         }
      else{
         return false;
      }
   }
   
   //Get the hitboxes' base parameters
   public double getX(){ return x; }
   public double getY(){ return y; }
   public double getWidth(){ return width; }
   public double getHeight(){ return height; }
   
   //get functions for each corner of the hotboxes (used for collisions)
   public Point getUpperRight(){ return upperRight; }
   public Point getUpperLeft(){ return upperLeft; }
   public Point getLowerRight(){ return lowerRight; }
   public Point getLowerLeft(){ return lowerLeft; }
   
   //these methods are just used for testing
   public void printLowerLeft(){System.out.println("("+lowerRight.getX()+","+lowerRight.getY()+")");};
   public void printHitbox(){System.out.println("("+x+","+y+")");};
}