import java.util.*;
import java.util.HashSet;
import java.util.Random;

public class AsteroidSetSmall{
   private HashSet<Asteroid> asteroidSet;
   private Random rand;
   
   public AsteroidSetSmall(){
      asteroidSet = new HashSet<Asteroid>();
      rand = new Random();
   }
   
   public AsteroidSetSmall(int numOfAsteroids){
      this();
      for(int i = 0; i < numOfAsteroids; i++) {
         asteroidSet.add(Asteroid.createRandomSmallAsteroid());
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
      Asteroid asteroid = Asteroid.createRandomSmallAsteroid();
      asteroidSet.add(asteroid);
   }

   public void addAsteroidAt(double X, double Y) {
      Asteroid asteroid = Asteroid.createAsteroid(X, Y, 20, 20);
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