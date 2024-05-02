/*
This class was made with help from this website:
https://www.stefanonsoftware.com/post/gamedev-hit-detection

@author Christian Blair
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
   
   public void offset(double dx, double dy){
      x += dx;
      y += dy;
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
   
   public void moveHitbox(double x, double y, double width, double height){
      this.x = x;
      this.y = y;
      upperLeft.movePoint(x-(0.5*this.width),y+(0.5*this.height));
      upperRight.movePoint(x+(0.5*this.width),y+(0.5*this.height));
      lowerLeft.movePoint(x-(0.5*this.width),y-(0.5*this.height));
      lowerRight.movePoint(x+(0.5*this.width),y-(0.5*this.height));
   };

   // Check whether or not the hitboxes of one object collides with another.
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
   
   //Get the hitboxes' width and/or height
   public double getWidth(){ return width; }
   public double getHeight(){ return height; }
   
   //get functions for each corner of the hotboxes (used for collisions)
   public Point getUpperRight(){ return upperRight; }
   public Point getUpperLeft(){ return upperLeft; }
   public Point getLowerRight(){ return lowerRight; }
   public Point getLowerLeft(){ return lowerLeft; }
   
   //these methods are just used for testing
   public void printLowerLeft(){System.out.println("("+lowerLeft.getX()+","+lowerLeft.getY()+")");};
   public void printLowerRight(){System.out.println("("+lowerRight.getX()+","+lowerRight.getY()+")");};
   public void printUpperRight(){System.out.println("("+upperRight.getX()+","+upperRight.getY()+")");};
    public void printUpperLeft(){System.out.println("("+upperLeft.getX()+","+upperRight.getY()+")");};
   public void printHitbox(){System.out.println("("+x+","+y+")");};
}