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