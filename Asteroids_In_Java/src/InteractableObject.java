import javafx.scene.shape.Polygon;
import javafx.geometry.Point2D;

public abstract class InteractableObject{
   
   //Don't use Point2D for referencing position anymore.
   private Polygon shape;
   private double xPosition;
   private double yPosition;
   private double xSpeed;
   private double ySpeed;
   private HitBox hitbox; // Collision detection tool

   public InteractableObject(double x, double y, , double dx, double dy, Polygon SHAPE, HitBox hitbox){
      this.shape = SHAPE;
      this.xPosition = x;
      this.yPosition = y;
      this.xSpeed = dx;
      this.ySpeed = dy;
      this.hitbox = hitbox; // Collision-detection tool
   }
   
   public void move(long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension) { 
      this.shape.setTranslateX(this.shape.getTranslateX() + xPosition); // * elapsedTimeInNanoseconds / 1_000_000_000.0;
      this.shape.setTranslateY(this.shape.getTranslateY() + this.position.getY()); // * elapsedTimeInNanoseconds / 1_000_000_000.0;
      
      //Keep object on the torus
      if (xPosition < 0 - (this.getHitbox().getWidth() * 2)) { // moving in the negative x direction
         xPosition = worldXDimension + (xPosition % worldXDimension) + (this.getHitbox().getWidth() * 3);
      } else if (xPosition >= worldXDimension + (this.getHitbox().getWidth() * 2)) {
         this.shape.setTranslateX(xPosition % worldXDimension - (this.getHitbox().getWidth() * 3));
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
   
   public double getX() { return x; }
   public double getY() { return y; }
   public double getXSpeed() { return xSpeed; }
   public double getYSpeed() { return ySpeed; }
   public HitBox getHitbox() { return hitbox; }
   public Polygon getShape() { return shape; }
}