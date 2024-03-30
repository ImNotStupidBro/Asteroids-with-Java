/**
 * Describes the physics of the world.
 */
import java.util.Random;
 
public class World {
   private Balls balls;
   public static final int X_DIMENSION = 100; // meters
   public static final int Y_DIMENSION = 50; // meters
   private final int MAX_BALL_RADIUS = 9;
   private final double MAX_BALL_SPEED = 30.0;
   
   private final int NUMBER_OF_BALLS = 5;
   
   public World() {
      //Create Ball set.
      balls = new Balls(NUMBER_OF_BALLS);
   }
   
   /** Runs the physics of the world. */
   public void run(long elapsedTimeInNanoseconds) {
      balls.move(elapsedTimeInNanoseconds, getXDimension(), getYDimension());   
   }

   public Balls getBalls() {
      return balls;
   }
   
   public Ball[] getBallsAsArray() {
      return balls.getBallsAsArray();
   }

   public double getXDimension() {
      return X_DIMENSION;
   }

   public double getYDimension() {
      return Y_DIMENSION;
   }
}
