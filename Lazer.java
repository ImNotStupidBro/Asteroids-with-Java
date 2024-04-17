public class Lazer{
   private double x; // meters
   private double y; // meters
   private static double radius; // meters 
   private static double xSpeed; // meters/second
   private static double ySpeed; // meters/second
   private static double LazerDegrees;
   private HitBox hitbox; // Collision detection tool

   public Lazer(double x, double y, double radius, double xSpeed, double ySpeed, HitBox hitbox, double LazerDegrees) {
      this.x = x; // meters
      this.y = y; // meters
      this.radius = radius; //meters
      this.xSpeed = xSpeed; // meters/second
      this.ySpeed = ySpeed; // meter/second
      this.hitbox = hitbox; // Collision-detection tool
      this.LazerDegrees = LazerDegrees;
   }
   
   /** Moves the lazer based on the elapsed time and the velocity. */
   public void move(long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension) { 
      //taken from move function in ship
      xSpeed = 50*(Math.cos(Math.toRadians(LazerDegrees)));
      ySpeed = 50*(Math.sin(Math.toRadians(LazerDegrees)));
      //taken from original move function in ball
      x += xSpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      y += ySpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
     
   }
 
   public static Lazer createLazer(double ShipX, double ShipY, double lazerDegrees) {
      double lazerXStartLocation = ShipX; //ships x position
      double lazerYStartLocation = ShipY; //ships y position
      radius = 5; //this also corresponds for hitboxes, DO NOT CHANGE
      //double lazerRadius = radius;
      //double lazerDegrees = ShipDirection;
      //double lazerXSpeed = xSpeed;
      //double lazerYSpeed = ySpeed;
      HitBox lazerHitbox = new HitBox(lazerXStartLocation, lazerYStartLocation, (radius * 2), (radius * 2));
      Lazer lazer = new Lazer(lazerXStartLocation, lazerYStartLocation, radius, xSpeed, ySpeed, lazerHitbox, lazerDegrees);
      return lazer;
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
   
   public HitBox getHitbox() {
      return hitbox;
   }
}
   
   