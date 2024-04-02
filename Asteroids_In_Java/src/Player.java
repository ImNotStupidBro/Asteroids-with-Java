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
}
