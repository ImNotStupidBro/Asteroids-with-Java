public class Player {
  private double x; // meters
  private double y; // meters
  private double radius; // meters
  private double xSpeed; // meters/second
  private double ySpeed; // meters/second
  private HitBox hitbox; // Collision detection tool

   public Player(double x, double y, double radius, double xSpeed, double ySpeed, HitBox hitbox) {
      this.x = x; // meters
      this.y = y; // meters
      this.radius = radius; //meters
      this.xSpeed = xSpeed; // meters/second
      this.ySpeed = ySpeed; // meter/second
      this.hitbox = hitbox; // Collision-detection tool
   }
   public void move(long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension) { 
      x += xSpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      y += ySpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      
      //Keep ball on the torus
      if (x < 0 - (this.radius * 2)) { // moving in the negative x direction
         x = worldXDimension + x % worldXDimension + (this.radius * 3);
      } else if (x >= worldXDimension + (this.radius * 2)) {
         x = x % worldXDimension - (this.radius * 3);
      }
      if (y < 0 - (this.radius * 2)) { // moving in the negative y direction
         y = worldYDimension + y % worldYDimension + (this.radius * 3);
      } else if (y >= worldYDimension + (this.radius * 2)) {
         y = y % worldYDimension - (this.radius * 3);
      }
   }
   
   public void up() {
      if (ySpeed<31){ //the if else statement sets the max speed
         ySpeed += 3.0;
      }
      else{
      ySpeed = 30;
      }
   }
   
   public void down() {
      if (ySpeed>-31){
         ySpeed -= 3.0;
      }
      else{
      ySpeed = -30;
      }
   }
   
   public void right() {
     if (xSpeed<31){
         xSpeed += 3.0;
      }
      else{
      xSpeed = 30;
      }
   }
   
   public void left() {
      if (xSpeed>-31){
         xSpeed -= 3.0;
      }
      else{
      xSpeed = -30;
      }
   }

   public double getX() {
      return x;
   }

   public double getY() {
      return y;
   }

   public double getRadius() {
      return radius;
   }

   public double getXSpeed() {
      return xSpeed;
   }

   public double getYSpeed() {
      return ySpeed;
   }
}
