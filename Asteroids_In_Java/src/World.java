/**
 * Describes the physics of the world.
 */
import java.util.Random;
 
public class World {
   private Ball balls[];
   private final int X_DIMENSION = 100; // meters
   private final int Y_DIMENSION = 50; // meters
   private final int MAX_BALL_RADIUS = 9;
   private final double MAX_BALL_SPEED = 30.0;
   
   private final int NUMBER_OF_BALLS = 5;
   
   public World() {
      //Define ball properties.
      Random random = new Random();
      balls = new Ball[NUMBER_OF_BALLS];
      int ballXStartLocation = 0;
      int ballYStartLocation = 0;
      int ballRadius = 0;
      double ballXSpeed = 0;
      double ballYSpeed = 0;
      //Generate the player ball.
         ballXStartLocation = X_DIMENSION/2;
         ballYStartLocation = Y_DIMENSION/2;
         ballRadius = 3; //no zero radius balls
         ballXSpeed = 0.0;
         ballYSpeed = 0.0;
         balls[0] = new Ball(ballXStartLocation, ballYStartLocation, ballRadius, ballXSpeed, ballYSpeed);
      //Spawn every other ball.
      for(int i = 1; i < balls.length; i++) {
         ballXStartLocation = random.nextInt(X_DIMENSION);
         ballYStartLocation = random.nextInt(Y_DIMENSION);
         ballRadius = random.nextInt(MAX_BALL_RADIUS) + 1; //no zero radius balls
         ballXSpeed = (random.nextDouble() - 0.5) * MAX_BALL_SPEED;
         ballYSpeed = (random.nextDouble() - 0.5) * MAX_BALL_SPEED;
         balls[i] = new Ball(ballXStartLocation, ballYStartLocation, ballRadius, ballXSpeed, ballYSpeed);
      }
   }
   
   /** Runs the physics of the world. */
   public void run(long elapsedTimeInNanoseconds) {
      for(int i = 0; i < balls.length; i++) {
         balls[i].move(elapsedTimeInNanoseconds, getXDimension(), getYDimension());
      }      
   }

   public Ball[] getBalls() {
      return balls;
   }

   public double getXDimension() {
      return X_DIMENSION;
   }

   public double getYDimension() {
      return Y_DIMENSION;
   }
}
