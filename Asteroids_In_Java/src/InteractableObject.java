public abstract class InteractableObject{
   
   private double xPosition;
   private double yPosition;
   private double xSpeed;
   private double ySpeed;
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
      if (xPosition < 0 - (this.getHitBox().getWidth() * 2)) { // moving in the negative x direction
         xPosition = worldXDimension + (xPosition % worldXDimension) + (this.getHitBox().getWidth() * 3);
      } else if (xPosition >= worldXDimension + (this.getHitBox().getWidth() * 2)) {
         xPosition = xPosition % worldXDimension - (this.getHitBox().getWidth() * 3);
      }
      if (yPosition < 0 - (this.getHitBox().getHeight() * 2)) { // moving in the negative y direction
         yPosition = worldYDimension + yPosition % worldYDimension + (this.getHitBox().getHeight() * 3);
      } else if (yPosition >= worldYDimension + (this.getHitBox().getHeight() * 2)) {
         yPosition = yPosition % worldYDimension - (this.getHitBox().getHeight() * 3);
      }
   }
   
   public void turnRight() {
      this.direction += 5.0;
   }
   
   public void turnLeft() {
      this.direction -= 5.0;;
   }
   
   public void accelerate() {
      xSpeed += Math.cos(Math.toRadians(this.direction));
      ySpeed += Math.sin(Math.toRadians(this.direction));
   
      xSpeed *= 0.05;
      ySpeed *= 0.05;
   }
   
   public double getX() { return xPosition; }
   public double getY() { return yPosition; }
   public double getXSpeed() { return xSpeed; }
   public double getYSpeed() { return ySpeed; }
   public HitBox getHitBox(){ return hitbox; }
   public double getDirection() { return direction; }
}