import java.util.*;
import java.util.HashSet;
import java.util.Random;

public class AlienShipSet{
   private HashSet<AlienShip> alienShipSet;
   private Random rand;
   
   public AlienShipSet(){
      alienShipSet = new HashSet<AlienShip>();
      rand = new Random();
   }
   
   public AlienShipSet(int numOfAlienShips){
      this();
      for(int i = 0; i < numOfAlienShips; i++) {
         alienShipSet.add(AlienShip.createRandomAlienShip());
      }
   }
   
   /** Moves the alienShips based on the elapsed time and the velocity. */
   public void move(long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension, double width, double height) { 
      for(AlienShip alienShip: alienShipSet) {
         alienShip.move(elapsedTimeInNanoseconds, worldXDimension, worldYDimension, width, height);
      }
   }
   
   public void deleteAlienShip() {
      int size = alienShipSet.size();
      if (size > 0) {
         int indexOfAlienShipToDelete = rand.nextInt(size);
         int currentIndex = 0;
         for(AlienShip alienShip: alienShipSet) {
            if (currentIndex == indexOfAlienShipToDelete) {
               alienShipSet.remove(alienShip);
               break;
            }
            currentIndex++;
         }
      }
   }
   
   public void deleteSpecifiedAlienShip(AlienShip alienShipIDToDelete) {
      int size = alienShipSet.size();
      if (size > 0) { // don't try to delete from empty set of balls
         Iterator<AlienShip> value = alienShipSet.iterator();
         AlienShip indexOfAlienShipToDelete = alienShipIDToDelete;
         while(value.hasNext()) {
            AlienShip element = value.next();
            if (element == indexOfAlienShipToDelete) {
               value.remove();
               break;
            }

         }
      }
   }
   
   public void addAlienShip() {
      AlienShip alienShip = AlienShip.createRandomAlienShip();
      alienShipSet.add(alienShip);
   }
   
   public HashSet<AlienShip> getAlienShips() {
      return alienShipSet;
   }
   
   public AlienShip[] getAlienShipsAsArray() {
      int numberOfAlienShips = this.alienShipSet.size();
      AlienShip[] alienShipArray = new AlienShip[numberOfAlienShips];
      int i = 0;
      for(AlienShip alienShip: this.alienShipSet) {
         alienShipArray[i++] = alienShip;
      }
      return alienShipArray;
   }
}