public class Ship extends InteractableObject{
   
   private final double[] SHIPVERTICECOORDINATES = {
      116.0, 80.0,
      100.0, 128.0,
      104.0, 116.0,
      128.0, 116.0,
      132.0, 128.0,
      116.0, 80.0
   };
   
   public Ship(double x, double y, double dx, double dy, double degrees, HitBox hitbox) {
      super(x, y, dx, dy, degrees, hitbox);
   }

}
