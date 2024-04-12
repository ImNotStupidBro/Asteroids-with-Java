import java.util.Random;
public class Asteroid extends InteractableObject{
   
   private Polygon asteroidShape;
   
   public Asteroid(double x, double y, double xSpeed, double ySpeed, HitBox hitbox, Polygon theShape){
      super(x, y, xSpeed, ySpeed, theShape, hitbox);
      this.asteroidShape = theShape;
   }
   
   public void move(long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension) { 
      x += xSpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      y += ySpeed * elapsedTimeInNanoseconds / 1_000_000_000.0;
      
      //Keep asteroid on the torus
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
   
   private static Polygon generateConfiguration() {
      int[] x_points;
      int[] y_points;
      Random rand = new Random();
      int randNum = rand.nextInt(2);
      
      if(randNum == 0){
         x_points = {40, 46, 52, 56, 56, 52, 48, 48, 44, 40, 44, 40};
         y_points = {46, 40, 40, 46, 52, 56, 56, 50, 56, 50, 48, 46};
      }else if(randNum == 1){
         x_points = {60, 64, 68, 72, 76, 72, 76, 72, 66, 64, 60, 62, 60};
         y_points = {44, 40, 42, 40, 44, 46, 50, 56, 54, 56, 52, 48, 44};
      }else{
         x_points = {80, 86, 84, 90, 96, 96, 90, 96, 92, 90, 82, 80, 80};
         y_points = {44, 44, 40, 40, 44, 46, 48, 50, 56, 54, 56, 50, 44};
      }
      
      Polygon generatedPolygon = new Polygon(x_points, y_points, x_points.length);
      return generatedPolygon;
   }
   
   public static Asteroid createRandomAsteroid(){
      Random rand = new Random();
      int asteroidXStartLocation = random.nextInt(World.X_DIMENSION);
      int asteroidYStartLocation = random.nextInt(World.Y_DIMENSION);
      double asteroidXSpeed = (random.nextDouble() - 0.5) * MAX_ASTEROID_SPEED;
      double asteroidYSpeed = (random.nextDouble() - 0.5) * MAX_ASTEROID_SPEED;
      Polygon asteroidPolygon = generateConfiguration();
      Rectangle asteroidBox = asteroidPolygon.getBounds();
      HitBox asteroidHitbox = new HitBox(asteroidXStartLocation, asteroidYStartLocation, asteroidBox.getWidth(), asteroidBox.getHeight());
      Asteroid asteroid = new Asteroid(asteroidXStartLocation, asteroidYStartLocation, ballXSpeed, ballYSpeed, ballHitbox, asteroidPolygon);
      return asteroid;
   }
   
   public Polygon getAsteroidShape(){ return asteroidShape }
}