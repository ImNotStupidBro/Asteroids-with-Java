public class Ship extends InteractableObject{
  
   private Polygon shipShape; //Polygon of the ship
  
   public Ship(double x, double y, double xSpeed, double ySpeed, HitBox hitbox, Polygon theShape) {
      super(x, y, xSpeed, ySpeed, theShape, hitbox)
      this.shipShape = theShape;
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
