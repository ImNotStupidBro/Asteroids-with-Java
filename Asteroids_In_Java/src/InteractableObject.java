public abstract class InteractableObject{
   
   public double xPosition;
   public double yPosition;
   public double xSpeed;
   public double ySpeed;
   private double direction;
   private HitBox hitbox; // Collision detection tool

   public InteractableObject(double x, double y, double dx, double dy, double deg, HitBox hitbox){
      this.xPosition = x;
      this.yPosition = y;
      this.xSpeed = dx;
      this.ySpeed = dy;
      this.direction = deg;
      this.hitbox = hitbox; // Collision-detection tool
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
      if(direction <= (0 - 5)){
         direction = 355.0;
      }
      this.direction -= 5.0;
   }
   
   public void turnLeft() {
      if(direction >= 365){
         direction = 5.0;
      }
      this.direction += 5.0;;
   }
   
   public double getX() { return xPosition; }
   public double getY() { return yPosition; }
   public double getXSpeed() { return xSpeed; }
   public double getYSpeed() { return ySpeed; }
   public HitBox getHitBox(){ return hitbox; }
   public double getDirection() { return direction; }
}