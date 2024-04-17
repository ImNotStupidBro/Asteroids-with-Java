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
         if (lazer.getX() < 0 - (lazer.getRadius() * 2)) { // moving in the negative x direction              ///all of the deletelazer methods need to be passed
            deleteLazer(); //calls function to delete lazer - must specify it is that laze
         } else if (lazer.getX() >= worldXDimension + (lazer.getRadius() * 2)) {
            deleteLazer(); //calls function to delete lazer
         }
         if (lazer.getY() < 0 - (lazer.getRadius() * 2)) { // moving in the negative y direction
            deleteLazer(); //calls function to delete lazer
         } else if (lazer.getY() >= worldYDimension + (lazer.getRadius() * 2)) {
            deleteLazer(); //calls function to delete lazer
         }
      }
   }
   
   public void deleteLazer() {
   //cycles through each lazer in the hashset, and if theyre position is out of wack, it deletes it
      int size = lazers.size();
      if (size > 0) { // don't try to delete from empty set of balls
         int currentIndex = 0;
         Lazer[] LazersAsArray = getLazersAsArray();
         for(Lazer lazer: lazers) {
            //variableslocal to this for loop just so its not that long
            double index_radius =  LazersAsArray[currentIndex].getRadius();
            double index_x = LazersAsArray[currentIndex].getX();
            double index_y =  LazersAsArray[currentIndex].getY();
            double worldXDimension = World.X_DIMENSION;
            double worldYDimension = World.Y_DIMENSION;
            //checks if the current indexed lazer is off the page
            if ((index_x < 0 - (index_radius * 2))||(index_x >= worldXDimension + (index_radius * 2))||(index_y < 0 - (index_radius * 2))||(index_y >= worldYDimension + (index_radius * 2))) {
               lazers.remove(lazer);
               System.out.println("LASER WENT INTO SPACE");
               break;
            }
            currentIndex++;
         }
      }
   }
   
   public void addLazer(double ShipX, double ShipY, double ShipDirection) {
      Lazer lazer = Lazer.createLazer(ShipX, ShipY, ShipDirection);
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