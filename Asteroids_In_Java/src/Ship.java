public class Ship extends InteractableObject{
   private final double MAX_SHIP_SPEED = 30.0;
   private boolean isAccelerating;
   
   public Ship(double x, double y, double dx, double dy, double degrees, HitBox hitbox) {
      super(x, y, dx, dy, degrees, hitbox);
   }
   
   public void move(long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension) {
      xPosition += xSpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      yPosition += ySpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      
      //Keep object on the torus
      if (xPosition < 0) { // moving in the negative x direction
         xPosition = worldXDimension + xPosition % worldXDimension;
      } else if (xPosition >= worldXDimension) {
         xPosition = xPosition % worldXDimension;
      }
      if (yPosition < 0) { // moving in the negative y direction
         yPosition = worldYDimension + yPosition % worldYDimension;
      } else if (yPosition >= worldYDimension) {
         yPosition = yPosition % worldYDimension;
      }
   }
   
   public void accelerate() {
      xSpeed += Math.cos(Math.toRadians(this.direction));
      ySpeed += Math.sin(Math.toRadians(this.direction));

      xSpeed *= 0.9;
      ySpeed *= 0.9;
   }
   
   public void set(double X, double Y, double XSpeed, double YSpeed, double Direction){
      xPosition = X;
      yPosition = Y;
      xSpeed = XSpeed;
      ySpeed = YSpeed;
      direction = Direction;
   }
}
