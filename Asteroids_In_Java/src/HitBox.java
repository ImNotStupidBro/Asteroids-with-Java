/*
This class was made with help from this website:
https://www.stefanonsoftware.com/post/gamedev-hit-detection

@author Christian Blair and Jason Zigmant
*/
public class HitBox{
   private double x, y, width, height; //x and y are at the center of each hotbox
   public Point upperLeft, upperRight, lowerRight, lowerLeft; //keeps track of each corner of the hitbox
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
      this.upperLeft.movePoint(x-(0.5*this.width),y+(0.5*this.height)); 
      this.upperRight.movePoint(x+(0.5*this.width),y+(0.5*this.height));
      this.lowerLeft.movePoint(x-(0.5*this.width),y-(0.5*this.height));
      this.lowerRight.movePoint(x+(0.5*this.width),y-(0.5*this.height));
   }
   
   public void moveHitbox(double x, double y, double width, double height){
      this.x = x;
      this.y = y;
      upperLeft.movePoint(x-(0.5*this.width),y+(0.5*this.height));
      upperRight.movePoint(x+(0.5*this.width),y+(0.5*this.height));
      lowerLeft.movePoint(x-(0.5*this.width),y-(0.5*this.height));
      lowerRight.movePoint(x+(0.5*this.width),y-(0.5*this.height));
   }
   
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
   
   /*
   private void movePoint(Point point, double dx, double dy, long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension){
      point.x += dx * elapsedTimeInNanoseconds / 1_000_000_000.0;
      point.y += dy * elapsedTimeInNanoseconds / 1_000_000_000.0;
      
      //Keep object on the torus
      if (point.x < 0 - (this.getWidth() / 8)) { // moving in the negative x direction
         point.x = worldXDimension + point.x % worldXDimension + (this.getWidth() / 8);
      } else if (point.x >= worldXDimension + (this.getWidth() / 8)) {
         point.x = point.x % worldXDimension - (this.getWidth() / 8);
      }
      if (point.y < 0 - (this.getHeight() / 8)) { // moving in the negative y direction
         point.y = worldYDimension + point.y % worldYDimension + (this.getHeight() / 8);
      } else if (point.y >= worldYDimension + (this.getHeight() / 8)) {
         point.y = point.y % worldYDimension - (this.getHeight() / 8);
      }
   }
   */
   
   //Check whether or not the hitboxes of one object collides with another.
   public boolean intersect(HitBox hitbox) {
      boolean upperRightIsBelowAndToLeftHitboxUpperRight = upperRight.isBelowAndToLeft(hitbox.upperRight);
      boolean upperRightIsAboveAndToRightHitboxLowerLeft = upperRight.isAboveAndToRight(hitbox.lowerLeft); 
      //System.out.println(upperRightIsBelowAndToLeftHitboxUpperRight +" "+ upperRightIsAboveAndToRightHitboxLowerLeft);
      if (upperLeft.isBelowAndToRight(hitbox.upperLeft) && upperLeft.isAboveAndToLeft(hitbox.lowerRight)) {
         return true;
      } else if (upperRightIsBelowAndToLeftHitboxUpperRight && upperRightIsAboveAndToRightHitboxLowerLeft) {
         return true;
      } else if (lowerLeft.isBelowAndToLeft(hitbox.upperRight) && lowerLeft.isAboveAndToRight(hitbox.lowerLeft)) {
         return true;
      } else if (lowerRight.isAboveAndToLeft(hitbox.lowerRight) && lowerRight.isBelowAndToRight(hitbox.upperLeft)) {
         return true;
      } else {
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