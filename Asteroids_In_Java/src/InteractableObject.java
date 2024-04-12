import java.awt.Polygon;

public class InteractableObject{
   private double x; // meters
   private double y; // meters
   private double xSpeed; // meters/second
   private double ySpeed; // meters/second
   private HitBox hitbox; // Collision detection tool

   public InteractableObject(double x, double y, double xSpeed, double ySpeed, HitBox hitbox){
      this.x = x; // meters
      this.y = y; // meters
      this.xSpeed = xSpeed; // meters/second
      this.ySpeed = ySpeed; // meter/second
      this.hitbox = hitbox; // Collision-detection tool
   }
   
   public void move(long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension) { 
      x += xSpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      y += ySpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      
      //Keep object on the torus
      if (x < 0 - (this.getHitbox().getWidth() * 2)) { // moving in the negative x direction
         x = worldXDimension + x % worldXDimension + (this.getHitbox().getWidth() * 3);
      } else if (x >= worldXDimension + (this.getHitbox().getWidth() * 2)) {
         x = x % worldXDimension - (this.getHitbox().getWidth() * 3);
      }
      if (y < 0 - (this.getHitbox().getHeight() * 2)) { // moving in the negative y direction
         y = worldYDimension + y % worldYDimension + (this.getHitbox().getHeight() * 3);
      } else if (y >= worldYDimension + (this.getHitbox().getHeight() * 2)) {
         y = y % worldYDimension - (this.getHitbox().getHeight() * 3);
      }
   }
   
   public double getX() {
      return x;
   }

   public double getY() {
      return y;
   }
   
   public double getXSpeed() {
      return xSpeed;
   }

   public double getYSpeed() {
      return ySpeed;
   }
   
   public HitBox getHitbox() {
      return hitbox;
   }
}