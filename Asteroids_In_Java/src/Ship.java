public class Ship extends InteractableObject{
   private final double MAX_SHIP_SPEED = 30.0;
   public boolean isAccelerating;
   
   public Ship(double x, double y, double dx, double dy, double degrees, HitBox hitbox) {
      super(x, y, dx, dy, degrees, hitbox);
      isAccelerating = false;
   }
   
   public void move(long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension) {
      xPosition += xSpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      yPosition += ySpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      
      decelerate();
      
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
         if(xSpeed > MAX_SHIP_SPEED){
            xSpeed = MAX_SHIP_SPEED;
         }
         if(ySpeed > MAX_SHIP_SPEED){
            ySpeed = MAX_SHIP_SPEED;
         }
         
         xSpeed += Math.cos(Math.toRadians(this.direction));
         ySpeed += Math.sin(Math.toRadians(this.direction));
   }
   
   public void decelerate() {
      if(!isAccelerating){
         xSpeed *= 0.99;
         ySpeed *= 0.99;
         if(xSpeed < 0.0){
            xSpeed = 0.0;
         }
         if(ySpeed < 0.0){
            ySpeed = 0.0;
         }
      }
   }
   
   public void set(double X, double Y, double XSpeed, double YSpeed, double Direction){
      xPosition = X;
      yPosition = Y;
      xSpeed = XSpeed;
      ySpeed = YSpeed;
      direction = Direction;
   }
}
