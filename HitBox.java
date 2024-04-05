/*
This class was made with help from this website:
https://www.stefanonsoftware.com/post/gamedev-hit-detection
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
   
   public void set(double x, double y, double width, double height){
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
   }
   
   public boolean intersects(HitBox h) {
      return x + width >= h.x && h.x + h.width >= x && y + height >= h.y && h.y + h.height >= y;
   }
}