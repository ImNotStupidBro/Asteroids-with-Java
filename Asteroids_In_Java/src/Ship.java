import javafx.scene.shape.Polygon;
public class Ship extends InteractableObject{
  
   //private Polygon shipShape; //Polygon of the ship
  
   public Ship(double x, double y, Polygon theShape, HitBox hitbox) {
      super(x, y, theShape, hitbox);
   }
   
}
