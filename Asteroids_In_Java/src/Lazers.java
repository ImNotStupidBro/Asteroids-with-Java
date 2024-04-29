import java.util.HashSet;

public class Lazers{
   private HashSet<Lazer> lazers;
   
   public Lazers() {
      lazers = new HashSet<Lazer>();
    }

    
   public void move(long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension) { 
      for(Lazer lazer: lazers) {
         lazer.move(elapsedTimeInNanoseconds, worldXDimension, worldYDimension);
         //Delete ball off of the torus
         if (lazer.getX() < 0 - (lazer.getRadius() * 2)) { // moving in the negative x direction
            deleteLazer(lazer); //calls function to delete lazer 
         } else if (lazer.getX() >= worldXDimension + (lazer.getRadius() * 2)) {
            deleteLazer(lazer); //calls function to delete lazer
         }
         if (lazer.getY() < 0 - (lazer.getRadius() * 2)) { // moving in the negative y direction
            deleteLazer(lazer); //calls function to delete lazer
         } else if (lazer.getY() >= worldYDimension + (lazer.getRadius() * 2)) {
            deleteLazer(lazer); //calls function to delete lazer
         }
      }
   }
   
   public void deleteLazer(Lazer lazerToDelete) {
   //cycles through each lazer in the hashset, and if theyre position is out of wack, it deletes it
      int size = lazers.size();
      if (size > 0) { // don't try to delete from empty set of lazers
               lazers.remove(lazerToDelete);
               System.out.println("LASER WENT INTO SPACE");
      }
   }
   
   public void addLazer(double ShipX, double ShipY, double ShipDirection) {
      Lazer lazer = Lazer.createLazer(ShipX, ShipY, (ShipDirection+90));
      lazers.add(lazer);
   }
   
   public HashSet<Lazer> getLazers() {
      return lazers;
   }
   
   public Lazer[] getLazersAsArray() {
      int numberOfLazers = this.lazers.size();
      Lazer[] lazers = new Lazer[numberOfLazers];
      int i = 0;
      for(Lazer lazer: this.lazers) {
         lazers[i++] = lazer;
      }
      return lazers;
   }
}