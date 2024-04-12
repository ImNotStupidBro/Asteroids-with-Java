public class Ship extends InteractableObject{
  
   private Polygon shipShape; //Polygon of the ship
  
   public Ship(double x, double y, double xSpeed, double ySpeed, HitBox hitbox, Polygon theShape) {
      super(x, y, xSpeed, ySpeed, theShape, hitbox)
      this.shipShape = theShape;
   }
   
   public void move(long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension) { 
      x += xSpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      y += ySpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      
      //Keep ship on the torus
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
   
   public Polygon getPolygon() {
      return shipShape;
   }
}
