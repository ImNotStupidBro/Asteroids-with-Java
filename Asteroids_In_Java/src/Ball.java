/**
 * Describes the physics of a ball.
 */
public class Ball {
   private double x; // meters
   private double y; // meters
   private double radius; // meters
   private double xSpeed; // meters/second
   private double ySpeed; // meters/second

   public Ball(double x, double y, double radius, double xSpeed, double ySpeed) {
      this.x = x; // meters
      this.y = y; // meters
      this.radius = radius; //meters
      this.xSpeed = xSpeed; // meters/second
      this.ySpeed = ySpeed; // meter/second
   }
   
   /** Moves the ball based on the elapsed time and the velocity. */
   public void move(long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension) { 
      x += xSpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      y += ySpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      
      //Keep ball on the torus
      if (x < 0) { // moving in the negative x direction
         x = worldXDimension + x % worldXDimension;
      } else if (x >= worldXDimension) {
         x = x % worldXDimension;
      }
      if (y < 0) { // moving in the negative y direction
         y = worldYDimension + y % worldYDimension;
      } else if (y >= worldYDimension) {
         y = y % worldYDimension;
      }
   }
   
   public void up() {
      ySpeed += 1.0;
   }
   
   public void down() {
      ySpeed -= 1.0;
   }
   
   public void right() {
       xSpeed += 1.0;
   }
   
   public void left() {
      xSpeed -= 1.0;
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
