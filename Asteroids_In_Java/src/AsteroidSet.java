import java.util.*;
import java.util.HashSet;
import java.util.Random;

public class AsteroidSet{
   private HashSet<Asteroid> asteroidSet;
   private Random rand;
   
   public AsteroidSet(){
      asteroidSet = new HashSet<Asteroid>();
      rand = new Random();
   }
   
   public AsteroidSet(int numOfAsteroids){
      this();
      for(int i = 0; i < numOfAsteroids; i++) {
         asteroidSet.add(Asteroid.createRandomAsteroid());
      }
   }
   
   /** Moves the asteroids based on the elapsed time and the velocity. */
   public void move(long elapsedTimeInNanoseconds, double worldXDimension, double worldYDimension, double width, double height) { 
      for(Asteroid asteroid: asteroidSet) {
         asteroid.move(elapsedTimeInNanoseconds, worldXDimension, worldYDimension, width, height);
      }
   }
   
   public void deleteAsteroid() {
      int size = asteroidSet.size();
      if (size > 0) { // don't try to delete from empty set of balls
         int indexOfAsteroidToDelete = rand.nextInt(size);
         int currentIndex = 0;
         for(Asteroid asteroid: asteroidSet) {
            if (currentIndex == indexOfAsteroidToDelete) {
               asteroidSet.remove(asteroid);
               break;
            }
            currentIndex++;
         }
      }
   }
   
   public void deleteSpecifiedAsteroid(Asteroid asteroidIDToDelete) {
      int size = asteroidSet.size();
      if (size > 0) { // don't try to delete from empty set of balls
         Iterator<Asteroid> value = asteroidSet.iterator();
         Asteroid indexOfAsteroidToDelete = asteroidIDToDelete;
         while(value.hasNext()) {
            Asteroid element = value.next();
            if (element == indexOfAsteroidToDelete) {
               value.remove();
               break;
            }

         }
      }
   }
   
   public void addAsteroid() {
      Asteroid asteroid = Asteroid.createRandomAsteroid();
      asteroidSet.add(asteroid);
   }
   
   public HashSet<Asteroid> getAsteroids() {
      return asteroidSet;
   }
   
   public Asteroid[] getAsteroidsAsArray() {
      int numberOfAsteroids = this.asteroidSet.size();
      Asteroid[] asteroidArray = new Asteroid[numberOfAsteroids];
      int i = 0;
      for(Asteroid asteroid: this.asteroidSet) {
         asteroidArray[i++] = asteroid;
      }
      return asteroidArray;
   }
}