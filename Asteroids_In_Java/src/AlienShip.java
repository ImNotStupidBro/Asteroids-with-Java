import java.util.Random;
public class AlienShip extends InteractableObject{

   private static final double MAX_ALIENSHIP_SPEED = 30.0;
   private int id;
   
   public AlienShip(double x, double y, double dx, double dy, double degrees, HitBox hitbox, int newID){
      super(x, y, dx, dy, degrees, hitbox);
      id = newID;
   }
   
   public void move(long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension) {
      xPosition += xSpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      yPosition += ySpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      
      this.hitbox.moveHitbox(xSpeed, ySpeed, elapsedTimeInNanoseconds, worldXDimension, worldYDimension);
      
      //Keep object on the torus
      if (xPosition < 0 - (this.hitbox.getWidth() / 4)) { // moving in the negative x direction
         xPosition = worldXDimension + xPosition % worldXDimension + (this.hitbox.getWidth() / 4);
      } else if (xPosition >= worldXDimension + (this.hitbox.getWidth() / 4)) {
         xPosition = xPosition % worldXDimension - (this.hitbox.getWidth() / 4);
      }
      if (yPosition < 0 - (this.hitbox.getHeight() / 4)) { // moving in the negative y direction
         yPosition = worldYDimension + yPosition % worldYDimension + (this.hitbox.getHeight() / 4);
      } else if (yPosition >= worldYDimension + (this.hitbox.getHeight() / 4)) {
         yPosition = yPosition % worldYDimension - (this.hitbox.getHeight() / 4);
      }
   }
   
   public static AlienShip createRandomAlienShip(){
      Random rand = new Random();
      double alienShipXStartLocation = rand.nextInt(World.X_DIMENSION);
      double alienShipYStartLocation = rand.nextInt(World.Y_DIMENSION);
      double alienShipXSpeed = MAX_ALIENSHIP_SPEED;
      double alienShipYSpeed = MAX_ALIENSHIP_SPEED;
      double alienShipDirection = 360 * rand.nextDouble();
      Point alienShipUpperLeft = new Point(alienShipXStartLocation-40,alienShipYStartLocation+20); 
      Point alienShipUpperRight = new Point(alienShipXStartLocation+40,alienShipYStartLocation+20);
      Point alienShipLowerLeft = new Point(alienShipXStartLocation-40,alienShipYStartLocation-20);
      Point alienShipLowerRight = new Point(alienShipXStartLocation+40,alienShipYStartLocation-20);
      HitBox alienShipHitbox = new HitBox(alienShipXStartLocation, alienShipYStartLocation, 80, 40, alienShipUpperLeft, alienShipUpperRight, alienShipLowerLeft, alienShipLowerRight);
      int alienShipID = rand.nextInt(1000);
      AlienShip alienShip = new AlienShip(alienShipXStartLocation, alienShipYStartLocation, alienShipXSpeed, alienShipYSpeed, alienShipDirection, alienShipHitbox, alienShipID);
      return alienShip;
   }

   public int getID(){ return id; }
}