public class Ship extends InteractableObject{
   private final double MAX_SHIP_SPEED = 30.0;
   private boolean isAccelerating;
   
   public Ship(double x, double y, double dx, double dy, double degrees, HitBox hitbox) {
      super(x, y, dx, dy, degrees, hitbox);
   }
   
   public void accelerate() {
      xSpeed += Math.cos(Math.toRadians(this.direction));
      ySpeed += Math.sin(Math.toRadians(this.direction));

      xSpeed *= 0.9;
      ySpeed *= 0.9;
   }
}
