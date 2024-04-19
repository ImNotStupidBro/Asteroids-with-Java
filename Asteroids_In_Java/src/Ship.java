public class Ship extends InteractableObject{
/*
   private final double[] SHIPVERTICECOORDINATES = {
      116.0, 80.0,
      100.0, 128.0,
      104.0, 116.0,
      128.0, 116.0,
      132.0, 128.0,
      116.0, 80.0
   };
*/
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
   
   public void turnRight() {
      if(direction < (0)){
         direction += 360.0;
      }
      this.direction -= 5.0;
   }
   
   public void turnLeft() {
      if(direction > 360){
         direction -= 360.0;
      }
      this.direction += 5.0;;
   }
   
   public void accelerate() {
      xSpeed += Math.cos(Math.toRadians(this.direction));
      ySpeed += Math.sin(Math.toRadians(this.direction));
   }
   
   private void applyDrag() {
      xSpeed -= xSpeed * 0.5;
      ySpeed -= ySpeed * 0.5;
   }
}
