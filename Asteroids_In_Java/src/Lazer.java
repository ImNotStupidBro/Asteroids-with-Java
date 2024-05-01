public class Lazer{
   private double x; // meters
   private double y; // meters
   private static double radius; // meters 
   private static double xSpeed; // meters/second
   private static double ySpeed; // meters/second
   private static double LazerDegrees;
   private HitBox hitbox; // Collision detection tool
   private boolean offScreen;

   public Lazer(double x, double y, double radius, double xSpeed, double ySpeed, HitBox hitbox, double LazerDegrees) {
      this.x = x; // meters
      this.y = y; // meters
      this.radius = radius; //meters
      this.xSpeed = xSpeed; // meters/second
      this.ySpeed = ySpeed; // meter/second
      this.hitbox = hitbox; // Collision-detection tool
      this.LazerDegrees = LazerDegrees;
      this.offScreen = false;
   }
   
   /** Moves the lazer based on the elapsed time and the velocity. */
   public void move(long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension) { 
      //taken from move function in ship
      xSpeed = 50*(Math.sin(Math.toRadians(LazerDegrees)));
      ySpeed = 50*(Math.cos(Math.toRadians(LazerDegrees)));
      //taken from original move function in ball
      x += xSpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      y += ySpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      //move the corresponding hitbox
      this.hitbox.moveHitbox(xSpeed, ySpeed, elapsedTimeInNanoseconds, worldXDimension, worldYDimension);
      
      //Delete ball off of the torus
      if (this.x < 0 - (this.radius * 2)) { // moving in the negative x direction
         this.offScreen = true;
      } else if (this.x >= worldXDimension + (this.radius * 2)) {
         this.offScreen = true;
      }
      if (this.y < 0 - (this.radius * 2)) { // moving in the negative y direction
         this.offScreen = true;
      } else if (this.y >= worldYDimension + (this.radius * 2)) {
         this.offScreen = true;
      }
   }
 
   public static Lazer createLazer(double ShipX, double ShipY, double lazerDegrees) {
      double lazerXStartLocation = ShipX; //ships x position
      double lazerYStartLocation = ShipY; //ships y position
      radius = 5; 
      Point upperLeft = new Point((lazerXStartLocation-(radius/10)),(lazerYStartLocation+(radius/10))); 
      Point upperRight = new Point((lazerXStartLocation+(radius/10)),(lazerYStartLocation+(radius/10)));
      Point lowerLeft = new Point((lazerXStartLocation-(radius/10)),(lazerYStartLocation-(radius/10)));
      Point lowerRight = new Point((lazerXStartLocation+(radius/10)),(lazerYStartLocation-(radius/10)));
      HitBox lazerHitbox = new HitBox(lazerXStartLocation, lazerYStartLocation, (radius * 2), (radius * 2), upperLeft, upperRight, lowerLeft, lowerRight);
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
   
   public boolean isOffScreen() {
      return offScreen;
   }
}
   
   