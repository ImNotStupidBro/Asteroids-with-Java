/*
This class was made with help from this website:
https://www.stefanonsoftware.com/post/gamedev-hit-detection

@author Christian Blair
*/
public class HitBox{
   private double x, y, width, height;
   public HitBox(double x, double y, double width, double height){
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
   }
   
   public void offset(double dx, double dy){
      x += dx;
      y += dy;
   }
   /**
   * Set the hitboxes parameters once declared.
   */
   public void set(double x, double y, double width, double height){
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
   }
   /**
   * Check whether or not the hitboxes of one object collides with another.
   */
   public boolean intersects(HitBox h) {
      return x + width >= h.x && h.x + h.width >= x && y + height >= h.y && h.y + h.height >= y;
   }
}