import javafx.scene.shape.Polygon;
import javafx.geometry.Point2D;

public abstract class InteractableObject{
   /*
   private double x; // meters
   private double y; // meters
   private double xSpeed; // meters/second
   private double ySpeed; // meters/second
   */
   private Polygon shape;
   private Point2D position;
   private HitBox hitbox; // Collision detection tool

   public InteractableObject(double x, double y, Polygon SHAPE, HitBox hitbox){
      /*
      this.x = x; // meters
      this.y = y; // meters
      this.xSpeed = xSpeed; // meters/second
      this.ySpeed = ySpeed; // meter/second
      */
      this.shape = SHAPE;
      this.shape.setTranslateX(x);
      this.shape.setTranslateY(y);
      this.position = new Point2D(0,0);
      this.hitbox = hitbox; // Collision-detection tool
   }
   
   public void move(long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension) { 
      this.shape.setTranslateX(this.shape.getTranslateX() + this.position.getX()); // * elapsedTimeInNanoseconds / 1_000_000_000.0;
      this.shape.setTranslateY(this.shape.getTranslateY() + this.position.getY()); // * elapsedTimeInNanoseconds / 1_000_000_000.0;
      
      //Keep object on the torus
      if (this.position.getX() < 0 - (this.getHitbox().getWidth() * 2)) { // moving in the negative x direction
         this.shape.setTranslateX(worldXDimension + (this.position.getX() % worldXDimension) + (this.getHitbox().getWidth() * 3));
      } else if (this.position.getX() >= worldXDimension + (this.getHitbox().getWidth() * 2)) {
         this.shape.setTranslateX(this.position.getX() % worldXDimension - (this.getHitbox().getWidth() * 3));
      }
      if (this.position.getY() < 0 - (this.getHitbox().getHeight() * 2)) { // moving in the negative y direction
         this.shape.setTranslateY(worldYDimension + (this.position.getY() % worldYDimension) + (this.getHitbox().getHeight() * 3));
      } else if (this.position.getY() >= worldYDimension + (this.getHitbox().getHeight() * 2)) {
         this.shape.setTranslateY(this.position.getY() % worldYDimension - (this.getHitbox().getHeight() * 3));
      }
   }
   
   public void turnRight() {
      this.getShape().setRotate(this.shape.getRotate() + 5);
   }
   
   public void turnLeft() {
      this.getShape().setRotate(this.shape.getRotate() - 5);
   }
   
   public void accelerate() {
      double changeX = Math.cos(Math.toRadians(this.shape.getRotate()));
      double changeY = Math.sin(Math.toRadians(this.shape.getRotate()));
   
      changeX *= 0.05;
      changeY *= 0.05;
      
      this.position = this.position.add(changeX, changeY);
   }
   /*
   public double getX() {
      return x;
   }

   public double getY() {
      return y;
   }
   
   public double getXSpeed() {
      return xSpeed;
   }

   public double getYSpeed() {
      return ySpeed;
   }
   */
   public Polygon getShape() { return shape; }
   public Point2D getPosition() { return position; }
   public HitBox getHitbox() { return hitbox; }
}